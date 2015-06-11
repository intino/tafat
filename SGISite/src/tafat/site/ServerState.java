package tafat.site;

import SubscriptionsState.SubscriptionsState;
import connection.NetInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerState implements SubscriptionsState {
    private static HashMap<String, NetInformation> subscriptions;
    private static ServerState instance;

    private ServerState() {
        subscriptions = new HashMap<String, NetInformation>();
    }

    public static ServerState instance() {
        if (instance == null)
            instance = new ServerState();
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public void addSubscription(NetInformation subscriptionInformation) {
        subscriptions.put(subscriptionInformation.getHostName(), subscriptionInformation);
    }

    public int numberOfSubscriptions() {
        return subscriptions.size();
    }


    public NetInformation getSubscription(String hostname) {
        return subscriptions.get(hostname);
    }

    public List<String> getSimulations() {
        return new ArrayList<>(subscriptions.keySet());
    }

    @Override
    public boolean exitsNetInformation(NetInformation netInformation) {
        for (String hostname : subscriptions.keySet())
            if (subscriptions.get(hostname).equals(netInformation))
                return true;
        return false;
    }
}
