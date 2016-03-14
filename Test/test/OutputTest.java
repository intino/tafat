import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Model;
import testlanguage.TestLanguageApplication;

public class OutputTest {

	TafatPlatform engine;
	TestLanguageApplication domain;

	@Before
	public void setUp() {
		Model model = Model.load("Output").init(TestLanguageApplication.class, TafatPlatform.class);
		this.engine = model.platform();
		this.domain = model.application();
		this.engine.init();
	}

	@Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
