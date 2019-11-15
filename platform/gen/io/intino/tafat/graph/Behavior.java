package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public abstract class Behavior  extends io.intino.tara.magritte.Layer {
	protected java.lang.String implementation;
	protected int step;
	protected java.util.List<io.intino.tafat.graph.Rule> ruleList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.TableFunction> tableFunctionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Implementation> implementationList = new java.util.ArrayList<>();

	public Behavior(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String implementation() {
		return implementation;
	}

	public int step() {
		return step;
	}

	public Behavior implementation(java.lang.String value) {
		this.implementation = value;
		return (Behavior) this;
	}

	public Behavior step(int value) {
		this.step = value;
		return (Behavior) this;
	}

	public java.util.List<io.intino.tafat.graph.Rule> ruleList() {
		return java.util.Collections.unmodifiableList(ruleList);
	}

	public io.intino.tafat.graph.Rule rule(int index) {
		return ruleList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Rule> ruleList(java.util.function.Predicate<io.intino.tafat.graph.Rule> predicate) {
		return ruleList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.TableFunction> tableFunctionList() {
		return java.util.Collections.unmodifiableList(tableFunctionList);
	}

	public io.intino.tafat.graph.TableFunction tableFunction(int index) {
		return tableFunctionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.TableFunction> tableFunctionList(java.util.function.Predicate<io.intino.tafat.graph.TableFunction> predicate) {
		return tableFunctionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Implementation> implementationList() {
		return java.util.Collections.unmodifiableList(implementationList);
	}

	public io.intino.tafat.graph.Implementation implementation(int index) {
		return implementationList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Implementation> implementationList(java.util.function.Predicate<io.intino.tafat.graph.Implementation> predicate) {
		return implementationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(ruleList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(tableFunctionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(implementationList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("implementation", new java.util.ArrayList(java.util.Collections.singletonList(this.implementation)));
		map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this.step)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Rule")) this.ruleList.add(node.as(io.intino.tafat.graph.Rule.class));
		if (node.is("TableFunction")) this.tableFunctionList.add(node.as(io.intino.tafat.graph.TableFunction.class));
		if (node.is("Implementation")) this.implementationList.add(node.as(io.intino.tafat.graph.Implementation.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Rule")) this.ruleList.remove(node.as(io.intino.tafat.graph.Rule.class));
	        if (node.is("TableFunction")) this.tableFunctionList.remove(node.as(io.intino.tafat.graph.TableFunction.class));
	        if (node.is("Implementation")) this.implementationList.remove(node.as(io.intino.tafat.graph.Implementation.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("implementation")) this.implementation = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("step")) this.step = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("implementation")) this.implementation = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("step")) this.step = (java.lang.Integer) values.get(0);
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

		public io.intino.tafat.graph.Implementation implementation() {
		    io.intino.tafat.graph.Implementation newElement = core$().graph().concept(io.intino.tafat.graph.Implementation.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Implementation.class);

		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void implementation(java.util.function.Predicate<io.intino.tafat.graph.Implementation> filter) {
			new java.util.ArrayList<>(implementationList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}



	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}