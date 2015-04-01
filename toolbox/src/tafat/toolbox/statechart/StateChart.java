package tafat.toolbox.statechart;

import tafat.toolbox.Action;
import tafat.toolbox.Checker;
import tafat.toolbox.timeout.RateFunction;
import tafat.toolbox.timeout.Timeout;
import tafat.toolbox.timeout.TimeoutFunction;

import java.util.*;

public class StateChart {

    StateChart parent;
    List<State> states = createStateList();
    List<Transition> transitions = new ArrayList<>();

    State state;
    Message message = new Message("");

    private StateChart() {
    }

    public static StateChart define() {
        return new StateChart();
    }

    StateChart(StateChart stateChart) {
        this.parent = stateChart;
    }

    public StateDefinition state(String id) {
        if (!states.add(new State(id, this))) throw new StateChartException("State uses an identifier " + id + " that has been already defined");
        if (states.size() == 1) state = states.get(0);
        return new StateDefinition(this);
    }

    public String currentState() {
        return state.currentState();
    }

    public void receive(String message) {
        this.message.content = message;
        if (state != null) state.receive(message);
    }

    public void update() {
        update(0);
    }

    public void update(long advancedTime) {
        propagate(advancedTime);
        while (checkTransitions()) if (parent != null && parent.state != this) break;
        this.message.content = "";
    }

    private StateChart commit() {
        StateChartResolver.resolve(this);
        return this;
    }

    private void propagate(long advancedTime) {
        transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == state).
                forEach(t -> ((Timeout) t.checker).step(advancedTime));
        if (state != null) state.update(advancedTime);
    }

    private boolean checkTransitions() {
        Optional<Transition> first = transitions.stream().filter(t -> t.from == state && t.check()).findFirst();
        if (!first.isPresent()) return false;
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
        if (transition.to.parent != this) toOtherStateChart(transition);
        else doTransition(transition);
    }

    private void toOtherStateChart(Transition transition) {
        transition.to.parent.doTransition(transition);
        state = states.get(0);
    }

    private void doTransition(Transition transition) {
        state = transition.to;
        activate();
    }

    protected void activate() {
        activateState(state);
        transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == state).
                forEach(t -> ((Timeout) t.checker).activate());
    }

    private void activateState(State state) {
        if (state.states.size() > 0) {
            state.state = state.states.get(0);
            state.activate();
        }
    }

    public class StateDefinition{

        private final StateChart stateChart;

        public StateDefinition(StateChart stateChart) {
            this.stateChart = stateChart;
        }

        public StateDefinition state(String id) {
            stateChart.state(id);
            return this;
        }

        public StateDefinition in(Action action) {
            stateChart.states.get(stateChart.states.size() - 1).in = action;
            return this;
        }

        public StateDefinition out(Action action) {
            stateChart.states.get(stateChart.states.size() - 1).out = action;
            return this;
        }

        public StateDefinition include(StateChart newStateChart) {
            stateChart.states.get(stateChart.states.size() - 1).states(newStateChart.states);
            stateChart.states.get(stateChart.states.size() - 1).transitions = newStateChart.transitions;
            stateChart.states.get(stateChart.states.size() - 1).state = newStateChart.state;
            stateChart.states.get(stateChart.states.size() - 1).message = newStateChart.message;
            return this;
        }

        public PrevTransitionDefinition transition() {
            return new PrevTransitionDefinition(stateChart);
        }

        public StateChart stateChart() {
            return stateChart.commit();
        }
    }

    public class PrevTransitionDefinition {
        private final StateChart stateChart;

        public PrevTransitionDefinition(StateChart stateChart) {
            this.stateChart = stateChart;
        }

        public TransitionDefinition from(String id) {
            stateChart.transitions.add(new Transition());
            stateChart.transitions.get(stateChart.transitions.size() - 1).fromString = id;
            return new TransitionDefinition(stateChart);
        }
    }

    public class TransitionDefinition {

        private final StateChart stateChart;

        public TransitionDefinition(StateChart stateChart) {
            this.stateChart = stateChart;
        }

        public DefinedTransitionDefinition to(String id) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).toString = id;
            return new DefinedTransitionDefinition(stateChart);
        }

    }

    public class DefinedTransitionDefinition {

        private final StateChart stateChart;

        public DefinedTransitionDefinition(StateChart stateChart) {
            this.stateChart = stateChart;
        }

        public FinishedTransitionDefinition condition(Checker checker) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).checker = checker::check;
            return new FinishedTransitionDefinition(stateChart);
        }

        public FinishedTransitionDefinition message(String message) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).checker = (() -> stateChart.message.content.equals(message));
            return new FinishedTransitionDefinition(stateChart);
        }

        public FinishedTransitionDefinition timeout(TimeoutFunction calculator) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).checker = new Timeout(calculator);
            return new FinishedTransitionDefinition(stateChart);
        }

        public FinishedTransitionDefinition rate(int times, long period) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).checker = new Timeout(new RateFunction(times, period));
            return new FinishedTransitionDefinition(stateChart);
        }
    }

    public class FinishedTransitionDefinition {

        private final StateChart stateChart;

        public FinishedTransitionDefinition(StateChart stateChart) {
            this.stateChart = stateChart;
        }

        public FinishedTransitionDefinition action(Action action) {
            stateChart.transitions.get(stateChart.transitions.size() - 1).action = action;
            return this;
        }

        public PrevTransitionDefinition transition() {
            return new PrevTransitionDefinition(stateChart);
        }

        public StateChart stateChart() {
            return stateChart.commit();
        }
    }

    private ArrayList<State> createStateList() {
        return new ArrayList<State>(){
            @Override
            public boolean add(State state) {
                return !contains(state) && super.add(state);
            }

            @Override
            public boolean contains(Object object) {
                for (State state : this)
                    if(state.id.equals(((State) object).id))
                        return true;
                return false;
            }
        };
    }
}