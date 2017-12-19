package io.intino.tafat.engine.tablefunction;

import java.util.List;

public class Dimension {

	double coordinate;

	List<Dimension> dimensions;
	Interpolation pointSet;

	public Dimension(double coordinate, List<Dimension> dimensions) {
		this.coordinate = coordinate;
		this.dimensions = dimensions;
	}

	public Dimension(Interpolation pointSet) {
		this.pointSet = pointSet;
	}

	public double get(double value) {
		return pointSet.y(value);
	}

	public double coordinate() {
		return coordinate;
	}

	public List<Dimension> dimensions() {
		return this.dimensions;
	}
}
