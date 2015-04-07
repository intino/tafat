package tafat.toolbox.tablefunction.functions;

import tafat.toolbox.tablefunction.Function;
import tafat.toolbox.pointset.PointSet;

public class Custom implements Function {


    private double value;

    public Custom(double value) {

        this.value = value;
    }

    @Override
    public void set(PointSet set) {
    }

    @Override
    public double y(double x) {
        return value;
    }

}
