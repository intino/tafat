package test.graph.natives.fridge.electrical.v3;

/**Fridge.Electrical.v3.StateChart.s1.s0.EntryAction#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#33#31**/
public class Action_1 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public void execute() {
		self.value(self.value() + 1);
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