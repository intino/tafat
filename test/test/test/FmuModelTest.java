package test;

import io.intino.tafat.Tafat;
import io.intino.tara.magritte.Graph;

import static junit.framework.TestCase.assertEquals;
import static test.Fridge.Mode.Off;
import static test.Fridge.Mode.On;

public class FmuModelTest {

	private Tafat platform;
	private Test application;

	@org.junit.Test
	public void should_execute_fmu_properly() throws Exception {
		Graph model = Graph.use(Test.class, Tafat.class).load("FmuModel");
		this.platform = model.wrapper(Tafat.class);
		this.application= model.wrapper(Test.class);
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
