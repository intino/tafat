package tafat.toolbox.statechart;

import tafat.toolbox.Action;
import tafat.toolbox.Checker;
import tafat.toolbox.timeout.RateFunction;
import tafat.toolbox.timeout.Timeout;
import tafat.toolbox.timeout.TimeoutFunction;

public class Definition {

    final StateChart stateChart;

    Definition(StateChart stateChart) {
        this.stateChart = stateChart;
    }

    public static class State extends Definition {

        public State(StateChart stateChart) {
            super(stateChart);
        }

        public State state(String id) {
            stateChart.state(id);
            return this;
        }

        public State in(Action action) {
            stateChart.in(action);
            return this;
        }

        public State out(Action action) {
            stateChart.out(action);
            return this;
        }

        public State include(StateChart newStateChart) {
            stateChart.include(newStateChart);
            return this;
        }

        public PrevTransition transition() {
            return new PrevTransition(stateChart);
        }

        public StateChart stateChart() {
            stateChart.commit();
            return stateChart;
        }

    }

    public static class PrevTransition extends Definition {

        public PrevTransition(StateChart stateChart) {
            super(stateChart);
        }

        public Transition from(String id) {
            stateChart.from(id);
            return new Transition(stateChart);
        }
    }

    public static class Transition extends Definition {

        public Transition(StateChart stateChart) {
            super(stateChart);
        }

        public DefinedTransition to(String id) {
            stateChart.to(id);
            return new DefinedTransition(stateChart);
        }

    }

    public class DefinedTransition extends Definition {

        public DefinedTransition(StateChart stateChart) {
            super(stateChart);
        }

        public FinishedTransition condition(Checker checker) {
            stateChart.checker(checker);
            return new FinishedTransition(stateChart);
        }

        public FinishedTransition message(String message) {
            stateChart.checker(() -> stateChart.message.content.equals(message));
            return new FinishedTransition(stateChart);
        }

        public FinishedTransition timeout(TimeoutFunction calculator) {
            stateChart.checker(new Timeout(calculator));
            return new FinishedTransition(stateChart);
        }

        public FinishedTransition rate(int times, long period) {
            stateChart.checker(new Timeout(new RateFunction(times, period)));
            return new FinishedTransition(stateChart);
        }

    }

    public class FinishedTransition extends Definition {


        public FinishedTransition(StateChart stateChart) {
            super(stateChart);
        }

        public FinishedTransition action(Action action) {
            stateChart.action(action);
            return this;
        }

        public PrevTransition transition() {
            return new PrevTransition(stateChart);
        }

        public StateChart stateChart() {
            stateChart.commit();
            return stateChart;
        }

    }

}
