package tafat;

import tafat.StateChart;
import tafat.Task;

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
}
