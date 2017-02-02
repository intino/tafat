package test;

import io.intino.tafat.Tafat;
import org.junit.Before;
import io.intino.tara.magritte.Graph;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TableFunctionTest {

	private Tafat platform;
	private Test application;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("TableFunction");
		this.platform = model.wrapper(Tafat.class);
		this.application = model.wrapper(Test.class);
		this.platform.init();
	}

	@org.junit.Test
	public void oneDimension() throws Exception {
		Fridge f6 = application.fridgeList(f -> f.name().equals("f6")).get(0);
		//assertThat(f6.asElectrical().wattsV6().get(20), is(1.1));
		//assertEquals(1.67, f6.asElectrical().wattsV6().get(25), 0.01);
	}

	@org.junit.Test
	public void severalDimensions() throws Exception {
		Fridge f5 = application.fridgeList(f -> f.name().equals("f5")).get(0);
		//assertThat(f5.asElectrical().wattsV5().get(0.016, 20), is(1.1));
		//assertThat(f5.asElectrical().wattsV5().get(0.02, 20), is(1.18));
		//assertEquals(1.785, f5.asElectrical().wattsV5().get(0.02, 25), 0.01);
	}
}
