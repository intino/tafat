import org.junit.Before;
import org.junit.Test;
import tafat.TafatPlatform;
import tafat.behavior.BehaviorEntity;
import tara.magritte.Model;
import testlanguage.Fridge;
import testlanguage.TestLanguageApplication;
import testlanguage.electrical2.Electrical2Fridge;
import testlanguage.electrical3.Electrical3Fridge;

import static java.util.stream.IntStream.range;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StateChartTest {

	TafatPlatform engine;
	TestLanguageApplication domain;

	@Before
	public void setUp() {
		Model model = Model.load("StateChart").init(TestLanguageApplication.class, TafatPlatform.class);
		this.engine = model.platform();
		this.domain = model.application();
		this.engine.init();
	}

	@Test
	public void should_initially_be_in_state_on_and_after_updating_in_state_off() throws Exception {
		Fridge fridge = domain.fridgeList().get(0);
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("On"));
		engine.run();
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("Off"));
	}

	@Test
	public void should_pass_all_stages_correctly() throws Exception {
		Fridge fridge = domain.fridgeList().get(1);
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("Off"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(-1));

		fridge.as(BehaviorEntity.class).stateChart(0).receiveMessage("ON");
		engine.run();

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current().current()._simpleName(), is("Heating"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(1000));

		range(0, 19).forEach(i -> engine.run());

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current().current()._simpleName(), is("Heating"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(1000));

		engine.run();

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current().current()._simpleName(), is("Washing"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(500));

		range(0, 20).forEach(i -> engine.run());

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("On"));
		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current().current()._simpleName(), is("Drying"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(800));

		range(0, 20).forEach(i -> engine.run());

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("Off"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(0));

		range(0, 20).forEach(i -> engine.run());

		assertThat(fridge.as(BehaviorEntity.class).stateChart(0).current()._simpleName(), is("Off"));
		assertThat(fridge.as(Electrical2Fridge.class).value(), is(0));
	}

	@Test
	public void after_updating_value_must_be_46() throws Exception {
		Fridge fridge = domain.fridgeList().get(2);
		engine.run();
		assertThat(fridge.as(Electrical3Fridge.class).value(), is(46));
	}
}
