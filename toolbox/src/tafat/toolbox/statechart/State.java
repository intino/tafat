package tafat.toolbox.statechart;

import tafat.toolbox.Action;

public class State extends StateChart {
    final String id;
    Action in = () -> {};
    Action out = () -> {};

    State(String id, StateChart parent) {
        super(parent);
        this.id = id;
    }

    void in(StateChart fromParent) {
        if(parent == fromParent) doIn();
        else if(parent != null && parent instanceof State) ((State)parent).in(fromParent);
        else doIn();
    }

    private void doIn() {
        in.execute();
        if(state != null) state.doIn();
    }

    void out(StateChart toParent) {
        if(state != null) state.out(toParent);
        else doOut(toParent);
    }

    private void doOut(StateChart toParent) {
        out.execute();
        if(parent != null && parent != toParent && parent instanceof State)
            ((State)parent).doOut(toParent);
    }

    @Override
    public String currentState() {
        if(state != null) return state.currentState();
        return id;
    }
}
