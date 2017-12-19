package test;

import io.intino.tafat.graph.TafatGraph;
import org.junit.Before;
import io.intino.tara.magritte.Graph;
import org.junit.Test;
import test.graph.TestGraph;

public class OutputTest {

	private TafatGraph engine;
	private TestGraph product;

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("Output");
		this.engine = graph.as(TafatGraph.class);
		this.product = graph.as(TestGraph.class);
	}

	@Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
