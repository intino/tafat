package connection;

import java.io.IOException;

public interface Connection extends AutoCloseable{
    void send(Datagram datagram) throws IOException;

    Datagram receiveData() throws IOException;
}
