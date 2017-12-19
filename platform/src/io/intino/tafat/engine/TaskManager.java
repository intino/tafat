package io.intino.tafat.engine;

import io.intino.tafat.graph.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TaskManager {

	private static List<Task> taskList;
	private static List<Task> nextTasks;
	private static Instant nextDate;

	static void init() {
		taskList = new ArrayList<>();
		nextTasks = new ArrayList<>();
		nextDate = Instant.MAX;
	}

	public static void addAll(List<Task> tasks) {
		if (tasks.isEmpty()) return;
		tasks.forEach(Task::program);
		taskList.addAll(tasks);
		prepareNextTasks();
	}

	@SuppressWarnings("Convert2streamapi")
	static void update() {
		if (nextDate.isAfter(Date.getInstant())) return;
		for (Task nextTask : nextTasks)
			if (nextTask.check()) {
				nextTask.startActionList().forEach(Task.Action::action);
				nextTask.program();
			}
		prepareNextTasks();
	}

	private static void prepareNextTasks() {
		nextDate = taskList.parallelStream().map(Task::scheduledDate).reduce((d1, d2) -> d1.isBefore(d2) ? d1 : d2).get();
		nextTasks = taskList.parallelStream().filter(t -> t.scheduledDate().equals(nextDate)).collect(toList());
	}
}
