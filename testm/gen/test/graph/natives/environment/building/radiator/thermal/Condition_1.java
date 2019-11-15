package test.graph.natives.environment.building.radiator.thermal;

/**#/Users/oroncal/workspace/tafat/testm/src/test/SystemDynamic.tara#12#23**/
public class Condition_1 implements io.intino.tara.magritte.Expression<Boolean> {
	private test.graph.Environment.Building.Radiator.Thermal self;

	@Override
	public Boolean value() {
		return test.SystemDynamic.highTemperature(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Environment.Building.Radiator.Thermal) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Environment.Building.Radiator.Thermal.class;
	}
}