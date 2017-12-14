package test;

import io.intino.tafat.graph.TafatGraph;
import org.junit.Before;
import org.junit.Test;
import tara.magritte.Graph;

public class OutputTest {

	TafatGraph engine;
	TestApplication domain;

	@Before
	public void setUp() {
		Graph model = Graph.load("Output").wrap(TestApplication.class, TafatGraph.class);
		this.engine = model.platform();
		this.domain = model.application();
	}

	@Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
