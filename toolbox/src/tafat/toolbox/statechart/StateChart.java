package tafat.toolbox.statechart;

import java.util.ArrayList;
import java.util.List;

public class StateChart {

    List<State> states = new ArrayList<>();
    List<Transition> transitions = new ArrayList<>();
    State state;

    public int currentState() {
        return state.id();
    }

    public void update() {
        Transition transition = transitions.stream().filter(t -> t.from() == state.id() && t.check()).findFirst().get();
        if(transition == null) return;
        update(transition);
    }

    private void update(Transition transition) {
        state.out();
        transition.action();
        state = states.stream().filter(s -> s.id() == transition.to()).findFirst().get();
        state.in();
    }

    public StateTransaction state(int id) {
        if(stateExists(id)) throw new StateChartException("State with identifier " + id + " has been already defined");
        states.add(new State(id));
        if(states.size() == 1) state = states.get(0);
        return createStateTransaction();
    }

    public StateTransaction in(Action action) {
        states.get(states.size() - 1).in(action);
        return createStateTransaction();
    }

    public StateTransaction out(Action action) {
        states.get(states.size() - 1).out(action);
        return createStateTransaction();
    }

    private int onGoingFrom;

    public TransitionTransaction from(int id) {
        onGoingFrom = id;
        return createTransitionTransaction();
    }

    public DestinatedTransaction to(int id) {
        transitions.add(new Transition(onGoingFrom, id));
        return createDestinatedTransaction();
    }

    public DefinedTransaction condition(Condition condition) {
        transitions.get(transitions.size() - 1).check(condition::check);
        return createDefinedTransaction();
    }

    public DefinedTransaction action(Action action) {
        transitions.get(transitions.size() - 1).action(action);
        return createDefinedTransaction();
    }

    private boolean stateExists(int id) {
        return states.stream().filter(s -> s.id() == id).findFirst().isPresent();
    }

    private StateTransaction createStateTransaction() {
        return new StateTransaction() {
            @Override
            public StateTransaction in(Action action) {
                return StateChart.this.in(action);
            }

            @Override
            public StateTransaction out(Action action) {
                return StateChart.this.out(action);
            }

            @Override
            public StateTransaction state(int id) {
                return StateChart.this.state(id);
            }

            @Override
            public TransitionTransaction from(int id) {
                return StateChart.this.from(id);
            }
        };
    }

    private TransitionTransaction createTransitionTransaction() {
        return StateChart.this::to;
    }

    private DestinatedTransaction createDestinatedTransaction() {
        return new DestinatedTransaction() {
            @Override
            public DefinedTransaction condition(Condition condition) {
                return StateChart.this.condition(condition);
            }
        };
    }

    private DefinedTransaction createDefinedTransaction() {
        return new DefinedTransaction() {
            @Override
            public DefinedTransaction action(Action action) {
                return StateChart.this.action(action);
            }

            @Override
            public TransitionTransaction from(int id) {
                return StateChart.this.from(id);
            }

            @Override
            public StateTransaction state(int id) {
                return StateChart.this.state(id);
            }
        };
    }

    public interface StateTransaction {
        StateTransaction in(Action action);
        StateTransaction out(Action action);
        StateTransaction state(int id);
        TransitionTransaction from(int id);
    }

    public interface TransitionTransaction {
        DestinatedTransaction to(int id);
    }

    public interface DestinatedTransaction {
        DefinedTransaction condition(Condition condition);
    }

    public interface DefinedTransaction {
        DefinedTransaction action(Action action);
        TransitionTransaction from(int id);
        StateTransaction state(int id);
    }
}
