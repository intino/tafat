package tafat.toolbox.tablefunction.functions;

import tafat.toolbox.tablefunction.Function;
import tafat.toolbox.pointset.PointSet;

public class Nearest implements Function {

    private PointSet set;

    @Override
    public void set(PointSet set) {
        this.set = set;
    }

    @Override
    public double y(double x) {
        return x < set.first().x() ?
                set.first().y() :
                set.last().y();
    }

}
