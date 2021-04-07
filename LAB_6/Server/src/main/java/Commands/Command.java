package Commands;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public abstract class Command {
    public abstract void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket);
}
