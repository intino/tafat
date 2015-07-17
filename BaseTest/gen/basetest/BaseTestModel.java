package basetest;

public class BaseTestModel extends magritte.wraps.Model {

    public BaseTestModel(magritte.wraps.Morph morph) {
        super(morph);
    }

    public BaseTestModel(magritte.Graph graph) {
		super(graph);
	}

	public magritte.Set<basetest.Fridge> Fridges() {
	    return _get("fridges", basetest.Fridge.class);
	}

	public basetest.Fridge Fridge(int index) {
	    return Fridges().get(index);
	}
}