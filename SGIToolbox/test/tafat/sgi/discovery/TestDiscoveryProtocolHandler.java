package tafat.sgi.discovery;

import tafat.sgi.discovery.SubscriptionsState.SubscriptionsState;
import tafat.sgi.discovery.connection.Connection;
import tafat.sgi.discovery.connection.Datagram;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.discovery.handler.DiscoveryProtocolHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDiscoveryProtocolHandler {
    @Test
    public void clientSideIntegrationWithProtocolHandler() throws Exception {
        Connection connection = connectionWithServer();
        NetInformation netInformation= new DiscoveryProtocolHandler(new ClientProtocol("simulator"), connection).start();
        assertEquals(50000, netInformation.getPort());
    }

    @Test
    public void serverSideIntegrationWithProtocolHandler() throws Exception {
        Connection connection = connectionWithClient();
        NetInformation netInformation = new DiscoveryProtocolHandler(new ServerProtocol(createSubscriptionState()), connection).start();
        assertEquals(50000, netInformation.getPort());
        assertEquals("10.230.138.241", netInformation.getRemoteIP());
        assertEquals("simulator", netInformation.getHostName());
    }

    private SubscriptionsState createSubscriptionState() {
        return netInformation -> false;
    }

    private Connection connectionWithServer() throws IOException {
        Connection connection = mock(Connection.class);
        Datagram locationDatagram = mockDatagram("OK, Server running on:10.230.138.241", false);
        when(locationDatagram.commandValue()).thenReturn("10.230.138.241");
        Datagram acceptSubscriptionDatagram = mockDatagram("DONE, port assigned:50000", false);
        when(acceptSubscriptionDatagram.commandValue()).thenReturn("50000");
        when(connection.receiveData()).thenReturn(locationDatagram)
                                      .thenReturn(acceptSubscriptionDatagram);

        return connection;
    }

    private Connection connectionWithClient() throws IOException {
        Connection connection = mock(Connection.class);
        Datagram searchingDatagram = mockDatagram("Searching server from 10.230.138.241", true);
        Datagram subscribeDatagram = mockDatagram("Subscribe :simulator", false);
        when(subscribeDatagram.commandValue()).thenReturn("simulator");
        Datagram finalDatagram = mockDatagram("", false);
        when(connection.receiveData()).thenReturn(searchingDatagram)
                                    .thenReturn(subscribeDatagram)
                                    .thenReturn(finalDatagram);
        return connection;
    }

    private Datagram mockDatagram(String message, boolean broadcastDatagram) {
        Datagram datagram = mock(Datagram.class);
        when(datagram.message()).thenReturn(message);
        when(datagram.getPort()).thenReturn(35000);
        if(broadcastDatagram)
            when(datagram.remoteAddress()).thenReturn("255.255.255.255");
        else
            when(datagram.remoteAddress()).thenReturn("10.230.138.241");
        return datagram;
    }
}
