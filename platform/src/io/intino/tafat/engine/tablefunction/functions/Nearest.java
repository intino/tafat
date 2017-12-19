package io.intino.tafat.engine.tablefunction.functions;


import io.intino.tafat.engine.pointset.PointSet;
import io.intino.tafat.engine.tablefunction.Function;

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
