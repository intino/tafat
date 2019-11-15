package test.graph.natives.family.consumer2;

/**Family.Consumer2.Implementation.Task.EndAction#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#79#14**/
public class Action_3 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Family.Consumer2 self;

	@Override
	public void execute() {
		System.out.println("Switching off oven");
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Family.Consumer2) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Family.Consumer2.class;
	}
}