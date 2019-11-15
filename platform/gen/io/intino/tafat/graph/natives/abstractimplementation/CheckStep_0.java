package io.intino.tafat.graph.natives.abstractimplementation;

/**Implementation#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/Main.tara#42#1**/
public class CheckStep_0 implements io.intino.tafat.graph.functions.CheckStep, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.Implementation self;

	@Override
	public boolean checkStep(int stepSize) {
		return io.intino.tafat.graph.Main.checkStep(self, stepSize);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.Implementation) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.Implementation.class;
	}
}