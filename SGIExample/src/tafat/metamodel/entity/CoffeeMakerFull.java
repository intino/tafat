/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.entity;

import java.text.ParseException;


public class CoffeeMakerFull extends CoffeeMaker{
	
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
 OFF(0),STANDBY(1),ON(2);
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
public PhasesSystem phasesSystem;
public VoltageLevel voltageLevel;


	//Variables
public Mode mode;
public double powerFactor;


	

	public CoffeeMakerFull(){

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
phasesSystem = PhasesSystem.SINGLE_PHASE_AND_NEUTRAL;
voltageLevel = VoltageLevel.LOW;
powerFactor = 0.0;

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("phasesSystem"))
phasesSystem=PhasesSystem.valueOf(value.toUpperCase());
else if (name.equals("voltageLevel"))
voltageLevel=VoltageLevel.valueOf(value.toUpperCase());
else if (name.equals("mode"))
mode=Mode.valueOf(value.toUpperCase());
else if (name.equals("powerFactor"))
powerFactor = Double.parseDouble(value);
else
super.loadAttribute(name, value);
}


	

	

	public String toString()
{
String result="";
 result+="<feature name=\"phasesSystem\" value=\"" + phasesSystem.toString() + "\"/>\n"; 
 result+="<feature name=\"voltageLevel\" value=\"" + voltageLevel.toString() + "\"/>\n"; 
 result+="<feature name=\"mode\" value=\"" + mode.toString() + "\"/>\n"; 
 result+="<feature name=\"powerFactor\" value=\"" + powerFactor + "\"/>\n"; 
return result;

}

}

