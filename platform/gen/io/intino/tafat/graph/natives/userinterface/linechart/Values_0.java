package io.intino.tafat.graph.natives.userinterface.linechart;

import com.google.gson.JsonElement;

/**UserInterface.LineChart#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#37#3**/
public class Values_0 implements io.intino.tafat.graph.functions.BuildJson, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.UserInterface.LineChart self;

	@Override
	public JsonElement buildJson() {
		return io.intino.tafat.graph.GUI.getLineChartValues(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface.LineChart) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.LineChart.class;
	}
}