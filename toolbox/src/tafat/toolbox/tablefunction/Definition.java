package tafat.toolbox.tablefunction;

import tafat.toolbox.pointset.PointSet;

abstract class Definition {

    protected final TableFunction function;

    Definition(TableFunction function) {
        this.function = function;
    }

    public static class Start extends Definition{

        Start(TableFunction tableFunction) {
            super(tableFunction);
        }

        public Extrapolation stepInterpolation() {
            function.stepInterpolation();
            return new Extrapolation(function);
        }

        public Extrapolation linearInterpolation() {
            function.linearInterpolation();
            return new Extrapolation(function);
        }

        public Extrapolation splineInterpolation() {
            function.splineInterpolation();
            return new Extrapolation(function);
        }

        public Extrapolation polynomialInterpolation(int order) {
            function.polynomialInterpolation(order);
            return new Extrapolation(function);
        }

        public Point customExtrapolation(double customValue) {
            function.customExtrapolation(customValue);
            return new Point(function);
        }

        public Point nearestExtrapolation() {
            function.nearestExtrapolation();
            return new Point(function);
        }

        public Point repeatSerieExtrapolation() {
            function.repeatExtrapolation();
            return new Point(function);
        }

        public Point linearExtrapolation() {
            function.linearExtrapolation();
            return new Point(function);
        }

        public Point splineExtrapolation() {
            function.splineExtrapolation();
            return new Point(function);
        }

        public Point polynomialExtrapolation(int order) {
            function.polynomialExtrapolation(order);
            return new Point(function);
        }

        public Finished points(PointSet set) {
            function.points(set);
            return new Finished(function);
        }

    }

    public static class Extrapolation extends Definition{

        public Extrapolation(TableFunction function) {
            super(function);
        }

        public Point customExtrapolation(double customValue) {
            function.customExtrapolation(customValue);
            return new Point(function);
        }

        public Point nearestPointExtrapolation() {
            function.nearestExtrapolation();
            return new Point(function);
        }

        public Point repeatExtrapolation() {
            function.repeatExtrapolation();
            return new Point(function);
        }

        public Point linearExtrapolation() {
            function.linearExtrapolation();
            return new Point(function);
        }

        public Point splineExtrapolation() {
            function.splineExtrapolation();
            return new Point(function);
        }

        public Point polynomialExtrapolation(int order) {
            function.polynomialExtrapolation(order);
            return new Point(function);
        }

        public Finished points(PointSet set) {
            function.points(set);
            return new Finished(function);
        }

    }

    public static class Point extends Definition{
        public Point(TableFunction function) {
            super(function);
        }

        public Finished points(PointSet set) {
            function.points(set);
            return new Finished(function);
        }
    }

    public static class Finished extends Definition{
        public Finished(TableFunction function) {
            super(function);
        }
        public TableFunction tableFunction() {
            function.commit();
            return function;
        }
    }

}
