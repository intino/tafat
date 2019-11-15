package test.graph.natives.router.electrical;

/**Router.Electrical.Implementation.StateChart.Working.EntryAction#/Users/oroncal/workspace/tafat/testm/src/test/RouterStateChart.tara#10#32**/
public class Action_1 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Router.Electrical self;

	@Override
	public void execute() {
		System.out.println("Working");
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