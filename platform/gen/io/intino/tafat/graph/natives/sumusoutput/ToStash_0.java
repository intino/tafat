package io.intino.tafat.graph.natives.sumusoutput;

import java.util.List;
import io.intino.tafat.graph.SumusOutput;

/**SumusOutput#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#13#1**/
public class ToStash_0 implements io.intino.tafat.graph.functions.ToStash, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SumusOutput self;

	@Override
	public void toStash(List<? extends SumusOutput.Extractor> extractors) {
		io.intino.tafat.graph.output.SumusOutput.toStash(self, extractors);
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