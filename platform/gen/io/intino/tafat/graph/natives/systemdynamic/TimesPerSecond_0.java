package io.intino.tafat.graph.natives.systemdynamic;

/**#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#24#1**/
public class TimesPerSecond_0 implements io.intino.tara.magritte.Expression<Integer> {
	private io.intino.tafat.graph.SystemDynamic self;

	@Override
	public Integer value() {
		return (int) (1. / self.step());
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