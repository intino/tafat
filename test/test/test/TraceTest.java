package test;

import io.intino.tafat.Tafat;
import org.junit.Before;
import io.intino.tara.magritte.Graph;

public class TraceTest {

	Tafat platform;
	Test application;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("Trace");
		this.platform = model.wrapper(Tafat.class);
		this.application = model.wrapper(Test.class);
		this.platform.execute();
	}

	@org.junit.Test
	public void should_trace_message() throws Exception {
		this.platform.execute();
	}

	public static void main(String[] args) {
		Graph model = Graph.use(Test.class, Tafat.class).load("Trace");
		model.wrapper(Tafat.class).execute();
	}
}
