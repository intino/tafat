package tafat.toolbox.statechart;

import tafat.toolbox.Checker;

public class Transition implements Checker {

    final int from;
    final int to;
    Checker checker = () -> false;
    Action action = () -> {};

    Transition(int from, int to) {
        this.from = from;
        this.to = to;
    }

    int from(){
        return from;
    }

    int to(){
        return to;
    }

    public boolean check(){
        return checker.check();
    }

    void check(Checker checker){
        this.checker = checker;
    }

    void action(){
        action.execute();
    }

    void action(Action action){
        this.action = action;
    }

}
