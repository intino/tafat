package tafat.engine.gis;

public class GisCoordinate implements Cloneable{

	private String longitude;	
	private String latitude;

	
	public GisCoordinate(){
	}
	
	public GisCoordinate (String longitude, String latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public String getLatitude(){return latitude;}
	
	public String getLongitude(){return longitude;}
	
	public double getLatitudeDouble(){return Double.parseDouble(latitude);}
	
	public double getLongitudeDouble(){return Double.parseDouble(longitude);}
	
	public void setLatitude(String latitude){this.latitude = latitude;}
	
	public void setLongitude(String longitude){this.longitude = longitude;}
	
	public boolean equal(GisCoordinate coordinate){
		
		if(this.latitude.equals(coordinate.latitude) && this.longitude.equals(coordinate.longitude))return true;
		else return false;
	}
	
	public String String(){
		return this.longitude + ";" + this.latitude;
	}
	
	public Object clone(){
		return new GisCoordinate(this.longitude, this.latitude);
	}
}
