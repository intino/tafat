import tafat.TafatPlatform;
import tara.magritte.Graph;
import testlanguage.TestLanguageApplication;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Graph model = Graph.load("UserInterface").wrap(TestLanguageApplication.class, TafatPlatform.class);
		TafatPlatform engine = model.platform();
		engine.execute();
	}
}
