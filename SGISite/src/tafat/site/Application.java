package tafat.site;

import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Spark;
import spark.SparkBase;
import tafat.sgi.discovery.connection.DatagramConnection;
import tafat.sgi.http.connection.controller.HttpNativeService;
import tafat.sgi.http.connection.model.conection.HttpRequest;
import tafat.sgi.http.connection.model.handler.Handler;
import tafat.site.route.HandlerDictionary;
import tafat.site.route.notification.NotificationServer;
import tafat.site.route.notification.WebSocketConnection;
import tafat.site.subscription.SimulatorSubscriptionsService;

import java.io.IOException;
import java.net.SocketException;
import java.util.Map;

import static tafat.sgi.exception.ExceptionHandler.getSafe;

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
        RouterRequestProcessor processor = new RouterRequestProcessor(new HandlerDictionary());
        (new HttpNativeService(processor, 8082)).start();
    }

    private void runSubscriptionServer() throws SocketException {
        (new SimulatorSubscriptionsService(new DatagramConnection(0XCAB0))).start();
    }

    private void runStaticServer() {
        SparkBase.port(8080);
        SparkBase.staticFileLocation("/public");
        setDefault(requestProcessor::process);

        Spark.get("/about", (req, res) -> "Tafat. SIANI-2015");
    }

    private void setDefault(Handler handler) {
        Spark.post("/api/*", (req, res) -> handler.handle(toSGIRequest(req)).getBody());
        Spark.get("/api/*", (req, res) -> processRequestWithQuery(handler, req));
        Spark.delete("/api/*", (req, res) -> processRequestWithQuery(handler, req));
    }


    private String processRequestWithQuery(Handler handler, Request req) throws IOException, ParseException {
        return handler.handle(queryToBody(req)).getBody();
    }

    private tafat.sgi.http.connection.model.conection.Request queryToBody(Request request) {
        Map<String, String[]> queryMap = request.queryMap().toMap();
        HttpRequest sgiRequest = toSGIRequest(request);
        for (String key : queryMap.keySet())
            sgiRequest.setParameter(key, queryMap.get(key)[0]);
        return sgiRequest;
    }

    private HttpRequest toSGIRequest(Request req)  {
        return getSafe(()->new HttpRequest(req.requestMethod(), req.pathInfo().replace("/api",""), req.body()));
    }


    private void runNotificationServer() {
        WebSocketConnection connection = new WebSocketConnection(8081);
        connection.start();
        NotificationServer.instance().setConnection(connection);
    }

}
