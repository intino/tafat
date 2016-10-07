package io.intino.tafat.engine;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Comparator;

public class Date {

    private static LocalDateTime dateTime;

    public static void setDateTime(LocalDateTime dateTime) {
        Date.dateTime = dateTime;
    }

    public static LocalDateTime getDateTime() {
        return dateTime;
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    public static int compareTo(ChronoLocalDateTime<?> other) {
        return dateTime.compareTo(other);
    }

    public static void plusSeconds(long seconds) {
        dateTime = dateTime.plusSeconds(seconds);
    }

    public static LocalDate toLocalDate() {
        return dateTime.toLocalDate();
    }

    public static boolean isBefore(ChronoLocalDateTime<?> other) {
        return dateTime.isBefore(other);
    }

    public static String format(DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    public static LocalDateTime ofInstant(Instant instant, ZoneId zone) {
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static LocalDateTime withMinute(int minute) {
        return dateTime.withMinute(minute);
    }

    public static Instant toInstant(ZoneOffset offset) {
        return dateTime.toInstant(offset);
    }

    public static LocalDateTime now(ZoneId zone) {
        return LocalDateTime.now(zone);
    }

    public static int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

    public static int getHour() {
        return dateTime.getHour();
    }

    public static boolean isSupported(TemporalField field) {
        return dateTime.isSupported(field);
    }

    public static LocalDateTime withDayOfMonth(int dayOfMonth) {
        return dateTime.withDayOfMonth(dayOfMonth);
    }

    public static LocalDateTime withNano(int nanoOfSecond) {
        return dateTime.withNano(nanoOfSecond);
    }

    public static LocalDateTime minusSeconds(long seconds) {
        return dateTime.minusSeconds(seconds);
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    public static boolean isSupported(TemporalUnit unit) {
        return dateTime.isSupported(unit);
    }

    public static LocalTime toLocalTime() {
        return dateTime.toLocalTime();
    }

    public static int getSecond() {
        return dateTime.getSecond();
    }

    public static int get(TemporalField field) {
        return dateTime.get(field);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
    }

    public static LocalDateTime ofEpochSecond(long epochSecond, int nanoOfSecond, ZoneOffset offset) {
        return LocalDateTime.ofEpochSecond(epochSecond, nanoOfSecond, offset);
    }

    public static LocalDateTime now(Clock clock) {
        return LocalDateTime.now(clock);
    }

    public static ZonedDateTime atZone(ZoneId zone) {
        return dateTime.atZone(zone);
    }

    public static LocalDateTime minus(TemporalAmount amountToSubtract) {
        return dateTime.minus(amountToSubtract);
    }

    public static OffsetDateTime atOffset(ZoneOffset offset) {
        return dateTime.atOffset(offset);
    }

    public static LocalDateTime withHour(int hour) {
        return dateTime.withHour(hour);
    }

    public static LocalDateTime plusMonths(long months) {
        return dateTime.plusMonths(months);
    }

    public static LocalDateTime withMonth(int month) {
        return dateTime.withMonth(month);
    }

    public static LocalDateTime from(TemporalAccessor temporal) {
        return LocalDateTime.from(temporal);
    }

    public static LocalDateTime plusWeeks(long weeks) {
        return dateTime.plusWeeks(weeks);
    }

    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        return LocalDateTime.parse(text, formatter);
    }

    public static Month getMonth() {
        return dateTime.getMonth();
    }

    public static int getMinute() {
        return dateTime.getMinute();
    }

    public static long until(Temporal endExclusive, TemporalUnit unit) {
        return dateTime.until(endExclusive, unit);
    }

    public static long getLong(TemporalField field) {
        return dateTime.getLong(field);
    }

    public static DayOfWeek getDayOfWeek() {
        return dateTime.getDayOfWeek();
    }

    public static LocalDateTime plusDays(long days) {
        return dateTime.plusDays(days);
    }

    public static LocalDateTime minusNanos(long nanos) {
        return dateTime.minusNanos(nanos);
    }

    public static LocalDateTime plus(long amountToAdd, TemporalUnit unit) {
        return dateTime.plus(amountToAdd, unit);
    }

    public static LocalDateTime with(TemporalAdjuster adjuster) {
        return dateTime.with(adjuster);
    }

    public static long toEpochSecond(ZoneOffset offset) {
        return dateTime.toEpochSecond(offset);
    }

    public static LocalDateTime minusMonths(long months) {
        return dateTime.minusMonths(months);
    }

    public static LocalDateTime with(TemporalField field, long newValue) {
        return dateTime.with(field, newValue);
    }

    public static LocalDateTime plus(TemporalAmount amountToAdd) {
        return dateTime.plus(amountToAdd);
    }

    public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public static int getNano() {
        return dateTime.getNano();
    }

    public static boolean isAfter(ChronoLocalDateTime<?> other) {
        return dateTime.isAfter(other);
    }

    public static boolean isEqual(ChronoLocalDateTime<?> other) {
        return dateTime.isEqual(other);
    }

    public static Temporal adjustInto(Temporal temporal) {
        return dateTime.adjustInto(temporal);
    }

    public static LocalDateTime plusMinutes(long minutes) {
        return dateTime.plusMinutes(minutes);
    }

    public static LocalDateTime plusNanos(long nanos) {
        return dateTime.plusNanos(nanos);
    }

    public static LocalDateTime plusHours(long hours) {
        return dateTime.plusHours(hours);
    }

    public static <R> R query(TemporalQuery<R> query) {
        return dateTime.query(query);
    }

    public static Chronology getChronology() {
        return dateTime.getChronology();
    }

    public static LocalDateTime withYear(int year) {
        return dateTime.withYear(year);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public static LocalDateTime truncatedTo(TemporalUnit unit) {
        return dateTime.truncatedTo(unit);
    }

    public static int getMonthValue() {
        return dateTime.getMonthValue();
    }

    public static LocalDateTime withDayOfYear(int dayOfYear) {
        return dateTime.withDayOfYear(dayOfYear);
    }

    public static int getDayOfYear() {
        return dateTime.getDayOfYear();
    }

    public static LocalDateTime withSecond(int second) {
        return dateTime.withSecond(second);
    }

    public static LocalDateTime minusYears(long years) {
        return dateTime.minusYears(years);
    }

    public static LocalDateTime minusWeeks(long weeks) {
        return dateTime.minusWeeks(weeks);
    }

    public static LocalDateTime minusDays(long days) {
        return dateTime.minusDays(days);
    }

    public static LocalDateTime minusMinutes(long minutes) {
        return dateTime.minusMinutes(minutes);
    }

    public static LocalDateTime minusHours(long hours) {
        return dateTime.minusHours(hours);
    }

    public static int getYear() {
        return dateTime.getYear();
    }

    public static LocalDateTime minus(long amountToSubtract, TemporalUnit unit) {
        return dateTime.minus(amountToSubtract, unit);
    }

    public static Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return ChronoLocalDateTime.timeLineOrder();
    }

    public static ValueRange range(TemporalField field) {
        return dateTime.range(field);
    }

    public static LocalDateTime of(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    public static LocalDateTime parse(CharSequence text) {
        return LocalDateTime.parse(text);
    }

    public static LocalDateTime plusYears(long years) {
        return dateTime.plusYears(years);
    }
}
