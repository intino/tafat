package io.intino.tafat.test.model;

import io.intino.tafat.engine.DifferentialEquation;
import io.intino.tafat.test.model.Environment.Building.Radiator;

import static io.intino.tafat.test.model.Environment.Building;

public class SystemDynamic {

	public static boolean lowTemperature(Radiator.Thermal self) {
		return self.core$().ownerAs(Building.class).temperature() < 18;
	}

	public static void startHeating(Radiator.Thermal self) {
		self.temperature(60);
	}

	public static boolean highTemperature(Radiator.Thermal self) {
		return self.core$().ownerAs(Building.class).temperature() > 24;
	}

	public static void stopHeating(Radiator.Thermal self) {
		self.temperature(20);
	}

	public static DifferentialEquation thermalBuildingOde(Building.Thermal self) {
		return new ThermalBuildingOde(self);
	}

	private static class ThermalBuildingOde extends DifferentialEquation {

		private static final int BuildingTemperature = 0;
		private static final int EnvironmentHeatGains = 1;
		private static final int RadiatorHeatGains = 2;

		private static final int EnvironmentTemperature = 0;
		private static final int RadiatorTemperature = 1;

		private final Environment environment;
		private final Building.Thermal building;
		private final Radiator radiator;

		ThermalBuildingOde(Building.Thermal self) {
			this.environment = self.core$().ownerAs(Environment.class);
			this.building = self;
			this.radiator = self.asBuilding().radiator(0);
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
