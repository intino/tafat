package tafat.toolbox.pointfunction;

import tafat.toolbox.pointfunction.functions.*;
import tafat.toolbox.pointfunction.functions.Error;
import tafat.toolbox.pointset.PointSet;

public class PointFunction {

    PointSet set = null;
    Function interpolation = new None();
    Function extrapolation = new Error();

    private PointFunction(){}

    public static Definition.Interpolation define(){
        return new Definition.Interpolation(new PointFunction());
    }

    void linearInterpolation() {
        interpolation = new Linear();
    }

    void linearExtrapolation() {
        extrapolation = new LinearOut();
    }

    void using(PointSet set) {
        if(set.size() < 2) throw new Exception("Point set must contain two points at least");
        process(set);
    }

    private void process(PointSet set) {
        set.sort();
        this.set = set;
        interpolation.set(set);
        extrapolation.set(set);
    }

    public double y(double x) {
        return isInRange(x) ?
                interpolation.y(x) :
                extrapolation.y(x);
    }

    private boolean isInRange(double x) {
        return x >= set.first().x() && x <= set.last().x();
    }

    public static class Exception extends RuntimeException{
        public Exception(String message) {
            super(message);
        }
    }
}
