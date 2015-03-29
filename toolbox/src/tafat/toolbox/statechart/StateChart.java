package tafat.toolbox.statechart;

import tafat.toolbox.Checker;
import tafat.toolbox.timeout.RateFunction;
import tafat.toolbox.timeout.Timeout;
import tafat.toolbox.timeout.TimeoutFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateChart {

    List<State> states = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    StateChart parent;
    State state;
    String message = "";

    public StateChart() {}

    StateChart(StateChart stateChart) {
        this.parent = stateChart;
    }

    public int currentState() {
        return state.currentState();
    }

    public void update(){
        update(0);
    }

    public void update(long advancedTime) {
        propagate(advancedTime);
        if (checkTransitions()) message = "";
    }

    public void receive(String message) {
        this.message = message;
    }

    public StateTransaction state(int id) {
        if(findState(id) != null) throw new StateChartException("State with identifier " + id + " has been already defined");
        states.add(new State(id, this));
        if(states.size() == 1) state = states.get(0);
        return createStateTransaction();
    }

    private void propagate(long advancedTime) {
        transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == state).
                forEach(t -> ((Timeout) t.checker).step(advancedTime));
        if(state != null) state.update(advancedTime);
    }

    private boolean checkTransitions() {
        Optional<Transition> first = transitions.stream().filter(t -> t.from == state && t.check()).findFirst();
        if(!first.isPresent()) return false;
        processTransition(first.get());
        return true;
    }

    private void processTransition(Transition transition) {
        state.out(transition.to.parent);
        transition.action();
        calculateState(transition);
    }

    private void calculateState(Transition transition) {
        transition.to.in(state.parent);
        if(transition.to.parent != this){
            transition.to.parent.state = transition.to;
            transition.to.parent.activate();
            state = states.get(0);
        }
        else{
            state = transition.to;
            activate();
        }
    }

    protected void activate() {
        if(state.states.size() > 0){
            state.state = state.states.get(0);
            state.state.in.execute();
            state.activate();
        }
        transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == state).
                forEach(t -> ((Timeout) t.checker).activate());
    }

    private StateChart with() {
        State state = states.get(states.size() - 1);
        state.parent = this;
        return state;
    }

    private StateChart end() {
        return parent != null ? parent : this;
    }

    private StateTransaction in(Action action) {
        states.get(states.size() - 1).in = action;
        return createStateTransaction();
    }

    private StateTransaction out(Action action) {
        states.get(states.size() - 1).out = action;
        return createStateTransaction();
    }

    private int onGoingFrom;

    public TransitionTransaction from(int id) {
        if(findState(id) == null) throw new StateChartException("Transition has a non-existing from state: " + id);
        onGoingFrom = id;
        return createTransitionTransaction();
    }

    private DestinatedTransaction to(int id) {
        if(findState(id) == null) throw new StateChartException("Transition has a non-existing to state: " + id);
        transitions.add(new Transition(findState(onGoingFrom), findState(id)));
        return createDestinatedTransaction();
    }

    private DefinedTransaction condition(Checker checker) {
        transitions.get(transitions.size() - 1).check(checker::check);
        return createDefinedTransaction();
    }

    private DefinedTransaction message(String message) {
        transitions.get(transitions.size() - 1).check(() -> this.message.equals(message));
        return createDefinedTransaction();
    }

    private DefinedTransaction timeout(TimeoutFunction calculator) {
        transitions.get(transitions.size() - 1).check(new Timeout(calculator));
        return createDefinedTransaction();
    }

    private DefinedTransaction rate(int times, long period) {
        transitions.get(transitions.size() - 1).check(new Timeout(new RateFunction(times, period)));
        return createDefinedTransaction();
    }

    private DefinedTransaction action(Action action) {
        transitions.get(transitions.size() - 1).action(action);
        return createDefinedTransaction();
    }

    protected State findState(int id) {
        for (State state : allStates())
            if (state.id == id) return state;
        return null;
    }

    private List<State> allStates() {
        if(parent != null) return parent.allStates();
        List<State> states = new ArrayList<>();
        for (State state : this.states) fillWithChild(state, states);
        return states;
    }

    private void fillWithChild(State state, List<State> states) {
        states.add(state);
        for (State child : state.states) fillWithChild(child, states);
    }

    private StateTransaction createStateTransaction() {
        return new StateTransaction() {
            @Override
            public StateChart end() {
                return StateChart.this.end();
            }

            @Override
            public StateTransaction in(Action action) {
                return StateChart.this.in(action);
            }

            @Override
            public StateTransaction out(Action action) {
                return StateChart.this.out(action);
            }

            @Override
            public StateTransaction state(int id) {
                return StateChart.this.state(id);
            }

            @Override
            public StateChart with() {
                return StateChart.this.with();
            }

            @Override
            public TransitionTransaction from(int id) {
                return StateChart.this.from(id);
            }
        };
    }

    private TransitionTransaction createTransitionTransaction() {
        return StateChart.this::to;
    }

    private DestinatedTransaction createDestinatedTransaction() {
        return new DestinatedTransaction() {
            @Override
            public DefinedTransaction condition(Checker checker) {
                return StateChart.this.condition(checker);
            }

            @Override
            public DefinedTransaction message(String message) {
                return StateChart.this.message(message);
            }

            @Override
            public DefinedTransaction timeout(TimeoutFunction calculator) {
                return StateChart.this.timeout(calculator);
            }

            @Override
            public DefinedTransaction rate(int times, long period) {
                return StateChart.this.rate(times, period);
            }
        };
    }

    private DefinedTransaction createDefinedTransaction() {
        return new DefinedTransaction() {
            @Override
            public StateChart end() {
                return StateChart.this.end();
            }

            @Override
            public DefinedTransaction action(Action action) {
                return StateChart.this.action(action);
            }

            @Override
            public TransitionTransaction from(int id) {
                return StateChart.this.from(id);
            }

        };
    }

    public interface Transaction{
        StateChart end();
    }

    public interface StateTransaction extends Transaction{
        StateTransaction in(Action action);
        StateTransaction out(Action action);
        StateTransaction state(int id);
        StateChart with();
        TransitionTransaction from(int id);

    }

    public interface TransitionTransaction{
        DestinatedTransaction to(int id);
    }

    public interface DestinatedTransaction{
        DefinedTransaction condition(Checker checker);
        DefinedTransaction message(String message);
        DefinedTransaction timeout(TimeoutFunction calculator);
        DefinedTransaction rate(int times, long period);
    }

    public interface DefinedTransaction extends Transaction{
        DefinedTransaction action(Action action);
        TransitionTransaction from(int id);
    }
}