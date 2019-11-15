package test.graph.natives.environment.building.radiator.thermal;

/**Environment.Building.Radiator.Thermal.Implementation.ConditionalAction#/Users/oroncal/workspace/tafat/testm/src/test/SystemDynamic.tara#11#52**/
public class Action_0 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Environment.Building.Radiator.Thermal self;

	@Override
	public void execute() {
		test.SystemDynamic.startHeating(self);
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