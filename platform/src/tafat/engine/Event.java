package tafat.engine;

public interface Event {

    void step(long time);
    boolean finished();

}
