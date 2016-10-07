package io.intino.tafat.toolbox.statechart;

import io.intino.tafat.toolbox.Action;
import io.intino.tafat.toolbox.Checker;

import java.util.ArrayList;
import java.util.List;

public class StateChart {

    StateChart parent;
    List<State> states = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    Message message = new Message();
    State state;

    StateChart(StateChart stateChart) {
        this.parent = stateChart;
    }

    public static Definition.Start define() {
        return new Definition.Start(new StateChart(null));
    }

    void state(String id) {
        if (stateExists(id))
            throw new Exception("State uses an identifier " + id + " that has been already defined");
        addState(id);
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
        Updater.update(this, advancedTime);
    }

    void commit() {
        Resolver.resolve(this);
    }

    void in(Action action) {
        lastState().in = action;
    }

    void out(Action action) {
        lastState().out = action;
    }

    void include(StateChart newStateChart) {
        lastState().include(newStateChart);
    }

    void from(String id) {
        transitions.add(new Transition(id));
    }

    void to(String id) {
        lastTransition().toId = id;
    }

    void checker(Checker checker) {
        lastTransition().checker = checker;
    }

    void action(Action action) {
        lastTransition().action = action;
    }

    private boolean stateExists(String id) {
        return states.stream().filter(s -> state.id.equals(id)).count() > 0;
    }

    private void addState(String id) {
        states.add(new State(id, this));
        if (states.size() == 1) state = states.get(0);
    }

    private State lastState() {
        return states.get(states.size() - 1);
    }

    private Transition lastTransition() {
        return transitions.get(transitions.size() - 1);
    }

    static class Message {
        String content = "";
    }

    static class Exception extends RuntimeException {
        public Exception(String message) {
            super(message);
        }
    }
}