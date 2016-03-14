import org.junit.Before;
import org.junit.Test;
import tara.magritte.Model;
import testlanguage.TestLanguageApplication;
import tafat.TafatPlatform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProfilingTest {

	TafatPlatform platform;
	TestLanguageApplication application;

	@Before
	public void setUp() {
		Model model = Model.load("Profiling").init(TestLanguageApplication.class, TafatPlatform.class);
		this.platform = model.platform();
		this.application = model.application();
		this.platform.init();
	}

	@Test
	public void power_should_be_the_same_as_seed_is_fixed() throws Exception {
		assertThat(application.fridgeList().size(), is(5));
		assertThat(application.fridgeList().get(0).power(), is(0.7311469360199058));
		assertThat(application.fridgeList().get(1).power(), is(0.9014476240300544));
		assertThat(application.fridgeList().get(2).power(), is(0.49682259343089075));
	}

}
