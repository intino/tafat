package tafat.engine.interpolatedfunction;

public class LinearInterpolation extends InterpolationMethod {

	private Coordinate[] coordinates;
	private int cache = 0;
	
	public LinearInterpolation (Coordinate[] coordinates){
		this.coordinates = coordinates;
		if(!SortHandler.isSortedNumberList(this.coordinates))
			this.coordinates = SortHandler.sortNumberList(this.coordinates);
	}
	
	@Override
	public double getY(double X) {
		if((X >= coordinates[cache].x) && (X <= coordinates[cache + 1].x))
			return getRectValue(coordinates[cache], coordinates[cache + 1], X);
		
		if (cache + 2 < coordinates.length)
			if((X >= coordinates[cache + 1].x) && (X <= coordinates[cache + 2].x)){
				cache++;
				return getRectValue(coordinates[cache], coordinates[cache + 1], X);
			}
		
		if (cache - 2 >= 0)
			if((X >= coordinates[cache - 1].x) && (X <= coordinates[cache].x)){
				cache--;
				return getRectValue(coordinates[cache], coordinates[cache + 1], X);
			}	
		
		return dichotomicSearch(X);
	}
	
	private double dichotomicSearch(double X) {
		int currentMinRange = 0;
		int currentMaxRange = coordinates.length - 1;
		int indexToAccess = ((currentMaxRange - currentMinRange) / 2) + currentMinRange;
		
		while (true){
			if (X < coordinates[indexToAccess].x){
				currentMaxRange = indexToAccess;
				indexToAccess = ((currentMaxRange - currentMinRange) / 2) + currentMinRange;
			}
			else if (X > coordinates[indexToAccess + 1].x){
				currentMinRange = indexToAccess + 1;
				indexToAccess = ((currentMaxRange - currentMinRange) / 2) + currentMinRange;
			}
			else if((X >= coordinates[indexToAccess].x) && (X <= coordinates[indexToAccess+1].x)){
				cache = indexToAccess;
				return getRectValue(coordinates[indexToAccess], coordinates[indexToAccess+1], X);
			}
		}
	}

	private double getRectValue(Coordinate c1, Coordinate C2, double X){
		double x1 = c1.x;
		double y1 = c1.y;
		double x2 = C2.x;
		double y2 = C2.y;
		
		double xdifference = x1 - x2;
		double ydifference = y1 - y2;
		
		double numerator = ydifference * X - ydifference * x1 + xdifference * y1;
		
		return numerator/xdifference;
	}
}
