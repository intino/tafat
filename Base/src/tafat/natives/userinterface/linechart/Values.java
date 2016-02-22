package tafat.natives.userinterface.linechart;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tafat.UserInterface;

public class Values {

	public static JsonArray values(UserInterface.LineChart $) {
		JsonArray array = new JsonArray();
		$.lineList().forEach(l -> array.add(jsonObjectOf(l)));
		return array;
	}

	private static JsonElement jsonObjectOf(UserInterface.LineChart.Line line) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", line.label());
		jsonObject.addProperty("value", line.value());
		return jsonObject;
	}
}