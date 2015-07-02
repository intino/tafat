package tafat.natives;

import magritte.NativeCode;

/**
 * Created by jevora on 12/06/2015.
 */
public interface Message extends NativeCode {

    void receiveMessage(String message);
}
