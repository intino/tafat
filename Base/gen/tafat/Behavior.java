package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Behavior extends tafat.Facet {
    public int step() {
        return _get("step").asInteger();
    }

    public void init() {
        ((tafat.natives.Action) _get("init").asNative()).execute();
    }

    public void step(java.lang.Integer value) {
        _edit().set("step", value);
    }

    public void step(magritte.Expression<java.lang.Integer> value) {
        _edit().let("step", value);
    }

    public void init(magritte.NativeCode value) {
        _edit().let("init", value);
    }

    public magritte.Set<tafat.Behavior.Knol> knolSet() {
        return _components(tafat.Behavior.Knol.class);
    }

    public tafat.Behavior.Knol knol(int index) {
        return knolSet().get(index);
    }

    public magritte.Set<tafat.Action> actionSet() {
        return _definition()._components(tafat.Action.class);
    }

    public tafat.Action action(int index) {
        return actionSet().get(index);
    }

    public magritte.Set<tafat.EquationSystem> equationSystemSet() {
        return _components(tafat.EquationSystem.class);
    }

    public tafat.EquationSystem equationSystem(int index) {
        return equationSystemSet().get(index);
    }

    public magritte.Set<tafat.StateChart> stateChartSet() {
        return _components(tafat.StateChart.class);
    }

    public tafat.StateChart stateChart(int index) {
        return stateChartSet().get(index);
    }

    public magritte.Set<tafat.Task> taskSet() {
        return _components(tafat.Task.class);
    }

    public tafat.Task task(int index) {
        return taskSet().get(index);
    }

    public magritte.Set<tafat.TableFunction> tableFunctionSet() {
        return _definition()._components(tafat.TableFunction.class);
    }

    public tafat.TableFunction tableFunction(int index) {
        return tableFunctionSet().get(index);
    }

    public magritte.Set<tafat.PointSet> pointSetSet() {
        return _definition()._components(tafat.PointSet.class);
    }

    public tafat.PointSet pointSet(int index) {
        return pointSetSet().get(index);
    }

    public magritte.Set<tafat.Job> jobSet() {
        return _definition()._components(tafat.Job.class);
    }

    public tafat.Job job(int index) {
        return jobSet().get(index);
    }

    public magritte.Set<tafat.Map> mapSet() {
        return _definition()._components(tafat.Map.class);
    }

    public tafat.Map map(int index) {
        return mapSet().get(index);
    }

    public static class Knol extends magritte.wraps.Morph {
    }
}
