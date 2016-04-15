package tafat.natives.userinterface.heatmap.circle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Data {

	public static JsonElement buildJson(tafat.UserInterface.Heatmap.Circle $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", $.name());
		jsonObject.addProperty("type", "circle");
		jsonObject.addProperty("zIndex", $.zIndex());
		jsonObject.addProperty("top", $.centerY() - $.diameter() / 2);
		jsonObject.addProperty("left", $.centerX() - $.diameter() / 2);
		jsonObject.addProperty("height", $.diameter());
		jsonObject.addProperty("width", $.diameter());
		return jsonObject;
	}
}