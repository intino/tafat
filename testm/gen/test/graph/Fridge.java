package test.graph;

import test.graph.*;

public class Fridge  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected Mode mode;

	public enum Mode {
		On, Off;
	}
	protected double power;
	protected test.graph.Fridge.Electrical _electrical;

	public Fridge(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Mode mode() {
		return mode;
	}

	public double power() {
		return power;
	}

	public Fridge mode(test.graph.Fridge.Mode value) {
		this.mode = value;
		return (Fridge) this;
	}

	public Fridge power(double value) {
		this.power = value;
		return (Fridge) this;
	}





	public test.graph.Fridge.Electrical asElectrical() {
		io.intino.tara.magritte.Layer as = a$(test.graph.Fridge.Electrical.class);
		return as != null ? (test.graph.Fridge.Electrical) as : core$().addAspect(test.graph.Fridge.Electrical.class);
	}

	public boolean isElectrical() {
		return core$().is(test.graph.Fridge.Electrical.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("mode", new java.util.ArrayList(java.util.Collections.singletonList(this.mode)));
		map.put("power", new java.util.ArrayList(java.util.Collections.singletonList(this.power)));
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
		if (name.equalsIgnoreCase("mode")) this.mode = io.intino.tara.magritte.loaders.WordLoader.load(values, Mode.class, this).get(0);
		else if (name.equalsIgnoreCase("power")) this.power = io.intino.tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("mode")) this.mode = (Mode) values.get(0);
		else if (name.equalsIgnoreCase("power")) this.power = (java.lang.Double) values.get(0);
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

	public static class Electrical  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected Implementation implementation;

		public enum Implementation {
			v1, v2, v3, v4, v5, v6, vFmu;
		}
		protected int value;

		protected test.graph.Fridge _fridge;
		protected io.intino.tafat.graph.Entity.Behavior _metaType;

		public Electrical(io.intino.tara.magritte.Node node) {
			super(node);
			_metaType = a$(io.intino.tafat.graph.Entity.Behavior.class);
		}

		public Implementation implementation() {
			return implementation;
		}

		public int value() {
			return value;
		}

		public test.graph.Fridge.Mode mode() {
			return _fridge.mode();
		}

		public double power() {
			return _fridge.power();
		}

		public int step() {
			return _metaType.step();
		}

		public Electrical implementation(test.graph.Fridge.Electrical.Implementation value) {
			this.implementation = value;
			return (Electrical) this;
		}

		public Electrical value(int value) {
			this.value = value;
			return (Electrical) this;
		}

		public Electrical mode(test.graph.Fridge.Mode value) {
			this._fridge.mode(value);
			return (Electrical) this;
		}

		public Electrical power(double value) {
			this._fridge.power(value);
			return (Electrical) this;
		}

		public Electrical step(int value) {
			this._metaType.step(value);
			return (Electrical) this;
		}

		public io.intino.tafat.graph.Implementation v1() {
			return _metaType.implementationList(o -> o.name$().equals("v1")).get(0);
		}

		public io.intino.tafat.graph.Implementation v2() {
			return _metaType.implementationList(o -> o.name$().equals("v2")).get(0);
		}

		public io.intino.tafat.graph.Implementation v3() {
			return _metaType.implementationList(o -> o.name$().equals("v3")).get(0);
		}

		public io.intino.tafat.graph.Implementation v4() {
			return _metaType.implementationList(o -> o.name$().equals("v4")).get(0);
		}

		public io.intino.tafat.graph.Implementation v5() {
			return _metaType.implementationList(o -> o.name$().equals("v5")).get(0);
		}

		public io.intino.tafat.graph.Implementation v6() {
			return _metaType.implementationList(o -> o.name$().equals("v6")).get(0);
		}

		public io.intino.tafat.graph.Implementation vFmu() {
			return _metaType.implementationList(o -> o.name$().equals("vFmu")).get(0);
		}


		public test.graph.Fridge asFridge() {
			return (test.graph.Fridge) a$(test.graph.Fridge.class);
		}

		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("implementation", new java.util.ArrayList(java.util.Collections.singletonList(this.implementation)));
			map.put("value", new java.util.ArrayList(java.util.Collections.singletonList(this.value)));
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
			else if (name.equalsIgnoreCase("value")) this.value = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			core$().set(_metaType, name, values);
			if (name.equalsIgnoreCase("implementation")) this.implementation = (Implementation) values.get(0);
			else if (name.equalsIgnoreCase("value")) this.value = (java.lang.Integer) values.get(0);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof test.graph.Fridge) _fridge = (test.graph.Fridge) layer;

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


	public test.graph.TestGraph graph() {
		return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
	}
}