package test;

import io.intino.tafat.graph.TafatGraph;
import org.junit.Before;
import io.intino.tara.magritte.Graph;
import org.junit.Test;
import test.graph.TestGraph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProfilingTest {

	TafatGraph platform;
	TestGraph product;

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("Profiling");
		this.platform = graph.as(TafatGraph.class);
		this.product = graph.as(TestGraph.class);
		this.platform.execute();
	}

	@Test
	public void power_should_be_the_same_as_seed_is_fixed() throws Exception {
		assertThat(product.fridgeList().size(), is(5));
		assertThat(product.fridgeList().get(0).power(), is(0.7311469360199058));
		assertThat(product.fridgeList().get(1).power(), is(0.9014476240300544));
		assertThat(product.fridgeList().get(2).power(), is(0.49682259343089075));
	}

}
