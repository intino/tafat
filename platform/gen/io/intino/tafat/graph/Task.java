package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Task  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected java.util.List<io.intino.tafat.graph.rules.DayOfWeek> days = new java.util.ArrayList<>();
	protected java.time.Instant scheduledDate;
	protected io.intino.tara.magritte.Expression<java.lang.Boolean> check;
	protected io.intino.tafat.graph.functions.Action program;
	protected io.intino.tafat.graph.Task.Start start;
	protected io.intino.tafat.graph.Task.Finish finish;
	protected io.intino.tafat.graph.Task.End end;
	protected io.intino.tafat.graph.Task.Duration duration;
	protected java.util.List<io.intino.tafat.graph.Task> taskList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.JobAction> jobActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Task.Action> actionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Task.StartAction> startActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Task.RecurrentAction> recurrentActionList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.Task.EndAction> endActionList = new java.util.ArrayList<>();

	public Task(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<io.intino.tafat.graph.rules.DayOfWeek> days() {
		return days;
	}

	public java.time.Instant scheduledDate() {
		return scheduledDate;
	}

	public Boolean check() {
		return check.value();
	}

	public void program() {
		 program.execute();
	}

	public Task days(java.util.List<io.intino.tafat.graph.rules.DayOfWeek> values) {
		this.days = values;
		return (Task) this;
	}

	public Task scheduledDate(java.time.Instant value) {
		this.scheduledDate = value;
		return (Task) this;
	}

	public io.intino.tafat.graph.Task.Start start() {
		return start;
	}

	public io.intino.tafat.graph.Task.Finish finish() {
		return finish;
	}

	public io.intino.tafat.graph.Task.End end() {
		return end;
	}

	public io.intino.tafat.graph.Task.Duration duration() {
		return duration;
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

	public java.util.List<io.intino.tafat.graph.JobAction> jobActionList() {
		return java.util.Collections.unmodifiableList(jobActionList);
	}

	public io.intino.tafat.graph.JobAction jobAction(int index) {
		return jobActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.JobAction> jobActionList(java.util.function.Predicate<io.intino.tafat.graph.JobAction> predicate) {
		return jobActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Task.Action> actionList() {
		return java.util.Collections.unmodifiableList(actionList);
	}

	public io.intino.tafat.graph.Task.Action action(int index) {
		return actionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Task.Action> actionList(java.util.function.Predicate<io.intino.tafat.graph.Task.Action> predicate) {
		return actionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Task.StartAction> startActionList() {
		return java.util.Collections.unmodifiableList(startActionList);
	}

	public io.intino.tafat.graph.Task.StartAction startAction(int index) {
		return startActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Task.StartAction> startActionList(java.util.function.Predicate<io.intino.tafat.graph.Task.StartAction> predicate) {
		return startActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Task.RecurrentAction> recurrentActionList() {
		return java.util.Collections.unmodifiableList(recurrentActionList);
	}

	public io.intino.tafat.graph.Task.RecurrentAction recurrentAction(int index) {
		return recurrentActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Task.RecurrentAction> recurrentActionList(java.util.function.Predicate<io.intino.tafat.graph.Task.RecurrentAction> predicate) {
		return recurrentActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.Task.EndAction> endActionList() {
		return java.util.Collections.unmodifiableList(endActionList);
	}

	public io.intino.tafat.graph.Task.EndAction endAction(int index) {
		return endActionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.Task.EndAction> endActionList(java.util.function.Predicate<io.intino.tafat.graph.Task.EndAction> predicate) {
		return endActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		if (start != null) components.add(this.start.core$());
		if (finish != null) components.add(this.finish.core$());
		if (end != null) components.add(this.end.core$());
		if (duration != null) components.add(this.duration.core$());
		new java.util.ArrayList<>(taskList).forEach(c -> components.add(c.core$()));
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
		map.put("days", this.days);
		map.put("scheduledDate", new java.util.ArrayList(java.util.Collections.singletonList(this.scheduledDate)));
		map.put("check", new java.util.ArrayList(java.util.Collections.singletonList(this.check)));
		map.put("program", this.program != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.program)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("Task$Start")) this.start = node.as(io.intino.tafat.graph.Task.Start.class);
		if (node.is("Task$Finish")) this.finish = node.as(io.intino.tafat.graph.Task.Finish.class);
		if (node.is("Task$End")) this.end = node.as(io.intino.tafat.graph.Task.End.class);
		if (node.is("Task$Duration")) this.duration = node.as(io.intino.tafat.graph.Task.Duration.class);
		if (node.is("Task")) this.taskList.add(node.as(io.intino.tafat.graph.Task.class));
		if (node.is("JobAction")) this.jobActionList.add(node.as(io.intino.tafat.graph.JobAction.class));
		if (node.is("Task$Action")) this.actionList.add(node.as(io.intino.tafat.graph.Task.Action.class));
		if (node.is("Task$StartAction")) this.startActionList.add(node.as(io.intino.tafat.graph.Task.StartAction.class));
		if (node.is("Task$RecurrentAction")) this.recurrentActionList.add(node.as(io.intino.tafat.graph.Task.RecurrentAction.class));
		if (node.is("Task$EndAction")) this.endActionList.add(node.as(io.intino.tafat.graph.Task.EndAction.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("Task$Start")) this.start = null;
	        if (node.is("Task$Finish")) this.finish = null;
	        if (node.is("Task$End")) this.end = null;
	        if (node.is("Task$Duration")) this.duration = null;
	        if (node.is("Task")) this.taskList.remove(node.as(io.intino.tafat.graph.Task.class));
	        if (node.is("JobAction")) this.jobActionList.remove(node.as(io.intino.tafat.graph.JobAction.class));
	        if (node.is("Task$Action")) this.actionList.remove(node.as(io.intino.tafat.graph.Task.Action.class));
	        if (node.is("Task$StartAction")) this.startActionList.remove(node.as(io.intino.tafat.graph.Task.StartAction.class));
	        if (node.is("Task$RecurrentAction")) this.recurrentActionList.remove(node.as(io.intino.tafat.graph.Task.RecurrentAction.class));
	        if (node.is("Task$EndAction")) this.endActionList.remove(node.as(io.intino.tafat.graph.Task.EndAction.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("days")) this.days = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.DayOfWeek.class, this);
		else if (name.equalsIgnoreCase("scheduledDate")) this.scheduledDate = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("program")) this.program = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("days")) this.days = new java.util.ArrayList<>((java.util.List<io.intino.tafat.graph.rules.DayOfWeek>) values);
		else if (name.equalsIgnoreCase("scheduledDate")) this.scheduledDate = (java.time.Instant) values.get(0);
		else if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("program")) this.program = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
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

		public io.intino.tafat.graph.Task.Start start(java.time.LocalTime start) {
		    io.intino.tafat.graph.Task.Start newElement = core$().graph().concept(io.intino.tafat.graph.Task.Start.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.Start.class);
			newElement.core$().set(newElement, "start", java.util.Collections.singletonList(start));
		    return newElement;
		}

		public io.intino.tafat.graph.Task.End end(java.time.LocalTime end) {
		    io.intino.tafat.graph.Task.End newElement = core$().graph().concept(io.intino.tafat.graph.Task.End.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.End.class);
			newElement.core$().set(newElement, "end", java.util.Collections.singletonList(end));
		    return newElement;
		}

		public io.intino.tafat.graph.Task.Duration duration(int duration) {
		    io.intino.tafat.graph.Task.Duration newElement = core$().graph().concept(io.intino.tafat.graph.Task.Duration.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.Duration.class);
			newElement.core$().set(newElement, "duration", java.util.Collections.singletonList(duration));
		    return newElement;
		}

		public io.intino.tafat.graph.Task task() {
		    io.intino.tafat.graph.Task newElement = core$().graph().concept(io.intino.tafat.graph.Task.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.class);

		    return newElement;
		}

		public io.intino.tafat.graph.Task.StartAction startAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Task.StartAction newElement = core$().graph().concept(io.intino.tafat.graph.Task.StartAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.StartAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}

		public io.intino.tafat.graph.Task.RecurrentAction recurrentAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Task.RecurrentAction newElement = core$().graph().concept(io.intino.tafat.graph.Task.RecurrentAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.RecurrentAction.class);
			newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
		    return newElement;
		}

		public io.intino.tafat.graph.Task.EndAction endAction(io.intino.tafat.graph.functions.Action action) {
		    io.intino.tafat.graph.Task.EndAction newElement = core$().graph().concept(io.intino.tafat.graph.Task.EndAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.Task.EndAction.class);
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
		public void task(java.util.function.Predicate<io.intino.tafat.graph.Task> filter) {
			new java.util.ArrayList<>(taskList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void startAction(java.util.function.Predicate<io.intino.tafat.graph.Task.StartAction> filter) {
			new java.util.ArrayList<>(startActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void recurrentAction(java.util.function.Predicate<io.intino.tafat.graph.Task.RecurrentAction> filter) {
			new java.util.ArrayList<>(recurrentActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void endAction(java.util.function.Predicate<io.intino.tafat.graph.Task.EndAction> filter) {
			new java.util.ArrayList<>(endActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class Start  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.time.LocalTime start;
		protected int deviation;

		public Start(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.time.LocalTime start() {
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
			if (name.equalsIgnoreCase("start")) this.start = io.intino.tara.magritte.loaders.TimeLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("start")) this.start = (java.time.LocalTime) values.get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = (java.lang.Integer) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static abstract class Finish  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {

		public Finish(io.intino.tara.magritte.Node node) {
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

	public static class End extends io.intino.tafat.graph.Task.Finish implements io.intino.tara.magritte.tags.Terminal {
		protected java.time.LocalTime end;
		protected int deviation;

		public End(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.time.LocalTime end() {
			return end;
		}

		public int deviation() {
			return deviation;
		}

		public End deviation(int value) {
			this.deviation = value;
			return (End) this;
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
			map.put("end", new java.util.ArrayList(java.util.Collections.singletonList(this.end)));
			map.put("deviation", new java.util.ArrayList(java.util.Collections.singletonList(this.deviation)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("end")) this.end = io.intino.tara.magritte.loaders.TimeLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("end")) this.end = (java.time.LocalTime) values.get(0);
			else if (name.equalsIgnoreCase("deviation")) this.deviation = (java.lang.Integer) values.get(0);
		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Duration extends io.intino.tafat.graph.Task.Finish implements io.intino.tara.magritte.tags.Terminal {
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
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
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

	public static class StartAction extends io.intino.tafat.graph.Task.Action implements io.intino.tara.magritte.tags.Terminal {


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

	public static class RecurrentAction extends io.intino.tafat.graph.Task.Action implements io.intino.tara.magritte.tags.Terminal {


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

	public static class EndAction extends io.intino.tafat.graph.Task.Action implements io.intino.tara.magritte.tags.Terminal {


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