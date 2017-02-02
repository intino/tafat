package test;

import io.intino.tafat.Tafat;
import io.intino.tara.magritte.Graph;

public class BuildingOdeTest {

    @org.junit.Test
    public void ode_test() throws Exception {
        Graph graph = Graph.use(Test.class, Tafat.class).load("BuildingOde");
        graph.wrapper(Tafat.class).execute();
    }
}
