package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class Assertion  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Component, io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.functions.Thing that;
	protected io.intino.tafat.graph.functions.Thing shouldBe;
	protected java.time.Instant at;

	public Assertion(io.intino.tara.magritte.Node node) {
		super(node);
	}

	public Object that() {
		return that.get();
	}

	public Object shouldBe() {
		return shouldBe.get();
	}

	public java.time.Instant at() {
		return at;
	}

	public Assertion that(io.intino.tafat.graph.functions.Thing value) {
		this.that = io.intino.tara.magritte.loaders.FunctionLoader.load(that, this, io.intino.tafat.graph.functions.Thing.class);
		return (Assertion) this;
	}

	public Assertion shouldBe(io.intino.tafat.graph.functions.Thing value) {
		this.shouldBe = io.intino.tara.magritte.loaders.FunctionLoader.load(shouldBe, this, io.intino.tafat.graph.functions.Thing.class);
		return (Assertion) this;
	}

	public Assertion at(java.time.Instant value) {
		this.at = value;
		return (Assertion) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("that", this.that != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.that)) : java.util.Collections.emptyList());
		map.put("shouldBe", this.shouldBe != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.shouldBe)) : java.util.Collections.emptyList());
		map.put("at", new java.util.ArrayList(java.util.Collections.singletonList(this.at)));
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("that")) this.that = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Thing.class).get(0);
		else if (name.equalsIgnoreCase("shouldBe")) this.shouldBe = io.intino.tara.magritte.loaders.FunctionLoader.load(values, this, io.intino.tafat.graph.functions.Thing.class).get(0);
		else if (name.equalsIgnoreCase("at")) this.at = io.intino.tara.magritte.loaders.InstantLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("that")) this.that = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Thing.class);
		else if (name.equalsIgnoreCase("shouldBe")) this.shouldBe = io.intino.tara.magritte.loaders.FunctionLoader.load(values.get(0), this, io.intino.tafat.graph.functions.Thing.class);
		else if (name.equalsIgnoreCase("at")) this.at = (java.time.Instant) values.get(0);
	}

	public io.intino.tafat.graph.TafatGraph graph() {
		return (io.intino.tafat.graph.TafatGraph) core$().graph().as(io.intino.tafat.graph.TafatGraph.class);
	}
}