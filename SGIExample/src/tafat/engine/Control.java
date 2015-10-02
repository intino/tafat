package tafat.engine;

import java.util.Date;

public interface Control {

    Date initialDate();
    Date finalDate();
    void play();
    void pause();
    void reset();

}
