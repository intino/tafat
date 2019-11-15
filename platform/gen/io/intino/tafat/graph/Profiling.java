package io.intino.tafat.graph;

import io.intino.tafat.graph.*;
import io.intino.tara.magritte.Graph;
import java.util.Random;

public class Profiling  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected int seed;
	protected java.util.List<io.intino.tafat.graph.Profiling.Profiler> profilerList = new java.util.ArrayList<>();

	public Profiling(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public int seed() {
		return seed;
	}

	public Profiling seed(int value) {
		this.seed = value;
		return (Profiling) this;
	}

	public java.util.List<io.intino.tafat.graph.Profiling.Profiler> profilerList() {
		return java.util.Collections.unmodifiableList(profilerList);
	}

	public io.intino.tafat.graph.Profiling.Profiler profiler(int index) {
		return profilerList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Profiling.Profiler> profilerList(java.util.function.Predicate<io.intino.tafat.graph.Profiling.Profiler> predicate) {
		return profilerList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(profilerList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("seed", new java.util.ArrayList(java.util.Collections.singletonList(this.seed)));
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Profiling$Profiler")) this.profilerList.add(node.as(io.intino.tafat.graph.Profiling.Profiler.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Profiling$Profiler")) this.profilerList.remove(node.as(io.intino.tafat.graph.Profiling.Profiler.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("seed")) this.seed = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("seed")) this.seed = (java.lang.Integer) values.get(0);
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

		public io.intino.tafat.graph.Profiling.Profiler profiler(io.intino.tafat.graph.functions.Profile execute) {
		    io.intino.tafat.graph.Profiling.Profiler newElement = core$().graph().concept(io.intino.tafat.graph.Profiling.Profiler.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Profiling.Profiler.class);
			newElement.core$().set(newElement, "execute", java.util.Collections.singletonList(execute));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void profiler(java.util.function.Predicate<io.intino.tafat.graph.Profiling.Profiler> filter) {
			new java.util.ArrayList<>(profilerList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Profiler  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.Profile execute;

		public Profiler(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void execute(Graph graph, Random random) {
			 execute.execute(graph, random);
		}

		public Profiler execute(io.intino.tafat.graph.functions.Profile value) {
			this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(execute, this, io.intino.tafat.graph.functions.Profile.class);
			return (Profiler) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("execute", this.execute != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.execute)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Profile.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Profile.class);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}