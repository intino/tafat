package io.intino.tafat.test.model;

import io.intino.magritte.framework.Graph;

public class TestGraph extends AbstractGraph {

	public TestGraph(Graph graph) {
		super(graph);
	}

	public TestGraph(Graph graph, TestGraph wrapper) {
	    super(graph, wrapper);
	}
}