package test.graph;

import test.graph.*;

public class Family  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected test.graph.Family.Consumer _consumer;
	protected test.graph.Family.Consumer2 _consumer2;

	public Family(io.intino.tara.magritte.Node node) {
		super(node);
	}





	public test.graph.Family.Consumer asConsumer() {
		io.intino.tara.magritte.Layer as = a$(test.graph.Family.Consumer.class);
		return as != null ? (test.graph.Family.Consumer) as : core$().addAspect(test.graph.Family.Consumer.class);
	}

	public boolean isConsumer() {
		return core$().is(test.graph.Family.Consumer.class);
	}

	public test.graph.Family.Consumer2 asConsumer2() {
		io.intino.tara.magritte.Layer as = a$(test.graph.Family.Consumer2.class);
		return as != null ? (test.graph.Family.Consumer2) as : core$().addAspect(test.graph.Family.Consumer2.class);
	}

	public boolean isConsumer2() {
		return core$().is(test.graph.Family.Consumer2.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

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

	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);

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

	public static class Consumer  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected Implementation implementation;

		public enum Implementation {
			Simple, Complex;
		}

		protected test.graph.Family _family;
		protected io.intino.tafat.graph.Agent.Behavior _metaType;

		public Consumer(io.intino.tara.magritte.Node node) {
			super(node);
			_metaType = a$(io.intino.tafat.graph.Agent.Behavior.class);
		}

		public Implementation implementation() {
			return implementation;
		}

		public int step() {
			return _metaType.step();
		}

		public Consumer implementation(test.graph.Family.Consumer.Implementation value) {
			this.implementation = value;
			return (Consumer) this;
		}

		public Consumer step(int value) {
			this._metaType.step(value);
			return (Consumer) this;
		}

		public io.intino.tafat.graph.Implementation simple() {
			return _metaType.implementationList(o -> o.name$().equals("Simple")).get(0);
		}

		public io.intino.tafat.graph.Implementation complex() {
			return _metaType.implementationList(o -> o.name$().equals("Complex")).get(0);
		}


		public test.graph.Family asFamily() {
			return (test.graph.Family) a$(test.graph.Family.class);
		}

		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("implementation", new java.util.ArrayList(java.util.Collections.singletonList(this.implementation)));
			map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this._metaType.step())));
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

			core$().load(_metaType, name, values);
			if (name.equalsIgnoreCase("implementation")) this.implementation = io.intino.tara.magritte.loaders.WordLoader.load(values, Implementation.class, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			core$().set(_metaType, name, values);
			if (name.equalsIgnoreCase("implementation")) this.implementation = (Implementation) values.get(0);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof test.graph.Family) _family = (test.graph.Family) layer;

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



		public test.graph.TestGraph graph() {
			return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
		}
	}

	public static class Consumer2  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {

		protected test.graph.Family _family;
		protected io.intino.tafat.graph.Agent.Behavior _metaType;

		public Consumer2(io.intino.tara.magritte.Node node) {
			super(node);
			_metaType = a$(io.intino.tafat.graph.Agent.Behavior.class);
		}

		public int step() {
			return _metaType.step();
		}

		public Consumer2 step(int value) {
			this._metaType.step(value);
			return (Consumer2) this;
		}
		public test.graph.Family asFamily() {
			return (test.graph.Family) a$(test.graph.Family.class);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this._metaType.step())));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

			core$().load(_metaType, name, values);

		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			core$().set(_metaType, name, values);

		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof test.graph.Family) _family = (test.graph.Family) layer;

		}

		public test.graph.TestGraph graph() {
			return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
		}
	}


	public test.graph.TestGraph graph() {
		return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
	}
}