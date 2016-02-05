package tafat.natives.userinterface.heatmap.circle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Data {

	public static JsonElement buildJson(tafat.UserInterface.Heatmap.Circle $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", $._simpleName());
		jsonObject.addProperty("type", "circle");
		jsonObject.addProperty("zIndex", $.zIndex());
		jsonObject.addProperty("centerX", $.centerX());
		jsonObject.addProperty("centerY", $.centerY());
		jsonObject.addProperty("diameter", $.diameter());
		return jsonObject;
	}
}