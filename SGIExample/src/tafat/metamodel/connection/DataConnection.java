/*****
Generated automatically by tafat.framework.Translator
Version: 13.06.2012 at 12:46 BST
*****/
package tafat.metamodel.connection;

import tafat.engine.ModelObject;
import tafat.engine.Connection;

import java.text.ParseException;

public abstract class DataConnection extends ModelObject implements Connection{

	//Features
public String destination;
public String source;


	

	
	
	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("destination"))
destination = value;
else if (name.equals("source"))
source = value;
else
super.loadAttribute(name, value);
}

	
	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
destination = "";
source = "";

}

}

