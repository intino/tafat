package tafat.toolbox.statechart;

import tafat.toolbox.Action;
import tafat.toolbox.Checker;

public class Transition implements Checker {

    String fromString;
    String toString;
    State from;
    State to;
    Checker checker = () -> false;
    Action action = () -> {};

    public boolean check(){
        return checker.check();
    }

    void action(){
        action.execute();
    }

}
