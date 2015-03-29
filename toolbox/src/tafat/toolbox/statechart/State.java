package tafat.toolbox.statechart;

public class State extends StateChart {
    final int id;
    Action in = () -> {};
    Action out = () -> {};

    State(int id, StateChart parent) {
        super(parent);
        this.id = id;
    }

    void in(StateChart fromParent) {
        if(parent instanceof State && parent != fromParent)
            ((State)parent).in(fromParent);
        in.execute();
    }

    void out(StateChart toParent) {
        out.execute();
        if(parent != null && parent != toParent)
            ((State)parent).out(toParent);
    }

    @Override
    public int currentState() {
        if(state != null) return state.currentState();
        return id;
    }
}
