package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Rule  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tara.magritte.Expression<java.lang.Boolean> check;

	public Rule(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Boolean check() {
		return check.value();
	}

	public Rule check(io.intino.tara.magritte.Expression<java.lang.Boolean> value) {
		this.check = io.intino.tara.magritte.loaders.FunctionLoader.load(value, this, io.intino.tara.magritte.Expression.class);
		return (Rule) this;
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

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}