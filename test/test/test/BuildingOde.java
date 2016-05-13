package test;

public class BuildingOde {
    public static double buildingTemperature(tafat.LogOutput.Line self) {
        return self.graph().<TestApplication>application().environment(0).building(0).temperature();
    }
}
