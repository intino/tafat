package test;

import io.intino.tafat.TafatPlatform;
import io.intino.tara.magritte.Graph;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph model = Graph.load("UserInterface").wrap(TestApplication.class, TafatPlatform.class);
		TafatPlatform engine = model.platform();
		engine.execute();
	}
}
