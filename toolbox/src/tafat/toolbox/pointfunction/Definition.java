package tafat.toolbox.pointfunction;

import tafat.toolbox.pointset.PointSet;

public abstract class Definition {

    protected final PointFunction function;

    Definition(PointFunction function) {
        this.function = function;
    }

    public static class Interpolation extends Definition{

        Interpolation(PointFunction pointFunction) {
            super(pointFunction);
        }

        public Extrapolation linearInterpolation() {
            function.linearInterpolation();
            return new Extrapolation(function);
        }

        public PointFunction using(PointSet set) {
            function.using(set);
            return function;
        }
    }

    public static class Extrapolation extends Definition{

        Extrapolation(PointFunction function) {
            super(function);
        }

        public Using linearExtrapolation() {
            function.linearExtrapolation();
            return new Using(function);
        }

        public PointFunction using(PointSet set) {
            function.using(set);
            return function;
        }
    }

    public static class Using extends Definition{

        Using(PointFunction function) {
            super(function);
        }

        public PointFunction using(PointSet set) {
            function.using(set);
            return function;
        }
    }
}
