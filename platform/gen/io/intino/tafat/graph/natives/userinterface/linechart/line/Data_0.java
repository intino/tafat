package io.intino.tafat.graph.natives.userinterface.linechart.line;

import com.google.gson.JsonElement;

/**UserInterface.LineChart.Line#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#39#4**/
public class Data_0 implements io.intino.tafat.graph.functions.BuildJson, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.UserInterface.LineChart.Line self;

	@Override
	public JsonElement buildJson() {
		return io.intino.tafat.graph.GUI.buildLineJson(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface.LineChart.Line) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.LineChart.Line.class;
	}
}