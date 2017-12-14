package test;

import io.intino.tafat.graph.TafatGraph;
import tara.magritte.Graph;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph model = Graph.load("UserInterface").wrap(TestApplication.class, TafatGraph.class);
		TafatGraph engine = model.platform();
		engine.execute();
	}
}
