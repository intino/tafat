package protocol;

import connection.Datagram;
import connection.NetInformation;

public class ClientSideDiscoverProtocol implements Protocol {
    private static final String DISCOVERY_COMMAND = "DISCOVERY";
    private static final String ACCEPTANCE_COMMAND = "OK";
    private static final String FINAL_COMMAND = "DONE";
    public static final String SEARCHING_SERVER_MESSAGE = "Searching server from ";

    private String hostname;
    private NetInformation result;

    public ClientSideDiscoverProtocol(String hostname) {
        this.hostname = hostname;
    }

    public Datagram firstMessage() {
        System.out.println(DISCOVERY_COMMAND);
        return new Datagram(SEARCHING_SERVER_MESSAGE + hostname, new NetInformation("255.255.255.255", 0xCAB0));
    }

    @Override
    public Datagram process(Datagram datagram) {
        return createResponse(datagram);
    }

    @Override
    public boolean finished() {
        return result != null ;
    }

    @Override
    public NetInformation result() {
        return result;
    }

    private Datagram createResponse(Datagram datagram) {
        if(datagram.message().contains(ACCEPTANCE_COMMAND)) {
            System.out.println(ACCEPTANCE_COMMAND);
            return subscribeDatagram(datagram);
        }else if( datagram.message().contains(FINAL_COMMAND)) {
            System.out.println(FINAL_COMMAND);
            return finishDatagram(datagram);
        }
        return firstMessage();
    }

    private Datagram subscribeDatagram(Datagram datagram) {
        return new Datagram("Subscribe :"+hostname, new NetInformation(datagram.remoteAddress(), datagram.getPort()));
    }

    private Datagram finishDatagram(Datagram datagram) {
        int port = Integer.parseInt(datagram.commandValue());
        result = new NetInformation (datagram.remoteAddress(), port);
        return new Datagram();
    }

}
