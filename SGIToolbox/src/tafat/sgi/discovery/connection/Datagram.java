package tafat.sgi.discovery.connection;

public class Datagram {

    private NetInformation netInformation;
    private String message;

    public Datagram(String message, NetInformation netInformation) {
        this.message = message;
        this.netInformation = netInformation;
    }

    public Datagram() {
        message = "";
        netInformation = new NetInformation("",35000);
    }

    public String remoteAddress() {
        return netInformation.getRemoteIP();
    }

    public String message() {
        return message;
    }

    public String commandValue() {
        return message().split(":")[1];
    }

    public int getPort() {
        return netInformation.getPort();
    }

    public void setNetInformation(NetInformation netInformation) {
        this.netInformation = netInformation;
    }
}
