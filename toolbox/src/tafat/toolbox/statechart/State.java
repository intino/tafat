package tafat.toolbox.statechart;

public class State {
    private final int id;
    private Action in = () -> {};
    private Action out = () -> {};
    private StateChart stateChart = null;

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

    void stateChart(StateChart stateChart){
        this.stateChart = stateChart;
    }

    public void update(long advancedTime) {
        if(stateChart != null) stateChart.update(advancedTime);
    }
}
