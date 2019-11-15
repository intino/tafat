package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Stop  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Component, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tara.magritte.Expression<java.lang.Boolean> when;
	protected io.intino.tafat.graph.functions.Action execute;

	public Stop(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Boolean when() {
		return when.value();
	}

	public void execute() {
		 execute.execute();
	}

	public Stop when(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
		this.when = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (Stop) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("when", new java.util.ArrayList(java.util.Collections.singletonList(this.when)));
		map.put("execute", this.execute != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.execute)) : java.util.Collections.emptyList());
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("when")) this.when = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tara.magritte.Expression.class).get(0);
		else if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Action.class).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("when")) this.when = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tara.magritte.Expression.class);
		else if (name.equalsIgnoreCase("execute")) this.execute = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Action.class);
	}

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}