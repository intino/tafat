package tafat.actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static tafat.engine.Date.getDateTime;
import static tafat.engine.helpers.ImageHelper.base64;

public class UserInterface {
	public static String jsonData(tafat.UserInterface self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", self.title());
		jsonObject.addProperty("logo", base64(self.logo()));
		jsonObject.addProperty("time", getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
		jsonObject.add("components", arrayOfComponents(self.graphicalComponentList()));
		return jsonObject.toString();
	}

	private static JsonArray arrayOfComponents(List<tafat.UserInterface.GraphicalComponent> components) {
		JsonArray array = new JsonArray();
		components.forEach(r -> array.add(r.data()));
		return array;
	}

	public static String interfaceValues(tafat.UserInterface self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("time", getDateTime().format(ofPattern("dd/MM/yyyy HH:mm:ss")));
		jsonObject.add("values", arrayOf(self.graphicalComponentList()));
		return jsonObject.toString();
	}

	private static JsonArray arrayOf(List<tafat.UserInterface.GraphicalComponent> components) {
		JsonArray array = new JsonArray();
		components.forEach(r -> array.add(objectOf(r)));
		return array;
	}

	private static JsonObject objectOf(tafat.UserInterface.GraphicalComponent component) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", component.title());
		jsonObject.add("values", component.values());
		return jsonObject;
	}

	public static JsonElement heatmapData(tafat.UserInterface.Heatmap self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", "heatmap");
		jsonObject.addProperty("title", self.title());
		jsonObject.addProperty("background", base64(self.background()));
		jsonObject.addProperty("color", self.color().code());
		jsonObject.add("regions", arrayOfRegions(self.regionList()));
		return jsonObject;
	}

	private static JsonArray arrayOfRegions(List<? extends tafat.UserInterface.Heatmap.Region> regions) {
		JsonArray array = new JsonArray();
		regions.forEach(r -> array.add(r.data()));
		return array;
	}

	public static JsonElement heatmapValues(tafat.UserInterface.Heatmap self) {
		JsonArray array = new JsonArray();
		self.regionList().forEach(r -> array.add(jsonObjectOf(r)));
		return array;
	}

	private static JsonElement jsonObjectOf(tafat.UserInterface.Heatmap.Region region) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", region.name());
		jsonObject.addProperty("value", region.value());
		return jsonObject;
	}

	public static JsonElement buildSquareJson(tafat.UserInterface.Heatmap.Square self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", self.name());
		jsonObject.addProperty("type", "square");
		jsonObject.addProperty("zIndex", self.zIndex());
		jsonObject.addProperty("top", self.top());
		jsonObject.addProperty("left", self.left());
		jsonObject.addProperty("height", self.height());
		jsonObject.addProperty("width", self.width());
		return jsonObject;
	}

	public static JsonElement buildCircleJson(tafat.UserInterface.Heatmap.Circle self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", self.name());
		jsonObject.addProperty("type", "circle");
		jsonObject.addProperty("zIndex", self.zIndex());
		jsonObject.addProperty("top", self.centerY() - self.diameter() / 2);
		jsonObject.addProperty("left", self.centerX() - self.diameter() / 2);
		jsonObject.addProperty("height", self.diameter());
		jsonObject.addProperty("width", self.diameter());
		return jsonObject;
	}

	public static JsonElement buildLineChartJson(tafat.UserInterface.LineChart self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("type", "chart");
		jsonObject.addProperty("title", self.title());
		jsonObject.add("lines", arrayOfLines(self.lineList()));
		return jsonObject;
	}

	private static JsonArray arrayOfLines(List<tafat.UserInterface.LineChart.Line> lines) {
		JsonArray array = new JsonArray();
		lines.forEach(l -> array.add(l.data()));
		return array;
	}

	public static JsonElement getLineChartValues(tafat.UserInterface.LineChart self) {
		JsonArray array = new JsonArray();
		self.lineList().forEach(l -> array.add(jsonObjectOf(l)));
		return array;
	}

	private static JsonElement jsonObjectOf(tafat.UserInterface.LineChart.Line line) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", line.label());
		jsonObject.addProperty("value", line.value());
		return jsonObject;
	}

	public static JsonElement buildLineJson(tafat.UserInterface.LineChart.Line self) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", self.name());
		jsonObject.addProperty("label", self.label());
		return jsonObject;
	}
}
