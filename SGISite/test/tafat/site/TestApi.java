package tafat.site;

import connection.Connection;
import connection.Datagram;
import connection.NetInformation;
import org.junit.Test;
import protocol.ClientSideDiscoverProtocol;
import protocol.Protocol;
import tafat.site.subscription.SubscriptionsHandler;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestApi {

    @Test
    public void subscribeSimulatorWithHostnameFelipe() throws Exception {
        Connection clientSideConnection = clientSideConnection("Felipe", "192.168.1.4");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        assertEquals(1, ServerState.instance().numberOfSubscriptions());
        assertNotNull(ServerState.instance().getSubscription("Felipe"));
    }

    @Test
    public void subscribeSimulatorsFelipeAndFrancisco() throws Exception {
        Connection clientSideConnection = clientSideConnection("Felipe", "192.168.1.4");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        clientSideConnection = clientSideConnection("Francisco", "192.168.1.5");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        assertEquals(2, ServerState.instance().numberOfSubscriptions());
        assertNotNull(ServerState.instance().getSubscription("Felipe"));
        assertNotNull(ServerState.instance().getSubscription("Francisco"));
    }

    @Test
    public void subscribeTwoSimulationsWithTheSameIP() throws Exception {
        Connection clientSideConnection = clientSideConnection("Felipe", "192.168.1.4");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        clientSideConnection = clientSideConnection("Francisco", "192.168.1.4");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        assertEquals(50000, ServerState.instance().getSubscription("Felipe").getPort());
        assertEquals(50001, ServerState.instance().getSubscription("Francisco").getPort());
    }

    //TODO HACER PASAR ESTE TEST .
    //TODO IMPLICA DAR UNA NUEVA KEY EN VEZ DEL HOSTNAME Y PROBABLEMENTE CAMBIAR EL HASHMAP
    //TODO ¿No permitir hostnames iguales?
    @Test
    public void subscribeTwoSimulationsWithTheSameHostname() throws Exception {
        Connection clientSideConnection = clientSideConnection("Felipe", "192.168.1.4");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
        clientSideConnection = clientSideConnection("Felipe", "192.168.1.5");
        new SubscriptionsHandler(clientSideConnection).collectingSubscriptions();
    }

    private Connection clientSideConnection(String hostname, String ip) {
        return new Connection() {
            Protocol protocol = new ClientSideDiscoverProtocol(hostname);
            public Datagram datagram;

            @Override
            public void close() throws Exception {

            }

            @Override
            public void send(Datagram datagram) throws IOException {
                    this.datagram = datagram;
            }

            @Override
            public Datagram receiveData() throws IOException {
                if(datagram==null)
                    return new Datagram("", new NetInformation("", 35001));
                Datagram response = responseClientProtocol();
                response.setNetInformation(new NetInformation(hostname, ip, 35001));
                return response;
            }

            private Datagram responseClientProtocol() {
                if(!datagram.message().contains("WAITING"))
                    return protocol.process(datagram);
                return protocol.firstMessage();
            }
        };
    }
}
