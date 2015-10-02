package tafat.engine;

import java.util.Date;

public class Time {
	final public int minute = 60;
	final public int hour = 3600;
	final public int day = 86400;

	private Date initialDate;
	private Date finishDate;
	
	private long initialTimeMilliseconds;
	private long finishTimeMilliseconds;
	
	private long durationInTicks;
	private long time;
	private long currentTick = -1;
	
	
	private static Time instance = null;
	
	private Time (Date initialDate, Date finishDate){
		this.initialDate = initialDate;
		this.finishDate = finishDate;
		this.initialTimeMilliseconds = initialDate.getTime();
		this.time = initialTimeMilliseconds - 1000;
		this.finishTimeMilliseconds = finishDate.getTime();
		this.durationInTicks = (finishTimeMilliseconds - initialTimeMilliseconds) / 1000;
		this.currentTick = -1;
	}
	
	public static void createInstance(Date initialDate, Date finishDate){
		if (instance == null){
			instance = new Time (initialDate, finishDate);
		}
	}
	
	public static Time getInstance (){
		return instance;
	}
	
	public long getSimulationSeconds(){
		return currentTick;
	}
	
	public long getSimulationMinutes(){
		return currentTick / minute;
	}
	
	public long getSimulationHours(){
		return currentTick / hour;
	}
	
	public long getSimulationDays(){
		return currentTick / day;
	}
	
	public Date getSimulationDate(){
//		if (currentTick == -1)
//			return new Date(initialTimeMilliseconds);
//		long time = initialTimeMilliseconds + (currentTick * 1000);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(time);
//		return calendar.getTime();
		return new Date (time);
	}
	
	public long getSimulationDateInLong(){
		return time;
	}
	
	public Date getFinishSimulationDate(){
		return finishDate;
	}
	
	public Date getInitialSimulationDate(){
		return initialDate;
	}
	
	public long getSimulationDurationTicks(){
		return durationInTicks;
	}
	
	public long getSimulationTick(){
		return currentTick;
	}
	
	public void tick(){
		currentTick++;
		time += 1000;
	}
	
	public static void clear() {
		instance = null;
	}
	
}
