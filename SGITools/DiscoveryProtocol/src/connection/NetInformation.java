package connection;

public class NetInformation {

    public String remoteIP;
    public int port;
    private String hostName;

    public NetInformation(String hostName, String remoteIP, int port) {
        this.hostName = hostName;
        this.remoteIP = remoteIP;
        this.port = port;
    }

    public NetInformation(String remoteIP, int port) {
        this.remoteIP = remoteIP;
        this.port = port;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public int getPort() {
        return port;
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetInformation that = (NetInformation) o;

        if (port != that.port) return false;
        if (!remoteIP.equals(that.remoteIP)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = remoteIP.hashCode();
        result = 31 * result + port;
        result = 31 * result + hostName.hashCode();
        return result;
    }
}
