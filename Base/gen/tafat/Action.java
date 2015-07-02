package tafat;

import magritte.primitives.*;

import magritte.wraps.*;


public class Action extends magritte.wraps.Definition {
    public boolean condition() {
        return ((tafat.natives.Check) _get("condition").asNative()).check();
    }

    public void action() {
        ((tafat.natives.Action) _get("action").asNative()).execute();
    }

    public void condition(magritte.NativeCode value) {
        _edit().let("condition", value);
    }

    public void action(magritte.NativeCode value) {
        _edit().let("action", value);
    }
}
