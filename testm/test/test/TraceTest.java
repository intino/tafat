package test;

import io.intino.tafat.graph.TafatGraph;
import io.intino.tara.magritte.Graph;
import org.junit.Before;
import org.junit.Test;
import test.graph.TestGraph;

public class TraceTest {

	TafatGraph platform;
	TestGraph product;

	public static void main(String[] args) {
		Graph graph = new Graph().loadStashes("Trace");
		graph.as(TafatGraph.class).execute();
	}

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("Trace");
		this.platform = graph.as(TafatGraph.class);
		this.product = graph.as(TestGraph.class);
		this.platform.execute();
	}

	@Test
	public void should_trace_message() throws Exception {
		this.platform.execute();
	}
}
