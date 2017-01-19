package io.intino.tafat.engine;

import io.intino.tara.magritte.types.InstantX;

import java.time.*;
import java.time.temporal.*;

public class Date {

    private static InstantX dateTime;

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

	public static InstantX plus(long amountToAdd, TemporalUnit unit) {
		return dateTime = dateTime.plus(amountToAdd, unit);
	}

	public static InstantX plusSeconds(long secondsToAdd) {
		return dateTime = dateTime.plusSeconds(secondsToAdd);
	}

	public static InstantX plusMillis(long millisToAdd) {
		return dateTime = dateTime.plusMillis(millisToAdd);
	}

	public static InstantX plusNanos(long nanosToAdd) {
		return dateTime = dateTime.plusNanos(nanosToAdd);
	}

	public static InstantX minus(TemporalAmount amountToSubtract) {
		return dateTime = dateTime.minus(amountToSubtract);
	}

	public static InstantX minus(long amountToSubtract, TemporalUnit unit) {
		return dateTime = dateTime.minus(amountToSubtract, unit);
	}

	public static InstantX minusSeconds(long secondsToSubtract) {
		return dateTime = dateTime.minusSeconds(secondsToSubtract);
	}

	public static InstantX minusMillis(long millisToSubtract) {
		return dateTime = dateTime.minusMillis(millisToSubtract);
	}

	public static InstantX minusNanos(long nanosToSubtract) {
		return dateTime = dateTime.minusNanos(nanosToSubtract);
	}

	public static <R> R query(TemporalQuery<R> query) {
		return dateTime.query(query);
	}

	public static Temporal adjustInto(Temporal temporal) {
		return dateTime.adjustInto(temporal);
	}

	public static long until(InstantX endExclusive, TemporalUnit unit) {
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

	public static int compareTo(InstantX otherInstant) {
		return dateTime.compareTo(otherInstant);
	}

	public static boolean isAfter(InstantX otherInstant) {
		return dateTime.isAfter(otherInstant);
	}

	public static boolean isBefore(InstantX otherInstant) {
		return dateTime.isBefore(otherInstant);
	}

	public static void setInstant(InstantX otherInstant){
		dateTime = otherInstant;
	}

	public static InstantX getInstant() {
		return dateTime;
	}


	public static LocalDateTime toLocalDateTime() {
		return Date.getInstant().toLocalDateTime(ZoneId.of("UTC"));
	}
}
