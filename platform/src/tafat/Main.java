package tafat;

public class Main {
	public static boolean checkStep(tafat.Behavior self) {
		if (self.timeout() == 0) {
			self.timeout(self.step() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}
}
