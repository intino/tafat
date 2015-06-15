package tafat.site.subscription;


import tafat.sgi.discovery.connection.Connection;

public class SimulatorSubscriptionsService{
    private Connection connection;

    public SimulatorSubscriptionsService(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        new Thread(new SubscriptionsHandler(connection)).start();
    }


}
