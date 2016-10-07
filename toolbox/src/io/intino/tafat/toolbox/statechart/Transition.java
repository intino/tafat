package io.intino.tafat.toolbox.statechart;

import io.intino.tafat.toolbox.Checker;
import io.intino.tafat.toolbox.Action;

public class Transition implements Checker {

    final String fromId;
    String toId;
    State from;
    State to;
    Checker checker = () -> false;
    Action action = () -> {};

    public Transition(String fromId) {
        this.fromId = fromId;
    }

    public boolean check() {
        return checker.check();
    }

    void action() {
        action.execute();
    }

}
