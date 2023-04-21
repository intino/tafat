package io.intino.tafat.engine.utils;

import io.intino.tafat.model.Task;
import io.intino.tafat.engine.Date;
import io.intino.tafat.model.rules.DayOfWeek;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.Random;

import static io.intino.tafat.engine.Date.toLocalDateTime;
import static java.time.temporal.TemporalAdjusters.next;

public class TaskHelper {
    public static Instant scheduledDate(Task task) {
        return nextDateTime(task);
    }

    private static Instant nextDateTime(Task task) {
        int deltaInSeconds = task.start().deviation() * 60;
        LocalTime localTime = task.start().start().plusSeconds(deltaInSeconds == 0 ? 0 : new Random().nextInt(deltaInSeconds * 2) - deltaInSeconds);
        java.time.DayOfWeek dayOfWeek = nextDayOfWeek(task);
		return task.scheduledDate() == null && dayOfWeek.equals(java.time.DayOfWeek.values()[Date.get(ChronoField.DAY_OF_WEEK)]) ?
				localTime.atDate(toLocalDateTime().toLocalDate()).toInstant(ZoneOffset.UTC) :
				localTime.atDate(toLocalDateTime().with(next(dayOfWeek)).toLocalDate()).toInstant(ZoneOffset.UTC);
	}

    private static java.time.DayOfWeek nextDayOfWeek(Task task) {
        DayOfWeek tafatDayOfWeek = DayOfWeek.values()[toLocalDateTime().getDayOfWeek().ordinal()];
        tafatDayOfWeek = task.scheduledDate() == null ? tafatDayOfWeek : tafatDayOfWeek.nextDay(task.days());
        return java.time.DayOfWeek.values()[tafatDayOfWeek.ordinal()];
    }

}
