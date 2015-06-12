package tafat.sgi.discovery.handler;

import tafat.sgi.discovery.connection.Connection;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.discovery.Protocol;

import java.io.IOException;

public class DiscoveryProtocolHandler {
    private final Protocol protocol;
    private Connection connection;

    public DiscoveryProtocolHandler(Protocol protocol, Connection connection) {
        this.protocol = protocol;
        this.connection = connection;
    }

    public NetInformation start() throws Exception {
        launchProtocol();
        return protocol.result();
    }

    private void launchProtocol(){
        try {
            while(!protocol.finished())
                connection.send(protocol.process(connection.receiveData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
