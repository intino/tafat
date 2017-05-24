package test;

import io.intino.tafat.TafatGraph;
import io.intino.tara.magritte.Graph;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph graph = new Graph().loadStashes("UserInterface");
		TafatGraph tafatGraph = graph.as(TafatGraph.class);
		graph.as(TestGraph.class);
		tafatGraph.execute();
	}
}
