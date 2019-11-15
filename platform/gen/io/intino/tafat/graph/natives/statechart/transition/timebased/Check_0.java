package io.intino.tafat.graph.natives.statechart.transition.timebased;

/**StateChart.Transition.TimeBased#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#56#4**/
public class Check_0 implements io.intino.tafat.graph.functions.CheckTransition, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.StateChart.Transition.TimeBased self;

	@Override
	public boolean check(int advancedTime) {
		return io.intino.tafat.graph.ModelingMechanisms.checkTimeBasedTransition(self, advancedTime);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.StateChart.Transition.TimeBased) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.StateChart.Transition.TimeBased.class;
	}
}