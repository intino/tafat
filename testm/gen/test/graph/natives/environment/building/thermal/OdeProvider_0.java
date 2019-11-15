package test.graph.natives.environment.building.thermal;

/**#/Users/oroncal/workspace/tafat/testm/src/test/SystemDynamic.tara#15#24**/
public class OdeProvider_0 implements io.intino.tara.magritte.Expression<io.intino.tafat.engine.DifferentialEquation> {
	private test.graph.Environment.Building.Thermal self;

	@Override
	public io.intino.tafat.engine.DifferentialEquation value() {
		return test.SystemDynamic.thermalBuildingOde(self);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Environment.Building.Thermal) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Environment.Building.Thermal.class;
	}
}