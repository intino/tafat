package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Job extends magritte.wraps.Definition {
    public magritte.Set<Type> startTypes() {
        return _aggregables(tafat.Job.Start.class);
    }

    public Type startType(int index) {
        return startTypes().get(index);
    }

    public magritte.Set<Type> durationTypes() {
        return _aggregables(tafat.Job.Duration.class);
    }

    public Type durationType(int index) {
        return durationTypes().get(index);
    }

    public magritte.Set<Type> jobTypes() {
        return _aggregables(tafat.Job.class);
    }

    public Type jobType(int index) {
        return jobTypes().get(index);
    }

    public magritte.Set<Type> jobActionTypes() {
        return _aggregables(tafat.JobAction.class);
    }

    public Type jobActionType(int index) {
        return jobActionTypes().get(index);
    }

    public magritte.Set<Type> actionTypes() {
        return _aggregables(tafat.Job.Action.class);
    }

    public Type actionType(int index) {
        return actionTypes().get(index);
    }

    public magritte.Set<Type> startActionTypes() {
        return _aggregables(tafat.Job.StartAction.class);
    }

    public Type startActionType(int index) {
        return startActionTypes().get(index);
    }

    public magritte.Set<Type> recurrentActionTypes() {
        return _aggregables(tafat.Job.RecurrentAction.class);
    }

    public Type recurrentActionType(int index) {
        return recurrentActionTypes().get(index);
    }

    public magritte.Set<Type> endActionTypes() {
        return _aggregables(tafat.Job.EndAction.class);
    }

    public Type endActionType(int index) {
        return endActionTypes().get(index);
    }

    public tafat.Job.Start start() {
        return _component(tafat.Job.Start.class);
    }

    public tafat.Job.Duration duration() {
        return _component(tafat.Job.Duration.class);
    }

    public magritte.Set<tafat.Job> jobSet() {
        return _components(tafat.Job.class);
    }

    public tafat.Job job(int index) {
        return jobSet().get(index);
    }

    public magritte.Set<tafat.JobAction> jobActionSet() {
        return _components(tafat.JobAction.class);
    }

    public tafat.JobAction jobAction(int index) {
        return jobActionSet().get(index);
    }

    public magritte.Set<tafat.Job.Action> actionSet() {
        return _components(tafat.Job.Action.class);
    }

    public tafat.Job.Action action(int index) {
        return actionSet().get(index);
    }

    public magritte.Set<tafat.Job.StartAction> startActionSet() {
        return _components(tafat.Job.StartAction.class);
    }

    public tafat.Job.StartAction startAction(int index) {
        return startActionSet().get(index);
    }

    public magritte.Set<tafat.Job.RecurrentAction> recurrentActionSet() {
        return _components(tafat.Job.RecurrentAction.class);
    }

    public tafat.Job.RecurrentAction recurrentAction(int index) {
        return recurrentActionSet().get(index);
    }

    public magritte.Set<tafat.Job.EndAction> endActionSet() {
        return _components(tafat.Job.EndAction.class);
    }

    public tafat.Job.EndAction endAction(int index) {
        return endActionSet().get(index);
    }

    public static class Start extends magritte.wraps.Definition {
        public double time() {
            return _get("time").asDouble();
        }

        public double deviation() {
            return _get("deviation").asDouble();
        }

        public void time(double time) {
            _edit().set("time", time);
        }

        public void time(magritte.Expression<Double> value) {
            _edit().let("time", value);
        }

        public void deviation(double deviation) {
            _edit().set("deviation", deviation);
        }

        public void deviation(magritte.Expression<Double> value) {
            _edit().let("deviation", value);
        }
    }

    public static class Duration extends magritte.wraps.Definition {
        public double time() {
            return _get("time").asDouble();
        }

        public double deviation() {
            return _get("deviation").asDouble();
        }

        public void time(double time) {
            _edit().set("time", time);
        }

        public void time(magritte.Expression<Double> value) {
            _edit().let("time", value);
        }

        public void deviation(double deviation) {
            _edit().set("deviation", deviation);
        }

        public void deviation(magritte.Expression<Double> value) {
            _edit().let("deviation", value);
        }
    }

    public static class Action extends magritte.wraps.Definition {
        public void action() {
            ((tafat.natives.Action) _get("action").asNative()).execute();
        }

        public void action(magritte.NativeCode value) {
            _edit().let("action", value);
        }
    }

    public static class StartAction extends tafat.Job.Action {
    }

    public static class RecurrentAction extends tafat.Job.Action {
    }

    public static class EndAction extends tafat.Job.Action {
    }
}
