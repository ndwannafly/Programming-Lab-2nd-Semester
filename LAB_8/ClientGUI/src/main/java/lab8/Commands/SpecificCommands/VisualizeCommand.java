package lab8.Commands.SpecificCommands;

import lab8.Client.Receiver;
import lab8.Commands.Command;

import java.io.IOException;
import java.io.Serializable;

public class VisualizeCommand extends Command implements Serializable {

    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public VisualizeCommand(Receiver receiver){
        this.receiver = receiver;
    }

    public VisualizeCommand(){

    }

    @Override
    public String aboutCommand() {
        return "Visualize";
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute ShowCommand!");
        }
        else{
            receiver.visualize();
        }
    }
}
