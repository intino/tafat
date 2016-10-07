package io.intino.tafat.toolbox.tablefunction.functions;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.Function;
import io.intino.tafat.toolbox.tablefunction.TableFunction;

public class NoneExtrapolation implements Function {
    @Override
    public void set(PointSet set) {
    }

    @Override
    public double y(double x) {
        throw new TableFunction.Exception("Values out of the point set cannot be extrapolated. Please defined an extrapolator");
    }
}
