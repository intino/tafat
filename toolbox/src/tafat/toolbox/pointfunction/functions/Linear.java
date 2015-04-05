package tafat.toolbox.pointfunction.functions;

import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointset.Point;
import tafat.toolbox.pointset.PointSet;

import java.util.Collections;

public class Linear implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
        if (!set.isSorted()) set.sort();
    }

    @Override
    public double y(double x) {
        int index = Collections.binarySearch(set, new Point(x), (p1, p2) -> Double.compare(p1.x(), p2.x()));
        if(index >= 0) return set.get(index).y();
        return interpolateY(x, set.get(-(index+2)), set.get(-(index+1)));
    }

    private double interpolateY(double x, Point p1, Point p2) {
        return ((x - p1.x()) * (p2.y() - p1.y()) / (p2.x() - p1.x())) + p1.y();
    }

}
