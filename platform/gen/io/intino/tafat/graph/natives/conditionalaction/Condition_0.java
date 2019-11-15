package io.intino.tafat.graph.natives.conditionalaction;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#15#1**/
public class Condition_0 implements io.intino.tara.magritte.Expression<Boolean> {
	private io.intino.tafat.graph.ConditionalAction self;

	@Override
	public Boolean value() {
		return true;
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.ConditionalAction) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.ConditionalAction.class;
	}
}