package io.intino.tafat.graph.natives.statechart.transition.message;

/**StateChart.Transition.Message#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#70#4**/
public class Check_0 implements io.intino.tafat.graph.functions.CheckTransition, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition.Message self;

	@Override
	public boolean check(int advancedTime) {
		return io.intino.tafat.graph.ModelingMechanisms.checkMessageTransition(self, advancedTime);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition.Message) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.Message.class;
	}
}