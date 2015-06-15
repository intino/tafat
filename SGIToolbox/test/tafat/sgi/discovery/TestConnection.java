package tafat.sgi.discovery;

import org.junit.Assert;
import org.junit.Test;
import tafat.sgi.discovery.connection.Datagram;
import tafat.sgi.discovery.connection.DatagramConnection;

public class TestConnection {

    @Test
    public void sendFirstMessageServerSideProtocol() {
        Datagram datagram = new ServerProtocol(netInformation -> false).firstMessage();
        try {
            DatagramConnection connection = new DatagramConnection(35000);
            connection.send(datagram);
            connection.close();
        }catch (Exception e) {
            Assert.fail();
        }
    }

}
