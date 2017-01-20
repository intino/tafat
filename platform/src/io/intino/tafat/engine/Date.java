package io.intino.tafat.engine;

import java.time.*;
import java.time.temporal.*;

public class Date {

    private static java.time.Instant dateTime;

	public static java.time.Instant now() {
		return java.time.Instant.now();
	}

	public static java.time.Instant now(Clock clock) {
		return java.time.Instant.now(clock);
	}

	public static java.time.Instant ofEpochSecond(long epochSecond) {
		return java.time.Instant.ofEpochSecond(epochSecond);
	}

	public static java.time.Instant ofEpochSecond(long epochSecond, long nanoAdjustment) {
		return java.time.Instant.ofEpochSecond(epochSecond, nanoAdjustment);
	}

	public static java.time.Instant ofEpochMilli(long epochMilli) {
		return java.time.Instant.ofEpochMilli(epochMilli);
	}


	public static java.time.Instant parse(CharSequence text) {
		return java.time.Instant.parse(text);
	}

	public static boolean isSupported(TemporalField field) {
		return dateTime.isSupported(field);
	}

	public static boolean isSupported(TemporalUnit unit) {
		return dateTime.isSupported(unit);
	}

	public static ValueRange range(TemporalField field) {
		return dateTime.range(field);
	}

	public static int get(TemporalField field) {
		return dateTime.get(field);
	}

	public static long getLong(TemporalField field) {
		return dateTime.getLong(field);
	}

	public static long getEpochSecond() {
		return dateTime.getEpochSecond();
	}

	public static int getNano() {
		return dateTime.getNano();
	}

	public static java.time.Instant plus(long amountToAdd, TemporalUnit unit) {
		return dateTime = dateTime.plus(amountToAdd, unit);
	}

	public static java.time.Instant plusSeconds(long secondsToAdd) {
		return dateTime = dateTime.plusSeconds(secondsToAdd);
	}

	public static java.time.Instant plusMillis(long millisToAdd) {
		return dateTime = dateTime.plusMillis(millisToAdd);
	}

	public static java.time.Instant plusNanos(long nanosToAdd) {
		return dateTime = dateTime.plusNanos(nanosToAdd);
	}

	public static java.time.Instant minus(TemporalAmount amountToSubtract) {
		return dateTime = dateTime.minus(amountToSubtract);
	}

	public static java.time.Instant minus(long amountToSubtract, TemporalUnit unit) {
		return dateTime = dateTime.minus(amountToSubtract, unit);
	}

	public static java.time.Instant minusSeconds(long secondsToSubtract) {
		return dateTime = dateTime.minusSeconds(secondsToSubtract);
	}

	public static java.time.Instant minusMillis(long millisToSubtract) {
		return dateTime = dateTime.minusMillis(millisToSubtract);
	}

	public static java.time.Instant minusNanos(long nanosToSubtract) {
		return dateTime = dateTime.minusNanos(nanosToSubtract);
	}

	public static <R> R query(TemporalQuery<R> query) {
		return dateTime.query(query);
	}

	public static Temporal adjustInto(Temporal temporal) {
		return dateTime.adjustInto(temporal);
	}

	public static long until(Temporal endExclusive, TemporalUnit unit) {
		return dateTime.until(endExclusive, unit);
	}

	public static OffsetDateTime atOffset(ZoneOffset offset) {
		return dateTime.atOffset(offset);
	}

	public static ZonedDateTime atZone(ZoneId zone) {
		return dateTime.atZone(zone);
	}

	public static long toEpochMilli() {
		return dateTime.toEpochMilli();
	}

	public static int compareTo(java.time.Instant otherInstant) {
		return dateTime.compareTo(otherInstant);
	}

	public static boolean isAfter(java.time.Instant otherInstant) {
		return dateTime.isAfter(otherInstant);
	}

	public static boolean isBefore(java.time.Instant otherInstant) {
		return dateTime.isBefore(otherInstant);
	}

	public static void setInstant(java.time.Instant otherInstant){
		dateTime = otherInstant;
	}

	public static Instant getInstant() {
		return dateTime;
	}


	public static LocalDateTime toLocalDateTime() {
		return LocalDateTime.ofInstant(Date.getInstant(), ZoneId.of("UTC"));
	}
}
