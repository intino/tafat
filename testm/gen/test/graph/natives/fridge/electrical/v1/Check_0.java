package test.graph.natives.fridge.electrical.v1;

/**Fridge.Electrical.v1.StateChart.Transition.Condition#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#15#51**/
public class Check_0 implements io.intino.tafat.graph.functions.CheckTransition, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public boolean check(int advancedTime) {
		return true;
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Fridge.Electrical) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Fridge.Electrical.class;
	}
}