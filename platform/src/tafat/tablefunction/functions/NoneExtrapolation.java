package tafat.tablefunction.functions;

import tafat.pointset.PointSet;
import tafat.tablefunction.Function;

import java.util.logging.Logger;

public class NoneExtrapolation implements Function {

	private static final Logger LOG = Logger.getLogger(NoneExtrapolation.class.getName());

	@Override
	public void set(PointSet set) {
	}

	@Override
	public double y(double x) {
		LOG.warning("Extrapolation has not been defined. Returning same value");
		return x;
	}
}
