package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Entity  extends io.intino.tara.magritte.Layer {
	protected java.util.List<io.intino.tafat.graph.Entity> entityList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Agent> agentList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Entity.Feature> featureList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Entity.Layer> layerList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Entity.Behavior> behaviorList = new java.util.ArrayList<>();

	public Entity(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<io.intino.tafat.graph.Entity> entityList() {
		return java.util.Collections.unmodifiableList(entityList);
	}

	public io.intino.tafat.graph.Entity entity(int index) {
		return entityList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Entity> entityList(java.util.function.Predicate<io.intino.tafat.graph.Entity> predicate) {
		return entityList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Agent> agentList() {
		return java.util.Collections.unmodifiableList(agentList);
	}

	public io.intino.tafat.graph.Agent agent(int index) {
		return agentList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Agent> agentList(java.util.function.Predicate<io.intino.tafat.graph.Agent> predicate) {
		return agentList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Entity.Feature> featureList() {
		return java.util.Collections.unmodifiableList(featureList);
	}

	public io.intino.tafat.graph.Entity.Feature feature(int index) {
		return featureList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Entity.Feature> featureList(java.util.function.Predicate<io.intino.tafat.graph.Entity.Feature> predicate) {
		return featureList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Entity.Layer> layerList() {
		return java.util.Collections.unmodifiableList(layerList);
	}

	public io.intino.tafat.graph.Entity.Layer layer(int index) {
		return layerList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Entity.Layer> layerList(java.util.function.Predicate<io.intino.tafat.graph.Entity.Layer> predicate) {
		return layerList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Entity.Behavior> behaviorList() {
		return java.util.Collections.unmodifiableList(behaviorList);
	}

	public io.intino.tafat.graph.Entity.Behavior behavior(int index) {
		return behaviorList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Entity.Behavior> behaviorList(java.util.function.Predicate<io.intino.tafat.graph.Entity.Behavior> predicate) {
		return behaviorList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(entityList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(agentList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(featureList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(layerList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(behaviorList).forEach(c -> components.add(c.core$()));
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
		if (node.is("Entity")) this.entityList.add(node.as(io.intino.tafat.graph.Entity.class));
		if (node.is("Agent")) this.agentList.add(node.as(io.intino.tafat.graph.Agent.class));
		if (node.is("Entity$Feature")) this.featureList.add(node.as(io.intino.tafat.graph.Entity.Feature.class));
		if (node.is("Entity$Layer")) this.layerList.add(node.as(io.intino.tafat.graph.Entity.Layer.class));
		if (node.is("Entity$Behavior")) this.behaviorList.add(node.as(io.intino.tafat.graph.Entity.Behavior.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Entity")) this.entityList.remove(node.as(io.intino.tafat.graph.Entity.class));
	        if (node.is("Agent")) this.agentList.remove(node.as(io.intino.tafat.graph.Agent.class));
	        if (node.is("Entity$Feature")) this.featureList.remove(node.as(io.intino.tafat.graph.Entity.Feature.class));
	        if (node.is("Entity$Layer")) this.layerList.remove(node.as(io.intino.tafat.graph.Entity.Layer.class));
	        if (node.is("Entity$Behavior")) this.behaviorList.remove(node.as(io.intino.tafat.graph.Entity.Behavior.class));
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

		public io.intino.tafat.graph.Entity entity() {
		    io.intino.tafat.graph.Entity newElement = core$().graph().concept(io.intino.tafat.graph.Entity.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Entity.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Agent agent() {
		    io.intino.tafat.graph.Agent newElement = core$().graph().concept(io.intino.tafat.graph.Agent.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Agent.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Entity.Feature feature() {
		    io.intino.tafat.graph.Entity.Feature newElement = core$().graph().concept(io.intino.tafat.graph.Entity.Feature.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Entity.Feature.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Entity.Layer layer() {
		    io.intino.tafat.graph.Entity.Layer newElement = core$().graph().concept(io.intino.tafat.graph.Entity.Layer.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Entity.Layer.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Entity.Behavior behavior() {
		    io.intino.tafat.graph.Entity.Behavior newElement = core$().graph().concept(io.intino.tafat.graph.Entity.Behavior.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Entity.Behavior.class);

		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void entity(java.util.function.Predicate<io.intino.tafat.graph.Entity> filter) {
			new java.util.ArrayList<>(entityList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void agent(java.util.function.Predicate<io.intino.tafat.graph.Agent> filter) {
			new java.util.ArrayList<>(agentList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void feature(java.util.function.Predicate<io.intino.tafat.graph.Entity.Feature> filter) {
			new java.util.ArrayList<>(featureList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void layer(java.util.function.Predicate<io.intino.tafat.graph.Entity.Layer> filter) {
			new java.util.ArrayList<>(layerList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void behavior(java.util.function.Predicate<io.intino.tafat.graph.Entity.Behavior> filter) {
			new java.util.ArrayList<>(behaviorList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Feature  extends io.intino.tara.magritte.Layer {
		protected java.util.List<io.intino.tafat.graph.Entity.Feature> featureList = new java.util.ArrayList<>();

		public Feature(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.util.List<io.intino.tafat.graph.Entity.Feature> featureList() {
			return java.util.Collections.unmodifiableList(featureList);
		}

		public io.intino.tafat.graph.Entity.Feature feature(int index) {
			return featureList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.Entity.Feature> featureList(java.util.function.Predicate<io.intino.tafat.graph.Entity.Feature> predicate) {
			return featureList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(featureList).forEach(c -> components.add(c.core$()));
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
			if (node.is("Entity$Feature")) this.featureList.add(node.as(io.intino.tafat.graph.Entity.Feature.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("Entity$Feature")) this.featureList.remove(node.as(io.intino.tafat.graph.Entity.Feature.class));
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

			public io.intino.tafat.graph.Entity.Feature feature() {
			    io.intino.tafat.graph.Entity.Feature newElement = core$().graph().concept(io.intino.tafat.graph.Entity.Feature.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Entity.Feature.class);

			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {
			public void feature(java.util.function.Predicate<io.intino.tafat.graph.Entity.Feature> filter) {
				new java.util.ArrayList<>(featureList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}
		}



		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Layer  extends io.intino.tara.magritte.Layer {
		protected java.util.List<io.intino.tafat.graph.TableFunction> tableFunctionList = new java.util.ArrayList<>();

		public Layer(io.intino.tara.magritte.Node node) {
			super(node);
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



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(tableFunctionList).forEach(c -> components.add(c.core$()));
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
			if (node.is("TableFunction")) this.tableFunctionList.add(node.as(io.intino.tafat.graph.TableFunction.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("TableFunction")) this.tableFunctionList.remove(node.as(io.intino.tafat.graph.TableFunction.class));
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

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {

		}



		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Behavior extends io.intino.tafat.graph.Behavior {


		public Behavior(io.intino.tara.magritte.Node node) {
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


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}