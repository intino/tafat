package test;

import static test.Router.*;
import static test.Router.Mode.Off;
import static test.Router.Mode.On;

public class Statechart {
	public static boolean checkIsOn(test.electrical.ElectricalRouter self, int advancedTime) {
		return self.mode() == On;
	}

	public static boolean checkIsOff(test.electrical.ElectricalRouter self, int advancedTime) {
		return self.mode() == Off;
	}
}
