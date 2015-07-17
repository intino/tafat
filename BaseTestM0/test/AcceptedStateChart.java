import magritte.Set;
import magritte.Tag;
import magritte.handlers.NodeProducer;
import magritte.ontology.BaseTestM0Main;
import magritte.ontology.BaseTestStateChart;
import magritte.schema.MemoryGraph;
import magritte.schema.MemoryNode;
import org.junit.Test;
import tafat.Behavior;
import tafat.StateChart;
import tafat.TafatModel;
import tafat.engine.StatechartUpdater;

import static magritte.Node.Member.Component;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AcceptedStateChart {

    private Set<Behavior> behaviors;

    @Test
    public void testName() throws Exception {
        MemoryGraph graph = new MemoryGraph();
        BaseTestM0Main.box.load(graph);
        TafatModel model = new TafatModel(graph);
        behaviors = model._find(Behavior.class);
        StateChart stateChart = behaviors.get(0).stateChart(0);
        stateChart.current(stateChart.state(0));
        assertThat(stateChart.current()._name(), is("Off"));
        StatechartUpdater.update(stateChart, 0);
        assertThat(stateChart.current()._name(), is("On"));
    }
}
