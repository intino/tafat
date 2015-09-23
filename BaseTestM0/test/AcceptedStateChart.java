import basetest.BaseTestModel;
import org.junit.Test;
import tafat.Behavior;
import tafat.StateChart;
import tafat.engine.StatechartUpdater;
import tara.magritte.Node;
import tara.magritte.PersistenceManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AcceptedStateChart {

    @Test
    public void testName() throws Exception {
        Node node = PersistenceManager.load("/BaseTestM0.dsl");
        BaseTestModel.use(node);
        StateChart stateChart = BaseTestModel.fridgeList().get(0).as(Behavior.class).stateChart(0);
        stateChart.current(stateChart.state(0));
        assertThat(stateChart.current()._node().shortName(), is("Off"));
        StatechartUpdater.update(stateChart, 0);
        assertThat(stateChart.current()._node().shortName(), is("On"));
    }
}
