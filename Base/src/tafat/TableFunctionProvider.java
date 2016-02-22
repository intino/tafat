package tafat;

import tafat.functions.Calculate;
import tafat.pointset.Point;
import tafat.pointset.PointSet;
import tafat.tablefunction.Dimension;
import tafat.tablefunction.Interpolation;
import tara.magritte.Layer;
import tara.magritte.NativeCode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class TableFunctionProvider {

	List<Dimension> dimensions = new ArrayList<>();
	private TableFunction tableFunction;

	public TableFunctionProvider(TableFunction tableFunction) {
		this.tableFunction = tableFunction;
		List<Record> records = recordsOf(tableFunction.data());
		dimensions.addAll(dimensionsOf(records));
	}

	private List<Record> recordsOf(String tableContent) {
		String[] content = tableContent.replace("\r","").replace("\t", "").split("\n");
		if(content.length < 2) return new ArrayList<>();
		return asList(content).subList(2, content.length).stream()
				.filter(line -> !line.isEmpty())
				.map(line -> new Record(numbersAt(line.split(";"))))
				.collect(toList());
	}

	private List<Double> numbersAt(String[] elements) {
		return asList(elements).stream()
				.map(Double::parseDouble)
				.collect(toList());
	}

	private List<Dimension> dimensionsOf(List<Record> records, double... inputs) {
		if(records.get(0).numbers.size() - inputs.length <= 2)
			return singletonList(buildDimensionAt(records, inputs));
		List<Dimension> result = new ArrayList<>();
		choicesAt(records, inputs).forEach(c -> result.add(new Dimension(c, dimensionsOf(records, inputOf(inputs, c)))));
		return result;
	}

	private Set<Double> choicesAt(List<Record> records, double[] inputs) {
		return filterRecords(records, inputs).stream()
				.map(r -> r.numbers.get(inputs.length)).collect(toCollection(LinkedHashSet::new));
	}

	private List<Record> filterRecords(List<Record> records, double[] inputs) {
		List<Record> result = filterRecords(records, 0, NaN);
		for (int i = 0; i < inputs.length; i++) result = filterRecords(result, i, inputs[i]);
		return result;
	}

	private List<Record> filterRecords(List<Record> records, int columnIndex, double filter){
		List<Record> result = new ArrayList<>();
		range(0, records.size())
				.filter(i -> isNaN(filter) || records.get(i).numbers.get(columnIndex) == filter)
				.forEach(i -> result.add(records.get(i)));
		return result;
	}

	private Dimension buildDimensionAt(List<Record> records, double... inputs) {
		return new Dimension(pointSetAt(records, inputs));
	}

	private Interpolation pointSetAt(List<Record> records, double... inputs) {
		return new Interpolation(tableFunction.interpolation(), tableFunction.extrapolation(), pointsAt(records, inputs));
	}

	private PointSet pointsAt(List<Record> records, double[] inputs) {
		return new PointSet(filterRecords(records, inputs).stream()
				.map(r -> new Point(r.numbers.get(inputs.length), r.numbers.get(inputs.length + 1))).collect(toList()));
	}

	private double get(double... inputs) {
		return inputs.length == 1 ? dimensions.get(0).get(inputs[0])
				: findDimension(inputs).get(inputs[inputs.length - 1]);
	}

	private Dimension findDimension(double... inputs) {
		Dimension[] result = new Dimension[] {dimensionWith(dimensions, inputs[0])};
		range(1, inputs.length - 1).forEach(i -> result[0] = dimensionWith(result[0].dimensions(), inputs[i]));
		return result[0].dimensions().get(0);
	}

	private Dimension dimensionWith(List<Dimension> dimensions, double input) {
		Dimension closest = dimensions.get(0);
		double distance = abs(closest.coordinate() - input);
		for (Dimension dimension : dimensions) {
			if(abs(dimension.coordinate() - input) < distance){
				closest = dimension;
				distance = abs(dimension.coordinate() - input);
			}
		}
		return closest;
	}

	public NativeCode getter() {
		return new Getter();
	}

	private double[] inputOf(double[] inputs, double newInput) {
		double[] result = new double[inputs.length + 1];
		range(0, inputs.length).forEach(i -> result[i] = inputs[i]);
		result[result.length - 1] = newInput;
		return result;
	}

	private class Getter implements NativeCode, Calculate {

		@Override
		public double calculate(double... inputs) {
			return get(inputs);
		}

		@Override
		public void $(Layer layer) {
		}

		@Override
		public Class<? extends Layer> $Class() {
			return TableFunction.class;
		}

	}

	private static class Record{

		private List<Double> numbers;

		Record(List<Double> numbers){
			this.numbers = numbers;
		}

	}

}