package test.graph;

import io.intino.tara.magritte.Graph;

public class TestGraph extends test.graph.AbstractGraph {

	public TestGraph(Graph graph) {
		super(graph);
	}

	public TestGraph(io.intino.tara.magritte.Graph graph, TestGraph wrapper) {
	    super(graph, wrapper);
	}
}