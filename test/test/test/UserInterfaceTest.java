package test;

import io.intino.tafat.Tafat;
import io.intino.tara.magritte.Graph;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph model = Graph.use(Test.class, Tafat.class).load("UserInterface");
		Tafat engine = model.wrapper(Tafat.class);
		engine.execute();
	}
}
