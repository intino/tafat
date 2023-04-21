package io.intino.tafat.test.model;


import static io.intino.tafat.test.model.Router.Mode.Off;
import static io.intino.tafat.test.model.Router.Mode.On;

public class RouterStateChart {
	public static boolean checkIsOn(Router.Electrical self, int advancedTime) {
		return self.mode() == On;
	}

	public static boolean checkIsOff(Router.Electrical self, int advancedTime) {
		return self.mode() == Off;
	}
}
