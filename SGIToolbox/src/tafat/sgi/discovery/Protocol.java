package tafat.sgi.discovery;

import tafat.sgi.discovery.connection.Datagram;
import tafat.sgi.discovery.connection.NetInformation;

public interface Protocol {
    Datagram process(Datagram datagram);

    boolean finished();

    NetInformation result();

    Datagram firstMessage();
}
