package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class LogOutput extends io.intino.tafat.graph.Output implements io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.rules.TimeScale timeScale;
	protected java.lang.String format;
	protected int timeout;
	protected io.intino.tara.magritte.Expression<java.lang.Boolean> checkStep;
	protected java.util.List<io.intino.tafat.graph.LogOutput.Line> lineList = new java.util.ArrayList<>();

	public LogOutput(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public io.intino.tafat.graph.rules.TimeScale timeScale() {
		return timeScale;
	}

	public java.lang.String format() {
		return format;
	}

	public int timeout() {
		return timeout;
	}

	public Boolean checkStep() {
		return checkStep.value();
	}

	public LogOutput format(java.lang.String value) {
		this.format = value;
		return (LogOutput) this;
	}

	public LogOutput timeout(int value) {
		this.timeout = value;
		return (LogOutput) this;
	}

	public LogOutput checkStep(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
		this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (LogOutput) this;
	}

	public java.util.List<io.intino.tafat.graph.LogOutput.Line> lineList() {
		return java.util.Collections.unmodifiableList(lineList);
	}

	public io.intino.tafat.graph.LogOutput.Line line(int index) {
		return lineList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.LogOutput.Line> lineList(java.util.function.Predicate<io.intino.tafat.graph.LogOutput.Line> predicate) {
		return lineList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(lineList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("timeScale", new java.util.ArrayList(java.util.Collections.singletonList(this.timeScale)));
		map.put("format", new java.util.ArrayList(java.util.Collections.singletonList(this.format)));
		map.put("timeout", new java.util.ArrayList(java.util.Collections.singletonList(this.timeout)));
		map.put("checkStep", new java.util.ArrayList(java.util.Collections.singletonList(this.checkStep)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("LogOutput$Line")) this.lineList.add(node.as(io.intino.tafat.graph.LogOutput.Line.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("LogOutput$Line")) this.lineList.remove(node.as(io.intino.tafat.graph.LogOutput.Line.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("timeScale")) this.timeScale = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.TimeScale.class, this).get(0);
		else if (name.equalsIgnoreCase("format")) this.format = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("timeScale")) this.timeScale = (io.intino.tafat.graph.rules.TimeScale) values.get(0);
		else if (name.equalsIgnoreCase("format")) this.format = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("timeout")) this.timeout = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends io.intino.tafat.graph.Output.Create {


		public Create(java.lang.String name) {
			super(name);
		}

		public io.intino.tafat.graph.LogOutput.Line line(java.lang.String name, io.intino.tara.magritte.Expression<java.lang.Double> value) {
		    io.intino.tafat.graph.LogOutput.Line newElement = core$().graph().concept(io.intino.tafat.graph.LogOutput.Line.class).createNode(this.name, core$()).as(io.intino.tafat.graph.LogOutput.Line.class);
			newElement.core$().set(newElement, "name", java.util.Collections.singletonList(name));
			newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void line(java.util.function.Predicate<io.intino.tafat.graph.LogOutput.Line> filter) {
			new java.util.ArrayList<>(lineList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Line  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String name;
		protected io.intino.tara.magritte.Expression<java.lang.Double> value;

		public Line(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String name() {
			return name;
		}

		public Double value() {
			return value.value();
		}

		public Line name(java.lang.String value) {
			this.name = value;
			return (Line) this;
		}

		public Line value(io.intino.tara.magritte.Expression<java.lang.Double> value) {
			this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Line) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("name", new java.util.ArrayList(java.util.Collections.singletonList(this.name)));
			map.put("value", new java.util.ArrayList(java.util.Collections.singletonList(this.value)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("name")) this.name = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("name")) this.name = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}