package tafat.sgi.discovery;

import tafat.sgi.discovery.connection.Datagram;
import tafat.sgi.discovery.connection.NetInformation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestServerDiscover {

    public static final String PUBLIC_IP = "192.168.1.123";
    private ClientProtocol protocol;
    private String hostname = "simulator";;

    @Before
    public void setUp() {
        this.protocol = new ClientProtocol(hostname);
    }

    @Test
    public void launchBroadcastDiscoveryMessage() {
        Datagram datagram = protocol.firstMessage();
        assertEquals(ClientProtocol.SEARCHING_SERVER_MESSAGE + hostname, datagram.message());
    }

    @Test
    public void subscribeToControlServer() {
        Datagram datagram = new Datagram("OK, Server running on:192.168.1.213", new NetInformation("192.168.1.213", 35001));
        datagram = protocol.process(datagram);
        assertEquals("192.168.1.213", datagram.remoteAddress());
        assertEquals("Subscribe :simulator", datagram.message());
    }

    @Test
    public void finishingProtocolSubscription() {
        Datagram datagram = new Datagram("DONE, port assigned:89000", new NetInformation("192.168.1.3", 35001));
        protocol.process(datagram);
        assertEquals(true, protocol.finished());
        assertEquals(89000, protocol.result().getPort());
    }

}
