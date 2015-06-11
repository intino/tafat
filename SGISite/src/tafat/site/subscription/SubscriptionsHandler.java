package tafat.site.subscription;

import connection.Connection;
import connection.NetInformation;
import model.handler.DiscoveryProtocolHandler;
import protocol.ServerSideDiscoveryProtocol;
import tafat.site.ServerState;

public class SubscriptionsHandler implements Runnable {

    private final Connection connection;

    public SubscriptionsHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                collectingSubscriptions();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectingSubscriptions() throws Exception {
        DiscoveryProtocolHandler handler = new DiscoveryProtocolHandler(new ServerSideDiscoveryProtocol(ServerState.instance()), connection);
        NetInformation subscription = handler.start();
        ServerState.instance().addSubscription(subscription);
    }


}
