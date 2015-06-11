package connection;

import java.io.IOException;
import java.net.*;

import static exception.ExceptionHandler.getSafe;
import static exception.ExceptionHandler.runSafe;

public class DatagramConnection implements Connection {
    private final DatagramSocket socket;

    public DatagramConnection(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        socket.setSoTimeout(5000);
    }

    @Override
    public void send(Datagram datagram) {
        runSafe(() -> socket.send(packageDatagram(datagram)));
    }

    @Override
    public Datagram receiveData(){
        return getSafe(this::receiveDatagram, Datagram::new);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    private Datagram receiveDatagram() throws IOException {
        DatagramPacket packet = new DatagramPacket(new byte[15000], 15000);
        socket.receive(packet);
        return unpackedDatagram(packet);
    }

    private Datagram unpackedDatagram(DatagramPacket packet) {
        String data = new String(packet.getData()).trim();
        return new Datagram(data, new NetInformation(packet.getAddress().getHostAddress(), packet.getPort()));
    }

    private DatagramPacket packageDatagram(Datagram datagram) throws NullPointerException, UnknownHostException {
        return new DatagramPacket(datagram.message().getBytes(), datagram.message().length(),
                InetAddress.getByName(datagram.remoteAddress()), datagram.getPort());
    }
}
