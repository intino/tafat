package test.graph.natives.fridge.electrical.v4;

/**Fridge.Electrical.v4.Action#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#41#15**/
public class Action_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public void execute() {
		if(self.power() <= 100) self.power(self.power() + 10);
		    				else self.power(0);
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