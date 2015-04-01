package tafat.toolbox.statechart;

import tafat.toolbox.Action;
import tafat.toolbox.Checker;
import tafat.toolbox.timeout.RateFunction;
import tafat.toolbox.timeout.Timeout;
import tafat.toolbox.timeout.TimeoutFunction;

import java.util.*;

public class StateChart {

    StateChartUpdater updater;
    StateChart parent;
    List<State> states = createStateList();
    List<Transition> transitions = new ArrayList<>();

    State state;
    Message message = new Message("");

    public static StateChart define() {
        return new StateChart(null);
    }

    StateChart(StateChart stateChart) {
        this.parent = stateChart;
        updater = new StateChartUpdater(this);
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
        updater.update(advancedTime);
    }

    private StateChart commit() {
        StateChartResolver.resolve(this);
        return this;
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