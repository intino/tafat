package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class ConditionalAction extends io.intino.tafat.graph.PeriodicActivity implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tara.magritte.Expression<java.lang.Boolean> condition;
	protected io.intino.tafat.graph.functions.Action action;

	public ConditionalAction(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Boolean condition() {
		return condition.value();
	}

	public void action() {
		 action.execute();
	}

	public ConditionalAction condition(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
		this.condition = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (ConditionalAction) this;
	}

	public ConditionalAction action(io.intino.tafat.graph.functions.Action value) {
		this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(action, this, io.intino.tafat.graph.functions.Action.class);
		return (ConditionalAction) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables$());
		map.put("condition", new java.util.ArrayList(java.util.Collections.singletonList(this.condition)));
		map.put("action", this.action != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.action)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("condition")) this.condition = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("condition")) this.condition = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("action")) this.action = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
	}

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}