package test;

import io.intino.tafat.TafatGraph;
import org.junit.Before;
import io.intino.tara.magritte.Graph;
import org.junit.Test;

public class TraceTest {

	TafatGraph platform;
	TestGraph product;

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

	public static void main(String[] args) {
		Graph graph = new Graph().loadStashes("Trace");
		graph.as(TafatGraph.class).execute();
	}
}
