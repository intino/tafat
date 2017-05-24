package test;

import io.intino.tafat.TafatGraph;
import io.intino.tara.magritte.Graph;

public class BuildingOdeTest {

    @org.junit.Test
    public void ode_test() throws Exception {
        Graph graph = new Graph().loadStashes("BuildingOde");
        graph.as(TafatGraph.class).execute();
    }
}
