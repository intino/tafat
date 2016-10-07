package io.intino.tafat;

import io.intino.tafat.periodic.PeriodicTrace;

public class Control {
	public static void stop(Stop self) {
		self.graph().<TafatPlatform>platform().stop();
	}

	public static boolean checkStep(PeriodicTrace self) {
		if (self.timeout() == 0) {
			self.timeout(self.timeScale().toSeconds() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}
}
