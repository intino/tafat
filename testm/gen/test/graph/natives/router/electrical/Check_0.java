package test.graph.natives.router.electrical;

/**Router.Electrical.Implementation.StateChart.Transition.Condition#/Users/oroncal/workspace/tafat/testm/src/test/RouterStateChart.tara#14#42**/
public class Check_0 implements io.intino.tafat.graph.functions.CheckTransition, io.intino.tara.magritte.Function {
	private test.graph.Router.Electrical self;

	@Override
	public boolean check(int advancedTime) {
		return test.RouterStateChart.checkIsOn(self, advancedTime);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Router.Electrical) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Router.Electrical.class;
	}
}