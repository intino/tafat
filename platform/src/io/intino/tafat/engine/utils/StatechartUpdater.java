package io.intino.tafat.engine.utils;

import io.intino.tafat.model.StateChart;
import io.intino.tafat.model.StateChart.State;
import io.intino.tafat.model.StateChart.Transition;
import io.intino.tafat.model.StateChart.Transition.TimeBased;

public class StatechartUpdater {

    public static void update(StateChart stateChart, int advancedTime){
        updateStatechart(stateChart, advancedTime);
        doPeriodic(stateChart.current().core$().as(State.class));
        stateChart.message("");
    }

    private static void updateStatechart(StateChart stateChart, int advancedTime) {
		updateChild(stateChart, advancedTime);
		updateStatechart(stateChart, findTransition(stateChart, advancedTime), advancedTime);
    }

    private static void updateStatechart(StateChart stateChart, Transition transition, int advancedTime) {
        while (transition != null) {
            processTransition(transition, stateChart);
            if (transitionChangedStateChart(stateChart)) break;
			updateChild(stateChart, 0);
			transition = findTransition(stateChart.current(), advancedTime);
        }
    }

    private static boolean transitionChangedStateChart(StateChart stateChart) {
        return stateChart.core$().ownerAs(StateChart.class) != null && stateChart.core$().ownerAs(StateChart.class).current() != stateChart;
    }

    private static void updateChild(StateChart stateChart, int advancedTime) {
        if (stateChart.current() != null) updateStatechart(stateChart.current(), advancedTime);
    }

    private static Transition findTransition(StateChart stateChart, int advancedTime) {
		for (Transition transition : stateChart.transitionList())
			if (transition.from() == stateChart.current() && transition.trigger().check(advancedTime))
				return transition;
		return null;
	}

    private static void processTransition(Transition transition, StateChart stateChart) {
        out(stateChart.current().core$().as(State.class), transition.to().core$().owner().as(StateChart.class));
        doTransition(transition, transition.to().core$().owner().as(StateChart.class));
        in(transition.to(), stateChart);
    }

    private static void out(State state, StateChart toParent) {
        if (state.current() != null) out(state.current().core$().as(State.class), toParent);
        else doOut(state, toParent);
    }

    private static void doTransition(Transition transition, StateChart stateChart) {
        transition.action();
        stateChart.current(transition.to());
        updateParentsState(transition.to());
        activate(stateChart);
    }

    private static void in(State state, StateChart fromParent) {
        if (state.core$().owner() == fromParent.core$()) doIn(state);
        else if (state.core$().owner() != null && state.core$().owner().is(State.class))
            in(state.core$().owner().as(State.class), fromParent);
        else doIn(state);
    }

    private static void updateParentsState(StateChart stateChart) {
        StateChart owner = stateChart.core$().ownerAs(StateChart.class);
        if (owner == null) return;
        owner.current(stateChart);
        updateParentsState(owner);
    }

    private static void activate(StateChart stateChart) {
        activateTransitions(stateChart);
        activateState(stateChart.current().core$().as(State.class));
    }

    public static void activateTransitions(StateChart stateChart) {
        stateChart.transitionList().forEach(t -> {
            if(t.trigger().core$().is(TimeBased.class) && t.from() == stateChart.current())
                t.timeBased().activate();
        });
    }

    private static void activateState(State state) {
        if (state.stateList().size() > 0) {
            state.current(state.stateList().get(0));
            activate(state);
        }
    }

    private static void doPeriodic(State state) {
        if (state.current() != null) doPeriodic(state.current().core$().as(State.class));
        state.periodicActionList().forEach(State.Action::action);
    }

    private static void doIn(State state) {
        state.entryActionList().forEach(State.Action::action);
        if (state.current() != null) doIn(state.current().core$().as(State.class));
    }

    private static void doOut(State state, StateChart toParent) {
        state.exitActionList().forEach(State.Action::action);
        if (state.current() != null && state.core$().owner() != toParent.core$() && state.core$().owner().is(State.class))
            doOut(state.core$().ownerAs(State.class), toParent);
    }
}
