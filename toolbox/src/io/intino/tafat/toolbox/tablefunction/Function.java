package io.intino.tafat.toolbox.tablefunction;

import io.intino.tafat.toolbox.pointset.PointSet;

public interface Function {

    public void set(PointSet set);

    public double y(double x) throws TableFunction.Exception;

}
