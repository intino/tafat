package tafat.engine.gis;



public class Route implements Cloneable{
	
	public GisCoordinate origin;
	public GisCoordinate destination;
	public long	distance;
	public long duration;
	
	public Route(){};
	public Route(GisCoordinate origin,GisCoordinate destination,long distance, long duration){
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}
	public Object clone(){
		GisCoordinate origin = (GisCoordinate) this.origin.clone();
		GisCoordinate destination = (GisCoordinate) this.destination.clone();
		
		return new Route (origin, destination, this.distance, this.duration);
	}
}
