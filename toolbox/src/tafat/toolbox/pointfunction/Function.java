package tafat.toolbox.pointfunction;

import tafat.toolbox.pointset.PointSet;

public interface Function {

    public void set(PointSet set);

    public double y(double x) throws PointFunction.Exception;

}
