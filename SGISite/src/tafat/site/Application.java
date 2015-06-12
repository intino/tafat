package tafat.site;

import spark.Route;
import spark.Spark;
import spark.SparkBase;
import tafat.sgi.controller.HttpService;
import tafat.sgi.discovery.connection.DatagramConnection;
import tafat.sgi.model.conection.HttpRequest;
import tafat.site.route.HandlerDictionary;
import tafat.site.route.notification.NotificationServer;
import tafat.site.route.notification.WebSocketConnection;
import tafat.site.subscription.SimulatorSubscriptionsService;

import java.net.SocketException;

public class Application {
    static RouterRequestProcessor requestProcessor = new RouterRequestProcessor(new HandlerDictionary());

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
        (new HttpService(new RouterRequestProcessor(new HandlerDictionary()), 8082)).start();
    }

    private void runSubscriptionServer() throws SocketException {
        (new SimulatorSubscriptionsService(new DatagramConnection(0XCAB0))).start();
    }

    private void runStaticServer() {
        SparkBase.port(8080);
        SparkBase.staticFileLocation("/public");
        setDefault((req, res) ->
            requestProcessor.process
                    (new HttpRequest(req.requestMethod(), req.pathInfo(), req.body()))
                    .getBody());

        Spark.get("/about", (req, res) -> "Tafat. SIANI-2015");
    }


    private void setDefault(Route route) {
        Spark.get("/api/*","application/json", route);
        Spark.post("/api/*", route);
        Spark.delete("/api/*", route);
    }


    private void runNotificationServer() {
        WebSocketConnection connection = new WebSocketConnection(8081);
        connection.start();
        NotificationServer.instance().setConnection(connection);
    }

}
