package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class StateChart extends io.intino.tafat.graph.PeriodicActivity implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Message receiveMessage;
	protected java.lang.String message;
	protected io.intino.tafat.graph.StateChart current;
	protected java.util.List<io.intino.tafat.graph.StateChart.State> stateList = new java.util.ArrayList<>();
	protected java.util.List<io.intino.tafat.graph.StateChart.Transition> transitionList = new java.util.ArrayList<>();

	public StateChart(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public void receiveMessage(String message) {
		 receiveMessage.receiveMessage(message);
	}

	public java.lang.String message() {
		return message;
	}

	public io.intino.tafat.graph.StateChart current() {
		return current;
	}

	public StateChart message(java.lang.String value) {
		this.message = value;
		return (StateChart) this;
	}

	public StateChart current(io.intino.tafat.graph.StateChart value) {
		this.current = value;
		return (StateChart) this;
	}

	public java.util.List<io.intino.tafat.graph.StateChart.State> stateList() {
		return java.util.Collections.unmodifiableList(stateList);
	}

	public io.intino.tafat.graph.StateChart.State state(int index) {
		return stateList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.StateChart.State> stateList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State> predicate) {
		return stateList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<io.intino.tafat.graph.StateChart.Transition> transitionList() {
		return java.util.Collections.unmodifiableList(transitionList);
	}

	public io.intino.tafat.graph.StateChart.Transition transition(int index) {
		return transitionList.get(index);
	}

	public java.util.List<io.intino.tafat.graph.StateChart.Transition> transitionList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.Transition> predicate) {
		return transitionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}



	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
		new java.util.ArrayList<>(stateList).forEach(c -> components.add(c.core$()));
		new java.util.ArrayList<>(transitionList).forEach(c -> components.add(c.core$()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("receiveMessage", this.receiveMessage != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.receiveMessage)) : java.util.Collections.emptyList());
		map.put("message", new java.util.ArrayList(java.util.Collections.singletonList(this.message)));
		map.put("current", this.current != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.current)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void addNode$(io.intino.tara.magritte.Node node) {
		super.addNode$(node);
		if (node.is("StateChart$State")) this.stateList.add(node.as(io.intino.tafat.graph.StateChart.State.class));
		if (node.is("StateChart$Transition")) this.transitionList.add(node.as(io.intino.tafat.graph.StateChart.Transition.class));
	}

	@Override
	    protected void removeNode$(io.intino.tara.magritte.Node node) {
	        super.removeNode$(node);
	        if (node.is("StateChart$State")) this.stateList.remove(node.as(io.intino.tafat.graph.StateChart.State.class));
	        if (node.is("StateChart$Transition")) this.transitionList.remove(node.as(io.intino.tafat.graph.StateChart.Transition.class));
	    }

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("receiveMessage")) this.receiveMessage = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Message.class).get(0);
		else if (name.equalsIgnoreCase("message")) this.message = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("current")) this.current = io.intino.tara.magritte.loaders.NodeLoader.load(values, io.intino.tafat.graph.StateChart.class, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("receiveMessage")) this.receiveMessage = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Message.class);
		else if (name.equalsIgnoreCase("message")) this.message = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("current")) this.current = values.get(0)!= null ? core$().graph().load(((io.intino.tara.magritte.Layer) values.get(0)).core$().id()).as(io.intino.tafat.graph.StateChart.class) : null;
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends io.intino.tafat.graph.PeriodicActivity.Create {


		public Create(java.lang.String name) {
			super(name);
		}

		public io.intino.tafat.graph.StateChart.State state() {
		    io.intino.tafat.graph.StateChart.State newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.State.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.State.class);

		    return newElement;
		}

		public io.intino.tafat.graph.StateChart.Transition transition(io.intino.tafat.graph.StateChart.State from, io.intino.tafat.graph.StateChart.State to) {
		    io.intino.tafat.graph.StateChart.Transition newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.class);
			newElement.core$().set(newElement, "from", java.util.Collections.singletonList(from));
			newElement.core$().set(newElement, "to", java.util.Collections.singletonList(to));
		    return newElement;
		}

	}

	public Clear clear() {
		return new Clear();
	}

	public class Clear  {
		public void state(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State> filter) {
			new java.util.ArrayList<>(stateList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}

		public void transition(java.util.function.Predicate<io.intino.tafat.graph.StateChart.Transition> filter) {
			new java.util.ArrayList<>(transitionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
		}
	}

	public static class State extends io.intino.tafat.graph.StateChart implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {

		protected java.util.List<io.intino.tafat.graph.StateChart.State.Action> actionList = new java.util.ArrayList<>();
		protected java.util.List<io.intino.tafat.graph.StateChart.State.EntryAction> entryActionList = new java.util.ArrayList<>();
		protected java.util.List<io.intino.tafat.graph.StateChart.State.ExitAction> exitActionList = new java.util.ArrayList<>();
		protected java.util.List<io.intino.tafat.graph.StateChart.State.PeriodicAction> periodicActionList = new java.util.ArrayList<>();

		public State(io.intino.tara.magritte.Node node) {
			super(node);
		}





		public java.util.List<io.intino.tafat.graph.StateChart.State.Action> actionList() {
			return java.util.Collections.unmodifiableList(actionList);
		}

		public io.intino.tafat.graph.StateChart.State.Action action(int index) {
			return actionList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.Action> actionList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.Action> predicate) {
			return actionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.EntryAction> entryActionList() {
			return java.util.Collections.unmodifiableList(entryActionList);
		}

		public io.intino.tafat.graph.StateChart.State.EntryAction entryAction(int index) {
			return entryActionList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.EntryAction> entryActionList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.EntryAction> predicate) {
			return entryActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.ExitAction> exitActionList() {
			return java.util.Collections.unmodifiableList(exitActionList);
		}

		public io.intino.tafat.graph.StateChart.State.ExitAction exitAction(int index) {
			return exitActionList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.ExitAction> exitActionList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.ExitAction> predicate) {
			return exitActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.PeriodicAction> periodicActionList() {
			return java.util.Collections.unmodifiableList(periodicActionList);
		}

		public io.intino.tafat.graph.StateChart.State.PeriodicAction periodicAction(int index) {
			return periodicActionList.get(index);
		}

		public java.util.List<io.intino.tafat.graph.StateChart.State.PeriodicAction> periodicActionList(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.PeriodicAction> predicate) {
			return periodicActionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			new java.util.ArrayList<>(actionList).forEach(c -> components.add(c.core$()));
			new java.util.ArrayList<>(entryActionList).forEach(c -> components.add(c.core$()));
			new java.util.ArrayList<>(exitActionList).forEach(c -> components.add(c.core$()));
			new java.util.ArrayList<>(periodicActionList).forEach(c -> components.add(c.core$()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());

			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);
			if (node.is("StateChart$State$Action")) this.actionList.add(node.as(io.intino.tafat.graph.StateChart.State.Action.class));
			if (node.is("StateChart$State$EntryAction")) this.entryActionList.add(node.as(io.intino.tafat.graph.StateChart.State.EntryAction.class));
			if (node.is("StateChart$State$ExitAction")) this.exitActionList.add(node.as(io.intino.tafat.graph.StateChart.State.ExitAction.class));
			if (node.is("StateChart$State$PeriodicAction")) this.periodicActionList.add(node.as(io.intino.tafat.graph.StateChart.State.PeriodicAction.class));
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("StateChart$State$Action")) this.actionList.remove(node.as(io.intino.tafat.graph.StateChart.State.Action.class));
		        if (node.is("StateChart$State$EntryAction")) this.entryActionList.remove(node.as(io.intino.tafat.graph.StateChart.State.EntryAction.class));
		        if (node.is("StateChart$State$ExitAction")) this.exitActionList.remove(node.as(io.intino.tafat.graph.StateChart.State.ExitAction.class));
		        if (node.is("StateChart$State$PeriodicAction")) this.periodicActionList.remove(node.as(io.intino.tafat.graph.StateChart.State.PeriodicAction.class));
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

		public class Create extends io.intino.tafat.graph.StateChart.Create {


			public Create(java.lang.String name) {
				super(name);
			}

			public io.intino.tafat.graph.StateChart.State.EntryAction entryAction(io.intino.tafat.graph.functions.Action action) {
			    io.intino.tafat.graph.StateChart.State.EntryAction newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.State.EntryAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.State.EntryAction.class);
				newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.State.ExitAction exitAction(io.intino.tafat.graph.functions.Action action) {
			    io.intino.tafat.graph.StateChart.State.ExitAction newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.State.ExitAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.State.ExitAction.class);
				newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.State.PeriodicAction periodicAction(io.intino.tafat.graph.functions.Action action) {
			    io.intino.tafat.graph.StateChart.State.PeriodicAction newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.State.PeriodicAction.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.State.PeriodicAction.class);
				newElement.core$().set(newElement, "action", java.util.Collections.singletonList(action));
			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear extends io.intino.tafat.graph.StateChart.Clear {
			public void entryAction(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.EntryAction> filter) {
				new java.util.ArrayList<>(entryActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}

			public void exitAction(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.ExitAction> filter) {
				new java.util.ArrayList<>(exitActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
			}

			public void periodicAction(java.util.function.Predicate<io.intino.tafat.graph.StateChart.State.PeriodicAction> filter) {
				new java.util.ArrayList<>(periodicActionList()).stream().filter(filter).forEach(io.intino.tara.magritte.Layer::delete$);
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

		public static class EntryAction extends io.intino.tafat.graph.StateChart.State.Action implements io.intino.tara.magritte.tags.Terminal {


			public EntryAction(io.intino.tara.magritte.Node node) {
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

		public static class ExitAction extends io.intino.tafat.graph.StateChart.State.Action implements io.intino.tara.magritte.tags.Terminal {


			public ExitAction(io.intino.tara.magritte.Node node) {
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

		public static class PeriodicAction extends io.intino.tafat.graph.StateChart.State.Action implements io.intino.tara.magritte.tags.Terminal {


			public PeriodicAction(io.intino.tara.magritte.Node node) {
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

	public static class Transition  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tafat.graph.StateChart.State from;
		protected io.intino.tafat.graph.StateChart.State to;
		protected io.intino.tafat.graph.functions.Action action;
		protected io.intino.tafat.graph.StateChart.Transition.Trigger trigger;
		protected io.intino.tafat.graph.StateChart.Transition.Condition condition;
		protected io.intino.tafat.graph.StateChart.Transition.TimeBased timeBased;
		protected io.intino.tafat.graph.StateChart.Transition.Timeout timeout;
		protected io.intino.tafat.graph.StateChart.Transition.After after;
		protected io.intino.tafat.graph.StateChart.Transition.Rate rate;
		protected io.intino.tafat.graph.StateChart.Transition.Message message;

		public Transition(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public io.intino.tafat.graph.StateChart.State from() {
			return from;
		}

		public io.intino.tafat.graph.StateChart.State to() {
			return to;
		}

		public void action() {
			 action.execute();
		}

		public Transition action(io.intino.tafat.graph.functions.Action value) {
			this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(action, this, io.intino.tafat.graph.functions.Action.class);
			return (Transition) this;
		}

		public io.intino.tafat.graph.StateChart.Transition.Trigger trigger() {
			return trigger;
		}

		public io.intino.tafat.graph.StateChart.Transition.Condition condition() {
			return condition;
		}

		public io.intino.tafat.graph.StateChart.Transition.TimeBased timeBased() {
			return timeBased;
		}

		public io.intino.tafat.graph.StateChart.Transition.Timeout timeout() {
			return timeout;
		}

		public io.intino.tafat.graph.StateChart.Transition.After after() {
			return after;
		}

		public io.intino.tafat.graph.StateChart.Transition.Rate rate() {
			return rate;
		}

		public io.intino.tafat.graph.StateChart.Transition.Message message() {
			return message;
		}



		protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
			java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());
			if (trigger != null) components.add(this.trigger.core$());
			if (condition != null) components.add(this.condition.core$());
			if (timeBased != null) components.add(this.timeBased.core$());
			if (timeout != null) components.add(this.timeout.core$());
			if (after != null) components.add(this.after.core$());
			if (rate != null) components.add(this.rate.core$());
			if (message != null) components.add(this.message.core$());
			return new java.util.ArrayList<>(components);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("from", this.from != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.from)) : java.util.Collections.emptyList());
			map.put("to", this.to != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.to)) : java.util.Collections.emptyList());
			map.put("action", this.action != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.action)) : java.util.Collections.emptyList());
			return map;
		}

		@Override
		protected void addNode$(io.intino.tara.magritte.Node node) {
			super.addNode$(node);
			if (node.is("StateChart$Transition$Trigger")) this.trigger = node.as(io.intino.tafat.graph.StateChart.Transition.Trigger.class);
			if (node.is("StateChart$Transition$Condition")) this.condition = node.as(io.intino.tafat.graph.StateChart.Transition.Condition.class);
			if (node.is("StateChart$Transition$TimeBased")) this.timeBased = node.as(io.intino.tafat.graph.StateChart.Transition.TimeBased.class);
			if (node.is("StateChart$Transition$Timeout")) this.timeout = node.as(io.intino.tafat.graph.StateChart.Transition.Timeout.class);
			if (node.is("StateChart$Transition$After")) this.after = node.as(io.intino.tafat.graph.StateChart.Transition.After.class);
			if (node.is("StateChart$Transition$Rate")) this.rate = node.as(io.intino.tafat.graph.StateChart.Transition.Rate.class);
			if (node.is("StateChart$Transition$Message")) this.message = node.as(io.intino.tafat.graph.StateChart.Transition.Message.class);
		}

		@Override
		    protected void removeNode$(io.intino.tara.magritte.Node node) {
		        super.removeNode$(node);
		        if (node.is("StateChart$Transition$Trigger")) this.trigger = null;
		        if (node.is("StateChart$Transition$Condition")) this.condition = null;
		        if (node.is("StateChart$Transition$TimeBased")) this.timeBased = null;
		        if (node.is("StateChart$Transition$Timeout")) this.timeout = null;
		        if (node.is("StateChart$Transition$After")) this.after = null;
		        if (node.is("StateChart$Transition$Rate")) this.rate = null;
		        if (node.is("StateChart$Transition$Message")) this.message = null;
		    }

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);
			if (name.equalsIgnoreCase("from")) this.from = io.intino.tara.magritte.loaders.NodeLoader.load(values, io.intino.tafat.graph.StateChart.State.class, this).get(0);
			else if (name.equalsIgnoreCase("to")) this.to = io.intino.tara.magritte.loaders.NodeLoader.load(values, io.intino.tafat.graph.StateChart.State.class, this).get(0);
			else if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);
			if (name.equalsIgnoreCase("from")) this.from = values.get(0)!= null ? core$().graph().load(((io.intino.tara.magritte.Layer) values.get(0)).core$().id()).as(io.intino.tafat.graph.StateChart.State.class) : null;
			else if (name.equalsIgnoreCase("to")) this.to = values.get(0)!= null ? core$().graph().load(((io.intino.tara.magritte.Layer) values.get(0)).core$().id()).as(io.intino.tafat.graph.StateChart.State.class) : null;
			else if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
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

			public io.intino.tafat.graph.StateChart.Transition.Condition condition(io.intino.tafat.graph.functions.CheckTransition check) {
			    io.intino.tafat.graph.StateChart.Transition.Condition newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.Condition.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.Condition.class);
				newElement.core$().set(newElement, "check", java.util.Collections.singletonList(check));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.Transition.Timeout timeout(io.intino.tara.magritte.Expression<java.lang.Integer> timeout) {
			    io.intino.tafat.graph.StateChart.Transition.Timeout newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.Timeout.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.Timeout.class);
				newElement.core$().set(newElement, "timeout", java.util.Collections.singletonList(timeout));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.Transition.After after(int fixedTime) {
			    io.intino.tafat.graph.StateChart.Transition.After newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.After.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.After.class);
				newElement.core$().set(newElement, "fixedTime", java.util.Collections.singletonList(fixedTime));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.Transition.Rate rate(int times, int unit) {
			    io.intino.tafat.graph.StateChart.Transition.Rate newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.Rate.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.Rate.class);
				newElement.core$().set(newElement, "times", java.util.Collections.singletonList(times));
				newElement.core$().set(newElement, "unit", java.util.Collections.singletonList(unit));
			    return newElement;
			}

			public io.intino.tafat.graph.StateChart.Transition.Message message(java.lang.String expectedMessage) {
			    io.intino.tafat.graph.StateChart.Transition.Message newElement = core$().graph().concept(io.intino.tafat.graph.StateChart.Transition.Message.class).createNode(this.name, core$()).as(io.intino.tafat.graph.StateChart.Transition.Message.class);
				newElement.core$().set(newElement, "expectedMessage", java.util.Collections.singletonList(expectedMessage));
			    return newElement;
			}

		}

		public Clear clear() {
			return new Clear();
		}

		public class Clear  {

		}

		public static abstract class Trigger  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
			protected io.intino.tafat.graph.functions.CheckTransition check;

			public Trigger(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public boolean check(int advancedTime) {
				return check.check(advancedTime);
			}

			public Trigger check(io.intino.tafat.graph.functions.CheckTransition value) {
				this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(check, this, io.intino.tafat.graph.functions.CheckTransition.class);
				return (Trigger) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("check", this.check != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.check)) : java.util.Collections.emptyList());
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.CheckTransition.class).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.CheckTransition.class);
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

		public static class Condition extends io.intino.tafat.graph.StateChart.Transition.Trigger implements io.intino.tara.magritte.tags.Terminal {


			public Condition(io.intino.tara.magritte.Node node) {
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

		public static abstract class TimeBased extends io.intino.tafat.graph.StateChart.Transition.Trigger implements io.intino.tara.magritte.tags.Terminal {
			protected java.time.Instant when;
			protected io.intino.tafat.graph.functions.Action activate;

			public TimeBased(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public java.time.Instant when() {
				return when;
			}

			public void activate() {
				 activate.execute();
			}

			public TimeBased when(java.time.Instant value) {
				this.when = value;
				return (TimeBased) this;
			}

			public TimeBased activate(io.intino.tafat.graph.functions.Action value) {
				this.activate = io.intino.tara.magritte.loaders.FunctionLoader.load(activate, this, io.intino.tafat.graph.functions.Action.class);
				return (TimeBased) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("when", new java.util.ArrayList(java.util.Collections.singletonList(this.when)));
				map.put("activate", this.activate != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.activate)) : java.util.Collections.emptyList());
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("when")) this.when = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("activate")) this.activate = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("when")) this.when = (java.time.Instant) values.get(0);
				else if (name.equalsIgnoreCase("activate")) this.activate = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}

		public static class Timeout extends io.intino.tafat.graph.StateChart.Transition.TimeBased implements io.intino.tara.magritte.tags.Terminal {
			protected io.intino.tara.magritte.Expression<java.lang.Integer> timeout;

			public Timeout(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public Integer timeout() {
				return timeout.value();
			}



			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("timeout", new java.util.ArrayList(java.util.Collections.singletonList(this.timeout)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}

		public static class After extends io.intino.tafat.graph.StateChart.Transition.TimeBased implements io.intino.tara.magritte.tags.Terminal {
			protected int fixedTime;

			public After(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public int fixedTime() {
				return fixedTime;
			}



			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("fixedTime", new java.util.ArrayList(java.util.Collections.singletonList(this.fixedTime)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("fixedTime")) this.fixedTime = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("fixedTime")) this.fixedTime = (java.lang.Integer) values.get(0);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}

		public static class Rate extends io.intino.tafat.graph.StateChart.Transition.TimeBased implements io.intino.tara.magritte.tags.Terminal {
			protected int times;
			protected int unit;

			public Rate(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public int times() {
				return times;
			}

			public int unit() {
				return unit;
			}

			public Rate times(int value) {
				this.times = value;
				return (Rate) this;
			}

			public Rate unit(int value) {
				this.unit = value;
				return (Rate) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("times", new java.util.ArrayList(java.util.Collections.singletonList(this.times)));
				map.put("unit", new java.util.ArrayList(java.util.Collections.singletonList(this.unit)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("times")) this.times = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("unit")) this.unit = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("times")) this.times = (java.lang.Integer) values.get(0);
				else if (name.equalsIgnoreCase("unit")) this.unit = (java.lang.Integer) values.get(0);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
			}
		}

		public static class Message extends io.intino.tafat.graph.StateChart.Transition.Trigger implements io.intino.tara.magritte.tags.Terminal {
			protected java.lang.String expectedMessage;

			public Message(io.intino.tara.magritte.Node node) {
				super(node);
			}

			public java.lang.String expectedMessage() {
				return expectedMessage;
			}

			public Message expectedMessage(java.lang.String value) {
				this.expectedMessage = value;
				return (Message) this;
			}

			@Override
			protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
				map.put("expectedMessage", new java.util.ArrayList(java.util.Collections.singletonList(this.expectedMessage)));
				return map;
			}

			@Override
			protected void load$(java.lang.String name, java.util.List<?> values) {
				super.load$(name, values);
				if (name.equalsIgnoreCase("expectedMessage")) this.expectedMessage = io.intino.tara.magritte.loaders.StringLoader.load(values, this).get(0);
			}

			@Override
			protected void set$(java.lang.String name, java.util.List<?> values) {
				super.set$(name, values);
				if (name.equalsIgnoreCase("expectedMessage")) this.expectedMessage = (java.lang.String) values.get(0);
			}

			public io.intino.tafat.graph.TafatGraph graph() {
				return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
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