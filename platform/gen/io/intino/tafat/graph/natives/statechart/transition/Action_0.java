package io.intino.tafat.graph.natives.statechart.transition;

/**StateChart.Transition#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#50#2**/
public class Action_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition self;

	@Override
	public void execute() {
		;
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.class;
	}
}