package tafat.toolbox.pointset;

import java.util.ArrayList;
import java.util.List;

public class PointSet {

    private List<Point> points = new ArrayList<>();

    private PointSet() {
    }

    public static Definition.Start define() {
        return new Definition.Start(new PointSet());
    }

    public Point get(int index) {
        return points.get(index);
    }

    public PointSet point(double x, double y) {
        points.add(new Point(x, y));
        return this;
    }

    void x(double... values) {
        for (double value : values)
            points.add(new Point(value));
    }

    void y(double... values) {
        if (values.length != points.size())
            throw new Exception("X and Y coordinates do not have same size");
        for (int i = 0; i < values.length; i++)
            points.get(i).y = values[i];
    }

    public static class Point {

        double x;
        double y;

        public Point(double x) {
            this.x = x;
        }

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double x() {
            return x;
        }

        public double y() {
            return y;
        }
    }

    public class Exception extends RuntimeException{
        public Exception(String message) {
            super(message);
        }
    }
}
