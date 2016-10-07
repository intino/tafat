package io.intino.tafat.engine.tablefunction;

import io.intino.tafat.engine.pointset.PointSet;

public interface Function {

    void set(PointSet set);

    double y(double x);

}
