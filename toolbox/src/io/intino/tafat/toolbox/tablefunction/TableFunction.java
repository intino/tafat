package io.intino.tafat.toolbox.tablefunction;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.functions.*;

public class TableFunction {

    private PointSet set = null;
    private Function interpolation = new NoneInterpolation();
    private Function extrapolation = new NoneExtrapolation();

    private TableFunction(){}

    public static Definition.Start define(){
        return new Definition.Start(new TableFunction());
    }

    public double y(double x) {
        return set.isInRange(x) ?
                interpolation.y(x) :
                extrapolation.y(x);
    }

    void stepInterpolation() {
        interpolation = new Step();
    }

    void linearInterpolation() {
        interpolation = new Linear();
    }

    void splineInterpolation() {
        interpolation = new Spline();
    }

    void polynomialInterpolation(int order) {
        interpolation = new Polynomial(order);
    }

    void customExtrapolation(double customValue) {
        extrapolation = new Custom(customValue);
    }

    void nearestExtrapolation() {
        extrapolation = new Nearest();
    }

    void repeatExtrapolation() {
        extrapolation = new Repeat(interpolation);
    }

    void linearExtrapolation() {
        extrapolation = new Linear();
    }

    void splineExtrapolation() {
        extrapolation = new Spline();
    }

    void polynomialExtrapolation(int order) {
        extrapolation = new Polynomial(order);
    }

    void points(PointSet set) {
        if(set.size() < 2) throw new Exception("Point set must contain two points at least");
        this.set = set;
    }

    void commit() {
        interpolation.set(set);
        extrapolation.set(set);
    }

    public static class Exception extends RuntimeException{
        public Exception(String message) {
            super(message);
        }
    }
}
