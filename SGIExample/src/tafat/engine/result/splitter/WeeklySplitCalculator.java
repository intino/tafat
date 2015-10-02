package tafat.engine.result.splitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import tafat.engine.Time;

public class WeeklySplitCalculator extends SplitCalculator {
	@Override
	public long calculateNextSplit() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(Time.getInstance().getSimulationDate());
		
		DateFormat dateFormat = new SimpleDateFormat("w-y");
		currentPeriod = "week " + dateFormat.format(calendar.getTime());
		
		calendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
		calendar.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		
		return calendar.getTimeInMillis();
	}
}
