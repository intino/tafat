package tafat.toolbox.statechart;

import tafat.toolbox.Action;
import tafat.toolbox.Checker;

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
