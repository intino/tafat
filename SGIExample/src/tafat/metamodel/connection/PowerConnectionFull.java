/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.connection;

import java.text.ParseException;
import tafat.control.Main;
import tafat.engine.Notification;
import tafat.engine.conversion.UnitConversor;
import tafat.engine.ModelObject;


public class PowerConnectionFull extends PowerConnection{
	
	public enum CurrentType {
 AC(0),DC(1);
 private double value;
 CurrentType(double val)
 {
 	value=val;
 }
 
 public double getValue()
 {
 	return value;
 }
}
public enum LineType {
 AERIAL(0),SUBTERRANEAN(1);
 private double value;
 LineType(double val)
 {
 	value=val;
 }
 
 public double getValue()
 {
 	return value;
 }
}
public enum PhasesSystem {
 SINGLE_PHASE_AND_NEUTRAL(0),THREE_PHASE_WITHOUT_NEUTRAL(1),THREE_PHASE_AND_NEUTRAL(2);
 private double value;
 PhasesSystem(double val)
 {
 	value=val;
 }
 
 public double getValue()
 {
 	return value;
 }
}

	
	//Features
public double crossSection;// ( mm^2 )
public CurrentType currentType;// ( mm^2 )
public String destination;// ( mm^2 )
public double length;// ( m )
public LineType lineType;// ( m )
public double maximumCurrent;// ( A )
public PhasesSystem phasesSystem;// ( A )
public double powerCapacity;// ( MVA )
public double reactance;// ( ohms/m )
public double resistance;// ( ohms/m )
public String source;// ( ohms/m )
public double susceptance;// ( F/m )
public double voltageRated;// ( V )


	//Variables
public double destinationActivePowerFlow;// ( W )
public double destinationReactivePowerFlow;// ( VAr )
public boolean inService;// ( VAr )
public double power;// ( W )
public double sourceActivePowerFlow;// ( W )
public double sourceReactivePowerFlow;// ( VAr )
public double voltage;// ( kV )


	//Context Elements: begin
public ModelObject destinationObject;
public ModelObject sourceObject;
//Context Elements: end

	public PowerConnectionFull(){

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
crossSection = 0;
currentType = CurrentType.AC;
destination = "";
length = 0;
lineType = LineType.AERIAL;
maximumCurrent = 80;
phasesSystem = PhasesSystem.THREE_PHASE_AND_NEUTRAL;
powerCapacity = 100;
reactance = 0.000144;
resistance = 0.000087;
source = "";
susceptance = 0.000000000144;
voltageRated = 400;
destinationActivePowerFlow = 0.0;
destinationReactivePowerFlow = 0.0;
power = 0.0;
sourceActivePowerFlow = 0.0;
sourceReactivePowerFlow = 0.0;
voltage = 0.0;

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("crossSection"))
crossSection= UnitConversor.parse(value,"mm^2");
else if (name.equals("currentType"))
currentType=CurrentType.valueOf(value.toUpperCase());
else if (name.equals("destination"))
destination = value;
else if (name.equals("length"))
length= UnitConversor.parse(value,"m");
else if (name.equals("lineType"))
lineType=LineType.valueOf(value.toUpperCase());
else if (name.equals("maximumCurrent"))
maximumCurrent= UnitConversor.parse(value,"A");
else if (name.equals("phasesSystem"))
phasesSystem=PhasesSystem.valueOf(value.toUpperCase());
else if (name.equals("powerCapacity"))
powerCapacity= UnitConversor.parse(value,"MVA");
else if (name.equals("reactance"))
reactance= UnitConversor.parse(value,"ohms/m");
else if (name.equals("resistance"))
resistance= UnitConversor.parse(value,"ohms/m");
else if (name.equals("source"))
source = value;
else if (name.equals("susceptance"))
susceptance= UnitConversor.parse(value,"F/m");
else if (name.equals("voltageRated"))
voltageRated= UnitConversor.parse(value,"V");
else if (name.equals("destinationActivePowerFlow"))
destinationActivePowerFlow= UnitConversor.parse(value,"W");
else if (name.equals("destinationReactivePowerFlow"))
destinationReactivePowerFlow= UnitConversor.parse(value,"VAr");
else if (name.equals("inService"))
inService = Boolean.getBoolean(value);
else if (name.equals("power"))
power= UnitConversor.parse(value,"W");
else if (name.equals("sourceActivePowerFlow"))
sourceActivePowerFlow= UnitConversor.parse(value,"W");
else if (name.equals("sourceReactivePowerFlow"))
sourceReactivePowerFlow= UnitConversor.parse(value,"VAr");
else if (name.equals("voltage"))
voltage= UnitConversor.parse(value,"kV");
else
super.loadAttribute(name, value);
}


	

	public void init() throws ParseException {

			super.init();

			sourceObject = Main.scene.item.get(source);
			destinationObject = Main.scene.item.get(destination);

			sourceObject.receiveNotification(new Notification(0, "CONNECTION", this));
			destinationObject.receiveNotification(new Notification (0, "CONNECTION", this));
	}


	public String toString()
{
String result="";
 result+="<feature name=\"crossSection\" value=\"" + crossSection + "\"/>\n"; 
 result+="<feature name=\"currentType\" value=\"" + currentType.toString() + "\"/>\n"; 
 result+="<feature name=\"destination\" value=\"" + destination + "\"/>\n"; 
 result+="<feature name=\"length\" value=\"" + length + "\"/>\n"; 
 result+="<feature name=\"lineType\" value=\"" + lineType.toString() + "\"/>\n"; 
 result+="<feature name=\"maximumCurrent\" value=\"" + maximumCurrent + "\"/>\n"; 
 result+="<feature name=\"phasesSystem\" value=\"" + phasesSystem.toString() + "\"/>\n"; 
 result+="<feature name=\"powerCapacity\" value=\"" + powerCapacity + "\"/>\n"; 
 result+="<feature name=\"reactance\" value=\"" + reactance + "\"/>\n"; 
 result+="<feature name=\"resistance\" value=\"" + resistance + "\"/>\n"; 
 result+="<feature name=\"source\" value=\"" + source + "\"/>\n"; 
 result+="<feature name=\"susceptance\" value=\"" + susceptance + "\"/>\n"; 
 result+="<feature name=\"voltageRated\" value=\"" + voltageRated + "\"/>\n"; 
 result+="<feature name=\"destinationActivePowerFlow\" value=\"" + destinationActivePowerFlow + "\"/>\n"; 
 result+="<feature name=\"destinationReactivePowerFlow\" value=\"" + destinationReactivePowerFlow + "\"/>\n"; 
 result+="<feature name=\"inService\" value=\"" + inService + "\"/>\n"; 
 result+="<feature name=\"power\" value=\"" + power + "\"/>\n"; 
 result+="<feature name=\"sourceActivePowerFlow\" value=\"" + sourceActivePowerFlow + "\"/>\n"; 
 result+="<feature name=\"sourceReactivePowerFlow\" value=\"" + sourceReactivePowerFlow + "\"/>\n"; 
 result+="<feature name=\"voltage\" value=\"" + voltage + "\"/>\n"; 
return result;

}

}

