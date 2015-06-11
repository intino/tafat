package protocol;

import connection.Datagram;
import connection.NetInformation;

public interface Protocol {
    Datagram process(Datagram datagram);

    boolean finished();

    NetInformation result();

    Datagram firstMessage();
}
