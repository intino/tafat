package test;

import io.intino.tafat.engine.Executor;
import io.intino.tafat.model.TafatGraph;
import io.intino.magritte.framework.Graph;
import org.junit.Before;
import org.junit.Test;
import io.intino.tafat.test.model.TestGraph;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class TestInTafatTest {

	TafatGraph platform;
	TestGraph product;

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("TestInTafat");
		this.platform = graph.as(TafatGraph.class);
		this.product = graph.as(TestGraph.class);
	}

	@Test
	public void test_assertion_out() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		Logger.getLogger(Executor.class.getName()).addHandler(handler);
		platform.execute();
		handler.flush();
		assertThat(outputStream.toString(), containsString("assertion a3 failed"));
	}
}
