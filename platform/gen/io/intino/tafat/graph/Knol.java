package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Knol  extends io.intino.tara.magritte.Layer {
	protected java.util.List<io.intino.tafat.graph.Knol> knolList = new java.util.ArrayList<>();

	public Knol(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<io.intino.tafat.graph.Knol> knolList() {
		return java.util.Collections.unmodifiableList(knolList);
	}

	public io.intino.tafat.graph.Knol knol(int index) {
		return knolList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Knol> knolList(java.util.function.Predicate<io.intino.tafat.graph.Knol> predicate) {
		return knolList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(knolList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Knol")) this.knolList.add(node.as(io.intino.tafat.graph.Knol.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Knol")) this.knolList.remove(node.as(io.intino.tafat.graph.Knol.class));
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

		public io.intino.tafat.graph.Knol knol() {
		    io.intino.tafat.graph.Knol newElement = core$().graph().concept(io.intino.tafat.graph.Knol.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Knol.class);

		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void knol(java.util.function.Predicate<io.intino.tafat.graph.Knol> filter) {
			new java.util.ArrayList<>(knolList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}



	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}