import magritte.handlers.NodeProducer;
import magritte.ontology.BaseTestStateChart;
import magritte.schema.MemoryGraph;
import magritte.schema.MemoryNode;
import org.junit.Test;
import tafat.StateChart;
import tafat.engine.StatechartUpdater;

import static magritte.Node.Member.Component;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AcceptedStateChart {

    @Test
    public void testName() throws Exception {
        StateChart stateChart = asStateChart(buildNode("Fridge", "SimpleStateChart+Fridge"));
        stateChart.current(stateChart.state(0));
        assertThat(stateChart.current()._name(), is("Off"));
        StatechartUpdater.update(stateChart, 0);
        assertThat(stateChart.current()._name(), is("On"));
    }

    private StateChart asStateChart(MemoryNode node) {
        StateChart stateChart = new StateChart();
        stateChart._node(node.members(Component).filter(instancesOf(nameOf(StateChart.class))).get(0));
        return stateChart;
    }

    private MemoryNode buildNode(String... types) {
        MemoryGraph graph = new MemoryGraph();
        BaseTestStateChart.box.load(graph);
        MemoryNode node = new MemoryNode(graph);
        for (String type : types)
            NodeProducer.produce(node).with(graph.get(type));
        return node;
    }
}
