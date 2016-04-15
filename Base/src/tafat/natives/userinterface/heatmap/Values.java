package tafat.natives.userinterface.heatmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tafat.UserInterface;

public class Values {

	public static JsonArray values(tafat.UserInterface.Heatmap $) {
		JsonArray array = new JsonArray();
		$.regionList().forEach(r -> array.add(jsonObjectOf(r)));
		return array;
	}

	private static JsonElement jsonObjectOf(UserInterface.Heatmap.Region region) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", region.name());
		jsonObject.addProperty("value", region.value());
		return jsonObject;
	}
}