package test;

import test.graph.Router;

import static test.graph.Router.Mode.Off;
import static test.graph.Router.Mode.On;

public class RouterStateChart {
	public static boolean checkIsOn(Router.ElectricalRouter self, int advancedTime) {
		return self.mode() == On;
	}

	public static boolean checkIsOff(Router.ElectricalRouter self, int advancedTime) {
		return self.mode() == Off;
	}
}
