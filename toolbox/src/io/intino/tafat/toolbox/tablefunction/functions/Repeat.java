package io.intino.tafat.toolbox.tablefunction.functions;

import io.intino.tafat.toolbox.pointset.PointSet;
import io.intino.tafat.toolbox.tablefunction.Function;

public class Repeat implements Function {

    private PointSet set;
    private Function interpolation;

    public Repeat(Function interpolation) {
        this.interpolation = interpolation;
    }

    @Override
    public void set(PointSet set) {
        this.set = set;
    }

    @Override
    public double y(double x) {
        return interpolation.y(getInRange(x));
    }

    private double getInRange(double x) {
        return x < set.first().x() ?
                up(x) :
                down(x);
    }

    private double up(double x) {
        while(x < set.first().x()) x += setRange();
        return x;
    }

    private double down(double x) {
        while(x > set.last().x()) x -= setRange();
        return x;
    }

    private double setRange(){
        return set.last().x() - set.first().x();
    }

}
