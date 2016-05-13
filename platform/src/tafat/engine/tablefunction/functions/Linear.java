package tafat.engine.tablefunction.functions;

import tafat.engine.pointset.Point;
import tafat.engine.pointset.PointSet;
import tafat.engine.tablefunction.Function;

import java.util.Collections;

public class Linear implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
    }

    @Override
    public double y(double x) {
        int index = Collections.binarySearch(set, new Point(x), (p1, p2) -> Double.compare(p1.x(), p2.x()));
        if(index >= 0) return set.get(index).y();
        return interpolateY(x, -(index + 2), -(index + 1));
    }

    private double interpolateY(double x, int i1, int i2) {
        return i1 >= 0 && i2 < set.size() ?
                interpolate(x, set.get(i1), set.get(i2)) :
                interpolateOut(x, i1);
    }

    private double interpolateOut(double x, int i1) {
        return i1 < 0 ?
                interpolate(x, set.first(), set.get(1)) :
                interpolate(x, set.get(set.size() - 2), set.last());
    }

    private double interpolate(double x, Point p1, Point p2) {
        return ((x - p1.x()) * (p2.y() - p1.y()) / (p2.x() - p1.x())) + p1.y();
    }

}
