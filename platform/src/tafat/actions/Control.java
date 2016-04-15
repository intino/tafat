package tafat.actions;

import tafat.TafatPlatform;

public class Control {
	public static void stop(tafat.Stop self) {
		self.graph().<TafatPlatform>platform().stop();
	}

	public static boolean checkStep(tafat.periodic.PeriodicTrace self) {
		if (self.timeout() == 0) {
			self.timeout(self.timeScale().toSeconds() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}
}
