package tafat.engine;

import tafat.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static List<Task> taskList = new ArrayList<>();

    public static void addAll(List<Task> tasks) {
        tasks.forEach(Task::program);
        taskList.addAll(tasks);
    }

    public static void update() {
        taskList.stream().filter(Task::check).forEach(t -> {t.startAction(0).action(); t.program();});
    }
}
