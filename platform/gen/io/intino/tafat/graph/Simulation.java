package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Simulation  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected java.time.Instant from;
	protected java.time.Instant to;
	protected int seed;
	protected int times;
	protected java.util.List<io.intino.tafat.graph.Assertion> assertionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Trace> traceList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Stop> stopList = new java.util.ArrayList<>();

	public Simulation(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.time.Instant from() {
		return from;
	}

	public java.time.Instant to() {
		return to;
	}

	public int seed() {
		return seed;
	}

	public int times() {
		return times;
	}

	public Simulation seed(int value) {
		this.seed = value;
		return (Simulation) this;
	}

	public Simulation times(int value) {
		this.times = value;
		return (Simulation) this;
	}

	public java.util.List<io.intino.tafat.graph.Assertion> assertionList() {
		return java.util.Collections.unmodifiableList(assertionList);
	}

	public io.intino.tafat.graph.Assertion assertion(int index) {
		return assertionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Assertion> assertionList(java.util.function.Predicate<io.intino.tafat.graph.Assertion> predicate) {
		return assertionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Trace> traceList() {
		return java.util.Collections.unmodifiableList(traceList);
	}

	public io.intino.tafat.graph.Trace trace(int index) {
		return traceList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Trace> traceList(java.util.function.Predicate<io.intino.tafat.graph.Trace> predicate) {
		return traceList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Stop> stopList() {
		return java.util.Collections.unmodifiableList(stopList);
	}

	public io.intino.tafat.graph.Stop stop(int index) {
		return stopList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Stop> stopList(java.util.function.Predicate<io.intino.tafat.graph.Stop> predicate) {
		return stopList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(assertionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(traceList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(stopList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("from", new java.util.ArrayList(java.util.Collections.singletonList(this.from)));
		map.put("to", new java.util.ArrayList(java.util.Collections.singletonList(this.to)));
		map.put("seed", new java.util.ArrayList(java.util.Collections.singletonList(this.seed)));
		map.put("times", new java.util.ArrayList(java.util.Collections.singletonList(this.times)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Assertion")) this.assertionList.add(node.as(io.intino.tafat.graph.Assertion.class));
		if (node.is("Trace")) this.traceList.add(node.as(io.intino.tafat.graph.Trace.class));
		if (node.is("Stop")) this.stopList.add(node.as(io.intino.tafat.graph.Stop.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Assertion")) this.assertionList.remove(node.as(io.intino.tafat.graph.Assertion.class));
	        if (node.is("Trace")) this.traceList.remove(node.as(io.intino.tafat.graph.Trace.class));
	        if (node.is("Stop")) this.stopList.remove(node.as(io.intino.tafat.graph.Stop.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("from")) this.from = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("to")) this.to = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("seed")) this.seed = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("times")) this.times = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("from")) this.from = (java.time.Instant) values.get(0);
		else if (name.equalsIgnoreCase("to")) this.to = (java.time.Instant) values.get(0);
		else if (name.equalsIgnoreCase("seed")) this.seed = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("times")) this.times = (java.lang.Integer) values.get(0);
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

		public io.intino.tafat.graph.Assertion assertion(io.intino.tafat.graph.functions.Thing that, io.intino.tafat.graph.functions.Thing shouldBe, java.time.Instant at) {
		    io.intino.tafat.graph.Assertion newElement = core$().graph().concept(io.intino.tafat.graph.Assertion.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Assertion.class);
			newElement.core$().set(newElement, "that", java.util.Collections.singletonList(that));
			newElement.core$().set(newElement, "shouldBe", java.util.Collections.singletonList(shouldBe));
			newElement.core$().set(newElement, "at", java.util.Collections.singletonList(at));
		    return newElement;
		}

		public io.intino.tafat.graph.Trace trace(io.intino.tafat.graph.functions.Trace print) {
		    io.intino.tafat.graph.Trace newElement = core$().graph().concept(io.intino.tafat.graph.Trace.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Trace.class);
			newElement.core$().set(newElement, "print", java.util.Collections.singletonList(print));
		    return newElement;
		}

		public io.intino.tafat.graph.Stop stop(io.intino.tara.magritte.Expression<java.lang.Boolean> when) {
		    io.intino.tafat.graph.Stop newElement = core$().graph().concept(io.intino.tafat.graph.Stop.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Stop.class);
			newElement.core$().set(newElement, "when", java.util.Collections.singletonList(when));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void assertion(java.util.function.Predicate<io.intino.tafat.graph.Assertion> filter) {
			new java.util.ArrayList<>(assertionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void trace(java.util.function.Predicate<io.intino.tafat.graph.Trace> filter) {
			new java.util.ArrayList<>(traceList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void stop(java.util.function.Predicate<io.intino.tafat.graph.Stop> filter) {
			new java.util.ArrayList<>(stopList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}



	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}