package tafat.toolbox.statechart;

import tafat.toolbox.Checker;

public class Transition implements Checker {

    final State from;
    final State to;
    Checker checker = () -> false;
    Action action = () -> {};

    Transition(State from, State to) {
        this.from = from;
        this.to = to;
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
