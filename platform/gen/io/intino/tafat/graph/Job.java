package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Job  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.Job.Start start;
	protected io.intino.tafat.graph.Job.Duration duration;
	protected java.util.List<io.intino.tafat.graph.Job> jobList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.JobAction> jobActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Job.Action> actionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Job.StartAction> startActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Job.RecurrentAction> recurrentActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Job.EndAction> endActionList = new java.util.ArrayList<>();

	public Job(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public io.intino.tafat.graph.Job.Start start() {
		return start;
	}

	public io.intino.tafat.graph.Job.Duration duration() {
		return duration;
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

	public java.util.List<io.intino.tafat.graph.JobAction> jobActionList() {
		return java.util.Collections.unmodifiableList(jobActionList);
	}

	public io.intino.tafat.graph.JobAction jobAction(int index) {
		return jobActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.JobAction> jobActionList(java.util.function.Predicate<io.intino.tafat.graph.JobAction> predicate) {
		return jobActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Job.Action> actionList() {
		return java.util.Collections.unmodifiableList(actionList);
	}

	public io.intino.tafat.graph.Job.Action action(int index) {
		return actionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job.Action> actionList(java.util.function.Predicate<io.intino.tafat.graph.Job.Action> predicate) {
		return actionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Job.StartAction> startActionList() {
		return java.util.Collections.unmodifiableList(startActionList);
	}

	public io.intino.tafat.graph.Job.StartAction startAction(int index) {
		return startActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job.StartAction> startActionList(java.util.function.Predicate<io.intino.tafat.graph.Job.StartAction> predicate) {
		return startActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Job.RecurrentAction> recurrentActionList() {
		return java.util.Collections.unmodifiableList(recurrentActionList);
	}

	public io.intino.tafat.graph.Job.RecurrentAction recurrentAction(int index) {
		return recurrentActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job.RecurrentAction> recurrentActionList(java.util.function.Predicate<io.intino.tafat.graph.Job.RecurrentAction> predicate) {
		return recurrentActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Job.EndAction> endActionList() {
		return java.util.Collections.unmodifiableList(endActionList);
	}

	public io.intino.tafat.graph.Job.EndAction endAction(int index) {
		return endActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Job.EndAction> endActionList(java.util.function.Predicate<io.intino.tafat.graph.Job.EndAction> predicate) {
		return endActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		if (start != null) components.add(this.start.core$());
		if (duration != null) components.add(this.duration.core$());
		new java.util.ArrayList<>(jobList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(jobActionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(actionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(startActionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(recurrentActionList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(endActionList).forEach(c -> components.add(c.core$()));
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
		if (node.is("Job$Start")) this.start = node.as(io.intino.tafat.graph.Job.Start.class);
		if (node.is("Job$Duration")) this.duration = node.as(io.intino.tafat.graph.Job.Duration.class);
		if (node.is("Job")) this.jobList.add(node.as(io.intino.tafat.graph.Job.class));
		if (node.is("JobAction")) this.jobActionList.add(node.as(io.intino.tafat.graph.JobAction.class));
		if (node.is("Job$Action")) this.actionList.add(node.as(io.intino.tafat.graph.Job.Action.class));
		if (node.is("Job$StartAction")) this.startActionList.add(node.as(io.intino.tafat.graph.Job.StartAction.class));
		if (node.is("Job$RecurrentAction")) this.recurrentActionList.add(node.as(io.intino.tafat.graph.Job.RecurrentAction.class));
		if (node.is("Job$EndAction")) this.endActionList.add(node.as(io.intino.tafat.graph.Job.EndAction.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Job$Start")) this.start = null;
	        if (node.is("Job$Duration")) this.duration = null;
	        if (node.is("Job")) this.jobList.remove(node.as(io.intino.tafat.graph.Job.class));
	        if (node.is("JobAction")) this.jobActionList.remove(node.as(io.intino.tafat.graph.JobAction.class));
	        if (node.is("Job$Action")) this.actionList.remove(node.as(io.intino.tafat.graph.Job.Action.class));
	        if (node.is("Job$StartAction")) this.startActionList.remove(node.as(io.intino.tafat.graph.Job.StartAction.class));
	        if (node.is("Job$RecurrentAction")) this.recurrentActionList.remove(node.as(io.intino.tafat.graph.Job.RecurrentAction.class));
	        if (node.is("Job$EndAction")) this.endActionList.remove(node.as(io.intino.tafat.graph.Job.EndAction.class));
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

		public io.intino.tafat.graph.Job.Start start(int start) {
		    io.intino.tafat.graph.Job.Start newElement = core$().graph().concept(io.intino.tafat.graph.Job.Start.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.Start.class);
			newElement.core$().set(newElement, "start", java.util.Collections.singletonList(start));
		    return newElement;
		}

		public io.intino.tafat.graph.Job.Duration duration(int duration) {
		    io.intino.tafat.graph.Job.Duration newElement = core$().graph().concept(io.intino.tafat.graph.Job.Duration.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.Duration.class);
			newElement.core$().set(newElement, "duration", java.util.Collections.singletonList(duration));
		    return newElement;
		}

		public io.intino.tafat.graph.Job job() {
		    io.intino.tafat.graph.Job newElement = core$().graph().concept(io.intino.tafat.graph.Job.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Job.StartAction startAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Job.StartAction newElement = core$().graph().concept(io.intino.tafat.graph.Job.StartAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.StartAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}

		public io.intino.tafat.graph.Job.RecurrentAction recurrentAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Job.RecurrentAction newElement = core$().graph().concept(io.intino.tafat.graph.Job.RecurrentAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.RecurrentAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}

		public io.intino.tafat.graph.Job.EndAction endAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Job.EndAction newElement = core$().graph().concept(io.intino.tafat.graph.Job.EndAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Job.EndAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}
		public io.intino.tafat.graph.RecurrentJob recurrentJob(java.util.List<io.intino.tafat.graph.Job> job) {
		    io.intino.tafat.graph.RecurrentJob newElement = core$().graph().concept(io.intino.tafat.graph.RecurrentJob.class).createNode(this.name, core$()).as(io.intino.tafat.graph.RecurrentJob.class);
			newElement.core$().set(newElement, "job", job);
		    return newElement;
		}

		public io.intino.tafat.graph.EndJob endJob(java.util.List<io.intino.tafat.graph.Job> job) {
		    io.intino.tafat.graph.EndJob newElement = core$().graph().concept(io.intino.tafat.graph.EndJob.class).createNode(this.name, core$()).as(io.intino.tafat.graph.EndJob.class);
			newElement.core$().set(newElement, "job", job);
		    return newElement;
		}

		public io.intino.tafat.graph.StartJob startJob(java.util.List<io.intino.tafat.graph.Job> job) {
		    io.intino.tafat.graph.StartJob newElement = core$().graph().concept(io.intino.tafat.graph.StartJob.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StartJob.class);
			newElement.core$().set(newElement, "job", job);
		    return newElement;
		}
	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void job(java.util.function.Predicate<io.intino.tafat.graph.Job> filter) {
			new java.util.ArrayList<>(jobList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void startAction(java.util.function.Predicate<io.intino.tafat.graph.Job.StartAction> filter) {
			new java.util.ArrayList<>(startActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void recurrentAction(java.util.function.Predicate<io.intino.tafat.graph.Job.RecurrentAction> filter) {
			new java.util.ArrayList<>(recurrentActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void endAction(java.util.function.Predicate<io.intino.tafat.graph.Job.EndAction> filter) {
			new java.util.ArrayList<>(endActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Start  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected int start;
		protected int deviation;

		public Start(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public int start() {
			return start;
		}

		public int deviation() {
			return deviation;
		}

		public Start deviation(int value) {
			this.deviation = value;
			return (Start) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("start", new java.util.ArrayList(java.util.Collections.singletonList(this.start)));
			map.put("deviation", new java.util.ArrayList(java.util.Collections.singletonList(this.deviation)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("start")) this.start = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("start")) this.start = (java.lang.Integer) values.get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = (java.lang.Integer) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Duration  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected int duration;
		protected int deviation;

		public Duration(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public int duration() {
			return duration;
		}

		public int deviation() {
			return deviation;
		}

		public Duration deviation(int value) {
			this.deviation = value;
			return (Duration) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("duration", new java.util.ArrayList(java.util.Collections.singletonList(this.duration)));
			map.put("deviation", new java.util.ArrayList(java.util.Collections.singletonList(this.deviation)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("duration")) this.duration = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("duration")) this.duration = (java.lang.Integer) values.get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = (java.lang.Integer) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static abstract class Action  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.functions.Action action;

		public Action(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public void action() {
			 action.execute();
		}

		public Action action(io.intino.tafat.graph.functions.Action value) {
			this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(action, this, io.intino.tafat.graph.functions.Action.class);
			return (Action) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("action", this.action != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.action)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
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

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class StartAction extends io.intino.tafat.graph.Job.Action implements io.intino.tara.magritte.tags.Terminal {


		public StartAction(io.intino.tara.magritte.Node node) {
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

	public static class RecurrentAction extends io.intino.tafat.graph.Job.Action implements io.intino.tara.magritte.tags.Terminal {


		public RecurrentAction(io.intino.tara.magritte.Node node) {
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

	public static class EndAction extends io.intino.tafat.graph.Job.Action implements io.intino.tara.magritte.tags.Terminal {


		public EndAction(io.intino.tara.magritte.Node node) {
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