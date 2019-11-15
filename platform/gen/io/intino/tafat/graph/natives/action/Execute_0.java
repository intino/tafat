package io.intino.tafat.graph.natives.action;

/**Action#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#10#1**/
public class Execute_0 implements io.intino.tafat.graph.functions.Execute, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.Action self;

	@Override
	public void execute(int step) {
		self.action();
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Action) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Action.class;
	}
}