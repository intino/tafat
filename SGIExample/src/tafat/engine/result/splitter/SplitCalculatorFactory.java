package tafat.engine.result.splitter;

public class SplitCalculatorFactory {

	public SplitCalculator getSplitCalculator (String seed){
//		switch (seed){
//			case "MONTH": 
//				return new MonthlySplitCalculator();
//			case "WEEK":
//				return new WeeklySplitCalculator();
//			case "DAY":
//				return new DailySplitCalculator();
//			default:
//				return null;
//		}
		
		if (seed.equals("MONTH"))
			return new MonthlySplitCalculator();
		else if (seed.equals("WEEK"))
			return new WeeklySplitCalculator();
		else if (seed.equals("DAY"))
			return new DailySplitCalculator();
		else
			return null;
	}
}
