package tafat.toolbox.statechart;

public class Transition {

    private final int from;
    private final int to;
    private Checker checker = () -> false;
    private Action action = () -> {};

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

    boolean check(){
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
