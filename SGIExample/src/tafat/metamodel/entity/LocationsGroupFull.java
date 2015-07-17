/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.entity;

import java.text.ParseException;
import java.util.ArrayList;


public class LocationsGroupFull extends LocationsGroup{
	
	
	
	//Features
public String coordinates;


	

	//Context Elements: begin
public PowerBusFull powerBus;
public ArrayList<PowerEquipment> powerEquipmentList = new ArrayList<PowerEquipment>();
//Context Elements: end

	public LocationsGroupFull(){
contentList.add("locationsgroup");
contentList.add("outdoor");
contentList.add("household");
contentList.add("powerbus");
contentList.add("wildcard");

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
coordinates = "";

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("coordinates"))
coordinates = value;
else
super.loadAttribute(name, value);
}


	

	public void init() throws ParseException {

super.init();

powerBus = (PowerBusFull) findChild(PowerBusFull.class);

for (Object object : collect(PowerEquipment.class, true))
	powerEquipmentList.add((PowerEquipment) object);

if (powerBus == null) {
	return;
}

for (PowerEquipment powerEquipment : powerEquipmentList)
		powerBus.powerEquipmentList.add(powerEquipment);
}


	public String toString()
{
String result="";
 result+="<feature name=\"coordinates\" value=\"" + coordinates + "\"/>\n"; 
return result;

}

}

