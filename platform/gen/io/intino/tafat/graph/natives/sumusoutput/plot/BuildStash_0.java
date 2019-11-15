package io.intino.tafat.graph.natives.sumusoutput.plot;

import java.util.List;

/**SumusOutput.Plot#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#35#3**/
public class BuildStash_0 implements io.intino.tafat.graph.functions.Stash, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SumusOutput.Plot self;

	@Override
	public List<io.intino.tara.io.Node> build() {
		return io.intino.tafat.graph.output.SumusOutput.buildStashOfFacts(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.SumusOutput.Plot) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.SumusOutput.Plot.class;
	}
}