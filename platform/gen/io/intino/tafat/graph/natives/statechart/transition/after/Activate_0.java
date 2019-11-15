package io.intino.tafat.graph.natives.statechart.transition.after;

/**StateChart.Transition.After#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#62#5**/
public class Activate_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition.After self;

	@Override
	public void execute() {
		io.intino.tafat.graph.ModelingMechanisms.activateAfter(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition.After) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.After.class;
	}
}