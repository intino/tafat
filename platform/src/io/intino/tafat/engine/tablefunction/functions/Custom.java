package io.intino.tafat.engine.tablefunction.functions;

import io.intino.tafat.engine.pointset.PointSet;
import io.intino.tafat.engine.tablefunction.Function;

public class Custom implements Function {

	private io.intino.tafat.model.functions.Function function;

	@Override
	public void set(PointSet set) {
	}

	public void function(io.intino.tafat.model.functions.Function function) {
		this.function = function;
	}

	@Override
	public double y(double x) {
		return function.calculate();
	}

}
