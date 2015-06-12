package tafat.sgi.discovery;

import tafat.sgi.discovery.connection.Datagram;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSimulatorsSubscription {
    ServerProtocol protocol;

    @Before
    public void setUp() {
        protocol = new ServerProtocol(netInformation -> false);
    }

    @Test
    public void waitingForDiscoveryBroadcast(){
        Datagram datagram = protocol.process(mockDatagram(""));
        assertEquals("WAITING", datagram.message());
    }

    @Test
    public void sendToSimulatorSelfLocation() {
        Datagram datagram = protocol.process(mockDatagram("Searching server from 10.230.138.241"));
        assertEquals("OK, Server running", datagram.message());
    }


    @Test
    public void assignPortToSimulation() {
        Datagram datagram = protocol.process(mockDatagram("Subscribe :simulator"));
        assertEquals("DONE, port assigned:50000", datagram.message());
    }


    private Datagram mockDatagram(String message) {
        Datagram datagram = mock(Datagram.class);
        when(datagram.message()).thenReturn(message);
        return datagram;
    }

}
