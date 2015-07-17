package tafat.engine.gis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tafat.engine.Console;
import tafat.engine.interoperability.XLSHandler;




public class RouteHandler{
	
	//final String xlsPath = "/src/tafat/engine/gis/";
	final String xlsFilename = "routes.xls";
	private ArrayList<Route> routeCache = new ArrayList<Route>();
	private XLSHandler xlsFile  = new XLSHandler(/*xlsPath +*/ xlsFilename,0);
	public final static String GOOGLE_PLATFORM = "GOOGLE";
	
	
	private static RouteHandler INSTANCE = null;
	
	
    private RouteHandler(){
    	
    };
    
	public static RouteHandler getInstance(){
		if(INSTANCE == null){
			INSTANCE = new RouteHandler();
		}
		return INSTANCE;
	}
	
	public Route getRoute(GisCoordinate origin,GisCoordinate destination){
		return getRoute(origin,destination,GOOGLE_PLATFORM);
	}
	
	
	public Route getRoute(GisCoordinate origin,GisCoordinate destination,String platform){
		
		//Route route = new Route();
		
		//Look the route up in the system cache
		for (Route cacheRoute: routeCache)
			if (cacheRoute.origin.equal(origin) && cacheRoute.destination.equal(destination))
				return new Route (cacheRoute.origin, cacheRoute.destination, cacheRoute.distance, cacheRoute.duration);
				
		//If the route was not in cache it reads the xls route database
		for (int i=1; i < xlsFile.getNumberOfEntries(); i++){
			String ori = xlsFile.readCellofRow(i, "origin");
			ori = ori.replace(",", ".");
			String coord[] = ori.split(";");
			GisCoordinate xlsOrigin = new GisCoordinate();
			xlsOrigin.setLongitude(coord[0]);
			xlsOrigin.setLatitude(coord[1]);
			
			String dest = xlsFile.readCellofRow(i,"destination");
			dest = dest.replace(",", ".");
			String coord2[] = dest.split(";");
			GisCoordinate xlsDestination = new GisCoordinate();
			xlsDestination.setLongitude(coord2[0]);
			xlsDestination.setLatitude(coord2[1]);
			
			
			if(xlsOrigin.equal(origin)&& xlsDestination.equal(destination)){
				Route route = new Route (origin, destination, Integer.parseInt(xlsFile.readCellofRow(i, "distance")), Integer.parseInt(xlsFile.readCellofRow(i,"duration")));
				routeCache.add((Route)route.clone());
				return (Route)route.clone();
			}
		}
		
		//if the route was not in the xls database, it will be founded by accessing google maps service
		if(platform.equals(GOOGLE_PLATFORM)){
			Route route = getGoogleRoute(origin,destination);
			xlsRouteStore(route);
			routeCache.add((Route)route.clone());
			return (Route)route.clone();
		}
		return null;
	}
	
	
	private void xlsRouteStore(Route route) {
		
		String xlsformat = route.origin.String();
		xlsformat = xlsformat.replace(".", ",");
		xlsFile.writeCellOfRow(xlsFile.getNumberOfEntries(), "origin", xlsformat);
		
		xlsformat = route.destination.String();
		xlsformat = xlsformat.replace(".", ",");
		xlsFile.writeCellOfRow(xlsFile.getNumberOfEntries()-1, "destination", xlsformat);
		
		xlsformat = route.distance + "";
		xlsformat = xlsformat.replace(".", ",");
		xlsFile.writeCellOfRow(xlsFile.getNumberOfEntries()-1, "distance", xlsformat);
		
		xlsformat = route.duration + "";
		xlsformat = xlsformat.replace(".", ",");
		xlsFile.writeCellOfRow(xlsFile.getNumberOfEntries()-1, "duration", xlsformat);
	}
	
	private Route getGoogleRoute(GisCoordinate origin,GisCoordinate destination){
		
		String requestUrl = "http://maps.google.com/maps/api/directions/json?origin="+origin.getLongitude()+","+origin.getLatitude()+"&destination="+destination.getLongitude()+","+destination.getLatitude()+"&driving&sensor=false";
		Route route = new Route();
		route.origin = origin;
		route.destination = destination;
	
		try {
			URL url = new URL(requestUrl.toString());
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "",inputLine;
			System.out.println("-----RESPONSE START-----");
			while ((inputLine = in.readLine()) != null) {
				if(inputLine.contains("polyline"))
					for(int i = 0; i < 3;i++) inputLine= in.readLine();
				if(!inputLine.contains("html_instructions"))
					s = s.concat(inputLine);
			}
			in.close();
			System.out.println(s);
			System.out.println("-----RESPONSE END-----");

		
			JSONArray array;
			JSONParser parser = new JSONParser();
			
			try {
				Object obj1=parser.parse(s);
				System.out.println(obj1);
				array = (JSONArray)((JSONObject)obj1).get("routes");
				JSONObject obj2 = (JSONObject) array.get(0);
				array = (JSONArray)((JSONObject)obj2).get("legs");
				obj2 = (JSONObject) array.get(0);
				System.out.println(((JSONObject)obj2.get("distance")).get("value"));
				route.distance = (Long) (((JSONObject)obj2.get("distance")).get("value"));
				route.duration = (Long) (((JSONObject)obj2.get("duration")).get("value"));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e){
				xlsFile.write();
				Console.error("OVER_QUERY_LIMIT in Google");
				System.exit(0);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return route;
	}
	
	public void terminate () {
		xlsFile.write();
	}

	
}