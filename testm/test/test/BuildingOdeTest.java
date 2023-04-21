package test;

import io.intino.tafat.model.TafatGraph;
import io.intino.magritte.framework.Graph;
import org.junit.Test;

public class BuildingOdeTest {

    @Test
    public void ode_test() throws Exception {
        Graph graph = new Graph().loadStashes("BuildingOde");
        graph.as(TafatGraph.class).execute();
    }
}
