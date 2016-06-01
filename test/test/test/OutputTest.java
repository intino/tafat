package test;

import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Graph;
import test.TestApplication;

public class OutputTest {

	TafatPlatform engine;
	TestApplication domain;

	@Before
	public void setUp() {
		Graph model = Graph.load("Output").wrap(TestApplication.class, TafatPlatform.class);
		this.engine = model.platform();
		this.domain = model.application();
	}

	@Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
