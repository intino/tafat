package test;

import io.intino.tafat.graph.TafatGraph;
import org.junit.Test;
import tara.magritte.Graph;

public class BuildingOdeTest {

    @Test
    public void ode_test() throws Exception {
		Graph graph = Graph.load("BuildingOde").wrap(TestApplication.class, TafatGraph.class);
		graph.platform().execute();
    }
}
