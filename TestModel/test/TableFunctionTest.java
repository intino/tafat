import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Model;
import testlanguage.Fridge;
import testlanguage.TestLanguageApplication;
import testlanguage.electrical5.Electrical5Fridge;
import testlanguage.electrical6.Electrical6Fridge;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TableFunctionTest {

	TafatPlatform engine;
	TestLanguageApplication domain;

	@Before
	public void setUp() {
		Model model = Model.load("TableFunction").init(TestLanguageApplication.class, TafatPlatform.class);
		this.engine = model.platform();
		this.domain = model.application();
		this.engine.init();
	}

	@Test
	public void oneDimension() throws Exception {
		Fridge f6 = domain.fridgeList(f -> f._simpleName().equals("f6")).get(0);
		assertThat(f6.as(Electrical6Fridge.class).watts().get(20), is(1.1));
		assertEquals(1.67, f6.as(Electrical6Fridge.class).watts().get(25), 0.01);
	}

	@Test
	public void severalDimensions() throws Exception {
		Fridge f5 = domain.fridgeList(f -> f._simpleName().equals("f5")).get(0);
		assertThat(f5.as(Electrical5Fridge.class).watts().get(0.016, 20), is(1.1));
		assertThat(f5.as(Electrical5Fridge.class).watts().get(0.02, 20), is(1.18));
		assertEquals(1.785, f5.as(Electrical5Fridge.class).watts().get(0.02, 25), 0.01);
	}
}
