package test.graph.natives.fridge.electrical.v2;

/**#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#26#54**/
public class Timeout_2 implements io.intino.tara.magritte.Expression<Integer> {
	private test.graph.Fridge.Electrical self;

	@Override
	public Integer value() {
		return 20;
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