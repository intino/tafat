package tafat.toolbox.statechart;

import tafat.toolbox.Action;

import java.util.List;

public class State extends StateChart {
    String id;
    Action in = () -> {};
    Action out = () -> {};

    State(String id, StateChart parent) {
        super(parent);
        this.id = id;
    }

    @Override
    public String currentState() {
        if (state != null) return state.currentState();
        return id;
    }

    String shortId() {
        return id.contains(".") ? id.substring(id.lastIndexOf(".") + 1) : id;
    }

    @Override
    void include(StateChart newStateChart) {
        states(newStateChart.states);
        transitions = newStateChart.transitions;
        state = newStateChart.state;
        message = newStateChart.message;
    }

    private void states(List<State> states) {
        this.states = states;
        this.states.forEach(s -> s.parent = this);
    }

    public static class Null extends State {

        private Null(String id, StateChart stateChart) {
            super(id, stateChart);
            in = () -> {
                throw new Exception("State " + id + " does not exist");
            };
            out = () -> {
                throw new Exception("State " + id + " does not exist");
            };
        }

        public static Null create(String id, StateChart stateChart) {
            return new Null(id, stateChart);
        }
    }
}
