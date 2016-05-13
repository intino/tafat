package test;

import org.junit.Before;
import org.junit.Test;
import tafat.engine.Executor;
import tafat.TafatPlatform;
import tara.magritte.Graph;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class TestInTafatTest {

	TafatPlatform platform;
	TestApplication application;

	@Before
	public void setUp() {
		Graph model = Graph.load("TestInTafat").wrap(TestApplication.class, TafatPlatform.class);
		this.platform = model.<TafatPlatform>platform();
		this.application = model.<TestApplication>application();
		this.platform.execute();
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
