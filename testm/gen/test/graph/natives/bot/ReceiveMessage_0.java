package test.graph.natives.bot;

/**Bot#/Users/oroncal/workspace/tafat/testm/src/test/Agent.tara#3#22**/
public class ReceiveMessage_0 implements io.intino.tafat.graph.functions.Message, io.intino.tara.magritte.Function {
	private test.graph.Bot self;

	@Override
	public void receiveMessage(String message) {
		System.out.println("I received this message: " + message);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (test.graph.Bot) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return test.graph.Bot.class;
	}
}