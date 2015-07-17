package tafat.engine;

import java.text.ParseException;
import java.util.HashMap;


public class Scene extends ModelObject {
	public HashMap<String,ModelObject> item = new HashMap<String,ModelObject>();

	public Scene() {
		contentList.add("outdoor");
		contentList.add("locationsgroup");
	}
	
	public void init() throws ParseException{
		super.init();
		insertItem(this);
	}
	
	public void insertItem(ModelObject object) {
		if (object == null)
			return;
//		for (ModelObject aux : object.objectList)
//			insertItem(aux);
		if (object.id.equals(""))
			return;
		item.put(object.id, object);
	}
	
	/* IMPROVEMENT - 19/08/2011 */
	public void tick(Long time) {
		for (ModelObject object : objectList)
			object.tick(time);
		
		tickOn(time);
		tickOff(time);
	}

	public ModelObject search(String objectId) {
		ModelObject result = this;
		for (String piece : objectId.split("/")) {
			result = result.objectList.stream().filter(m -> m.id.equalsIgnoreCase(piece)).findFirst().orElse(null);
			if (result == null) return null;
		}
		return result;
	}
	
	/* IMPROVEMENT 19/08/2011 */

}
