package io.intino.tafat.graph;

import io.intino.tafat.graph.*;

public class RecurrentJob extends io.intino.tafat.graph.JobAction implements io.intino.tara.magritte.tags.Feature, io.intino.tara.magritte.tags.Terminal {


	public RecurrentJob(io.intino.tara.magritte.Node node) {
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