package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class CsvOutput extends io.intino.tafat.graph.Output implements io.intino.tara.magritte.tags.Terminal {
	protected java.lang.String path;
	protected io.intino.tafat.graph.rules.TimeScale timeScale;
	protected java.util.List<io.intino.tafat.graph.CsvOutput.Column> columnList = new java.util.ArrayList<>();

	public CsvOutput(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String path() {
		return path;
	}

	public io.intino.tafat.graph.rules.TimeScale timeScale() {
		return timeScale;
	}

	public CsvOutput path(java.lang.String value) {
		this.path = value;
		return (CsvOutput) this;
	}

	public java.util.List<io.intino.tafat.graph.CsvOutput.Column> columnList() {
		return java.util.Collections.unmodifiableList(columnList);
	}

	public io.intino.tafat.graph.CsvOutput.Column column(int index) {
		return columnList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.CsvOutput.Column> columnList(java.util.function.Predicate<io.intino.tafat.graph.CsvOutput.Column> predicate) {
		return columnList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(columnList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
		map.put("timeScale", new java.util.ArrayList(java.util.Collections.singletonList(this.timeScale)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("CsvOutput$Column")) this.columnList.add(node.as(io.intino.tafat.graph.CsvOutput.Column.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("CsvOutput$Column")) this.columnList.remove(node.as(io.intino.tafat.graph.CsvOutput.Column.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("path")) this.path = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("timeScale")) this.timeScale = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.TimeScale.class, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("path")) this.path = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("timeScale")) this.timeScale = (io.intino.tafat.graph.rules.TimeScale) values.get(0);
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

		public io.intino.tafat.graph.CsvOutput.Column column(java.lang.String name, io.intino.tara.magritte.Expression<java.lang.Double> value) {
		    io.intino.tafat.graph.CsvOutput.Column newElement = core$().graph().concept(io.intino.tafat.graph.CsvOutput.Column.class).createNode(this.name, core$()).as(io.intino.tafat.graph.CsvOutput.Column.class);
			newElement.core$().set(newElement, "name", java.util.Collections.singletonList(name));
			newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void column(java.util.function.Predicate<io.intino.tafat.graph.CsvOutput.Column> filter) {
			new java.util.ArrayList<>(columnList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Column  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String name;
		protected io.intino.tara.magritte.Expression<java.lang.Double> value;

		public Column(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String name() {
			return name;
		}

		public Double value() {
			return value.value();
		}

		public Column name(java.lang.String value) {
			this.name = value;
			return (Column) this;
		}

		public Column value(io.intino.tara.magritte.Expression<java.lang.Double> value) {
			this.value = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Column) this;
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