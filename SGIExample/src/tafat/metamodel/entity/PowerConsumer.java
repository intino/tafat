/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.entity;


import java.text.ParseException;

public abstract class PowerConsumer extends PowerEquipment{

	//Features
public double heatGainFraction;


	

	
	
	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("heatGainFraction"))
heatGainFraction = Double.parseDouble(value);
else
super.loadAttribute(name, value);
}

	
	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
heatGainFraction = 1;

}

}

