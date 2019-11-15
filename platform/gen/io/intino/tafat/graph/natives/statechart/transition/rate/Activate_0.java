package io.intino.tafat.graph.natives.statechart.transition.rate;

/**StateChart.Transition.Rate#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#65#5**/
public class Activate_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition.Rate self;

	@Override
	public void execute() {
		io.intino.tafat.graph.ModelingMechanisms.activateRate(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition.Rate) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.Rate.class;
	}
}