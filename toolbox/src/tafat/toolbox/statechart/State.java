package tafat.toolbox.statechart;

public class State {
    private final int id;
    private Action in = () -> {};
    private Action out = () -> {};

    State(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    void in() {
        in.execute();
    }

    void in(Action action) {
        in = action;
    }

    void out() {
        out.execute();
    }

    void out(Action action) {
        out = action;
    }
}
