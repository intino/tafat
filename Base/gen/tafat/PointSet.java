package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class PointSet extends magritte.wraps.Definition {
    public magritte.Set<tafat.PointSet.Definition> definitionSet() {
        return _components(tafat.PointSet.Definition.class);
    }

    public tafat.PointSet.Definition definition(int index) {
        return definitionSet().get(index);
    }

    public magritte.Set<tafat.PointSet.Point> pointSet() {
        return _components(tafat.PointSet.Point.class);
    }

    public tafat.PointSet.Point point(int index) {
        return pointSet().get(index);
    }

    public tafat.PointSet.X x() {
        return _component(tafat.PointSet.X.class);
    }

    public tafat.PointSet.Y y() {
        return _component(tafat.PointSet.Y.class);
    }

    public static class Definition extends magritte.wraps.Definition {
    }

    public static class Point extends tafat.PointSet.Definition {
        public double x() {
            return _get("x").asDouble();
        }

        public double y() {
            return _get("y").asDouble();
        }
    }

    public static class X extends tafat.PointSet.Definition {
        public double values() {
            return _get("values").asDouble();
        }
    }

    public static class Y extends tafat.PointSet.Definition {
        public double values() {
            return _get("values").asDouble();
        }
    }
}
