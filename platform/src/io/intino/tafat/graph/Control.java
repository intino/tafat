package io.intino.tafat.graph;

public class Control {
	public static void stop(Stop self) {
		self.graph().core$().as(TafatGraph.class).stop();
	}

	public static boolean checkStep(Trace.Periodic self) {
		if (self.timeout() == 0) {
			self.timeout(self.timeScale().toSeconds() - 1);
			return true;
		}
		self.timeout(self.timeout() - 1);
		return false;
	}
}
