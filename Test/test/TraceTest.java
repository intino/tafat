import org.junit.Before;
import org.junit.Test;
import tara.magritte.Graph;
import testlanguage.TestLanguageApplication;
import tafat.TafatPlatform;

public class TraceTest {

	TafatPlatform platform;
	TestLanguageApplication application;

	@Before
	public void setUp() {
		Graph model = Graph.load("Trace").wrap(TestLanguageApplication.class, TafatPlatform.class);
		this.platform = model.<TafatPlatform>platform();
		this.application = model.<TestLanguageApplication>application();
		this.platform.execute();
	}

	@Test
	public void should_trace_message() throws Exception {
		this.platform.execute();
	}

	public static void main(String[] args) {
		Graph model = Graph.load("Trace").wrap(TestLanguageApplication.class, TafatPlatform.class);
		model.platform().execute();
	}
}
