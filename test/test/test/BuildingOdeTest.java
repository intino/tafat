package test;

import org.junit.Test;
import io.intino.tafat.TafatPlatform;
import tara.magritte.Graph;

public class BuildingOdeTest {

    @Test
    public void ode_test() throws Exception {
        Graph graph = Graph.load("BuildingOde").wrap(TestApplication.class, TafatPlatform.class);
        graph.platform().execute();
    }
}
