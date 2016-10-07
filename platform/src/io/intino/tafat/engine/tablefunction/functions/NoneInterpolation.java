package io.intino.tafat.engine.tablefunction.functions;

import io.intino.tafat.engine.pointset.Point;
import io.intino.tafat.engine.pointset.PointSet;
import io.intino.tafat.engine.tablefunction.Function;

import java.util.Collections;
import java.util.logging.Logger;

public class NoneInterpolation implements Function {

	private static final Logger LOG = Logger.getLogger(NoneInterpolation.class.getName());
    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
    }

    @Override
    public double y(double x){
        int index = Collections.binarySearch(set, new Point(x), (p1, p2) -> Double.compare(p1.x(), p2.x()));
        if(index >= 0) return set.get(index).y();
        LOG.warning("Value " + x + " cannot be interpolated with none interpolation. Please define an interpolator");
		return x;
    }

}
