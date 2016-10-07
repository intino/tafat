package io.intino.tafat.engine.pointset;

public class Point {

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
