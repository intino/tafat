package io.intino.tafat.graph.natives.statechart.transition.timeout;

/**StateChart.Transition.Timeout#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#59#5**/
public class Activate_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition.Timeout self;

	@Override
	public void execute() {
		io.intino.tafat.graph.ModelingMechanisms.activateTimeout(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition.Timeout) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.Timeout.class;
	}
}