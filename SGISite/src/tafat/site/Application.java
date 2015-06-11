package tafat.site;

import Controller.HttpService;
import connection.DatagramConnection;
import spark.Spark;
import spark.SparkBase;
import tafat.site.route.HandlerDictionary;
import tafat.site.route.notification.NotificationServer;
import tafat.site.route.notification.WebSocketConnection;
import tafat.site.subscription.SimulatorSubscriptionsService;

import java.net.SocketException;

public class Application {
    public static void main(String[] args) {
        try {
            (new Application()).execute();
        } catch (SocketException var2) {
            var2.printStackTrace();
        }

    }

    public static void run(String[] args) {
        main(args);
    }

    private void execute() throws SocketException {
        runStaticServer();
        runSubscriptionServer();
        runHttpServer();
        runNotificationServer();
    }

    private void runHttpServer() {
        (new HttpService(new RouterRequestProcessor(new HandlerDictionary()), 8080)).start();
    }

    private void runSubscriptionServer() throws SocketException {
        (new SimulatorSubscriptionsService(new DatagramConnection(0XCAB0))).start();
    }

    private void runStaticServer() {
        SparkBase.staticFileLocation("/public");
        SparkBase.port(80);
        Spark.get("/about", (req, res) -> "Tafat. SIANI-2015");
    }

    private void runNotificationServer() {
        WebSocketConnection connection = new WebSocketConnection(8081);
        connection.start();
        NotificationServer.instance().setConnection(connection);
    }

}
