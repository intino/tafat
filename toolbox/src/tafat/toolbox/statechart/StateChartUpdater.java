package tafat.toolbox.statechart;

import tafat.toolbox.timeout.Timeout;

import java.util.List;
import java.util.Optional;

public class StateChartUpdater {

    private StateChart stateChart;

    public StateChartUpdater(StateChart stateChart) {
        this.stateChart = stateChart;
    }

    public void update(long advancedTime) {
        propagate(advancedTime);
        while (checkTransitions()) if (parent() != null && parent().state != stateChart) break;
        this.message().content = "";
    }


    private void propagate(long advancedTime) {
        transitions().stream().filter(t -> t.checker instanceof Timeout && t.from == state()).
                forEach(t -> ((Timeout) t.checker).step(advancedTime));
        if (state() != null) state().update(advancedTime);
    }



    private boolean checkTransitions() {
        Optional<Transition> first = transitions().stream().filter(t -> t.from == state() && t.check()).findFirst();
        if (!first.isPresent()) return false;
        processTransition(first.get());
        return true;
    }

    private void processTransition(Transition transition) {
        state().out(transition.to.parent);
        transition.action();
        calculateState(transition);
    }

    private void calculateState(Transition transition) {
        transition.to.in(state().parent);
        if (transition.to.parent != stateChart) toOtherStateChart(transition);
        else doTransitionForStateChart(transition, stateChart);
    }

    private void toOtherStateChart(Transition transition) {
        doTransitionForStateChart(transition, transition.to.parent);
        stateChart.state = states().get(0);
    }

    private void doTransitionForStateChart(Transition transition, StateChart stateChart) {
        stateChart.state = transition.to;
        activate(stateChart);
    }

    protected void activate(StateChart stateChart) {
        activateState(stateChart.state);
        stateChart.transitions.stream().filter(t -> t.checker instanceof Timeout && t.from == state()).
                forEach(t -> ((Timeout) t.checker).activate());
    }

    private void activateState(State state) {
        if (state.states.size() > 0) {
            state.state = state.states.get(0);
            activate(state);
        }
    }

    private StateChart parent() {
        return stateChart.parent;
    }

    private Message message() {
        return stateChart.message;
    }

    private List<Transition> transitions() {
        return stateChart.transitions;
    }

    private State state() {
        return stateChart.state;
    }

    private List<State> states() {
        return stateChart.states;
    }
}
