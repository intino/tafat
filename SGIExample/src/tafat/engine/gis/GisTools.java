package tafat.engine.gis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tafat.engine.ModelObject;
import tafat.metamodel.entity.OutdoorFull;

public class GisTools {

	
	public static ArrayList<ModelObject> searchObjectsInArea(ModelObject place, GisCoordinate coordinate, double radio, Class<?> classToSearch){
		ArrayList<ModelObject> objectList = place.collect(OutdoorFull.class, true);
		ArrayList<ModelObject> objectsInArea = new ArrayList<ModelObject>();
		for(ModelObject object : objectList){
			if(((OutdoorFull)object).coordinates.contains(";")){
				String objectCoord[] = ((OutdoorFull)object).coordinates.split(";"); 
				GisCoordinate objectCoordinate = new GisCoordinate(objectCoord[0], objectCoord[1]);
				if(radio >= distance(objectCoordinate,coordinate)){
					ArrayList<ModelObject> searchedObjectList = object.collect(classToSearch, true);
					for(ModelObject searchedObject: searchedObjectList)
						objectsInArea.add(searchedObject);
				}
			}
		}
		return objectsInArea;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<ModelObject> searchNearestObject(ModelObject place, GisCoordinate coordinate, int numberOfObjects, Class<?> classToSearch){
		ArrayList<ModelObject> outdoorList = place.collect(OutdoorFull.class, true);
		ArrayList<ModelObject> objectList = new ArrayList<ModelObject>();
		ArrayList<ModelObject> nearestObjectList = new ArrayList<ModelObject>();
		
		ArrayList<DistanceIndex> distanceIndexList = new ArrayList<DistanceIndex>();
		for(int i=0;i<outdoorList.size();i++){
			if(((OutdoorFull)outdoorList.get(i)).coordinates.contains(";")){
				//((OutdoorFull)outdoorList.get(i)).getChilds().get(0).id
				String objectCoord[] = ((OutdoorFull)outdoorList.get(i)).coordinates.split(";"); 
				GisCoordinate objectCoordinate = new GisCoordinate(objectCoord[0], objectCoord[1]);
				distanceIndexList.add(new DistanceIndex(distance(objectCoordinate,coordinate),i));
			}
		}
		
		Collections.sort(distanceIndexList,new CoordinatesComparator());
		
		for(int i=0;i<distanceIndexList.size();i++){
			ModelObject outdoor = outdoorList.get(distanceIndexList.get(i).index);
			ArrayList<ModelObject> collectedObjects = outdoor.collect(classToSearch, true);
			for(ModelObject object : collectedObjects)
				objectList.add(object);
		}
		
		if (numberOfObjects == -1)
			return objectList;
					if(numberOfObjects > objectList.size()) return objectList;
		for(int i = 0; i < numberOfObjects; i++)
			nearestObjectList.add(objectList.get(i));
		return nearestObjectList;
	}
	
	private static class DistanceIndex{
		
		public double distance;
		public int index;
		
		public DistanceIndex(double distance, int index){
			this.distance = distance;
			this.index = index;
		}
	}
	 @SuppressWarnings("rawtypes")
	private static class CoordinatesComparator implements Comparator {

		  public int compare(Object object1,Object object2) {
			  return (int) (((DistanceIndex)object1).distance - ((DistanceIndex)object2).distance); 
		  }

//		  public boolean equals(Object object) {
//		    return this == object;
//		  }
	}
	
	public static double distance(GisCoordinate coordinate1, GisCoordinate coordinate2) {
		double lat1 = coordinate1.getLatitudeDouble();
		double lng1 = coordinate1.getLongitudeDouble();
		
		double lat2 = coordinate2.getLatitudeDouble();
		double lng2 = coordinate2.getLongitudeDouble();
		
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    int meterConversion = 1609;

	    return new Double(dist * meterConversion).doubleValue();
	    }
}
