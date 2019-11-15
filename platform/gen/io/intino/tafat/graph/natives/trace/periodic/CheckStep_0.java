package io.intino.tafat.graph.natives.trace.periodic;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/Control.tara#13#5**/
public class CheckStep_0 implements io.intino.tara.magritte.Expression<Boolean> {
	private io.intino.tafat.graph.Trace.Periodic self;

	@Override
	public Boolean value() {
		return io.intino.tafat.graph.Control.checkStep(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Trace.Periodic) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Trace.Periodic.class;
	}
}