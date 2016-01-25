package tafat.natives.userinterface.heatmap.square;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Data {

	public static JsonElement buildJson(tafat.UserInterface.Heatmap.Square $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", $._simpleName());
		jsonObject.addProperty("type", "square");
		jsonObject.addProperty("top", $.top());
		jsonObject.addProperty("left", $.left());
		jsonObject.addProperty("height", $.height());
		jsonObject.addProperty("width", $.width());
		return jsonObject;
	}
}