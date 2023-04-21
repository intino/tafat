package test.test.graph;

import io.intino.magritte.framework.Graph;
import io.intino.tafat.test.model.AbstractGraph;

public class TestGraph extends AbstractGraph {

	public TestGraph(Graph graph) {
		super(graph);
	}

	public TestGraph(Graph graph, TestGraph wrapper) {
		super(graph, wrapper);
	}
}