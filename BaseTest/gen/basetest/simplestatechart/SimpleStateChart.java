package basetest.simplestatechart;

import magritte.primitives.*;

import magritte.wraps.*;


public class SimpleStateChart extends magritte.wraps.Morph {
    public int step() {
        return _get("step").asInteger();
    }

    public void step(java.lang.Integer value) {
        _edit().set("step", value);
    }

    public void step(magritte.Expression<java.lang.Integer> value) {
        _edit().let("step", value);
    }
}
