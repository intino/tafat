package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Entity extends magritte.wraps.Morph {
    public magritte.Set<Type> entityTypes() {
        return _definition()._aggregables(tafat.Entity.class);
    }

    public Type entityType(int index) {
        return entityTypes().get(index);
    }

    public magritte.Set<Type> featureTypes() {
        return _definition()._aggregables(tafat.Entity.Feature.class);
    }

    public Type featureType(int index) {
        return featureTypes().get(index);
    }

    public magritte.Set<tafat.Entity> entitySet() {
        return _components(tafat.Entity.class);
    }

    public tafat.Entity entity(int index) {
        return entitySet().get(index);
    }

    public magritte.Set<tafat.Entity.Feature> featureSet() {
        return _components(tafat.Entity.Feature.class);
    }

    public tafat.Entity.Feature feature(int index) {
        return featureSet().get(index);
    }

    public static class Feature extends magritte.wraps.Morph {
    }
}
