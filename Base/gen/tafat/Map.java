package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Map extends magritte.wraps.Definition {
    public double get(String key) {
        return ((tafat.natives.Get) _get("get").asNative()).get(key);
    }

    public magritte.Set<Type> entryTypes() {
        return _aggregables(tafat.Map.Entry.class);
    }

    public Type entryType(int index) {
        return entryTypes().get(index);
    }

    public magritte.Set<tafat.Map.Entry> entrySet() {
        return _components(tafat.Map.Entry.class);
    }

    public tafat.Map.Entry entry(int index) {
        return entrySet().get(index);
    }

    public static class Entry extends magritte.wraps.Definition {
        public String key() {
            return _get("key").asString();
        }

        public double value() {
            return _get("value").asDouble();
        }

        public void key(java.lang.String value) {
            _edit().set("key", value);
        }

        public void key(magritte.Expression<java.lang.String> value) {
            _edit().let("key", value);
        }

        public void value(java.lang.Double value) {
            _edit().set("value", value);
        }

        public void value(magritte.Expression<java.lang.Double> value) {
            _edit().let("value", value);
        }
    }
}
