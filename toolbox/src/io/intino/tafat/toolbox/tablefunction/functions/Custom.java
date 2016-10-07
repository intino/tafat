package io.intino.tafat.toolbox.tablefunction.functions;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.Function;

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
