package test.graph.natives.fridge.electrical.v2;

/**Fridge.Electrical.v2.StateChart.Off.EntryAction#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#19#31**/
public class Action_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public void execute() {
		self.value(0);
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