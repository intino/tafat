package io.intino.tafat.toolbox.tablefunction.functions;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.Function;
import io.intino.tafat.toolbox.pointset.Point;

import java.util.Collections;

public class Step implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
    }

    @Override
    public double y(double x) {
        int index = Collections.binarySearch(set, new Point(x), (p1, p2) -> Double.compare(p1.x(), p2.x()));
        if(index >= 0) return set.get(index).y();
        return set.get(-(index + 2)).y();
    }

}
