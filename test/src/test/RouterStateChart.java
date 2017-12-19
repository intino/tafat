package test;

import static test.graph.Router.Mode.Off;
import static test.graph.Router.Mode.On;

public class RouterStateChart {
	public static boolean checkIsOn(test.graph.electrical.ElectricalRouter self, int advancedTime) {
		return self.mode() == On;
	}

	public static boolean checkIsOff(test.graph.electrical.ElectricalRouter self, int advancedTime) {
		return self.mode() == Off;
	}
}
