package io.intino.tafat.graph.natives.tablefunction;

/**TableFunction#/Users/oroncal/workspace/tafat/platform/src/io/intino/tafat/graph/ModelingMechanisms.tara#134#1**/
public class Get_0 implements io.intino.tafat.graph.functions.Calculate, io.intino.tara.magritte.Function {
	private io.intino.tafat.graph.TableFunction self;

	@Override
	public double calculate(double... inputs) {
		return io.intino.tafat.graph.ModelingMechanisms.getY(self, inputs);
	}

	@Override
	public void self(io.intino.tara.magritte.Layer context) {
		self = (io.intino.tafat.graph.TableFunction) context;
	}

	@Override
	public Class<? extends io.intino.tara.magritte.Layer> selfClass() {
		return io.intino.tafat.graph.TableFunction.class;
	}
}