package test.graph.natives.fridge.electrical.v3;

/**Fridge.Electrical.v3.StateChart.s1.s1.s1.EntryAction#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#36#32**/
public class Action_3 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public void execute() {
		self.value(self.value() + 2);
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