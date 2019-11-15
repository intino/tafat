package test.graph;

import test.graph.*;

public class Router  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected Mode mode;

	public enum Mode {
		On, Off;
	}
	protected test.graph.Router.Electrical _electrical;

	public Router(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Mode mode() {
		return mode;
	}

	public Router mode(test.graph.Router.Mode value) {
		this.mode = value;
		return (Router) this;
	}





	public test.graph.Router.Electrical asElectrical() {
		io.intino.tara.magritte.Layer as = a$(test.graph.Router.Electrical.class);
		return as != null ? (test.graph.Router.Electrical) as : core$().addAspect(test.graph.Router.Electrical.class);
	}

	public boolean isElectrical() {
		return core$().is(test.graph.Router.Electrical.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("mode", new java.util.ArrayList(java.util.Collections.singletonList(this.mode)));
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
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("mode")) this.mode = (Mode) values.get(0);
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

		protected test.graph.Router _router;
		protected io.intino.tafat.graph.Entity.Behavior _metaType;

		public Electrical(io.intino.tara.magritte.Node node) {
			super(node);
			_metaType = a$(io.intino.tafat.graph.Entity.Behavior.class);
		}

		public test.graph.Router.Mode mode() {
			return _router.mode();
		}

		public int step() {
			return _metaType.step();
		}

		public Electrical mode(test.graph.Router.Mode value) {
			this._router.mode(value);
			return (Electrical) this;
		}

		public Electrical step(int value) {
			this._metaType.step(value);
			return (Electrical) this;
		}
		public test.graph.Router asRouter() {
			return (test.graph.Router) a$(test.graph.Router.class);
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
		    if (layer instanceof test.graph.Router) _router = (test.graph.Router) layer;

		}

		public test.graph.TestGraph graph() {
			return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
		}
	}


	public test.graph.TestGraph graph() {
		return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
	}
}