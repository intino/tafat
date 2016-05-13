package tafat.engine.tablefunction;

import tafat.engine.pointset.PointSet;

public interface Function {

    void set(PointSet set);

    double y(double x);

}
