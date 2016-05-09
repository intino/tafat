package tafat;

import static tafat.engine.Date.getDateTime;
import static tafat.engine.StatechartUpdater.update;
import static tafat.engine.helpers.TaskHelper.scheduledDate;

public class ModellingMechanisms {

	public static void receiveMessage(StateChart self, String message) {
		self.message(message);
		if (self.current() != null) self.current().receiveMessage(message);
	}

	public static void updateStateChart(StateChart self, int step) {
		update(self, step);
	}

	public static void programTask(Task self) {
		self.scheduledDate(scheduledDate(self));
	}

	public static boolean checkTimeBasedTransition(StateChart.Transition.TimeBased self, int advancedTime) {
		return !self.when().isAfter(getDateTime());
	}

	public static void activateTimeout(StateChart.Transition.Timeout self) {
		self.when(getDateTime().plusSeconds(self.timeout()));
	}

	public static void activateAfter(StateChart.Transition.After self) {
		self.when(getDateTime().plusSeconds(self.fixedTime()));
	}

	public static void activateRate(StateChart.Transition.Rate self) {
		self.when(getDateTime().plusSeconds(self.unit() / self.times()));
	}

	public static boolean checkMessageTransition(StateChart.Transition.Message self, int advancedTime) {
		return self.ownerAs(StateChart.class).message().equals(self.expectedMessage());
	}

	public static boolean checkTask(tafat.Task self) {
		return !self.scheduledDate().isAfter(getDateTime());
	}
}
