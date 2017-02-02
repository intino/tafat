package test;

import org.junit.Before;
import io.intino.tafat.Tafat;
import io.intino.tafat.behavior.BehaviorEntity;
import io.intino.tara.magritte.Graph;
import test.electrical.ElectricalFridge;

import static java.util.stream.IntStream.range;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StateChartTest {

	private Tafat platform;
	private Test domain;

	@Before
	public void setUp() {
		Graph model = Graph.use(Test.class, Tafat.class).load("StateChart");
		this.platform = model.wrapper(Tafat.class);
		this.domain = model.wrapper(Test.class);
		platform.init();
	}

	@org.junit.Test
	public void should_initially_be_in_state_on_and_after_updating_in_state_off() throws Exception {
		Fridge fridge = domain.fridgeList().get(0);
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("On"));
		platform.run();
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("Off"));
	}

	@org.junit.Test
	public void should_pass_all_stages_correctly() throws Exception {
		Fridge fridge = domain.fridgeList().get(1);
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("Off"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(-1));

		fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).receiveMessage("ON");
		platform.run();

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().current().name(), is("Heating"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(1000));

		range(0, 19).forEach(i -> platform.run());

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().current().name(), is("Heating"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(1000));

		platform.run();

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().current().name(), is("Washing"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(500));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().current().name(), is("Drying"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(800));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("Off"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(0));

		range(0, 20).forEach(i -> platform.run());

		assertThat(fridge.as(BehaviorEntity.class).implementation(0).stateChart(0).current().name(), is("Off"));
		assertThat(fridge.as(ElectricalFridge.class).value(), is(0));
	}

	@org.junit.Test
	public void after_updating_value_must_be_45() throws Exception {
		Fridge fridge = domain.fridgeList().get(2);
		platform.run();
		assertThat(fridge.as(ElectricalFridge.class).value(), is(45));
	}
}
