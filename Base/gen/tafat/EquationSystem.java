package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class EquationSystem extends magritte.wraps.Morph {
    public Solver solver() {
        return _definition()._get("solver").asEnumerate(Solver.values());
    }

    public double step() {
        return _get("step").asDouble();
    }

    public void Solver(Solver value) {
        _edit().set("Solver", magritte.primitives.Enumerate.ordinal(value));
    }

    public void step(java.lang.Double value) {
        _edit().set("step", value);
    }

    public void step(magritte.Expression<java.lang.Double> value) {
        _edit().let("step", value);
    }

    public magritte.Set<Type> elementTypes() {
        return _definition()._aggregables(tafat.EquationSystem.Element.class);
    }

    public Type elementType(int index) {
        return elementTypes().get(index);
    }

    public magritte.Set<Type> stockTypes() {
        return _definition()._aggregables(tafat.EquationSystem.Stock.class);
    }

    public Type stockType(int index) {
        return stockTypes().get(index);
    }

    public magritte.Set<Type> flowTypes() {
        return _definition()._aggregables(tafat.EquationSystem.Flow.class);
    }

    public Type flowType(int index) {
        return flowTypes().get(index);
    }

    public magritte.Set<tafat.EquationSystem.Element> elementSet() {
        return _components(tafat.EquationSystem.Element.class);
    }

    public tafat.EquationSystem.Element element(int index) {
        return elementSet().get(index);
    }

    public magritte.Set<tafat.EquationSystem.Stock> stockSet() {
        return _components(tafat.EquationSystem.Stock.class);
    }

    public tafat.EquationSystem.Stock stock(int index) {
        return stockSet().get(index);
    }

    public magritte.Set<tafat.EquationSystem.Flow> flowSet() {
        return _components(tafat.EquationSystem.Flow.class);
    }

    public tafat.EquationSystem.Flow flow(int index) {
        return flowSet().get(index);
    }

    public static class Element extends magritte.wraps.Morph {
        public double function() {
            return ((tafat.natives.Function) _get("function").asNative()).calculate();
        }

        public void function(magritte.NativeCode value) {
            _edit().let("function", value);
        }
    }

    public static class Stock extends tafat.EquationSystem.Element {
    }

    public static class Flow extends tafat.EquationSystem.Element {
    }

    public enum Solver {Euler;
    }
}
