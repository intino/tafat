package io.intino.tafat.toolbox.pointset;

import java.util.ArrayList;
import java.util.Collections;

public class PointSet extends ArrayList<Point> {

    private PointSet() {
    }

    public static Definition.Start define() {
        return new Definition.Start(new PointSet());
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

    void point(double x, double y) {
        add(new Point(x, y));
    }

    void x(double... values) {
        for (double value : values)
            add(new Point(value));
    }

    void y(double... values) {
        if (values.length != size())
            throw new Exception("X and Y coordinates do not have same size");
        for (int i = 0; i < values.length; i++)
            get(i).y = values[i];
    }

    void commit() {
        sort();
    }

    private void sort(){
        Collections.sort(this, (o1, o2) -> Double.compare(o1.x, o2.x));
    }

    public class Exception extends RuntimeException{
        public Exception(String message) {
            super(message);
        }
    }

}
