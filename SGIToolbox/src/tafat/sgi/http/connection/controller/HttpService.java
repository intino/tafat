package tafat.sgi.http.connection.controller;

import com.sun.net.httpserver.HttpServer;
import tafat.sgi.http.connection.model.handler.HttpPetitionHandler;
import tafat.sgi.http.connection.model.proccesor.Processor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpService {
    private final HttpPetitionHandler petitionsHandler;
    private final InetSocketAddress inetSocketAddress;

    public HttpService(Processor processor, int port) {
        this.petitionsHandler = new HttpPetitionHandler(processor);
        this.inetSocketAddress = new InetSocketAddress(port);
    }


    public void start() {
        try {
            HttpServer httpServer = HttpServer.create(inetSocketAddress, 500);
            httpServer.createContext("/", petitionsHandler);
            httpServer.start();
            System.out.println("Server running on the port : "+ inetSocketAddress.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enableRequestQueue() {
        petitionsHandler.enableRequestQueue();
    }

    public void disableRequestQueue() {
        petitionsHandler.disableRequestQueue();
    }

    public synchronized void executeStoreRequests() {
        petitionsHandler.runRequestsQueue();
    }
}
