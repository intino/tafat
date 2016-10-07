package test;

import io.intino.tafat.LogOutput;

public class BuildingOde {
    public static double buildingTemperature(LogOutput.Line self) {
        return self.graph().<TestApplication>application().environment(0).building(0).temperature();
    }
}
