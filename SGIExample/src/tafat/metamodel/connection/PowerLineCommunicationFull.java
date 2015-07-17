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


public class PowerLineCommunicationFull extends PowerLineCommunication{
	
	
	
	//Features
public double speedsRange;// ( kbps )


	

	//Context Elements: begin
public ModelObject destinationObject;
public ModelObject sourceObject;
//Context Elements: end

	public PowerLineCommunicationFull(){

}


	public void setDefaultValues() throws ParseException
{
super.setDefaultValues();
speedsRange = 0.0;

}


	public void loadAttribute(String name, String value) throws ParseException
{
if (name.equals("speedsRange"))
speedsRange= UnitConversor.parse(value,"kbps");
else
super.loadAttribute(name, value);
}


	

	public void init() throws ParseException {

        super.init();

        sourceObject = Main.scene.item.get(source);
        destinationObject = Main.scene.item.get(destination);

        sourceObject.receiveNotification(new Notification(0, "CONNECTION", destinationObject));
        destinationObject.receiveNotification(new Notification (0, "CONNECTION", sourceObject));
}


	public String toString()
{
String result="";
 result+="<feature name=\"speedsRange\" value=\"" + speedsRange + "\"/>\n"; 
return result;

}

}

