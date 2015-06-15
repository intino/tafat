package tafat.site.subscription;

import tafat.sgi.discovery.ServerProtocol;
import tafat.sgi.discovery.connection.Connection;
import tafat.sgi.discovery.connection.NetInformation;
import tafat.sgi.discovery.handler.DiscoveryProtocolHandler;
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
        DiscoveryProtocolHandler handler = new DiscoveryProtocolHandler(new ServerProtocol(ServerState.instance()), connection);
        NetInformation subscription = handler.start();
        ServerState.instance().addSubscription(subscription);
    }


}
