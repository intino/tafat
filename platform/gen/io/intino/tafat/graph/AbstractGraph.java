package io.intino.tafat.graph;

import io.intino.tara.magritte.Graph;

public class AbstractGraph extends io.intino.tara.magritte.GraphWrapper {

	protected io.intino.tara.magritte.Graph graph;
	private java.util.List<io.intino.tafat.graph.LogOutput> logOutputList = new java.util.ArrayList<>();
	private io.intino.tafat.graph.Simulation simulation;
	private java.util.List<io.intino.tafat.graph.Agent> agentList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Behavior> behaviorList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Entity> entityList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Output> outputList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Knol> knolList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Event> eventList = new java.util.ArrayList<>();
	private io.intino.tafat.graph.Profiling profiling;
	private java.util.List<io.intino.tafat.graph.CityCdfOutput> cityCdfOutputList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.SumusOutput> sumusOutputList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.CsvOutput> csvOutputList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList = new java.util.ArrayList<>();
	private java.util.List<io.intino.tafat.graph.Map> mapList = new java.util.ArrayList<>();
	private io.intino.tafat.graph.UserInterface userInterface;

	private java.util.Map<String, Indexer> _index = _fillIndex();

	public AbstractGraph(io.intino.tara.magritte.Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("tafat");
	}

	public AbstractGraph(io.intino.tara.magritte.Graph graph, AbstractGraph wrapper) {
		this.graph = graph;
		this.graph.i18n().register("tafat");
		this.logOutputList = new java.util.ArrayList<>(wrapper.logOutputList);
		this.simulation = wrapper.simulation;
		this.agentList = new java.util.ArrayList<>(wrapper.agentList);
		this.behaviorList = new java.util.ArrayList<>(wrapper.behaviorList);
		this.entityList = new java.util.ArrayList<>(wrapper.entityList);
		this.outputList = new java.util.ArrayList<>(wrapper.outputList);
		this.knolList = new java.util.ArrayList<>(wrapper.knolList);
		this.eventList = new java.util.ArrayList<>(wrapper.eventList);
		this.profiling = wrapper.profiling;
		this.cityCdfOutputList = new java.util.ArrayList<>(wrapper.cityCdfOutputList);
		this.sumusOutputList = new java.util.ArrayList<>(wrapper.sumusOutputList);
		this.csvOutputList = new java.util.ArrayList<>(wrapper.csvOutputList);
		this.periodicActivityList = new java.util.ArrayList<>(wrapper.periodicActivityList);
		this.mapList = new java.util.ArrayList<>(wrapper.mapList);
		this.userInterface = wrapper.userInterface;
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

	public java.util.List<io.intino.tafat.graph.LogOutput> logOutputList() {
		return logOutputList;
	}

	public io.intino.tafat.graph.Simulation simulation() {
		return simulation;
	}

	public java.util.List<io.intino.tafat.graph.Agent> agentList() {
		return agentList;
	}

	public java.util.List<io.intino.tafat.graph.Behavior> behaviorList() {
		return behaviorList;
	}

	public java.util.List<io.intino.tafat.graph.Entity> entityList() {
		return entityList;
	}

	public java.util.List<io.intino.tafat.graph.Output> outputList() {
		return outputList;
	}

	public java.util.List<io.intino.tafat.graph.Knol> knolList() {
		return knolList;
	}

	public java.util.List<io.intino.tafat.graph.Event> eventList() {
		return eventList;
	}

	public io.intino.tafat.graph.Profiling profiling() {
		return profiling;
	}

	public java.util.List<io.intino.tafat.graph.CityCdfOutput> cityCdfOutputList() {
		return cityCdfOutputList;
	}

	public java.util.List<io.intino.tafat.graph.SumusOutput> sumusOutputList() {
		return sumusOutputList;
	}

	public java.util.List<io.intino.tafat.graph.CsvOutput> csvOutputList() {
		return csvOutputList;
	}

	public java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList() {
		return periodicActivityList;
	}

	public java.util.List<io.intino.tafat.graph.Map> mapList() {
		return mapList;
	}

	public io.intino.tafat.graph.UserInterface userInterface() {
		return userInterface;
	}

	public java.util.stream.Stream<io.intino.tafat.graph.LogOutput> logOutputList(java.util.function.Predicate<io.intino.tafat.graph.LogOutput> filter) {
		return logOutputList.stream().filter(filter);
	}

	public io.intino.tafat.graph.LogOutput logOutput(int index) {
		return logOutputList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Agent> agentList(java.util.function.Predicate<io.intino.tafat.graph.Agent> filter) {
		return agentList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Agent agent(int index) {
		return agentList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Behavior> behaviorList(java.util.function.Predicate<io.intino.tafat.graph.Behavior> filter) {
		return behaviorList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Behavior behavior(int index) {
		return behaviorList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Entity> entityList(java.util.function.Predicate<io.intino.tafat.graph.Entity> filter) {
		return entityList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Entity entity(int index) {
		return entityList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Output> outputList(java.util.function.Predicate<io.intino.tafat.graph.Output> filter) {
		return outputList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Output output(int index) {
		return outputList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Knol> knolList(java.util.function.Predicate<io.intino.tafat.graph.Knol> filter) {
		return knolList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Knol knol(int index) {
		return knolList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Event> eventList(java.util.function.Predicate<io.intino.tafat.graph.Event> filter) {
		return eventList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Event event(int index) {
		return eventList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.CityCdfOutput> cityCdfOutputList(java.util.function.Predicate<io.intino.tafat.graph.CityCdfOutput> filter) {
		return cityCdfOutputList.stream().filter(filter);
	}

	public io.intino.tafat.graph.CityCdfOutput cityCdfOutput(int index) {
		return cityCdfOutputList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.SumusOutput> sumusOutputList(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput> filter) {
		return sumusOutputList.stream().filter(filter);
	}

	public io.intino.tafat.graph.SumusOutput sumusOutput(int index) {
		return sumusOutputList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.CsvOutput> csvOutputList(java.util.function.Predicate<io.intino.tafat.graph.CsvOutput> filter) {
		return csvOutputList.stream().filter(filter);
	}

	public io.intino.tafat.graph.CsvOutput csvOutput(int index) {
		return csvOutputList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.PeriodicActivity> periodicActivityList(java.util.function.Predicate<io.intino.tafat.graph.PeriodicActivity> filter) {
		return periodicActivityList.stream().filter(filter);
	}

	public io.intino.tafat.graph.PeriodicActivity periodicActivity(int index) {
		return periodicActivityList.get(index);
	}

	public java.util.stream.Stream<io.intino.tafat.graph.Map> mapList(java.util.function.Predicate<io.intino.tafat.graph.Map> filter) {
		return mapList.stream().filter(filter);
	}

	public io.intino.tafat.graph.Map map(int index) {
		return mapList.get(index);
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

		public io.intino.tafat.graph.LogOutput logOutput(io.intino.tafat.graph.rules.TimeScale timeScale) {
			io.intino.tafat.graph.LogOutput newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.LogOutput.class, stash, this.name).a$(io.intino.tafat.graph.LogOutput.class);
			newElement.core$().set(newElement, "timeScale", java.util.Collections.singletonList(timeScale));
			return newElement;
		}

		public io.intino.tafat.graph.Simulation simulation(java.time.Instant from, java.time.Instant to) {
			io.intino.tafat.graph.Simulation newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.Simulation.class, stash, this.name).a$(io.intino.tafat.graph.Simulation.class);
			newElement.core$().set(newElement, "from", java.util.Collections.singletonList(from));
			newElement.core$().set(newElement, "to", java.util.Collections.singletonList(to));
			return newElement;
		}

		public io.intino.tafat.graph.Output output(io.intino.tafat.graph.functions.Action init, io.intino.tafat.graph.functions.Action process, io.intino.tafat.graph.functions.Action terminate) {
			io.intino.tafat.graph.Output newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.Output.class, stash, this.name).a$(io.intino.tafat.graph.Output.class);
			newElement.core$().set(newElement, "init", java.util.Collections.singletonList(init));
			newElement.core$().set(newElement, "process", java.util.Collections.singletonList(process));
			newElement.core$().set(newElement, "terminate", java.util.Collections.singletonList(terminate));
			return newElement;
		}

		public io.intino.tafat.graph.Event event(java.time.Instant instantDate, io.intino.tafat.graph.functions.Action execute) {
			io.intino.tafat.graph.Event newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.Event.class, stash, this.name).a$(io.intino.tafat.graph.Event.class);
			newElement.core$().set(newElement, "instantDate", java.util.Collections.singletonList(instantDate));
			newElement.core$().set(newElement, "execute", java.util.Collections.singletonList(execute));
			return newElement;
		}

		public io.intino.tafat.graph.Profiling profiling() {
			io.intino.tafat.graph.Profiling newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.Profiling.class, stash, this.name).a$(io.intino.tafat.graph.Profiling.class);
			return newElement;
		}

		public io.intino.tafat.graph.CityCdfOutput cityCdfOutput(io.intino.tafat.graph.functions.Action init, io.intino.tafat.graph.functions.Action process, io.intino.tafat.graph.functions.Action terminate, io.intino.tafat.graph.rules.TimeScale timeScale) {
			io.intino.tafat.graph.CityCdfOutput newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.CityCdfOutput.class, stash, this.name).a$(io.intino.tafat.graph.CityCdfOutput.class);
			newElement.core$().set(newElement, "init", java.util.Collections.singletonList(init));
			newElement.core$().set(newElement, "process", java.util.Collections.singletonList(process));
			newElement.core$().set(newElement, "terminate", java.util.Collections.singletonList(terminate));
			newElement.core$().set(newElement, "timeScale", java.util.Collections.singletonList(timeScale));
			return newElement;
		}

		public io.intino.tafat.graph.SumusOutput sumusOutput(java.lang.String language, java.lang.String simulationId) {
			io.intino.tafat.graph.SumusOutput newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.SumusOutput.class, stash, this.name).a$(io.intino.tafat.graph.SumusOutput.class);
			newElement.core$().set(newElement, "language", java.util.Collections.singletonList(language));
			newElement.core$().set(newElement, "simulationId", java.util.Collections.singletonList(simulationId));
			return newElement;
		}

		public io.intino.tafat.graph.CsvOutput csvOutput(io.intino.tafat.graph.functions.Action init, io.intino.tafat.graph.functions.Action process, io.intino.tafat.graph.functions.Action terminate, java.lang.String path, io.intino.tafat.graph.rules.TimeScale timeScale) {
			io.intino.tafat.graph.CsvOutput newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.CsvOutput.class, stash, this.name).a$(io.intino.tafat.graph.CsvOutput.class);
			newElement.core$().set(newElement, "init", java.util.Collections.singletonList(init));
			newElement.core$().set(newElement, "process", java.util.Collections.singletonList(process));
			newElement.core$().set(newElement, "terminate", java.util.Collections.singletonList(terminate));
			newElement.core$().set(newElement, "path", java.util.Collections.singletonList(path));
			newElement.core$().set(newElement, "timeScale", java.util.Collections.singletonList(timeScale));
			return newElement;
		}

		public io.intino.tafat.graph.Map map() {
			io.intino.tafat.graph.Map newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.Map.class, stash, this.name).a$(io.intino.tafat.graph.Map.class);
			return newElement;
		}

		public io.intino.tafat.graph.UserInterface userInterface(java.lang.String title, java.net.URL logo) {
			io.intino.tafat.graph.UserInterface newElement = AbstractGraph.this.graph.createRoot(io.intino.tafat.graph.UserInterface.class, stash, this.name).a$(io.intino.tafat.graph.UserInterface.class);
			newElement.core$().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.core$().set(newElement, "logo", java.util.Collections.singletonList(logo));
			return newElement;
		}
	}

	public class Clear {
	    public void logOutput(java.util.function.Predicate<io.intino.tafat.graph.LogOutput> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.logOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void output(java.util.function.Predicate<io.intino.tafat.graph.Output> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.outputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void event(java.util.function.Predicate<io.intino.tafat.graph.Event> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.eventList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void cityCdfOutput(java.util.function.Predicate<io.intino.tafat.graph.CityCdfOutput> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.cityCdfOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void sumusOutput(java.util.function.Predicate<io.intino.tafat.graph.SumusOutput> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.sumusOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void csvOutput(java.util.function.Predicate<io.intino.tafat.graph.CsvOutput> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.csvOutputList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }

	    public void map(java.util.function.Predicate<io.intino.tafat.graph.Map> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.mapList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
	    }
	}


	private java.util.HashMap<String, Indexer> _fillIndex() {
		java.util.HashMap<String, Indexer> map = new java.util.HashMap<>();
		map.put("LogOutput", new Indexer(node -> logOutputList.add(node.as(io.intino.tafat.graph.LogOutput.class)), node -> logOutputList.remove(node.as(io.intino.tafat.graph.LogOutput.class)), () -> logOutputList.clear()));
		map.put("Simulation", new Indexer(node -> simulation = node.as(io.intino.tafat.graph.Simulation.class), node -> simulation = null, () -> simulation = null));
		map.put("Agent", new Indexer(node -> agentList.add(node.as(io.intino.tafat.graph.Agent.class)), node -> agentList.remove(node.as(io.intino.tafat.graph.Agent.class)), () -> agentList.clear()));
		map.put("Behavior", new Indexer(node -> behaviorList.add(node.as(io.intino.tafat.graph.Behavior.class)), node -> behaviorList.remove(node.as(io.intino.tafat.graph.Behavior.class)), () -> behaviorList.clear()));
		map.put("Entity", new Indexer(node -> entityList.add(node.as(io.intino.tafat.graph.Entity.class)), node -> entityList.remove(node.as(io.intino.tafat.graph.Entity.class)), () -> entityList.clear()));
		map.put("Output", new Indexer(node -> outputList.add(node.as(io.intino.tafat.graph.Output.class)), node -> outputList.remove(node.as(io.intino.tafat.graph.Output.class)), () -> outputList.clear()));
		map.put("Knol", new Indexer(node -> knolList.add(node.as(io.intino.tafat.graph.Knol.class)), node -> knolList.remove(node.as(io.intino.tafat.graph.Knol.class)), () -> knolList.clear()));
		map.put("Event", new Indexer(node -> eventList.add(node.as(io.intino.tafat.graph.Event.class)), node -> eventList.remove(node.as(io.intino.tafat.graph.Event.class)), () -> eventList.clear()));
		map.put("Profiling", new Indexer(node -> profiling = node.as(io.intino.tafat.graph.Profiling.class), node -> profiling = null, () -> profiling = null));
		map.put("CityCdfOutput", new Indexer(node -> cityCdfOutputList.add(node.as(io.intino.tafat.graph.CityCdfOutput.class)), node -> cityCdfOutputList.remove(node.as(io.intino.tafat.graph.CityCdfOutput.class)), () -> cityCdfOutputList.clear()));
		map.put("SumusOutput", new Indexer(node -> sumusOutputList.add(node.as(io.intino.tafat.graph.SumusOutput.class)), node -> sumusOutputList.remove(node.as(io.intino.tafat.graph.SumusOutput.class)), () -> sumusOutputList.clear()));
		map.put("CsvOutput", new Indexer(node -> csvOutputList.add(node.as(io.intino.tafat.graph.CsvOutput.class)), node -> csvOutputList.remove(node.as(io.intino.tafat.graph.CsvOutput.class)), () -> csvOutputList.clear()));
		map.put("PeriodicActivity", new Indexer(node -> periodicActivityList.add(node.as(io.intino.tafat.graph.PeriodicActivity.class)), node -> periodicActivityList.remove(node.as(io.intino.tafat.graph.PeriodicActivity.class)), () -> periodicActivityList.clear()));
		map.put("Map", new Indexer(node -> mapList.add(node.as(io.intino.tafat.graph.Map.class)), node -> mapList.remove(node.as(io.intino.tafat.graph.Map.class)), () -> mapList.clear()));
		map.put("UserInterface", new Indexer(node -> userInterface = node.as(io.intino.tafat.graph.UserInterface.class), node -> userInterface = null, () -> userInterface = null));
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