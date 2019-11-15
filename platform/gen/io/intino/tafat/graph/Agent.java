package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Agent  extends io.intino.tara.magritte.Layer {
	protected java.util.List<io.intino.tafat.graph.Knol> knolList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Agent.Layer> layerList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Agent.Behavior> behaviorList = new java.util.ArrayList<>();
	protected io.intino.tafat.graph.Agent.Listener _listener;

	public Agent(io.intino.tara.magritte.Node node) {
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

	public java.util.List<io.intino.tafat.graph.Agent.Layer> layerList() {
		return java.util.Collections.unmodifiableList(layerList);
	}

	public io.intino.tafat.graph.Agent.Layer layer(int index) {
		return layerList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Agent.Layer> layerList(java.util.function.Predicate<io.intino.tafat.graph.Agent.Layer> predicate) {
		return layerList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Agent.Behavior> behaviorList() {
		return java.util.Collections.unmodifiableList(behaviorList);
	}

	public io.intino.tafat.graph.Agent.Behavior behavior(int index) {
		return behaviorList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Agent.Behavior> behaviorList(java.util.function.Predicate<io.intino.tafat.graph.Agent.Behavior> predicate) {
		return behaviorList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	public io.intino.tafat.graph.Agent.Listener asListener() {
		io.intino.tara.magritte.Layer as = a$(io.intino.tafat.graph.Agent.Listener.class);
		return as != null ? (io.intino.tafat.graph.Agent.Listener) as : core$().addAspect(io.intino.tafat.graph.Agent.Listener.class);
	}

	public boolean isListener() {
		return core$().is(io.intino.tafat.graph.Agent.Listener.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(knolList).forEach(c -> components.add(c.core$()));
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
		if (node.is("Knol")) this.knolList.add(node.as(io.intino.tafat.graph.Knol.class));
		if (node.is("Agent$Layer")) this.layerList.add(node.as(io.intino.tafat.graph.Agent.Layer.class));
		if (node.is("Agent$Behavior")) this.behaviorList.add(node.as(io.intino.tafat.graph.Agent.Behavior.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Knol")) this.knolList.remove(node.as(io.intino.tafat.graph.Knol.class));
	        if (node.is("Agent$Layer")) this.layerList.remove(node.as(io.intino.tafat.graph.Agent.Layer.class));
	        if (node.is("Agent$Behavior")) this.behaviorList.remove(node.as(io.intino.tafat.graph.Agent.Behavior.class));
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

		public io.intino.tafat.graph.Agent.Layer layer() {
		    io.intino.tafat.graph.Agent.Layer newElement = core$().graph().concept(io.intino.tafat.graph.Agent.Layer.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Agent.Layer.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Agent.Behavior behavior() {
		    io.intino.tafat.graph.Agent.Behavior newElement = core$().graph().concept(io.intino.tafat.graph.Agent.Behavior.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Agent.Behavior.class);

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

		public void layer(java.util.function.Predicate<io.intino.tafat.graph.Agent.Layer> filter) {
			new java.util.ArrayList<>(layerList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void behavior(java.util.function.Predicate<io.intino.tafat.graph.Agent.Behavior> filter) {
			new java.util.ArrayList<>(behaviorList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Layer  extends io.intino.tara.magritte.Layer {

		public Layer(io.intino.tara.magritte.Node node) {
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

	public static class Listener  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.Message receiveMessage;

		protected io.intino.tafat.graph.Agent _agent;

		public Listener(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void receiveMessage(String message) {
			 receiveMessage.receiveMessage(message);
		}

		public Listener receiveMessage(io.intino.tafat.graph.functions.Message value) {
			this.receiveMessage = io.intino.tara.magritte.loaders.FunctionLoader.load(receiveMessage, this, io.intino.tafat.graph.functions.Message.class);
			return (Listener) this;
		}

		public java.util.List<io.intino.tafat.graph.Knol> knolList() {
			return (java.util.List<io.intino.tafat.graph.Knol>) _agent.knolList();
		}

		public io.intino.tafat.graph.Knol knolList(int index) {
			return _agent.knolList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.Agent.Layer> layerList() {
			return (java.util.List<io.intino.tafat.graph.Agent.Layer>) _agent.layerList();
		}

		public io.intino.tafat.graph.Agent.Layer layerList(int index) {
			return _agent.layerList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.Agent.Behavior> behaviorList() {
			return (java.util.List<io.intino.tafat.graph.Agent.Behavior>) _agent.behaviorList();
		}

		public io.intino.tafat.graph.Agent.Behavior behaviorList(int index) {
			return _agent.behaviorList().get(index);
		}


		public io.intino.tafat.graph.Agent asAgent() {
			return (io.intino.tafat.graph.Agent) a$(io.intino.tafat.graph.Agent.class);
		}

		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("receiveMessage", this.receiveMessage != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.receiveMessage)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);

		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);

		    }

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

			if (name.equalsIgnoreCase("receiveMessage")) this.receiveMessage = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Message.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			if (name.equalsIgnoreCase("receiveMessage")) this.receiveMessage = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Message.class);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof io.intino.tafat.graph.Agent) _agent = (io.intino.tafat.graph.Agent) layer;

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

			public io.intino.tafat.graph.Agent.Layer layer() {
			    io.intino.tafat.graph.Agent.Layer newElement = core$().graph().concept(io.intino.tafat.graph.Agent.Layer.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Agent.Layer.class);

			    return newElement;
			}

			public io.intino.tafat.graph.Agent.Behavior behavior() {
			    io.intino.tafat.graph.Agent.Behavior newElement = core$().graph().concept(io.intino.tafat.graph.Agent.Behavior.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Agent.Behavior.class);

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

			public void layer(java.util.function.Predicate<io.intino.tafat.graph.Agent.Layer> filter) {
				new java.util.ArrayList<>(layerList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}

			public void behavior(java.util.function.Predicate<io.intino.tafat.graph.Agent.Behavior> filter) {
				new java.util.ArrayList<>(behaviorList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}
		}



		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}