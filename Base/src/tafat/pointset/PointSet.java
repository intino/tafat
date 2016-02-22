package tafat.pointset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointSet extends ArrayList<Point> {

    public PointSet(List<Point> points) {
		this.addAll(points);
		sort();
    }

    public Point first() {
        return get(0);
    }

    public Point last() {
        return get(size() - 1);
    }

    public boolean isInRange(double x) {
        return x >= first().x() && x <= last().x();
    }

    private void sort(){
        Collections.sort(this, (o1, o2) -> Double.compare(o1.x, o2.x));
    }

}
