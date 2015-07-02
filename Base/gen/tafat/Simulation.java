package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Simulation extends magritte.wraps.Morph {
    public magritte.primitives.Date from() {
        return _get("from").asDate();
    }

    public magritte.primitives.Date to() {
        return _get("to").asDate();
    }

    public void from(magritte.primitives.Date value) {
        _edit().set("from", value);
    }

    public void from(magritte.Expression<Double> value) {
        _edit().let("from", value);
    }

    public void to(magritte.primitives.Date value) {
        _edit().set("to", value);
    }

    public void to(magritte.Expression<Double> value) {
        _edit().let("to", value);
    }
}
