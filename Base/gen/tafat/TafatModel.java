package tafat;

public class TafatModel extends magritte.wraps.Model {

    public TafatModel(magritte.wraps.Morph morph) {
        super(morph);
    }

    public TafatModel(magritte.Graph graph) {
		super(graph);
	}

	public tafat.Simulation Simulation() {
	    return _get("simulation", tafat.Simulation.class).get(0);
	}

	public magritte.Set<tafat.HeatMap> HeatMaps() {
	    return _get("heatMaps", tafat.HeatMap.class);
	}

	public tafat.HeatMap HeatMap(int index) {
	    return HeatMaps().get(index);
	}
}