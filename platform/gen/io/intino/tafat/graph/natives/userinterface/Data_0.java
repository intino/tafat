package io.intino.tafat.graph.natives.userinterface;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/GUI.tara#4#1**/
public class Data_0 implements io.intino.tara.magritte.Expression<String> {
	private io.intino.tafat.graph.UserInterface self;

	@Override
	public String value() {
		return io.intino.tafat.graph.GUI.jsonData(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.UserInterface) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.UserInterface.class;
	}
}