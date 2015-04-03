package tafat.toolbox.statechart;

import tafat.toolbox.timeout.Timeout;

class Updater {

    static void update(StateChart stateChart, long advancedTime) {
        stepTransitions(stateChart, advancedTime);
        updateChild(stateChart, advancedTime);
        update(stateChart, findTransition(stateChart));
        stateChart.message.content = "";
    }

    private static void update(StateChart stateChart, Transition transition) {
        while (transition != null) {
            processTransition(transition, stateChart);
            if (transitionChangedStateChart(stateChart)) break;
            updateChild(stateChart, 0);
            transition = findTransition(stateChart);
        }
    }

    private static boolean transitionChangedStateChart(StateChart stateChart) {
        return stateChart.parent != null && stateChart.parent.state != stateChart;
    }

    private static void updateChild(StateChart stateChart, long advancedTime) {
        if (stateChart.state != null) stateChart.state.update(advancedTime);
    }

    private static void stepTransitions(StateChart stateChart, long advancedTime) {
        stateChart.transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == stateChart.state).
                forEach(t -> ((Timeout) t.checker).step(advancedTime));
    }

    private static Transition findTransition(StateChart stateChart) {
        return stateChart.transitions.stream().filter(
                t -> t.from == stateChart.state && t.check()).findFirst().orElse(null);
    }

    private static void processTransition(Transition transition, StateChart stateChart) {
        out(stateChart.state, transition.to.parent);
        transition.action();
        doTransition(transition, transition.to.parent);
        in(transition.to, stateChart.state.parent);
    }

    private static void doTransition(Transition transition, StateChart stateChart) {
        stateChart.state = transition.to;
        updateParentsState(transition.to);
        activate(stateChart);
    }

    private static void updateParentsState(StateChart stateChart) {
        if(stateChart.parent == null) return;
        stateChart.parent.state = (State) stateChart;
        updateParentsState(stateChart.parent);
    }

    private static void activate(StateChart stateChart) {
        activateState(stateChart.state);
        stateChart.transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == stateChart.state).
                forEach(t -> ((Timeout) t.checker).activate());
    }

    private static void activateState(State state) {
        if (state.states.size() > 0) {
            state.state = state.states.get(0);
            activate(state);
        }
    }

    private static void in(State state, StateChart fromParent) {
        if (state.parent == fromParent) doIn(state);
        else if (state.parent != null && state.parent instanceof State) in(((State) state.parent),fromParent);
        else doIn(state);
    }

    private static void out(State state, StateChart toParent) {
        if (state.state != null) out(state.state, toParent);
        else doOut(state, toParent);
    }

    private static void doIn(State state) {
        state.in.execute();
        if (state.state != null) doIn(state.state);
    }

    private static void doOut(State state, StateChart toParent) {
        state.out.execute();
        if (state.parent != null && state.parent != toParent && state.parent instanceof State)
            doOut(((State) state.parent), toParent);
    }
}
