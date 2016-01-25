import tafat.TafatEngine;
import tara.magritte.Model;
import testlanguage.TestLanguageDomain;

public class UserInterfaceTest {


	public static void main(String[] args) {
		Model model = Model.load("UserInterface").init(TestLanguageDomain.class, TafatEngine.class);
		model.engine().init();
	}
}
