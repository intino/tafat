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
            t.from = findFromState(t.fromString, stateChart.states);
            t.to = findToState(t.toString, states);
        });
        stateChart.states.forEach(s -> resolveTransitions(s, states));
    }

    private static State findFromState(String fromString, List<State> states) {
        for (State state : states)
            if (state.shortId().equals(fromString))
                return state;
        throw new StateChartException("From id");
    }

    static State findToState(String id, List<State> states) {
        for (State state : states)
            if (state.id.equals(id)) return state;
        return null;
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
