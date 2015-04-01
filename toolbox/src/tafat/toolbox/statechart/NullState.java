package tafat.toolbox.statechart;

public class NullState extends State {

    public static NullState create(String id){
        return new NullState(id, null);
    }

    private NullState(String id, StateChart parent) {
        super(id, parent);
        in = () -> { throw new StateChartException("State " + id + " does not exist");};
        out = () -> { throw new StateChartException("State " + id + " does not exist");};
    }
}
