package tafat.natives.userinterface.linechart;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tafat.UserInterface;

import java.util.List;

public class Data {

	public static JsonElement buildJson(tafat.UserInterface.LineChart $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", "chart");
		jsonObject.addProperty("title", $.title());
		jsonObject.add("lines", arrayOf($.lineList()));
		return jsonObject;
	}

	private static JsonArray arrayOf(List<UserInterface.LineChart.Line> lines) {
		JsonArray array = new JsonArray();
		lines.forEach(l -> array.add(l.data()));
		return array;
	}
}