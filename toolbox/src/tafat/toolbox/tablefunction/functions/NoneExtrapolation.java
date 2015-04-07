package tafat.toolbox.tablefunction.functions;

import tafat.toolbox.tablefunction.Function;
import tafat.toolbox.tablefunction.TableFunction;
import tafat.toolbox.pointset.PointSet;

public class NoneExtrapolation implements Function {
    @Override
    public void set(PointSet set) {
    }

    @Override
    public double y(double x) {
        throw new TableFunction.Exception("Values out of the point set cannot be extrapolated. Please defined an extrapolator");
    }
}
