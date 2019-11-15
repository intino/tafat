package test.graph;

import io.intino.tara.magritte.Graph;

public class AbstractGraph extends io.intino.tara.magritte.GraphWrapper {

	protected io.intino.tara.magritte.Graph graph;
	private java.util.List<test.graph.Bot> botList = new java.util.ArrayList<>();
	private java.util.List<test.graph.Environment> environmentList = new java.util.ArrayList<>();
	private java.util.List<test.graph.Fridge> fridgeList = new java.util.ArrayList<>();
	private java.util.List<test.graph.Family> familyList = new java.util.ArrayList<>();
	private java.util.List<test.graph.Router> routerList = new java.util.ArrayList<>();

	private java.util.Map<String, Indexer> _index = _fillIndex();

	public AbstractGraph(io.intino.tara.magritte.Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("Test");
	}

	public AbstractGraph(io.intino.tara.magritte.Graph graph, AbstractGraph wrapper) {
		this.graph = graph;
		this.graph.i18n().register("Test");
		this.botList = new java.util.ArrayList<>(wrapper.botList);
		this.environmentList = new java.util.ArrayList<>(wrapper.environmentList);
		this.fridgeList = new java.util.ArrayList<>(wrapper.fridgeList);
		this.familyList = new java.util.ArrayList<>(wrapper.familyList);
		this.routerList = new java.util.ArrayList<>(wrapper.routerList);
	}

	public <T extends io.intino.tara.magritte.GraphWrapper> T a$(Class<T> t) {
		return this.core$().as(t);
	}

    @Override
	public void update() {
		this._index.values().forEach(v -> v.clear());
		graph.rootList().forEach(r -> addNode$(r));
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		for (io.intino.tara.magritte.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).add(node);
		if (this._index.containsKey(node.id())) this._index.get(node.id()).add(node);
	}

	@Override
	protected void removeNode$(io.intino.tara.magritte.Node node) {
		for (io.intino.tara.magritte.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).remove(node);
		if (this._index.containsKey(node.id())) this._index.get(node.id()).remove(node);
	}

	public java.net.URL resourceAsMessage$(String language, String key) {
		return graph.loadResource(graph.i18n().message(language, key));
	}

	public java.util.List<test.graph.Bot> botList() {
		return botList;
	}

	public java.util.List<test.graph.Environment> environmentList() {
		return environmentList;
	}

	public java.util.List<test.graph.Fridge> fridgeList() {
		return fridgeList;
	}

	public java.util.List<test.graph.Family> familyList() {
		return familyList;
	}

	public java.util.List<test.graph.Router> routerList() {
		return routerList;
	}

	public java.util.stream.Stream<test.graph.Bot> botList(java.util.function.Predicate<test.graph.Bot> filter) {
		return botList.stream().filter(filter);
	}

	public test.graph.Bot bot(int index) {
		return botList.get(index);
	}

	public java.util.stream.Stream<test.graph.Environment> environmentList(java.util.function.Predicate<test.graph.Environment> filter) {
		return environmentList.stream().filter(filter);
	}

	public test.graph.Environment environment(int index) {
		return environmentList.get(index);
	}

	public java.util.stream.Stream<test.graph.Fridge> fridgeList(java.util.function.Predicate<test.graph.Fridge> filter) {
		return fridgeList.stream().filter(filter);
	}

	public test.graph.Fridge fridge(int index) {
		return fridgeList.get(index);
	}

	public java.util.stream.Stream<test.graph.Family> familyList(java.util.function.Predicate<test.graph.Family> filter) {
		return familyList.stream().filter(filter);
	}

	public test.graph.Family family(int index) {
		return familyList.get(index);
	}

	public java.util.stream.Stream<test.graph.Router> routerList(java.util.function.Predicate<test.graph.Router> filter) {
		return routerList.stream().filter(filter);
	}

	public test.graph.Router router(int index) {
		return routerList.get(index);
	}

	public io.intino.tara.magritte.Graph core$() {
		return graph;
	}

	public io.intino.tara.magritte.utils.I18n i18n$() {
		return graph.i18n();
	}

	public Create create() {
		return new Create("Misc", null);
	}

	public Create create(String stash) {
		return new Create(stash, null);
	}

	public Create create(String stash, String name) {
		return new Create(stash, name);
	}

	public Clear clear() {
		return new Clear();
	}

	public class Create {
		private final String stash;
		private final String name;

		public Create(String stash, String name) {
			this.stash = stash;
			this.name = name;
		}

		public test.graph.Bot bot() {
			test.graph.Bot newElement = AbstractGraph.this.graph.createRoot(test.graph.Bot.class, stash, this.name).a$(test.graph.Bot.class);
			return newElement;
		}

		public test.graph.Environment environment(double temperature) {
			test.graph.Environment newElement = AbstractGraph.this.graph.createRoot(test.graph.Environment.class, stash, this.name).a$(test.graph.Environment.class);
			newElement.core$().set(newElement, "temperature", java.util.Collections.singletonList(temperature));
			return newElement;
		}

		public test.graph.Fridge fridge() {
			test.graph.Fridge newElement = AbstractGraph.this.graph.createRoot(test.graph.Fridge.class, stash, this.name).a$(test.graph.Fridge.class);
			return newElement;
		}

		public test.graph.Family family() {
			test.graph.Family newElement = AbstractGraph.this.graph.createRoot(test.graph.Family.class, stash, this.name).a$(test.graph.Family.class);
			return newElement;
		}

		public test.graph.Router router() {
			test.graph.Router newElement = AbstractGraph.this.graph.createRoot(test.graph.Router.class, stash, this.name).a$(test.graph.Router.class);
			return newElement;
		}
	}

	public class Clear {
	    public void bot(java.util.function.Predicate<test.graph.Bot> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.botList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void environment(java.util.function.Predicate<test.graph.Environment> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.environmentList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void fridge(java.util.function.Predicate<test.graph.Fridge> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.fridgeList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void family(java.util.function.Predicate<test.graph.Family> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.familyList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void router(java.util.function.Predicate<test.graph.Router> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.routerList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }
	}


	private java.util.HashMap<String, Indexer> _fillIndex() {
		java.util.HashMap<String, Indexer> map = new java.util.HashMap<>();
		map.put("Bot", new Indexer(node -> botList.add(node.as(test.graph.Bot.class)), node -> botList.remove(node.as(test.graph.Bot.class)), () -> botList.clear()));
		map.put("Environment", new Indexer(node -> environmentList.add(node.as(test.graph.Environment.class)), node -> environmentList.remove(node.as(test.graph.Environment.class)), () -> environmentList.clear()));
		map.put("Fridge", new Indexer(node -> fridgeList.add(node.as(test.graph.Fridge.class)), node -> fridgeList.remove(node.as(test.graph.Fridge.class)), () -> fridgeList.clear()));
		map.put("Family", new Indexer(node -> familyList.add(node.as(test.graph.Family.class)), node -> familyList.remove(node.as(test.graph.Family.class)), () -> familyList.clear()));
		map.put("Router", new Indexer(node -> routerList.add(node.as(test.graph.Router.class)), node -> routerList.remove(node.as(test.graph.Router.class)), () -> routerList.clear()));
		return map;
	}

	public static class Indexer {
		Add add;
		Remove remove;
		IndexClear clear;

		public Indexer(Add add, Remove remove, IndexClear clear) {
			this.add = add;
			this.remove = remove;
			this.clear = clear;
		}

		void add(io.intino.tara.magritte.Node node) {
			this.add.add(node);
		}

		void remove(io.intino.tara.magritte.Node node) {
			this.remove.remove(node);
		}

		void clear() {
			this.clear.clear();
		}
	}

	interface Add {
		void add(io.intino.tara.magritte.Node node);
	}

	interface Remove {
		void remove(io.intino.tara.magritte.Node node);
	}

	interface IndexClear {
		void clear();
	}
}