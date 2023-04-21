package test;

import io.intino.tafat.model.TafatGraph;
import io.intino.magritte.framework.Graph;
import io.intino.tafat.test.model.TestGraph;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception {
		Graph graph = new Graph().loadStashes("UserInterface");
		TafatGraph tafatGraph = graph.as(TafatGraph.class);
		graph.as(TestGraph.class);
		tafatGraph.execute();
	}
}
