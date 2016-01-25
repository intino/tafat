package tafat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tafat.engine.Date.getDateTime;

public class TaskManager {

    private static List<Task> taskList;
    private static List<Task> nextTasks;
    private static LocalDateTime nextDate;

	static void init(){
		taskList = new ArrayList<>();
		nextTasks = new ArrayList<>();
		nextDate = LocalDateTime.MAX;
	}

    public static void addAll(List<Task> tasks) {
        if(tasks.isEmpty()) return;
        tasks.forEach(Task::program);
        taskList.addAll(tasks);
        prepareNextTasks();
    }

    static void update() {
        if(nextDate.isAfter(getDateTime())) return;
        nextTasks.stream()
                .filter(Task::check)
                .forEach(t -> {
                    t.startActionList().forEach(Task.Action::action);
                    t.program();
                });
        prepareNextTasks();
    }

    private static void prepareNextTasks() {
        nextDate = taskList.parallelStream().map(Task::scheduledDate).reduce((d1, d2) -> d1.isBefore(d2) ? d1 : d2).get();
        nextTasks = taskList.parallelStream().filter(t -> t.scheduledDate().isEqual(nextDate)).collect(toList());
    }
}