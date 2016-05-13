package tafat.engine.utils;

import tafat.Task;
import tafat.rules.DayOfWeek;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import static java.time.temporal.TemporalAdjusters.next;
import static tafat.engine.Date.getDayOfWeek;
import static tafat.engine.Date.toLocalDate;
import static tafat.engine.Date.with;

public class TaskHelper {
    public static LocalDateTime scheduledDate(Task task) {
        return nextDateTime(task);
    }

    private static LocalDateTime nextDateTime(Task task) {
        int deltaInSeconds = task.start().deviation() * 60;
        LocalTime localTime = task.start().start().plusSeconds(deltaInSeconds == 0 ? 0 : new Random().nextInt(deltaInSeconds * 2) - deltaInSeconds);
        java.time.DayOfWeek dayOfWeek = nextDayOfWeek(task);
        return task.scheduledDate() == null && dayOfWeek.equals(getDayOfWeek()) ?
                localTime.atDate(toLocalDate()) :
                localTime.atDate(with(next(dayOfWeek)).toLocalDate());
    }

    private static java.time.DayOfWeek nextDayOfWeek(Task task) {
        DayOfWeek tafatDayOfWeek = DayOfWeek.values()[getDayOfWeek().ordinal()];
        tafatDayOfWeek = task.scheduledDate() == null ? tafatDayOfWeek : tafatDayOfWeek.nextDay(task.days());
        return java.time.DayOfWeek.values()[tafatDayOfWeek.ordinal()];
    }
}
