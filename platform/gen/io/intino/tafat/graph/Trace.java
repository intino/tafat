package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Trace  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Component, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Trace print;
	protected io.intino.tafat.graph.Trace.Periodic _periodic;
	protected io.intino.tafat.graph.Trace.Instant _instant;
	protected io.intino.tafat.graph.Trace.Conditional _conditional;

	public Trace(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public String print() {
		return print.message();
	}

	public Trace print(io.intino.tafat.graph.functions.Trace value) {
		this.print = io.intino.tara.magritte.loaders.FunctionLoader.load(print, this, io.intino.tafat.graph.functions.Trace.class);
		return (Trace) this;
	}





	public io.intino.tafat.graph.Trace.Periodic asPeriodic() {
		return a$(io.intino.tafat.graph.Trace.Periodic.class);
	}

	public io.intino.tafat.graph.Trace.Periodic asPeriodic(io.intino.tafat.graph.functions.Trace print) {
		io.intino.tafat.graph.Trace.Periodic newElement = core$().addAspect(io.intino.tafat.graph.Trace.Periodic.class);
		newElement.core$().set(newElement, "print", java.util.Collections.singletonList(print));
	    return newElement;
	}

	public boolean isPeriodic() {
		return core$().is(io.intino.tafat.graph.Trace.Periodic.class);
	}

	public void removePeriodic() {
		core$().removeAspect(io.intino.tafat.graph.Trace.Periodic.class);
	}

	public io.intino.tafat.graph.Trace.Instant asInstant() {
		return a$(io.intino.tafat.graph.Trace.Instant.class);
	}

	public io.intino.tafat.graph.Trace.Instant asInstant(io.intino.tafat.graph.functions.Trace print) {
		io.intino.tafat.graph.Trace.Instant newElement = core$().addAspect(io.intino.tafat.graph.Trace.Instant.class);
		newElement.core$().set(newElement, "print", java.util.Collections.singletonList(print));
	    return newElement;
	}

	public boolean isInstant() {
		return core$().is(io.intino.tafat.graph.Trace.Instant.class);
	}

	public void removeInstant() {
		core$().removeAspect(io.intino.tafat.graph.Trace.Instant.class);
	}

	public io.intino.tafat.graph.Trace.Conditional asConditional() {
		return a$(io.intino.tafat.graph.Trace.Conditional.class);
	}

	public io.intino.tafat.graph.Trace.Conditional asConditional(io.intino.tafat.graph.functions.Trace print) {
		io.intino.tafat.graph.Trace.Conditional newElement = core$().addAspect(io.intino.tafat.graph.Trace.Conditional.class);
		newElement.core$().set(newElement, "print", java.util.Collections.singletonList(print));
	    return newElement;
	}

	public boolean isConditional() {
		return core$().is(io.intino.tafat.graph.Trace.Conditional.class);
	}

	public void removeConditional() {
		core$().removeAspect(io.intino.tafat.graph.Trace.Conditional.class);
	}

	protected java.util.List<io.intino.tara.magritte.Node> componentList$() {
		java.util.Set<io.intino.tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList$());

		return new java.util.ArrayList<>(components);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("print", this.print != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.print)) : java.util.Collections.emptyList());
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
		if (name.equalsIgnoreCase("print")) this.print = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Trace.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("print")) this.print = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Trace.class);
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

	public static class Periodic  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected int timeout;
		protected io.intino.tara.magritte.Expression<java.lang.Boolean> checkStep;
		protected io.intino.tafat.graph.rules.TimeScale timeScale;
		protected io.intino.tafat.graph.Trace _trace;

		public Periodic(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public int timeout() {
			return timeout;
		}

		public Boolean checkStep() {
			return checkStep.value();
		}

		public io.intino.tafat.graph.rules.TimeScale timeScale() {
			return timeScale;
		}

		public String print() {
			return _trace.print();
		}

		public Periodic timeout(int value) {
			this.timeout = value;
			return (Periodic) this;
		}

		public Periodic checkStep(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
			this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Periodic) this;
		}
		public io.intino.tafat.graph.Trace asTrace() {
			return (io.intino.tafat.graph.Trace) a$(io.intino.tafat.graph.Trace.class);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("timeout", new java.util.ArrayList(java.util.Collections.singletonList(this.timeout)));
			map.put("checkStep", new java.util.ArrayList(java.util.Collections.singletonList(this.checkStep)));
			map.put("timeScale", new java.util.ArrayList(java.util.Collections.singletonList(this.timeScale)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

			if (name.equalsIgnoreCase("timeout")) this.timeout = io.intino.tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
			else if (name.equalsIgnoreCase("timeScale")) this.timeScale = io.intino.tara.magritte.loaders.WordLoader.load(values, io.intino.tafat.graph.rules.TimeScale.class, this).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			if (name.equalsIgnoreCase("timeout")) this.timeout = (java.lang.Integer) values.get(0);
			else if (name.equalsIgnoreCase("checkStep")) this.checkStep = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
			else if (name.equalsIgnoreCase("timeScale")) this.timeScale = (io.intino.tafat.graph.rules.TimeScale) values.get(0);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof io.intino.tafat.graph.Trace) _trace = (io.intino.tafat.graph.Trace) layer;

		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Instant  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected java.util.List<java.time.Instant> instants = new java.util.ArrayList<>();
		protected io.intino.tafat.graph.Trace _trace;

		public Instant(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public java.util.List<java.time.Instant> instants() {
			return instants;
		}

		public java.time.Instant instants(int index) {
			return instants.get(index);
		}

		public java.util.List<java.time.Instant> instants(java.util.function.Predicate<java.time.Instant> predicate) {
			return instants().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public String print() {
			return _trace.print();
		}


		public io.intino.tafat.graph.Trace asTrace() {
			return (io.intino.tafat.graph.Trace) a$(io.intino.tafat.graph.Trace.class);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("instants", this.instants);
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

			if (name.equalsIgnoreCase("instants")) this.instants = io.intino.tara.magritte.loaders.InstantLoader.load(values, this);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			if (name.equalsIgnoreCase("instants")) this.instants = new java.util.ArrayList<>((java.util.List<java.time.Instant>) values);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof io.intino.tafat.graph.Trace) _trace = (io.intino.tafat.graph.Trace) layer;

		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}

	public static class Conditional  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
		protected io.intino.tara.magritte.Expression<java.lang.Boolean> check;
		protected io.intino.tafat.graph.Trace _trace;

		public Conditional(io.intino.tara.magritte.Node node) {
			super(node);
		}

		public Boolean check() {
			return check.value();
		}

		public String print() {
			return _trace.print();
		}

		public Conditional check(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
			this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
			return (Conditional) this;
		}
		public io.intino.tafat.graph.Trace asTrace() {
			return (io.intino.tafat.graph.Trace) a$(io.intino.tafat.graph.Trace.class);
		}

		@Override
		protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("check", new java.util.ArrayList(java.util.Collections.singletonList(this.check)));
			return map;
		}

		@Override
		protected void load$(java.lang.String name, java.util.List<?> values) {
			super.load$(name, values);

			if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		}

		@Override
		protected void set$(java.lang.String name, java.util.List<?> values) {
			super.set$(name, values);

			if (name.equalsIgnoreCase("check")) this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		}

		@Override
		protected void sync$(io.intino.tara.magritte.Layer layer) {
			super.sync$(layer);
		    if (layer instanceof io.intino.tafat.graph.Trace) _trace = (io.intino.tafat.graph.Trace) layer;

		}

		public io.intino.tafat.graph.TafatGraph graph() {
			return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
		}
	}


	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}