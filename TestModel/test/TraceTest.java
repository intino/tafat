import org.junit.Before;
import org.junit.Test;
import tara.magritte.Model;
import testlanguage.TestLanguageApplication;
import tafat.TafatPlatform;

public class TraceTest {

	TafatPlatform platform;
	TestLanguageApplication application;

	@Before
	public void setUp() {
		Model model = Model.load("Trace").init(TestLanguageApplication.class, TafatPlatform.class);
		this.platform = model.<TafatPlatform>platform();
		this.application = model.<TestLanguageApplication>application();
		this.platform.init();
	}

	@Test
	public void should_trace_message() throws Exception {
		this.platform.execute();
	}
}
