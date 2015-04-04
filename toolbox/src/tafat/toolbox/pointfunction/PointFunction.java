package tafat.toolbox.pointfunction;

import tafat.toolbox.pointfunction.functions.*;
import tafat.toolbox.pointfunction.functions.Error;

public class PointFunction {

    Function interpolation = new Linear();
    Function extrapolation = new Error();

    private PointFunction(){}

    public static Definition.Interpolation define(){
        return new Definition.Interpolation(new PointFunction());
    }

    public void linearInterpolation() {
    }

    public void errorInterpolation() {
        interpolation = new Error();
    }

    public void linearExtrapolation() {
        extrapolation = new Linear();
    }

    public void errorExtrapolation() {
    }
}
