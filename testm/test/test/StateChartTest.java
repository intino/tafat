package test;

import io.intino.tafat.model.Entity;
import io.intino.tafat.model.TafatGraph;
import io.intino.magritte.framework.Graph;
import io.intino.tafat.test.model.Fridge;
import org.junit.Before;
import io.intino.tafat.test.model.TestGraph;
import org.junit.Test;

import static java.util.stream.IntStream.range;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StateChartTest {

	private TafatGraph platform;
	private TestGraph product;

	@Before
	public void setUp() {
		Graph graph = new Graph().loadStashes("StateChart");
		this.platform = graph.as(TafatGraph.class);
		this.product = graph.as(TestGraph.class);
		platform.init();
	}

	@Test
	public void should_initially_be_in_state_on_and_after_updating_in_state_off() throws Exception {
		Fridge fridge = product.fridgeList().get(0);
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("On"));
		platform.run();
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("Off"));
	}

	@Test
	public void should_pass_all_stages_correctly() {
		Fridge fridge = product.fridgeList().get(1);
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("Off"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(-1));

		fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).receiveMessage("ON");
		platform.run();

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("On"));
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().current().name$(), is("Heating"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(1000));

		range(0, 19).forEach(i -> platform.run());

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("On"));
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().current().name$(), is("Heating"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(1000));

		platform.run();

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("On"));
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().current().name$(), is("Washing"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(500));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("On"));
		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().current().name$(), is("Drying"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(800));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("Off"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(0));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.core$().as(Entity.Behavior.class).implementation(0).stateChart(0).current().name$(), is("Off"));
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(0));
	}

	@Test
	public void after_updating_value_must_be_45() throws Exception {
		Fridge fridge = product.fridgeList().get(2);
		platform.run();
		assertThat(fridge.core$().as(Fridge.Electrical.class).value(), is(45));
	}
}
