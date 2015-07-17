package tafat.engine.result.splitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import tafat.engine.Time;

public class DailySplitCalculator extends SplitCalculator {

	@Override
	public long calculateNextSplit() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(Time.getInstance().getSimulationDate());
		
		DateFormat dateFormat = new SimpleDateFormat("D-y");
		currentPeriod = "day " + dateFormat.format(calendar.getTime());
		
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		
		return calendar.getTimeInMillis();
	}

}
