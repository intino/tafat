import connection.Datagram;
import connection.DatagramConnection;
import org.junit.Assert;
import org.junit.Test;
import protocol.ServerSideDiscoveryProtocol;

public class TestConnection {

    @Test
    public void sendFirstMessageServerSideProtocol() {
        Datagram datagram = new ServerSideDiscoveryProtocol(netInformation -> false).firstMessage();
        try {
            DatagramConnection connection = new DatagramConnection(35000);
            connection.send(datagram);
            connection.close();
        }catch (Exception e) {
            Assert.fail();
        }
    }

}
