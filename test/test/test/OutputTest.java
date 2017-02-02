package test;

import io.intino.tafat.Tafat;
import org.junit.Before;
import io.intino.tara.magritte.Graph;

public class OutputTest {

	private Tafat engine;
	private test.Test domain;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("Output");
		this.engine = model.wrapper(Tafat.class);
		this.domain = model.wrapper(Test.class);
	}

	@org.junit.Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
