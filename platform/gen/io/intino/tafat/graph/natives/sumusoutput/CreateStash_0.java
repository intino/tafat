package io.intino.tafat.graph.natives.sumusoutput;

import java.util.List;
import io.intino.tafat.graph.SumusOutput;

/**SumusOutput#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#14#1**/
public class CreateStash_0 implements io.intino.tafat.graph.functions.CreateStash, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SumusOutput self;

	@Override
	public io.intino.tara.io.Stash create(List<? extends SumusOutput.Extractor> extractors) {
		return io.intino.tafat.graph.output.SumusOutput.createStash(self, extractors);
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