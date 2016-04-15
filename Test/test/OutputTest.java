import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tara.magritte.Graph;
import testlanguage.TestLanguageApplication;

public class OutputTest {

	TafatPlatform engine;
	TestLanguageApplication domain;

	@Before
	public void setUp() {
		Graph model = Graph.load("Output").wrap(TestLanguageApplication.class, TafatPlatform.class);
		this.engine = model.platform();
		this.domain = model.application();
		this.engine.execute();
	}

	@Test
	public void testModel() throws Exception {
		this.engine.execute();
	}
}
