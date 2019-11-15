package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class TableFunction  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Calculate get;
	protected io.intino.tafat.engine.tablefunction.TableFunctionProvider provider;
	protected io.intino.tafat.graph.TableFunction.Interpolation interpolation;
	protected io.intino.tafat.graph.TableFunction.NoneInterpolation noneInterpolation;
	protected io.intino.tafat.graph.TableFunction.LinearInterpolation linearInterpolation;
	protected io.intino.tafat.graph.TableFunction.PolynomialInterpolation polynomialInterpolation;
	protected io.intino.tafat.graph.TableFunction.SplineInterpolation splineInterpolation;
	protected io.intino.tafat.graph.TableFunction.StepInterpolation stepInterpolation;
	protected io.intino.tafat.graph.TableFunction.Extrapolation extrapolation;
	protected io.intino.tafat.graph.TableFunction.NoneExtrapolation noneExtrapolation;
	protected io.intino.tafat.graph.TableFunction.CustomExtrapolation customExtrapolation;
	protected io.intino.tafat.graph.TableFunction.NearestPointExtrapolation nearestPointExtrapolation;
	protected io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation repeatSeriesExtrapolation;
	protected io.intino.tafat.graph.TableFunction.LinearExtrapolation linearExtrapolation;
	protected io.intino.tafat.graph.TableFunction.PolynomialExtrapolation polynomialExtrapolation;
	protected io.intino.tafat.graph.TableFunction.SplineExtrapolation splineExtrapolation;
	protected io.intino.tafat.graph.TableFunction.StepExtrapolation stepExtrapolation;
	protected java.util.List<io.intino.tafat.graph.TableFunction.Data> dataList = new java.util.ArrayList<>();

	public TableFunction(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public double get(double... inputs) {
		return get.calculate(inputs);
	}

	public io.intino.tafat.engine.tablefunction.TableFunctionProvider provider() {
		return provider;
	}

	public TableFunction get(io.intino.tafat.graph.functions.Calculate value) {
		this.get = io.intino.tara.magritte.loaders.FunctionLoader.load(get, this, io.intino.tafat.graph.functions.Calculate.class);
		return (TableFunction) this;
	}

	public TableFunction provider(io.intino.tafat.engine.tablefunction.TableFunctionProvider value) {
		this.provider = value;
		return (TableFunction) this;
	}

	public io.intino.tafat.graph.TableFunction.Interpolation interpolation() {
		return interpolation;
	}

	public io.intino.tafat.graph.TableFunction.NoneInterpolation noneInterpolation() {
		return noneInterpolation;
	}

	public io.intino.tafat.graph.TableFunction.LinearInterpolation linearInterpolation() {
		return linearInterpolation;
	}

	public io.intino.tafat.graph.TableFunction.PolynomialInterpolation polynomialInterpolation() {
		return polynomialInterpolation;
	}

	public io.intino.tafat.graph.TableFunction.SplineInterpolation splineInterpolation() {
		return splineInterpolation;
	}

	public io.intino.tafat.graph.TableFunction.StepInterpolation stepInterpolation() {
		return stepInterpolation;
	}

	public io.intino.tafat.graph.TableFunction.Extrapolation extrapolation() {
		return extrapolation;
	}

	public io.intino.tafat.graph.TableFunction.NoneExtrapolation noneExtrapolation() {
		return noneExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.CustomExtrapolation customExtrapolation() {
		return customExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.NearestPointExtrapolation nearestPointExtrapolation() {
		return nearestPointExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation repeatSeriesExtrapolation() {
		return repeatSeriesExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.LinearExtrapolation linearExtrapolation() {
		return linearExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.PolynomialExtrapolation polynomialExtrapolation() {
		return polynomialExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.SplineExtrapolation splineExtrapolation() {
		return splineExtrapolation;
	}

	public io.intino.tafat.graph.TableFunction.StepExtrapolation stepExtrapolation() {
		return stepExtrapolation;
	}

	public java.util.List<io.intino.tafat.graph.TableFunction.Data> dataList() {
		return java.util.Collections.unmodifiableList(dataList);
	}

	public io.intino.tafat.graph.TableFunction.Data data(int index) {
		return dataList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.TableFunction.Data> dataList(java.util.function.Predicate<io.intino.tafat.graph.TableFunction.Data> predicate) {
		return dataList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		if (interpolation != null) components.add(this.interpolation.core$());
		if (noneInterpolation != null) components.add(this.noneInterpolation.core$());
		if (linearInterpolation != null) components.add(this.linearInterpolation.core$());
		if (polynomialInterpolation != null) components.add(this.polynomialInterpolation.core$());
		if (splineInterpolation != null) components.add(this.splineInterpolation.core$());
		if (stepInterpolation != null) components.add(this.stepInterpolation.core$());
		if (extrapolation != null) components.add(this.extrapolation.core$());
		if (noneExtrapolation != null) components.add(this.noneExtrapolation.core$());
		if (customExtrapolation != null) components.add(this.customExtrapolation.core$());
		if (nearestPointExtrapolation != null) components.add(this.nearestPointExtrapolation.core$());
		if (repeatSeriesExtrapolation != null) components.add(this.repeatSeriesExtrapolation.core$());
		if (linearExtrapolation != null) components.add(this.linearExtrapolation.core$());
		if (polynomialExtrapolation != null) components.add(this.polynomialExtrapolation.core$());
		if (splineExtrapolation != null) components.add(this.splineExtrapolation.core$());
		if (stepExtrapolation != null) components.add(this.stepExtrapolation.core$());
		new java.util.ArrayList<>(dataList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("get", this.get != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.get)) : java.util.Collections.emptyList());
		map.put("provider", new java.util.ArrayList(java.util.Collections.singletonList(this.provider)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("TableFunction$Interpolation")) this.interpolation = node.as(io.intino.tafat.graph.TableFunction.Interpolation.class);
		if (node.is("TableFunction$NoneInterpolation")) this.noneInterpolation = node.as(io.intino.tafat.graph.TableFunction.NoneInterpolation.class);
		if (node.is("TableFunction$LinearInterpolation")) this.linearInterpolation = node.as(io.intino.tafat.graph.TableFunction.LinearInterpolation.class);
		if (node.is("TableFunction$PolynomialInterpolation")) this.polynomialInterpolation = node.as(io.intino.tafat.graph.TableFunction.PolynomialInterpolation.class);
		if (node.is("TableFunction$SplineInterpolation")) this.splineInterpolation = node.as(io.intino.tafat.graph.TableFunction.SplineInterpolation.class);
		if (node.is("TableFunction$StepInterpolation")) this.stepInterpolation = node.as(io.intino.tafat.graph.TableFunction.StepInterpolation.class);
		if (node.is("TableFunction$Extrapolation")) this.extrapolation = node.as(io.intino.tafat.graph.TableFunction.Extrapolation.class);
		if (node.is("TableFunction$NoneExtrapolation")) this.noneExtrapolation = node.as(io.intino.tafat.graph.TableFunction.NoneExtrapolation.class);
		if (node.is("TableFunction$CustomExtrapolation")) this.customExtrapolation = node.as(io.intino.tafat.graph.TableFunction.CustomExtrapolation.class);
		if (node.is("TableFunction$NearestPointExtrapolation")) this.nearestPointExtrapolation = node.as(io.intino.tafat.graph.TableFunction.NearestPointExtrapolation.class);
		if (node.is("TableFunction$RepeatSeriesExtrapolation")) this.repeatSeriesExtrapolation = node.as(io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation.class);
		if (node.is("TableFunction$LinearExtrapolation")) this.linearExtrapolation = node.as(io.intino.tafat.graph.TableFunction.LinearExtrapolation.class);
		if (node.is("TableFunction$PolynomialExtrapolation")) this.polynomialExtrapolation = node.as(io.intino.tafat.graph.TableFunction.PolynomialExtrapolation.class);
		if (node.is("TableFunction$SplineExtrapolation")) this.splineExtrapolation = node.as(io.intino.tafat.graph.TableFunction.SplineExtrapolation.class);
		if (node.is("TableFunction$StepExtrapolation")) this.stepExtrapolation = node.as(io.intino.tafat.graph.TableFunction.StepExtrapolation.class);
		if (node.is("TableFunction$Data")) this.dataList.add(node.as(io.intino.tafat.graph.TableFunction.Data.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("TableFunction$Interpolation")) this.interpolation = null;
	        if (node.is("TableFunction$NoneInterpolation")) this.noneInterpolation = null;
	        if (node.is("TableFunction$LinearInterpolation")) this.linearInterpolation = null;
	        if (node.is("TableFunction$PolynomialInterpolation")) this.polynomialInterpolation = null;
	        if (node.is("TableFunction$SplineInterpolation")) this.splineInterpolation = null;
	        if (node.is("TableFunction$StepInterpolation")) this.stepInterpolation = null;
	        if (node.is("TableFunction$Extrapolation")) this.extrapolation = null;
	        if (node.is("TableFunction$NoneExtrapolation")) this.noneExtrapolation = null;
	        if (node.is("TableFunction$CustomExtrapolation")) this.customExtrapolation = null;
	        if (node.is("TableFunction$NearestPointExtrapolation")) this.nearestPointExtrapolation = null;
	        if (node.is("TableFunction$RepeatSeriesExtrapolation")) this.repeatSeriesExtrapolation = null;
	        if (node.is("TableFunction$LinearExtrapolation")) this.linearExtrapolation = null;
	        if (node.is("TableFunction$PolynomialExtrapolation")) this.polynomialExtrapolation = null;
	        if (node.is("TableFunction$SplineExtrapolation")) this.splineExtrapolation = null;
	        if (node.is("TableFunction$StepExtrapolation")) this.stepExtrapolation = null;
	        if (node.is("TableFunction$Data")) this.dataList.remove(node.as(io.intino.tafat.graph.TableFunction.Data.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("get")) this.get = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Calculate.class).get(0);
		else if (name.equalsIgnoreCase("provider")) this.provider = io.intino.tara.magritte.loaders.ObjectLoader.load(values,io.intino.tafat.engine.tablefunction.TableFunctionProvider.class, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("get")) this.get = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Calculate.class);
		else if (name.equalsIgnoreCase("provider")) this.provider = (io.intino.tafat.engine.tablefunction.TableFunctionProvider) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create  {
		protected final java.lang.String name;

		public Create(java.lang.String name) {
			this.name = name;
		}

		public io.intino.tafat.graph.TableFunction.NoneInterpolation noneInterpolation() {
		    io.intino.tafat.graph.TableFunction.NoneInterpolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.NoneInterpolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.NoneInterpolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.LinearInterpolation linearInterpolation() {
		    io.intino.tafat.graph.TableFunction.LinearInterpolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.LinearInterpolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.LinearInterpolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.PolynomialInterpolation polynomialInterpolation() {
		    io.intino.tafat.graph.TableFunction.PolynomialInterpolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.PolynomialInterpolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.PolynomialInterpolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.SplineInterpolation splineInterpolation() {
		    io.intino.tafat.graph.TableFunction.SplineInterpolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.SplineInterpolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.SplineInterpolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.StepInterpolation stepInterpolation() {
		    io.intino.tafat.graph.TableFunction.StepInterpolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.StepInterpolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.StepInterpolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.NoneExtrapolation noneExtrapolation() {
		    io.intino.tafat.graph.TableFunction.NoneExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.NoneExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.NoneExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.CustomExtrapolation customExtrapolation(io.intino.tafat.graph.functions.Function extrapolationFunction) {
		    io.intino.tafat.graph.TableFunction.CustomExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.CustomExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.CustomExtrapolation.class);
			newElement.core$().set(newElement, "extrapolationFunction", java.util.Collections.singletonList(extrapolationFunction));
		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.NearestPointExtrapolation nearestPointExtrapolation() {
		    io.intino.tafat.graph.TableFunction.NearestPointExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.NearestPointExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.NearestPointExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation repeatSeriesExtrapolation() {
		    io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.RepeatSeriesExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.LinearExtrapolation linearExtrapolation() {
		    io.intino.tafat.graph.TableFunction.LinearExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.LinearExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.LinearExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.PolynomialExtrapolation polynomialExtrapolation() {
		    io.intino.tafat.graph.TableFunction.PolynomialExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.PolynomialExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.PolynomialExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.SplineExtrapolation splineExtrapolation() {
		    io.intino.tafat.graph.TableFunction.SplineExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.SplineExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.SplineExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.StepExtrapolation stepExtrapolation() {
		    io.intino.tafat.graph.TableFunction.StepExtrapolation newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.StepExtrapolation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.StepExtrapolation.class);

		    return newElement;
		}

		public io.intino.tafat.graph.TableFunction.Data data(java.util.List<java.lang.Double> input, double output) {
		    io.intino.tafat.graph.TableFunction.Data newElement = core$().graph().concept(io.intino.tafat.graph.TableFunction.Data.class).createNode(this.name, core$()).as(io.intino.tafat.graph.TableFunction.Data.class);
			newElement.core$().set(newElement, "input", input);
			newElement.core$().set(newElement, "output", java.util.Collections.singletonList(output));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void data(java.util.function.Predicate<io.intino.tafat.graph.TableFunction.Data> filter) {
			new java.util.ArrayList<>(dataList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static abstract class Interpolation  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {

		public Interpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create  {
			protected final java.lang.String name;

			public Create(java.lang.String name) {
				this.name = name;
			}



		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class NoneInterpolation extends io.intino.tafat.graph.TableFunction.Interpolation implements io.intino.tara.magritte.tags.Terminal {

		public NoneInterpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class LinearInterpolation extends io.intino.tafat.graph.TableFunction.Interpolation implements io.intino.tara.magritte.tags.Terminal {

		public LinearInterpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class PolynomialInterpolation extends io.intino.tafat.graph.TableFunction.Interpolation implements io.intino.tara.magritte.tags.Terminal {

		public PolynomialInterpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class SplineInterpolation extends io.intino.tafat.graph.TableFunction.Interpolation implements io.intino.tara.magritte.tags.Terminal {

		public SplineInterpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class StepInterpolation extends io.intino.tafat.graph.TableFunction.Interpolation implements io.intino.tara.magritte.tags.Terminal {

		public StepInterpolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static abstract class Extrapolation  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {

		public Extrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create  {
			protected final java.lang.String name;

			public Create(java.lang.String name) {
				this.name = name;
			}



		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class NoneExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public NoneExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class CustomExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.Function extrapolationFunction;

		public CustomExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public double extrapolationFunction() {
			return extrapolationFunction.calculate();
		}

		public CustomExtrapolation extrapolationFunction(io.intino.tafat.graph.functions.Function value) {
			this.extrapolationFunction = io.intino.tara.magritte.loaders.FunctionLoader.load(extrapolationFunction, this, io.intino.tafat.graph.functions.Function.class);
			return (CustomExtrapolation) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("extrapolationFunction", this.extrapolationFunction != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.extrapolationFunction)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("extrapolationFunction")) this.extrapolationFunction = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Function.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("extrapolationFunction")) this.extrapolationFunction = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Function.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class NearestPointExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public NearestPointExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class RepeatSeriesExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public RepeatSeriesExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class LinearExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public LinearExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class PolynomialExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public PolynomialExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class SplineExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public SplineExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class StepExtrapolation extends io.intino.tafat.graph.TableFunction.Extrapolation implements io.intino.tara.magritte.tags.Terminal {

		public StepExtrapolation(io.intino.tara.magritte.Node node) {
			super(node);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Data  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.util.List<java.lang.Double> input = new java.util.ArrayList<>();
		protected double output;

		public Data(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.util.List<java.lang.Double> input() {
			return input;
		}

		public java.lang.Double input(int index) {
			return input.get(index);
		}

		public java.util.List<java.lang.Double> input(java.util.function.Predicate<java.lang.Double> predicate) {
			return input().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public double output() {
			return output;
		}

		public Data output(double value) {
			this.output = value;
			return (Data) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("input", this.input);
			map.put("output", new java.util.ArrayList(java.util.Collections.singletonList(this.output)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("input")) this.input = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this);
			else if (name.equalsIgnoreCase("output")) this.output = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("input")) this.input = new java.util.ArrayList<>((java.util.List<java.lang.Double>) values);
			else if (name.equalsIgnoreCase("output")) this.output = (java.lang.Double) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}