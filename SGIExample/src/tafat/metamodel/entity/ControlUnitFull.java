/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.entity;

import java.text.ParseException;
import tafat.engine.Console;
import tafat.engine.conversion.UnitConversor;
import java.util.Date;
import tafat.engine.DateParser;
import java.util.ArrayList;
import tafat.engine.ModelObject;
import tafat.metamodel.connection.*;


public class ControlUnitFull extends ControlUnit{
	
	
	
	//Features
public String address;
public double areaFloor;// ( m^2 )
public Date constructionDate = new Date();// ( m^2 )
public String powerBuses;// ( m^2 )
public Date renovationDate = new Date();// ( m^2 )
public String smartMeters;// ( m^2 )


	//Variables
public double aimConsumption;// ( W )
public double realConsumption;// ( W )


	//Context Elements: begin
public ArrayList<ModelObject> controlableDevicesList = new ArrayList<ModelObject>();
public OutdoorFull outdoor;
public ArrayList<PowerConnection> powerConnectionList = new ArrayList<PowerConnection>();
public ArrayList<ModelObject> supervisoryDevice = new ArrayList<ModelObject>();
//Context Elements: end

	public ControlUnitFull(){

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
address = "";
areaFloor = 100;
constructionDate = DateParser.parseDate("01/01/1970");
powerBuses = "";
renovationDate = DateParser.parseDate("01/01/1970");
smartMeters = "";
aimConsumption = 100000;
realConsumption = 0;

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("address"))
address = value;
else if (name.equals("areaFloor"))
areaFloor= UnitConversor.parse(value,"m^2");
else if (name.equals("constructionDate"))
constructionDate = DateParser.parseDate(value);
else if (name.equals("powerBuses"))
powerBuses = value;
else if (name.equals("renovationDate"))
renovationDate = DateParser.parseDate(value);
else if (name.equals("smartMeters"))
smartMeters = value;
else if (name.equals("aimConsumption"))
aimConsumption= UnitConversor.parse(value,"W");
else if (name.equals("realConsumption"))
realConsumption= UnitConversor.parse(value,"W");
else
super.loadAttribute(name, value);
}


	

	public void init() throws ParseException {

super.init();

outdoor = (OutdoorFull) findOwner(OutdoorFull.class);
if (outdoor == null){
	Console.out("WARNING: Control unit could need outdoor to get enviroment conditions depending of the behavior");
}
}


	public String toString()
{
String result="";
 result+="<feature name=\"address\" value=\"" + address + "\"/>\n"; 
 result+="<feature name=\"areaFloor\" value=\"" + areaFloor + "\"/>\n"; 
 result+="<feature name=\"constructionDate\" value=\"" + constructionDate.toString() + "\"/>\n"; 
 result+="<feature name=\"powerBuses\" value=\"" + powerBuses + "\"/>\n"; 
 result+="<feature name=\"renovationDate\" value=\"" + renovationDate.toString() + "\"/>\n"; 
 result+="<feature name=\"smartMeters\" value=\"" + smartMeters + "\"/>\n"; 
 result+="<feature name=\"aimConsumption\" value=\"" + aimConsumption + "\"/>\n"; 
 result+="<feature name=\"realConsumption\" value=\"" + realConsumption + "\"/>\n"; 
return result;

}

}

