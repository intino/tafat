package tafat.toolbox.pointfunction;

import tafat.toolbox.pointfunction.functions.*;
import tafat.toolbox.pointfunction.functions.Error;

public class PointFunction {

    Function interpolation = new Linear();
    Function extrapolation = new Error();

    private PointFunction(){}

    public static Definition.Start define(){

    }
}
