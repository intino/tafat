package io.intino.tafat.graph.natives.statechart;

/**StateChart#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#38#1**/
public class ReceiveMessage_0 implements io.intino.tafat.graph.functions.Message, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart self;

	@Override
	public void receiveMessage(String message) {
		io.intino.tafat.graph.ModelingMechanisms.receiveMessage(self, message);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.class;
	}
}