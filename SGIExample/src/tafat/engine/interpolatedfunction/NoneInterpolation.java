package tafat.engine.interpolatedfunction;

import tafat.engine.Console;


public class NoneInterpolation extends InterpolationMethod {

	private Coordinate[] coordinates;
	private double TOLERANCE;
	private int cache = 0;
	
	public NoneInterpolation (Coordinate[] coordinates, double tolerance){
		this.coordinates = coordinates;
		this.TOLERANCE = tolerance;
		if(!SortHandler.isSortedNumberList(this.coordinates))
			this.coordinates = SortHandler.sortNumberList(this.coordinates);
	}
	
	@Override
	public double getY(double X) {
		if (Math.abs(coordinates[cache].x - X) < TOLERANCE)
			return coordinates[cache].y;
		
		if (cache + 1 < coordinates.length)
			if (Math.abs(coordinates[cache + 1].x - X) < TOLERANCE){
				cache++;
				return coordinates[cache].y;
			}
		
		if (cache - 1 >= 0)
			if (Math.abs(coordinates[cache - 1].x - X) < TOLERANCE){
				cache--;
				return coordinates[cache].y;
			}
		
		return dichotomicSearch(X);
		
//		for (Coordinate coordinate : coordinates){
//			if (Math.abs(coordinate.x - X) < TOLERANCE){
//				return coordinate.y;
//			}
//		}
//		return Double.NaN;
	}
	
	private double dichotomicSearch(double X) {
		int currentMinRange = 0;
		int currentMaxRange = coordinates.length - 1;
		int indexToAccess = 0;
		double closest = Double.POSITIVE_INFINITY;
		double closestValue = 0;
		
		while (currentMaxRange >= currentMinRange){
			indexToAccess = (currentMinRange + currentMaxRange) / 2;
			
			if (Math.abs(coordinates[indexToAccess].x - X) < closest){
				closest = Math.abs(coordinates[indexToAccess].x - X);
				closestValue = coordinates[indexToAccess].y;
			}
			
			if (Math.abs(coordinates[indexToAccess].x - X) < TOLERANCE){
				cache = indexToAccess;
				return coordinates[indexToAccess].y;
			}
			else if (X < coordinates[indexToAccess].x){
				currentMaxRange = indexToAccess - 1;
//				indexToAccess = ((currentMaxRange - currentMinRange) / 2) + currentMinRange;
			}
			else if (X > coordinates[indexToAccess].x){
				currentMinRange = indexToAccess + 1;
//				indexToAccess = ((currentMaxRange - currentMinRange) / 2) + currentMinRange;
			}
		}
		Console.out("Warning: the returned coordinate is at this distance: " + closest + " but it does not reach the tolerance value");
		return closestValue;		
	}

}
