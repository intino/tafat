package tafat.toolbox.statechart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateChartResolver {
    public static void resolve(StateChart stateChart) {
        updateQualifiedName(stateChart.states);
        resolveTransitions(stateChart, allStates(stateChart));
    }

    private static void updateQualifiedName(Collection<State> states) {
        states.forEach(s -> s.id = buildQualifiedName(s));
        states.forEach(s -> updateQualifiedName(s.states));
    }

    private static String buildQualifiedName(State s) {
        return s.parent instanceof State ? ((State) s.parent).id + "." + s.id : s.id;
    }

    private static void resolveTransitions(StateChart stateChart, List<State> states) {
        stateChart.transitions.forEach(t -> {
            t.from = findStateInStateChart(t.fromString, stateChart);
            if (t.from instanceof NullState) throw new StateChartException("Transition has a non-existing from state: " + t.fromString + ". From states in transition has to be inside the state chart where the transition is declared");
            t.to = findToState(t.toString, stateChart);
        });
        stateChart.states.forEach(s -> resolveTransitions(s, states));
    }

    private static State findStateInStateChart(String id, StateChart stateChart) {
        return stateChart.states.stream().filter(s -> s.shortId().equals(id)).findFirst().orElse(NullState.create(id));
    }

    static State findToState(String id, StateChart stateChart) {
        State state = findStateInStateChart(id, stateChart);
        if(!(state instanceof NullState)) return state;
        if(stateChart.parent != null) return findToState(id, stateChart.parent);
        return state;
    }

    static List<State> allStates(StateChart stateChart) {
        List<State> states = new ArrayList<>();
        for (State state : stateChart.states) fillWithChild(state, states);
        return states;
    }

    static void fillWithChild(State state, List<State> states) {
        states.add(state);
        state.states.forEach(s -> fillWithChild(s, states));
    }


}
