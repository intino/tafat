package tafat.natives.userinterface.linechart.line;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Data {

	public static JsonElement buildJson(tafat.UserInterface.LineChart.Line $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", $.name());
		jsonObject.addProperty("label", $.label());
		return jsonObject;
	}
}