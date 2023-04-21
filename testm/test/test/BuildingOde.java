package test;


import io.intino.tafat.model.LogOutput;
import io.intino.tafat.test.model.TestGraph;

public class BuildingOde {
    public static double buildingTemperature(LogOutput.Line self) {
        return self.core$().graph().as(TestGraph.class).environment(0).building(0).temperature();
    }
}
