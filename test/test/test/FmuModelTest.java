package test;

import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Graph;

import static junit.framework.TestCase.assertEquals;
import static test.Fridge.Mode.Off;
import static test.Fridge.Mode.On;

public class FmuModelTest {

	TafatPlatform platform;
	TestApplication application;

	@Test
	public void should_execute_fmu_properly() throws Exception {
		Graph model = Graph.load("FmuModel").wrap(TestApplication.class, TafatPlatform.class);
		this.platform = model.platform();
		this.application = model.application();
		platform.init();

		assertEquals(0, application.fridge(0).power(), 0.1);
		for (int i = 0; i < 10; i++) platform.run();
		assertEquals(0, application.fridge(0).power(), 0.1);

		application.fridge(0).mode(On);
		platform.run();
		assertEquals(100.0, application.fridge(0).power(), 0.1);
		for (int i = 0; i < 9; i++) platform.run();
		assertEquals(100.0, application.fridge(0).power(), 0.1);
		platform.run();
		assertEquals(0, application.fridge(0).power(), 0.1);
		for (int i = 0; i < 12; i++) platform.run();
		assertEquals(100, application.fridge(0).power(), 0.1);

		application.fridge(0).mode(Off);
		platform.run();
		assertEquals(0, application.fridge(0).power(), 0.1);
	}
}
