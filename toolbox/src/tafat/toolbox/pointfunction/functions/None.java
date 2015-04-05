package tafat.toolbox.pointfunction.functions;

import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointfunction.PointFunction;
import tafat.toolbox.pointset.Point;
import tafat.toolbox.pointset.PointSet;

import java.util.Collections;

public class None implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
        if (!set.isSorted()) set.sort();
    }

    @Override
    public double y(double x) throws PointFunction.Exception {
        int index = Collections.binarySearch(set, new Point(x), (p1, p2) -> Double.compare(p1.x(), p2.x()));
        if(index >= 0) return set.get(index).y();
        throw new PointFunction.Exception("Value " + x + " cannot be interpolated with none interpolation. Please define an interpolator");
    }

}
