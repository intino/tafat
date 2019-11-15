package io.intino.tafat.graph.natives.fmu;

/**Fmu#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#146#1**/
public class Execute_0 implements io.intino.tafat.graph.functions.Execute, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.Fmu self;

	@Override
	public void execute(int step) {
		io.intino.tafat.graph.ModelingMechanisms.executeFmu(self, step);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Fmu) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Fmu.class;
	}
}