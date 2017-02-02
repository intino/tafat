package test;

import io.intino.tafat.Tafat;
import org.junit.Before;
import io.intino.tara.magritte.Graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProfilingTest {

	Tafat platform;
	Test application;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("Profiling");
		this.platform = model.wrapper(Tafat.class);
		this.application = model.wrapper(Test.class);
		this.platform.execute();
	}

	@org.junit.Test
	public void power_should_be_the_same_as_seed_is_fixed() throws Exception {
		assertThat(application.fridgeList().size(), is(5));
		assertThat(application.fridgeList().get(0).power(), is(0.7311469360199058));
		assertThat(application.fridgeList().get(1).power(), is(0.9014476240300544));
		assertThat(application.fridgeList().get(2).power(), is(0.49682259343089075));
	}

}
