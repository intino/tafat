package io.intino.tafat.engine.pointset;

public class Range {
    Point from;
    Point to;

    public Range(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Point from() {
        return from;
    }

    public Point to() {
        return to;
    }
}
