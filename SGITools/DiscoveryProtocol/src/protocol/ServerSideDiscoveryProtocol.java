package protocol;

import SubscriptionsState.SubscriptionsState;
import connection.Datagram;
import connection.NetInformation;

public class ServerSideDiscoveryProtocol implements Protocol {

    private final SubscriptionsState subscriptionsState;
    private NetInformation result;
    private int Default_Assigned_Port = 50000;

    public ServerSideDiscoveryProtocol(SubscriptionsState subscriptionsState) {
        this.subscriptionsState = subscriptionsState;
    }

    @Override
    public Datagram process(Datagram datagram) {
        if (datagram.message().contains("Searching server from")) {
            System.out.println("SHARING POSITION");
            return locationDatagram(datagram);
        }else if(datagram.message().contains("Subscribe :")) {
            System.out.println("ACEPTANCE_STATE");
            return assignPort(datagram);
        }
        System.out.println("WAITING MESSAGE");
        return firstMessage();
    }

    @Override
    public boolean finished() {
        return result != null;
    }

    @Override
    public NetInformation result() {
        return result;
    }

    @Override
    public Datagram firstMessage() {
        return new Datagram("WAITING", new NetInformation("127.0.0.1", 35002));
    }

    private Datagram locationDatagram(Datagram datagram) {
        return new Datagram("OK, Server running", new NetInformation(datagram.remoteAddress(), datagram.getPort()));
    }

    private Datagram assignPort(Datagram datagram) {
        result = new NetInformation(datagram.commandValue(), datagram.remoteAddress(), getFreePort(datagram));
        return new Datagram("DONE, port assigned:" + result.getPort(), new NetInformation(datagram.remoteAddress(), datagram.getPort()));
    }

    private int getFreePort(Datagram datagram) {
        int port = Default_Assigned_Port;
        while(subscriptionsState.exitsNetInformation(new NetInformation(datagram.remoteAddress(), port)))
            port++;
        return port;
    }

}
