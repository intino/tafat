package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Event  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected java.time.Instant instantDate;
	protected io.intino.tafat.graph.functions.Action execute;

	public Event(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public java.time.Instant instantDate() {
		return instantDate;
	}

	public void execute() {
		 execute.execute();
	}

	public Event instantDate(java.time.Instant value) {
		this.instantDate = value;
		return (Event) this;
	}

	public Event execute(io.intino.tafat.graph.functions.Action value) {
		this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(execute, this, io.intino.tafat.graph.functions.Action.class);
		return (Event) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("instantDate", new java.util.ArrayList(java.util.Collections.singletonList(this.instantDate)));
		map.put("execute", this.execute != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.execute)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("instantDate")) this.instantDate = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("instantDate")) this.instantDate = (java.time.Instant) values.get(0);
		else if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
	}

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}