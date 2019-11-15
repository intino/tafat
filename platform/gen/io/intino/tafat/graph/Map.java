package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Map  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Get get;
	protected java.util.List<io.intino.tafat.graph.Map.Entry> entryList = new java.util.ArrayList<>();

	public Map(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public double get(String key) {
		return get.get(key);
	}



	public java.util.List<io.intino.tafat.graph.Map.Entry> entryList() {
		return java.util.Collections.unmodifiableList(entryList);
	}

	public io.intino.tafat.graph.Map.Entry entry(int index) {
		return entryList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Map.Entry> entryList(java.util.function.Predicate<io.intino.tafat.graph.Map.Entry> predicate) {
		return entryList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(entryList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("get", this.get != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.get)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Map$Entry")) this.entryList.add(node.as(io.intino.tafat.graph.Map.Entry.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Map$Entry")) this.entryList.remove(node.as(io.intino.tafat.graph.Map.Entry.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("get")) this.get = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Get.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("get")) this.get = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Get.class);
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

		public io.intino.tafat.graph.Map.Entry entry(java.lang.String key, double value) {
		    io.intino.tafat.graph.Map.Entry newElement = core$().graph().concept(io.intino.tafat.graph.Map.Entry.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Map.Entry.class);
			newElement.core$().set(newElement, "key", java.util.Collections.singletonList(key));
			newElement.core$().set(newElement, "value", java.util.Collections.singletonList(value));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void entry(java.util.function.Predicate<io.intino.tafat.graph.Map.Entry> filter) {
			new java.util.ArrayList<>(entryList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Entry  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.lang.String key;
		protected double value;

		public Entry(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String key() {
			return key;
		}

		public double value() {
			return value;
		}

		public Entry key(java.lang.String value) {
			this.key = value;
			return (Entry) this;
		}

		public Entry value(double value) {
			this.value = value;
			return (Entry) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("key", new java.util.ArrayList(java.util.Collections.singletonList(this.key)));
			map.put("value", new java.util.ArrayList(java.util.Collections.singletonList(this.value)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("key")) this.key = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("key")) this.key = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("value")) this.value = (java.lang.Double) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}