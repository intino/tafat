package test;

import io.intino.tafat.graph.TafatGraph;
import io.intino.tara.magritte.Graph;
import org.junit.Before;
import test.graph.Fridge;
import test.graph.TestGraph;

import static java.util.stream.Collectors.toList;

public class TableFunctionTest {

	private TafatGraph platform;
	private TestGraph application;

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("TableFunction");
		this.platform = graph.as(TafatGraph.class);
		this.application = graph.as(TestGraph.class);
		this.platform.init();
	}

	@org.junit.Test
	public void oneDimension() throws Exception {
		Fridge f6 = application.fridgeList(f -> f.name$().equals("f6")).collect(toList()).get(0);
		//assertThat(f6.asElectrical().wattsV6().get(20), is(1.1));
		//assertEquals(1.67, f6.asElectrical().wattsV6().get(25), 0.01);
	}

	@org.junit.Test
	public void severalDimensions() throws Exception {
		Fridge f5 = application.fridgeList(f -> f.name$().equals("f5")).collect(toList()).get(0);
		//assertThat(f5.asElectrical().wattsV5().get(0.016, 20), is(1.1));
		//assertThat(f5.asElectrical().wattsV5().get(0.02, 20), is(1.18));
		//assertEquals(1.785, f5.asElectrical().wattsV5().get(0.02, 25), 0.01);
	}
}
