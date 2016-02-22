package tafat.natives.userinterface;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import tafat.UserInterface;

import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static tafat.engine.Date.getDateTime;
import static tafat.engine.helpers.ImageHelper.base64;

public class Data {

	public static String buildJson(tafat.UserInterface $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", $.title());
		jsonObject.addProperty("logo", base64($.logo()));
		jsonObject.addProperty("time", getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
		jsonObject.add("components", arrayOf($.graphicalComponentList()));
		return jsonObject.toString();
	}

	private static JsonArray arrayOf(List<UserInterface.GraphicalComponent> components) {
		JsonArray array = new JsonArray();
		components.forEach(r -> array.add(r.data()));
		return array;
	}
}