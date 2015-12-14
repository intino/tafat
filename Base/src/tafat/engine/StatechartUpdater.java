package tafat.engine;

import tafat.StateChart;
import tafat.StateChart.State;
import tafat.StateChart.Transition;
import tafat.StateChart.Transition.TimeBased;

public class StatechartUpdater {


    public static void update(StateChart stateChart, int advancedTime){
        updateStatechart(stateChart, advancedTime);
        doPeriodic(stateChart.current().as(State.class));
        stateChart.message("");
    }

    private static void updateStatechart(StateChart stateChart, int advancedTime) {
        updateChild(stateChart.current(), advancedTime);
        updateStatechart(stateChart, findTransition(stateChart, advancedTime), advancedTime);
    }

    private static void updateStatechart(StateChart stateChart, Transition transition, int advancedTime) {
        while (transition != null) {
            processTransition(transition, stateChart);
            if (transitionChangedStateChart(stateChart)) break;
            updateChild(stateChart.current(), 0);
            transition = findTransition(stateChart.current(), advancedTime);
        }
    }

    private static boolean transitionChangedStateChart(StateChart stateChart) {
        return stateChart._owner(StateChart.class) != null && stateChart._owner(StateChart.class).current() != stateChart;
    }

    private static void updateChild(StateChart stateChart, int advancedTime) {
        if (stateChart.current() != null) updateStatechart(stateChart.current(), advancedTime);
    }

    private static Transition findTransition(StateChart stateChart, int advancedTime) {
        return stateChart.transitionList().stream()
                .filter(t -> t.from() == stateChart.current() && t.trigger().check(advancedTime)).findFirst().orElse(null);
    }

    private static void processTransition(Transition transition, StateChart stateChart) {
        out(stateChart.current().as(State.class), transition.to()._owner().as(StateChart.class));
        doTransition(transition, transition.to()._owner().as(StateChart.class));
        in(transition.to(), stateChart);
    }

    private static void out(State state, StateChart toParent) {
        if (state.current() != null) out(state.current().as(State.class), toParent);
        else doOut(state, toParent);
    }

    private static void doTransition(Transition transition, StateChart stateChart) {
        transition.action();
        stateChart.current(transition.to());
        updateParentsState(transition.to());
        activate(stateChart);
    }

    private static void in(State state, StateChart fromParent) {
        if (state._owner() == fromParent._instance()) doIn(state);
        else if (state._owner() != null && state._owner().is(State.class))
            in(state._owner().as(State.class), fromParent);
        else doIn(state);
    }

    private static void updateParentsState(StateChart stateChart) {
        StateChart owner = stateChart._owner(StateChart.class);
        if (owner == null) return;
        owner.current(stateChart);
        updateParentsState(owner);
    }

    private static void activate(StateChart stateChart) {
        activateTransitions(stateChart);
        activateState(stateChart.current().as(State.class));
    }

    private static void activateTransitions(StateChart stateChart) {
        stateChart.transitionList().stream()
                .filter(t -> t.trigger().is(TimeBased.class) && t.from() == stateChart.current())
                .forEach(t -> t.timeBased().activate());
    }

    private static void activateState(State state) {
        if (state.stateList().size() > 0) {
            state.current(state.stateList().get(0));
            activate(state);
        }
    }

    private static void doPeriodic(State state) {
        if (state.current() != null) doPeriodic(state.current().as(State.class));
        state.periodicActionList().forEach(State.Action::action);
    }

    private static void doIn(State state) {
        state.entryActionList().forEach(State.Action::action);
        if (state.current() != null) doIn(state.current().as(State.class));
    }

    private static void doOut(State state, StateChart toParent) {
        state.exitActionList().forEach(State.Action::action);
        if (state.current() != null && state._owner() != toParent._instance() && state._owner().is(State.class))
            doOut(state._owner(State.class), toParent);
    }
}
