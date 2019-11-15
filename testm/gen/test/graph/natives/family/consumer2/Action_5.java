package test.graph.natives.family.consumer2;

/**Family.Consumer2.Implementation.Task.StartAction#/Users/oroncal/workspace/tafat/testm/src/test/Fridge.tara#85#16**/
public class Action_5 implements io.intino.tafat.graph.functions.Action, io.intino.tara.magritte.Function {
	private test.graph.Family.Consumer2 self;

	@Override
	public void execute() {
		System.out.println("Having a bath");
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