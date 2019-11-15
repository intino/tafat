package io.intino.tafat.graph.natives.logoutput;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/LogOutput.tara#13#1**/
public class CheckStep_0 implements io.intino.tara.magritte.Expression<Boolean> {
	private io.intino.tafat.graph.LogOutput self;

	@Override
	public Boolean value() {
		return io.intino.tafat.graph.output.LogOutput.checkStep(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.LogOutput) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.LogOutput.class;
	}
}