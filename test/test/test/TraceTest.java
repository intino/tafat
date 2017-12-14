package test;

import io.intino.tafat.graph.TafatGraph;
import org.junit.Before;
import org.junit.Test;
import tara.magritte.Graph;

public class TraceTest {

	TafatGraph platform;
	TestApplication application;

	public static void main(String[] args) {
		Graph model = Graph.load("Trace").wrap(TestApplication.class, TafatGraph.class);
		model.platform().execute();
	}

	@Test
	public void should_trace_message() throws Exception {
		this.platform.execute();
	}

	@Before
	public void setUp() {
		Graph model = Graph.load("Trace").wrap(TestApplication.class, TafatGraph.class);
		this.platform = model.<TafatGraph>platform();
		this.application = model.<TestApplication>application();
		this.platform.execute();
	}
}
