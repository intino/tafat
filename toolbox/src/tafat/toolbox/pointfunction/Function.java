package tafat.toolbox.pointfunction;

import tafat.toolbox.pointset.PointSet;

public interface Function {

    public void set(PointSet set);

    public double getY(double x);

}
