package test;

import tafat.engine.DifferentialEquation;
import test.evolver.EvolverSystem;

public class SystemDynamic {

    public static DifferentialEquation adoptersSD(test.evolver.EvolverSystem self) {
        return new AdoptersSd(self);
    }

    private static class AdoptersSd extends DifferentialEquation {

        private static final int SdElements = 4, Params = 1;
        // SD ELEMENTS
        private static final int Adopters = 0, AdoptersFromAds = 1, AdoptersFromWom = 2, AdoptionRate = 3;
        // Parameters
        private static final int PotentialAdopters = 0;

        private EvolverSystem evolverSystem;

        AdoptersSd(EvolverSystem evolverSystem) {
            this.evolverSystem = evolverSystem;
            init(SdElements, Params);
        }

        @Override
        public void pull() {
            param[PotentialAdopters] = evolverSystem.potentialAdopters();
        }

        @Override
        protected void calculateFlows(double[] previous, double[] current) {
            current[AdoptersFromAds] = param[PotentialAdopters];
            current[AdoptersFromWom] = param[PotentialAdopters] + previous[Adopters];
            current[AdoptionRate] = previous[AdoptersFromAds] + previous[AdoptersFromWom];
        }

        @Override
        protected void calculateStocks(double[] previous, double[] current) {
            current[Adopters] = current[AdoptionRate];
        }

        @Override
        public void push() {
            evolverSystem.adopters(state[Adopters]);
        }
    }

}
