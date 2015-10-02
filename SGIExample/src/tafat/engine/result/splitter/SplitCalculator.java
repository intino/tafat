package tafat.engine.result.splitter;

public abstract class SplitCalculator {
	String currentPeriod = "";
	
	public abstract long calculateNextSplit ();
	
	public String getCurrentPeriod(){
		return currentPeriod;
	}
}
