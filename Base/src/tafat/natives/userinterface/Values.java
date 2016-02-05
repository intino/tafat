package tafat.natives.userinterface;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import static java.time.format.DateTimeFormatter.ofPattern;
import static tafat.engine.Date.getDateTime;

public class Values {

	public static String values(tafat.UserInterface $) {
		return $.heatmap() != null ? process($.heatmap().values()) : null;
	}

	private static String process(JsonElement values) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("time", getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
		jsonObject.add("values", values);
		return jsonObject.toString();
	}
}