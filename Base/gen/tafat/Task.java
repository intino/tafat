package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Task extends magritte.wraps.Morph {
    public Days[] days() {
        return magritte.primitives.Enumerate.cardinal(_getMultiple("days")
                                                          .asInteger(),
            Days.values());
    }

    public void Days(Days... values) {
        _edit().set("Days", magritte.primitives.Enumerate.ordinal(values));
    }

    public magritte.Set<Type> startTypes() {
        return _definition()._aggregables(tafat.Task.Start.class);
    }

    public Type startType(int index) {
        return startTypes().get(index);
    }

    public magritte.Set<Type> finishTypes() {
        return _definition()._aggregables(tafat.Task.Finish.class);
    }

    public Type finishType(int index) {
        return finishTypes().get(index);
    }

    public magritte.Set<Type> endTypes() {
        return _definition()._aggregables(tafat.Task.End.class);
    }

    public Type endType(int index) {
        return endTypes().get(index);
    }

    public magritte.Set<Type> durationTypes() {
        return _definition()._aggregables(tafat.Task.Duration.class);
    }

    public Type durationType(int index) {
        return durationTypes().get(index);
    }

    public magritte.Set<Type> taskTypes() {
        return _definition()._aggregables(tafat.Task.class);
    }

    public Type taskType(int index) {
        return taskTypes().get(index);
    }

    public magritte.Set<Type> jobActionTypes() {
        return _definition()._aggregables(tafat.JobAction.class);
    }

    public Type jobActionType(int index) {
        return jobActionTypes().get(index);
    }

    public tafat.Task.Start start() {
        return _component(tafat.Task.Start.class);
    }

    public tafat.Task.Finish finish() {
        return _component(tafat.Task.Finish.class);
    }

    public tafat.Task.End end() {
        return _component(tafat.Task.End.class);
    }

    public tafat.Task.Duration duration() {
        return _component(tafat.Task.Duration.class);
    }

    public magritte.Set<tafat.Task> taskSet() {
        return _components(tafat.Task.class);
    }

    public tafat.Task task(int index) {
        return taskSet().get(index);
    }

    public magritte.Set<tafat.JobAction> jobActionSet() {
        return _definition()._components(tafat.JobAction.class);
    }

    public tafat.JobAction jobAction(int index) {
        return jobActionSet().get(index);
    }

    public static class Start extends magritte.wraps.Morph {
        public magritte.primitives.Date time() {
            return _get("time").asDate();
        }

        public double deviation() {
            return _get("deviation").asDouble();
        }

        public void time(magritte.primitives.Date value) {
            _edit().set("time", value);
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

    public static class Finish extends magritte.wraps.Morph {
    }

    public static class End extends tafat.Task.Finish {
        public magritte.primitives.Date time() {
            return _get("time").asDate();
        }

        public double deviation() {
            return _get("deviation").asDouble();
        }

        public void time(magritte.primitives.Date value) {
            _edit().set("time", value);
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

    public static class Duration extends tafat.Task.Finish {
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

    public enum Days {Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday;
    }
}
