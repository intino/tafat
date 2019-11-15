package io.intino.tafat.graph.natives.sumusoutput.export;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#25#3**/
public class Path_0 implements io.intino.tara.magritte.Expression<String> {
	private io.intino.tafat.graph.SumusOutput.Export self;

	@Override
	public String value() {
		return io.intino.tafat.graph.output.SumusOutput.getExportPath(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.SumusOutput.Export) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.SumusOutput.Export.class;
	}
}