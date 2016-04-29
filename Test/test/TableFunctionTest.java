import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Graph;
import test.Fridge;
import test.TestApplication;
import test.electrical5.Electrical5Fridge;
import test.electrical6.Electrical6Fridge;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TableFunctionTest {

	TafatPlatform platform;
	TestApplication application;

	@Before
	public void setUp() {
		Graph graph = Graph.load("TableFunction").wrap(TestApplication.class, TafatPlatform.class);
		this.platform = graph.platform();
		this.application = graph.application();
		this.platform.execute();
	}

	@Test
	public void oneDimension() throws Exception {
		Fridge f6 = application.fridgeList(f -> f.name().equals("f6")).get(0);
		assertThat(f6.as(Electrical6Fridge.class).watts().get(20), is(1.1));
		assertEquals(1.67, f6.as(Electrical6Fridge.class).watts().get(25), 0.01);
	}

	@Test
	public void severalDimensions() throws Exception {
		Fridge f5 = application.fridgeList(f -> f.name().equals("f5")).get(0);
		assertThat(f5.as(Electrical5Fridge.class).watts().get(0.016, 20), is(1.1));
		assertThat(f5.as(Electrical5Fridge.class).watts().get(0.02, 20), is(1.18));
		assertEquals(1.785, f5.as(Electrical5Fridge.class).watts().get(0.02, 25), 0.01);
	}
}
