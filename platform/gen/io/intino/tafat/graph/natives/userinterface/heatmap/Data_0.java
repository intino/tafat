package io.intino.tafat.graph.natives.userinterface.heatmap;

import com.google.gson.JsonElement;

/**UserInterface.Heatmap#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#15#3**/
public class Data_0 implements io.intino.tafat.graph.functions.BuildJson, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.UserInterface.Heatmap self;

	@Override
	public JsonElement buildJson() {
		return io.intino.tafat.graph.GUI.heatmapData(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface.Heatmap) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.Heatmap.class;
	}
}