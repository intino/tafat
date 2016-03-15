import org.junit.Before;
import org.junit.Test;
import tara.magritte.Model;
import testlanguage.TestLanguageApplication;
import tafat.TafatPlatform;

public class FmuModelTest {

	TafatPlatform platform;
	TestLanguageApplication application;

	@Before
	public void setUp() {
		Model model = Model.load("FmuModel").init(TestLanguageApplication.class, TafatPlatform.class);
		this.platform = model.platform();
		this.application = model.application();
		this.platform.init();
	}

	@Test
	public void testModel() throws Exception {

	}
}
