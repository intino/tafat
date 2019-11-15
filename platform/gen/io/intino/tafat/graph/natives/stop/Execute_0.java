package io.intino.tafat.graph.natives.stop;

/**Stop#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/Control.tara#22#1**/
public class Execute_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.Stop self;

	@Override
	public void execute() {
		io.intino.tafat.graph.Control.stop(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Stop) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Stop.class;
	}
}