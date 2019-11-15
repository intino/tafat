package io.intino.tafat.graph.natives.userinterface.heatmap.circle;

import com.google.gson.JsonElement;

/**UserInterface.Heatmap.Circle#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#30#5**/
public class Data_0 implements io.intino.tafat.graph.functions.BuildJson, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.UserInterface.Heatmap.Circle self;

	@Override
	public JsonElement buildJson() {
		return io.intino.tafat.graph.GUI.buildCircleJson(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface.Heatmap.Circle) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.Heatmap.Circle.class;
	}
}