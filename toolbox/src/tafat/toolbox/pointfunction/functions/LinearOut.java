package tafat.toolbox.pointfunction.functions;

import tafat.toolbox.pointfunction.Function;
import tafat.toolbox.pointset.Point;
import tafat.toolbox.pointset.PointSet;

public class LinearOut implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
        if (!set.isSorted()) set.sort();
    }

    @Override
    public double y(double x) {
        return x < set.first().x() ?
                interpolateY(x, set.first(), set.get(1)) :
                interpolateY(x, set.get(set.size() - 2), set.last());
    }

    private double interpolateY(double x, Point p1, Point p2) {
        return ((x - p1.x()) * (p2.y() - p1.y()) / (p2.x() - p1.x())) + p1.y();
    }

}
