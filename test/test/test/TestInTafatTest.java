package test;

import io.intino.tafat.Tafat;
import org.junit.Before;
import io.intino.tafat.engine.Executor;
import io.intino.tara.magritte.Graph;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class TestInTafatTest {

	Tafat platform;
	Test application;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("TestInTafat");
		this.platform = model.wrapper(Tafat.class);
		this.application = model.wrapper(Test.class);
	}

	@org.junit.Test
	public void test_assertion_out() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		StreamHandler handler = new StreamHandler(outputStream, new SimpleFormatter());
		Logger.getLogger(Executor.class.getName()).addHandler(handler);
		platform.execute();
		handler.flush();
		assertThat(outputStream.toString(), containsString("assertion a3 failed"));
	}
}
