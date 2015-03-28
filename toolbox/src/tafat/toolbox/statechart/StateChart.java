package tafat.toolbox.statechart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateChart {

    List<State> states = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();

    State state;
    String message = "";

    public int currentState() {
        return state.id();
    }

    public void update() {
        Optional<Transition> first = transitions.stream().filter(t -> t.from() == state.id() && t.check()).findFirst();
        if(!first.isPresent()) return;
        update(first.get());
    }

    private void update(Transition transition) {
        state.out();
        transition.action();
        state = states.stream().filter(s -> s.id() == transition.to()).findFirst().get();
        state.in();
    }

    public void receive(String message) {
        this.message = message;
    }

    public StateTransaction state(int id) {
        if(stateExists(id)) throw new StateChartException("State with identifier " + id + " has been already defined");
        states.add(new State(id));
        if(states.size() == 1) state = states.get(0);
        return createStateTransaction();
    }

    private StateTransaction in(Action action) {
        states.get(states.size() - 1).in(action);
        return createStateTransaction();
    }

    private StateTransaction out(Action action) {
        states.get(states.size() - 1).out(action);
        return createStateTransaction();
    }

    private int onGoingFrom;

    public TransitionTransaction from(int id) {
        if(!stateExists(id)) throw new StateChartException("Transition has a non-existing from state: " + id);
        onGoingFrom = id;
        return createTransitionTransaction();
    }

    private DestinatedTransaction to(int id) {
        if(!stateExists(id)) throw new StateChartException("Transition has a non-existing to state: " + id);
        transitions.add(new Transition(onGoingFrom, id));
        return createDestinatedTransaction();
    }

    private DefinedTransaction condition(Condition condition) {
        transitions.get(transitions.size() - 1).check(condition::check);
        return createDefinedTransaction();
    }

    private DefinedTransaction message(String message) {
        transitions.get(transitions.size() - 1).check(() -> this.message.equals(message));
        return createDefinedTransaction();
    }

    private DefinedTransaction timeout(TimeoutTransitionCalculator calculator) {
        return createDefinedTransaction();
    }

    private DefinedTransaction action(Action action) {
        transitions.get(transitions.size() - 1).action(action);
        return createDefinedTransaction();
    }

    private boolean stateExists(int id) {
        return states.stream().filter(s -> s.id() == id).findFirst().isPresent();
    }

    private StateTransaction createStateTransaction() {
        return new StateTransaction() {
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
            public DefinedTransaction condition(Condition condition) {
                return StateChart.this.condition(condition);
            }

            @Override
            public DefinedTransaction message(String message) {
                return StateChart.this.message(message);
            }

            @Override
            public DefinedTransaction timeout(TimeoutTransitionCalculator calculator) {
                return StateChart.this.timeout(calculator);
            }
        };
    }

    private DefinedTransaction createDefinedTransaction() {
        return new DefinedTransaction() {
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

    public interface StateTransaction {
        StateTransaction in(Action action);
        StateTransaction out(Action action);
        StateTransaction state(int id);
        TransitionTransaction from(int id);
    }

    public interface TransitionTransaction {
        DestinatedTransaction to(int id);
    }

    public interface DestinatedTransaction {
        DefinedTransaction condition(Condition condition);
        DefinedTransaction message(String message);
        DefinedTransaction timeout(TimeoutTransitionCalculator calculator);
    }

    public interface DefinedTransaction {
        DefinedTransaction action(Action action);
        TransitionTransaction from(int id);
    }
}
