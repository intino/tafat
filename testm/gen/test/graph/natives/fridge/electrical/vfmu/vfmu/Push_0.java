package test.graph.natives.fridge.electrical.vfmu.vfmu;

import static test.graph.Fridge.Mode.On;

/**Fridge.Electrical.vFmu.vFmu.BooleanInput#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#54#26**/
public class Push_0 implements io.intino.tafat.graph.functions.PushBoolean, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public boolean value() {
		return self.mode() == On;
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