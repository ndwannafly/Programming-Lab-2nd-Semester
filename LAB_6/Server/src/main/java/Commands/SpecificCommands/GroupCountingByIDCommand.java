package Commands.SpecificCommands;

import Commands.Command;
import Ulties.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class GroupCountingByIDCommand extends Command implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public GroupCountingByIDCommand(){

    }



    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        System.out.println("Server: Execute GroupCountingByIDCommand");
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        receiver.groupByCountingID();
    }


}
