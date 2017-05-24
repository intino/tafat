package io.intino.tafat.engine.tablefunction;

import io.intino.tafat.TableFunction;
import io.intino.tafat.engine.pointset.PointSet;
import io.intino.tafat.engine.tablefunction.functions.*;

import java.util.HashMap;
import java.util.Map;

public class Interpolation {

	private static Map<Class<?>,Class<? extends Function>> functions = new HashMap<>();
	private final Function interpolation;
	private final Function extrapolation;
	private final PointSet points;

	static{
		functions.put(TableFunction.NoneInterpolation.class, NoneInterpolation.class);
		functions.put(TableFunction.LinearInterpolation.class, Linear.class);
		functions.put(TableFunction.PolynomialInterpolation.class, Polynomial.class);
		functions.put(TableFunction.SplineInterpolation.class, Spline.class);
		functions.put(TableFunction.StepInterpolation.class, Step.class);
		functions.put(TableFunction.NoneExtrapolation.class, NoneExtrapolation.class);
		functions.put(TableFunction.NearestPointExtrapolation.class, Nearest.class);
		functions.put(TableFunction.RepeatSeriesExtrapolation.class, Repeat.class);
		functions.put(TableFunction.LinearExtrapolation.class, Linear.class);
		functions.put(TableFunction.PolynomialExtrapolation.class, Polynomial.class);
		functions.put(TableFunction.SplineExtrapolation.class, Spline.class);
		functions.put(TableFunction.StepExtrapolation.class, Step.class);
	}

	public Interpolation(TableFunction.Interpolation interpolation, TableFunction.Extrapolation extrapolation, PointSet points) {
		this.interpolation = functionFor(interpolation, points);
		this.extrapolation = functionFor(extrapolation, points);
		this.points = points;
	}

	private Function functionFor(TableFunction.Interpolation interpolation, PointSet points) {
		return instantiateFunction(interpolation.getClass(), points);
	}

	private Function functionFor(TableFunction.Extrapolation extrapolation, PointSet points) {
		Function function = instantiateFunction(extrapolation.getClass(), points);
		if(function != null && extrapolation.getClass() == TableFunction.CustomExtrapolation.class)
			((Custom) function).function(() -> extrapolation.core$().as(TableFunction.CustomExtrapolation.class).extrapolationFunction());
		return function;
	}

	private Function instantiateFunction(Class<?> interpolation, PointSet points) {
		try {
			Function function = functions.get(interpolation).newInstance();
			function.set(points);
			return function;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public double y(double x) {
		return points.isInRange(x) ?
				interpolation.y(x) :
				extrapolation.y(x);
	}
}
