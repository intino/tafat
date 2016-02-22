package tafat.natives.userinterface.heatmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import tafat.UserInterface;

import java.util.List;

import static tafat.engine.helpers.ImageHelper.base64;

public class Data {

	public static JsonObject buildJson(UserInterface.Heatmap $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", "heatmap");
		jsonObject.addProperty("title", $.title());
		jsonObject.addProperty("background", base64($.background()));
		jsonObject.addProperty("color", $.color().code());
		jsonObject.add("regions", arrayOf($.regionList()));
		return jsonObject;
	}

	private static JsonArray arrayOf(List<? extends UserInterface.Heatmap.Region> regions) {
		JsonArray array = new JsonArray();
		regions.forEach(r -> array.add(r.data()));
		return array;
	}

}