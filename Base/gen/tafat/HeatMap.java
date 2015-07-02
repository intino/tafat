package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class HeatMap extends tafat.View {
    public String path() {
        return _get("path").asString();
    }

    public void path(java.lang.String value) {
        _edit().set("path", value);
    }

    public void path(magritte.Expression<java.lang.String> value) {
        _edit().let("path", value);
    }

    public magritte.Set<Type> regionTypes() {
        return _definition()._aggregables(tafat.HeatMap.Region.class);
    }

    public Type regionType(int index) {
        return regionTypes().get(index);
    }

    public magritte.Set<tafat.HeatMap.Region> regionSet() {
        return _components(tafat.HeatMap.Region.class);
    }

    public tafat.HeatMap.Region region(int index) {
        return regionSet().get(index);
    }

    public static class Region extends magritte.wraps.Morph {
        public double coordinates() {
            return _get("coordinates").asDouble();
        }

        public double calculate() {
            return ((tafat.natives.Function) _get("calculate").asNative()).calculate();
        }

        public void coordinates(double... coordinates) {
            _edit().set("coordinates", coordinates);
        }

        public void coordinates(magritte.Expression<Double> value) {
            _edit().let("coordinates", value);
        }

        public void coordinates(magritte.wraps.Operation operation,
            double... coordinates) {
            _edit(operation).set("coordinates", coordinates);
        }

        public void calculate(magritte.NativeCode value) {
            _edit().let("calculate", value);
        }
    }
}
