package io.intino.tafat.graph.natives.sumusoutput;

import java.io.File;

/**SumusOutput#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#15#1**/
public class WriteStash_0 implements io.intino.tafat.graph.functions.WriteStash, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SumusOutput self;

	@Override
	public void writeStash(io.intino.tara.io.Stash stash, File file) {
		io.intino.tafat.graph.output.SumusOutput.writeStash(self, stash, file);
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