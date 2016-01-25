package tafat.natives.userinterface;

import com.google.gson.JsonObject;

import static tafat.engine.helpers.ImageHelper.base64;

public class Data {

	public static String buildJson(tafat.UserInterface $) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", $.title());
		jsonObject.addProperty("logo", base64($.logo()));
		jsonObject.add("interface", $.graphicalComponent().data());
		return jsonObject.toString();
	}
}