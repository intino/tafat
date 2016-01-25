package tafat.natives.userinterface.heatmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tafat.UserInterface;

public class Values {

	public static String values(tafat.UserInterface.Heatmap $) {
		JsonArray jsonElements = new JsonArray();
		$.regionList().forEach(r -> jsonElements.add(jsonObjectOf(r)));
		return jsonElements.toString();
	}

	private static JsonElement jsonObjectOf(UserInterface.Heatmap.Region region) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", region._simpleName());
		jsonObject.addProperty("value", region.value());
		return jsonObject;
	}
}