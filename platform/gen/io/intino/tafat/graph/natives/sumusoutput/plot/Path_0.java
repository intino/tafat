package io.intino.tafat.graph.natives.sumusoutput.plot;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/output/SumusOutput.tara#34#3**/
public class Path_0 implements io.intino.tara.magritte.Expression<String> {
	private io.intino.tafat.graph.SumusOutput.Plot self;

	@Override
	public String value() {
		return io.intino.tafat.graph.output.SumusOutput.getPlotPath(self);
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