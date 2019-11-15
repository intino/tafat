package test.graph.natives.fridge.electrical.vfmu.vfmu;

/**Fridge.Electrical.vFmu.vFmu.RealOutput#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#55#27**/
public class Pull_0 implements io.intino.tafat.graph.functions.PullReal, io.intino.tara.magritte.Function {
	private test.graph.Fridge.Electrical self;

	@Override
	public void pull(double value) {
		self.power(value);
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