package test;

import io.intino.tafat.engine.DifferentialEquation;
import test.graph.Environment;
import test.graph.thermal.environment.ThermalBuilding;
import test.graph.thermal.environment.building.ThermalRadiator;

public class SystemDynamic {

    public static boolean lowTemperature(ThermalRadiator self){
        return self.core$().ownerAs(Environment.Building.class).temperature() < 18;
    }

    public static void startHeating(ThermalRadiator self){
        self.temperature(60);
    }

    public static boolean highTemperature(ThermalRadiator self){
        return self.core$().ownerAs(Environment.Building.class).temperature() > 24;
    }

    public static void stopHeating(ThermalRadiator self){
        self.temperature(20);
    }

    public static DifferentialEquation thermalBuildingOde(ThermalBuilding self){
        return new ThermalBuildingOde(self);
    }

    private static class ThermalBuildingOde extends DifferentialEquation {

        private static final int BuildingTemperature = 0;
        private static final int EnvironmentHeatGains = 1;
        private static final int RadiatorHeatGains = 2;

        private static final int EnvironmentTemperature = 0;
        private static final int RadiatorTemperature = 1;

        private final Environment environment;
        private final ThermalBuilding building;
        private final Environment.Building.Radiator radiator;

        ThermalBuildingOde(ThermalBuilding self) {
            this.environment = self.core$().ownerAs(Environment.class);
            this.building = self;
            this.radiator = self.radiatorList(0);
            init(3, 2);
        }

        @Override
        public void pull() {
            param[EnvironmentHeatGains] = environment.temperature();
            state[BuildingTemperature] = building.temperature();
            param[RadiatorTemperature] = radiator.temperature();
        }

        @Override
        protected void calculateFlows(double[] previous, double[] current) {
            current[EnvironmentHeatGains] = (param[EnvironmentTemperature] - previous[BuildingTemperature]) / 1e4;
            current[RadiatorHeatGains] = (param[RadiatorTemperature] - previous[BuildingTemperature]) / 1e4;
        }

        @Override
        protected void calculateStocks(double[] previous, double[] current) {
            current[BuildingTemperature] = current[EnvironmentHeatGains] + current[RadiatorHeatGains];
        }

        @Override
        public void push() {
            building.temperature(state[BuildingTemperature]);
        }
    }
}
