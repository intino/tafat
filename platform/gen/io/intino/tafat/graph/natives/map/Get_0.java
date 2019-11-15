package io.intino.tafat.graph.natives.map;

/**Map#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#138#1**/
public class Get_0 implements io.intino.tafat.graph.functions.Get, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.Map self;

	@Override
	public double get(String key) {
		return 0;
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Map) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Map.class;
	}
}