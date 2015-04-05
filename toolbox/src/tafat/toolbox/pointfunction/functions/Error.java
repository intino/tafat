package tafat.toolbox.pointfunction.functions;

import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointfunction.PointFunction;
import tafat.toolbox.pointset.PointSet;

public class Error implements Function {
    @Override
    public void set(PointSet set) {
    }

    @Override
    public double y(double x) {
        throw new PointFunction.Exception("Values out of the point set cannot be extrapolated. Please defined an extrapolator");
    }
}
