package tafat.tablefunction;

import tafat.pointset.PointSet;

public interface Function {

    void set(PointSet set);

    double y(double x);

}
