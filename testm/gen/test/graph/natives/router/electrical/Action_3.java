package test.graph.natives.router.electrical;

/**Router.Electrical.Implementation.StateChart.Working.Connected.EntryAction#/Users/oroncal/workspace/tafat/testm/src/test/RouterStateChart.tara#12#35**/
public class Action_3 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Router.Electrical self;

	@Override
	public void execute() {
		System.out.println("Connected");
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