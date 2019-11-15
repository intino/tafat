package io.intino.tafat.graph.natives.logoutput;

/**LogOutput#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/LogOutput.tara#8#1**/
public class Process_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.LogOutput self;

	@Override
	public void execute() {
		io.intino.tafat.graph.output.LogOutput.process(self);
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