package tafat.toolbox.pointfunction;

public abstract class Definition {

    protected final PointFunction function;

    Definition(PointFunction function) {
        this.function = function;
    }

    static class Interpolation extends Definition{

        Interpolation(PointFunction pointFunction) {
            super(pointFunction);
        }

        public Extrapolation linearInterpolation() {
            function.linearInterpolation();
            return new Extrapolation(function);
        }

        public Extrapolation errorInterpolation() {
            function.errorInterpolation();
            return new Extrapolation(function);
        }

    }

    static class Extrapolation extends Definition{

        Extrapolation(PointFunction function) {
            super(function);
        }

        public Using linearExtrapolation() {
            function.linearExtrapolation();
            return new Using(function);
        }

        public Using errorExtrapolation() {
            function.errorExtrapolation();
            return new Using(function);
        }

    }

    static class Using extends Definition{

        Using(PointFunction function) {
            super(function);
        }
    }
}
