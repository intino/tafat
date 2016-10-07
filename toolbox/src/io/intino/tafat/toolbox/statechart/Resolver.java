package io.intino.tafat.toolbox.statechart;

import java.util.Collection;

class Resolver {

    static void resolve(StateChart stateChart) {
        updateQualifiedName(stateChart.states);
        resolveTransitions(stateChart);
    }

    private static void updateQualifiedName(Collection<State> states) {
        states.forEach(s -> s.id = buildQualifiedName(s));
        states.forEach(s -> updateQualifiedName(s.states));
    }

    private static String buildQualifiedName(State s) {
        return s.parent instanceof State ? ((State) s.parent).id + "." + s.shortId() : s.id;
    }

    private static void resolveTransitions(StateChart stateChart) {
        stateChart.transitions.forEach(t -> resolveTransition(stateChart, t));
        stateChart.states.forEach(Resolver::resolveTransitions);
    }

    private static void resolveTransition(StateChart stateChart, Transition transition) {
        transition.from = findStateInStateChart(transition.fromId, stateChart);
        if (transition.from instanceof State.Null)
            throw new StateChart.Exception("Transition has a non-existing from state: " + transition.fromId + ". From states in transition has to be inside the state chart where the transition is declared");
        transition.to = findToState(transition.toId, stateChart);
    }

    private static State findStateInStateChart(String id, StateChart stateChart) {
        return stateChart.states.stream().filter(s -> s.shortId().equals(id)).findFirst().orElse(State.Null.create(id, stateChart));
    }

    private static State findToState(String id, StateChart stateChart) {
        return id.contains(".") ?
                searchPath(id, stateChart) :
                searchId(id, stateChart);
    }

    private static State searchId(String id, StateChart stateChart) {
        StateChart current = stateChart;
        while(current != null){
            State state = findStateInStateChart(id, current);
            if (!(state instanceof State.Null)) return state;
            current = current.parent;
        }
        return State.Null.create(id, stateChart);
    }

    private static State searchPath(String path, StateChart stateChart) {
        State state = searchInChildren(path, stateChart);
        if (state instanceof State.Null)
            state = searchFromRoot(path, stateChart);
        return state;
    }

    private static State searchInChildren(String path, StateChart stateChart) {
        StateChart current = stateChart;
        for (String item : path.split("\\.")) {
            current = findState(item, current);
            if(current == null) return State.Null.create(path, stateChart);
        }
        return (State) current;
    }

    private static State searchFromRoot(String path, StateChart stateChart) {
        StateChart current = stateChart;
        while(current.parent != null) current = current.parent;
        return searchInChildren(path, current);

    }

    private static State findState(String item, StateChart current) {
        return current.states.stream().filter(s -> s.shortId().equals(item)).findFirst().orElse(null);
    }

}
