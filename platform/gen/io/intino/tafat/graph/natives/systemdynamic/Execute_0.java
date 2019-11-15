package io.intino.tafat.graph.natives.systemdynamic;

/**SystemDynamic#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#19#1**/
public class Execute_0 implements io.intino.tafat.graph.functions.Execute, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.SystemDynamic self;

	@Override
	public void execute(int step) {
		io.intino.tafat.graph.ModelingMechanisms.executeSd(self, step);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.SystemDynamic) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.SystemDynamic.class;
	}
}