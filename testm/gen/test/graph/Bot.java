package test.graph;

import test.graph.*;

public class Bot  extends io.intino.tara.magritte.Layer implements io.intino.tara.magritte.tags.Terminal {
	protected io.intino.tafat.graph.Agent.Listener _listener;

	public Bot(io.intino.tara.magritte.Node node) {
		super(node);
		_listener = a$(io.intino.tafat.graph.Agent.Listener.class);
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		core$().load(_listener, name, values);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		core$().set(_listener, name, values);
	}

	public test.graph.TestGraph graph() {
		return (test.graph.TestGraph) core$().graph().as(test.graph.TestGraph.class);
	}
}