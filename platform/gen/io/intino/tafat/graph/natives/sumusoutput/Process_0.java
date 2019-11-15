package io.intino.tafat.graph.natives.sumusoutput;

/**SumusOutput#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#11#1**/
public class Process_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SumusOutput self;

	@Override
	public void execute() {
		io.intino.tafat.graph.output.SumusOutput.process(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.SumusOutput) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.SumusOutput.class;
	}
}