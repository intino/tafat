package io.intino.tafat.graph.natives.task;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#74#1**/
public class Check_0 implements io.intino.tara.magritte.Expression<Boolean> {
	private io.intino.tafat.graph.Task self;

	@Override
	public Boolean value() {
		return io.intino.tafat.graph.ModelingMechanisms.checkTask(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Task) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Task.class;
	}
}