package test;

import tafat.TafatPlatform;
import tara.magritte.Graph;
import test.TestApplication;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph model = Graph.load("UserInterface").wrap(TestApplication.class, TafatPlatform.class);
		TafatPlatform engine = model.platform();
		engine.execute();
	}
}
