package tafat.tablefunction.functions;

import tafat.pointset.PointSet;
import tafat.tablefunction.Function;

public class Custom implements Function {

	private tafat.functions.Function function;

	@Override
	public void set(PointSet set) {
	}

	public void function(tafat.functions.Function function) {
		this.function = function;
	}

	@Override
	public double y(double x) {
		return function.calculate();
	}

}
