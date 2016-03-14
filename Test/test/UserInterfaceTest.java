import tafat.TafatPlatform;
import tara.magritte.Model;
import testlanguage.TestLanguageApplication;

public class UserInterfaceTest {

	public static void main(String[] args) throws Exception{
		Model model = Model.load("UserInterface").init(TestLanguageApplication.class, TafatPlatform.class);
		TafatPlatform engine = model.platform();
		engine.init();
	}
}