package tafat.natives.userinterface;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import tafat.UserInterface;

import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static tafat.engine.Date.getDateTime;

public class Values {

	public static String values(tafat.UserInterface $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("time", getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
		jsonObject.add("values", arrayOf($.graphicalComponentList()));
		return jsonObject.toString();
	}

	private static JsonArray arrayOf(List<UserInterface.GraphicalComponent> components) {
		JsonArray array = new JsonArray();
		components.forEach(r -> array.add(objectOf(r)));
		return array;
	}

	private static JsonObject objectOf(UserInterface.GraphicalComponent component) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", component.title());
		jsonObject.add("values", component.values());
		return jsonObject;
	}
}