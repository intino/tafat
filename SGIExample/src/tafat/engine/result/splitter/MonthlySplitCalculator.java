package tafat.engine.result.splitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import tafat.engine.Time;

public class MonthlySplitCalculator extends SplitCalculator{
	
	public long calculateNextSplit() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(Time.getInstance().getSimulationDate());
		
		DateFormat dateFormat = new SimpleDateFormat("M-y");
		currentPeriod = "month " + dateFormat.format(calendar.getTime());
		
		calendar.add(GregorianCalendar.MONTH, 1);
		calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
		calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}
	
}
