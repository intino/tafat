package io.intino.tafat.engine.tablefunction;

import io.intino.tafat.TableFunction;
import io.intino.tafat.engine.pointset.Point;
import io.intino.tafat.engine.pointset.PointSet;
import tara.magritte.Layer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.abs;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class TableFunctionProvider {

    List<Dimension> dimensions = new ArrayList<>();
    private TableFunction tableFunction;

    public TableFunctionProvider(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
        List<Record> records = recordsOf(tableFunction.dataList());
        new ArrayList<>(tableFunction.dataList()).stream().forEach(Layer::delete);
        dimensions.addAll(dimensionsOf(records));
    }

    private List<Record> recordsOf(List<TableFunction.Data> tableContent) {
        return tableContent.stream()
                .map(data -> new Record(numbersAt(data)))
                .collect(toList());
    }

    private List<Double> numbersAt(TableFunction.Data elements) {
        ArrayList<Double> numbers = new ArrayList<>(elements.input());
        numbers.add(elements.output());
        return numbers;
    }

    private List<Dimension> dimensionsOf(List<Record> records, double... inputs) {
        if (records.get(0).numbers.size() - inputs.length <= 2)
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

    private List<Record> filterRecords(List<Record> records, int columnIndex, double filter) {
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

    public double get(double... inputs) {
        return inputs.length == 1 ? dimensions.get(0).get(inputs[0])
                : findDimension(inputs).get(inputs[inputs.length - 1]);
    }

    private Dimension findDimension(double... inputs) {
        Dimension[] result = new Dimension[]{dimensionWith(dimensions, inputs[0])};
        for (int i = 1; i < inputs.length - 1; i++) result[0] = dimensionWith(result[0].dimensions(), inputs[i]);
        return result[0].dimensions().get(0);
    }

    private Dimension dimensionWith(List<Dimension> dimensions, double input) {
        Dimension closest = dimensions.get(0);
        double distance = abs(closest.coordinate() - input);
        for (Dimension dimension : dimensions) {
            if (abs(dimension.coordinate() - input) < distance) {
                closest = dimension;
                distance = abs(dimension.coordinate() - input);
            }
        }
        return closest;
    }

    private double[] inputOf(double[] inputs, double newInput) {
        double[] result = new double[inputs.length + 1];
        range(0, inputs.length).forEach(i -> result[i] = inputs[i]);
        result[result.length - 1] = newInput;
        return result;
    }

    private static class Record {

        private List<Double> numbers;

        Record(List<Double> numbers) {
            this.numbers = numbers;
        }

    }

}
