package io.intino.tafat.graph.natives.userinterface.heatmap.square;

import com.google.gson.JsonElement;

/**UserInterface.Heatmap.Square#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#24#5**/
public class Data_0 implements io.intino.tafat.graph.functions.BuildJson, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.UserInterface.Heatmap.Square self;

	@Override
	public JsonElement buildJson() {
		return io.intino.tafat.graph.GUI.buildSquareJson(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface.Heatmap.Square) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.Heatmap.Square.class;
	}
}