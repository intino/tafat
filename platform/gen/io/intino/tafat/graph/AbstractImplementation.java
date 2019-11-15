package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public abstract class AbstractImplementation  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected int step;
	protected int timeout;
	protected io.intino.tafat.graph.functions.CheckStep checkStep;
	protected java.util.List<io.intino.tafat.graph.Start> startList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Task> taskList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Job> jobList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Map> mapList = new java.util.ArrayList<>();
	protected io.intino.tafat.graph.Implementation.Parallelizable _parallelizable;

	public AbstractImplementation(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public int step() {
		return step;
	}

	public int timeout() {
		return timeout;
	}

	public boolean checkStep(int stepSize) {
		return checkStep.checkStep(stepSize);
	}

	public Implementation step(int value) {
		this.step = value;
		return (Implementation) this;
	}

	public Implementation timeout(int value) {
		this.timeout = value;
		return (Implementation) this;
	}

	public Implementation checkStep(io.intino.tafat.graph.functions.CheckStep value) {
		this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(checkStep, this, io.intino.tafat.graph.functions.CheckStep.class);
		return (Implementation) this;
	}

	public java.util.List<io.intino.tafat.graph.Start> startList() {
		return java.util.Collections.unmodifiableList(startList);
	}

	public io.intino.tafat.graph.Start start(int index) {
		return startList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Start> startList(java.util.function.Predicate<io.intino.tafat.graph.Start> predicate) {
		return startList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList() {
		return java.util.Collections.unmodifiableList(periodicActivityList);
	}

	public io.intino.tafat.graph.PeriodicActivity periodicActivity(int index) {
		return periodicActivityList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList(java.util.function.Predicate<io.intino.tafat.graph.PeriodicActivity> predicate) {
		return periodicActivityList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Task> taskList() {
		return java.util.Collections.unmodifiableList(taskList);
	}

	public io.intino.tafat.graph.Task task(int index) {
		return taskList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Task> taskList(java.util.function.Predicate<io.intino.tafat.graph.Task> predicate) {
		return taskList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Job> jobList() {
		return java.util.Collections.unmodifiableList(jobList);
	}

	public io.intino.tafat.graph.Job job(int index) {
		return jobList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job> jobList(java.util.function.Predicate<io.intino.tafat.graph.Job> predicate) {
		return jobList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Map> mapList() {
		return java.util.Collections.unmodifiableList(mapList);
	}

	public io.intino.tafat.graph.Map map(int index) {
		return mapList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Map> mapList(java.util.function.Predicate<io.intino.tafat.graph.Map> predicate) {
		return mapList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	public io.intino.tafat.graph.Implementation.Parallelizable asParallelizable() {
		io.intino.tara.magritte.Layer as = a$(io.intino.tafat.graph.Implementation.Parallelizable.class);
		return as != null ? (io.intino.tafat.graph.Implementation.Parallelizable) as : core$().addAspect(io.intino.tafat.graph.Implementation.Parallelizable.class);
	}

	public boolean isParallelizable() {
		return core$().is(io.intino.tafat.graph.Implementation.Parallelizable.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(startList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(periodicActivityList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(taskList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(jobList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(mapList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("step", new java.util.ArrayList(java.util.Collections.singletonList(this.step)));
		map.put("timeout", new java.util.ArrayList(java.util.Collections.singletonList(this.timeout)));
		map.put("checkStep", this.checkStep != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.checkStep)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Start")) this.startList.add(node.as(io.intino.tafat.graph.Start.class));
		if (node.is("PeriodicActivity")) this.periodicActivityList.add(node.as(io.intino.tafat.graph.PeriodicActivity.class));
		if (node.is("Task")) this.taskList.add(node.as(io.intino.tafat.graph.Task.class));
		if (node.is("Job")) this.jobList.add(node.as(io.intino.tafat.graph.Job.class));
		if (node.is("Map")) this.mapList.add(node.as(io.intino.tafat.graph.Map.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Start")) this.startList.remove(node.as(io.intino.tafat.graph.Start.class));
	        if (node.is("PeriodicActivity")) this.periodicActivityList.remove(node.as(io.intino.tafat.graph.PeriodicActivity.class));
	        if (node.is("Task")) this.taskList.remove(node.as(io.intino.tafat.graph.Task.class));
	        if (node.is("Job")) this.jobList.remove(node.as(io.intino.tafat.graph.Job.class));
	        if (node.is("Map")) this.mapList.remove(node.as(io.intino.tafat.graph.Map.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("step")) this.step = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.CheckStep.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("step")) this.step = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("timeout")) this.timeout = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.CheckStep.class);
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


		public io.intino.tafat.graph.SystemDynamic systemDynamic(io.intino.tara.magritte.Expression<io.intino.tafat.engine.DifferentialEquation> odeProvider) {
		    io.intino.tafat.graph.SystemDynamic newElement = core$().graph().concept(io.intino.tafat.graph.SystemDynamic.class).createNode(this.name, core$()).as(io.intino.tafat.graph.SystemDynamic.class);
			newElement.core$().set(newElement, "odeProvider", java.util.Collections.singletonList(odeProvider));
		    return newElement;
		}

		public io.intino.tafat.graph.Fmu fmu(java.lang.String file) {
		    io.intino.tafat.graph.Fmu newElement = core$().graph().concept(io.intino.tafat.graph.Fmu.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Fmu.class);
			newElement.core$().set(newElement, "file", java.util.Collections.singletonList(file));
		    return newElement;
		}

		public io.intino.tafat.graph.ConditionalAction conditionalAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.ConditionalAction newElement = core$().graph().concept(io.intino.tafat.graph.ConditionalAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.ConditionalAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}

		public io.intino.tafat.graph.StateChart.State state() {
		    io.intino.tafat.graph.StateChart.State newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.State.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.State.class);

		    return newElement;
		}

		public io.intino.tafat.graph.StateChart stateChart() {
		    io.intino.tafat.graph.StateChart newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Action action(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Action newElement = core$().graph().concept(io.intino.tafat.graph.Action.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Action.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}
	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {

	}

	public static class Parallelizable  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {


		protected io.intino.tafat.graph.Implementation _implementation;

		public Parallelizable(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public int step() {
			return _implementation.step();
		}

		public int timeout() {
			return _implementation.timeout();
		}

		public boolean checkStep(int stepSize) {
			return _implementation.checkStep(stepSize);
		}

		public Parallelizable step(int value) {
			this._implementation.step(value);
			return (Parallelizable) this;
		}

		public Parallelizable timeout(int value) {
			this._implementation.timeout(value);
			return (Parallelizable) this;
		}

		public java.util.List<io.intino.tafat.graph.Start> startList() {
			return java.util.Collections.unmodifiableList((java.util.List<io.intino.tafat.graph.Start>) _implementation.startList());
		}

		public io.intino.tafat.graph.Start startList(int index) {
			return _implementation.startList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.PeriodicActivity> periodicActivityList() {
			return (java.util.List<io.intino.tafat.graph.PeriodicActivity>) _implementation.periodicActivityList();
		}

		public io.intino.tafat.graph.PeriodicActivity periodicActivityList(int index) {
			return _implementation.periodicActivityList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.Task> taskList() {
			return java.util.Collections.unmodifiableList((java.util.List<io.intino.tafat.graph.Task>) _implementation.taskList());
		}

		public io.intino.tafat.graph.Task taskList(int index) {
			return _implementation.taskList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.Job> jobList() {
			return java.util.Collections.unmodifiableList((java.util.List<io.intino.tafat.graph.Job>) _implementation.jobList());
		}

		public io.intino.tafat.graph.Job jobList(int index) {
			return _implementation.jobList().get(index);
		}

		public java.util.List<io.intino.tafat.graph.Map> mapList() {
			return java.util.Collections.unmodifiableList((java.util.List<io.intino.tafat.graph.Map>) _implementation.mapList());
		}

		public io.intino.tafat.graph.Map mapList(int index) {
			return _implementation.mapList().get(index);
		}


		public io.intino.tafat.graph.Implementation asImplementation() {
			return (io.intino.tafat.graph.Implementation) a$(io.intino.tafat.graph.Implementation.class);
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

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof io.intino.tafat.graph.Implementation) _implementation = (io.intino.tafat.graph.Implementation) layer;

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


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}