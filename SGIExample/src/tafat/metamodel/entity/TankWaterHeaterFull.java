/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.entity;

import java.text.ParseException;
import tafat.engine.conversion.UnitConversor;


public class TankWaterHeaterFull extends TankWaterHeater{
	
	public enum Labelling {
 AAAA(0),AAA(1),AA(2),A(3),B(4),C(5),D(6),E(7),F(8),G(9);
 private double value;
 Labelling(double val)
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
public enum VoltageLevel {
 LOW(0),MEDIUM(1),HIGH(2);
 private double value;
 VoltageLevel(double val)
 {
 	value=val;
 }
 
 public double getValue()
 {
 	return value;
 }
}
public enum Mode {
 OFF(0),STANDBY(1),ON(2),LOAD_REDUCTION(3);
 private double value;
 Mode(double val)
 {
 	value=val;
 }
 
 public double getValue()
 {
 	return value;
 }
}

	
	//Features
public double installedPower;// ( W )
public Labelling labelling;// ( W )
public PhasesSystem phasesSystem;// ( W )
public double ratedPower;// ( W )
public VoltageLevel voltageLevel;// ( W )
public double volume;// ( m^3 )


	//Variables
public double currentWaterAmount;// ( m^3 )
public Mode mode;// ( m^3 )
public double powerFactor;// ( m^3 )
public double referenceTemperature;// ( dC )
public double temperatureWaterIn;// ( dC )
public double temperatureWaterOut;// ( dC )


	

	public TankWaterHeaterFull(){

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
installedPower = 3000;
labelling = Labelling.A;
phasesSystem = PhasesSystem.SINGLE_PHASE_AND_NEUTRAL;
ratedPower = 100;
voltageLevel = VoltageLevel.LOW;
volume = 20;
currentWaterAmount = 0.0;
powerFactor = 0.0;
referenceTemperature = 0.0;
temperatureWaterIn = 0.0;
temperatureWaterOut = 0.0;

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("installedPower"))
installedPower= UnitConversor.parse(value,"W");
else if (name.equals("labelling"))
labelling=Labelling.valueOf(value.toUpperCase());
else if (name.equals("phasesSystem"))
phasesSystem=PhasesSystem.valueOf(value.toUpperCase());
else if (name.equals("ratedPower"))
ratedPower= UnitConversor.parse(value,"W");
else if (name.equals("voltageLevel"))
voltageLevel=VoltageLevel.valueOf(value.toUpperCase());
else if (name.equals("volume"))
volume= UnitConversor.parse(value,"m^3");
else if (name.equals("currentWaterAmount"))
currentWaterAmount= UnitConversor.parse(value,"m^3");
else if (name.equals("mode"))
mode=Mode.valueOf(value.toUpperCase());
else if (name.equals("powerFactor"))
powerFactor = Double.parseDouble(value);
else if (name.equals("referenceTemperature"))
referenceTemperature= UnitConversor.parse(value,"dC");
else if (name.equals("temperatureWaterIn"))
temperatureWaterIn= UnitConversor.parse(value,"dC");
else if (name.equals("temperatureWaterOut"))
temperatureWaterOut= UnitConversor.parse(value,"dC");
else
super.loadAttribute(name, value);
}


	

	

	public String toString()
{
String result="";
 result+="<feature name=\"installedPower\" value=\"" + installedPower + "\"/>\n"; 
 result+="<feature name=\"labelling\" value=\"" + labelling.toString() + "\"/>\n"; 
 result+="<feature name=\"phasesSystem\" value=\"" + phasesSystem.toString() + "\"/>\n"; 
 result+="<feature name=\"ratedPower\" value=\"" + ratedPower + "\"/>\n"; 
 result+="<feature name=\"voltageLevel\" value=\"" + voltageLevel.toString() + "\"/>\n"; 
 result+="<feature name=\"volume\" value=\"" + volume + "\"/>\n"; 
 result+="<feature name=\"currentWaterAmount\" value=\"" + currentWaterAmount + "\"/>\n"; 
 result+="<feature name=\"mode\" value=\"" + mode.toString() + "\"/>\n"; 
 result+="<feature name=\"powerFactor\" value=\"" + powerFactor + "\"/>\n"; 
 result+="<feature name=\"referenceTemperature\" value=\"" + referenceTemperature + "\"/>\n"; 
 result+="<feature name=\"temperatureWaterIn\" value=\"" + temperatureWaterIn + "\"/>\n"; 
 result+="<feature name=\"temperatureWaterOut\" value=\"" + temperatureWaterOut + "\"/>\n"; 
return result;

}

}

