package Utils;

import Data.Person;
import Database.DatabaseCommunicator;
import Database.PersonBase;
import Database.SecretBase;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ForkJoinPool;

import static java.nio.ByteBuffer.wrap;

public class Receiver {

    private final DatagramChannel datagramChannel;
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public Receiver(DatagramChannel datagramChannel){
        this.datagramChannel = datagramChannel;
    }



    public void clear(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    CollectionManager.clearCollectionOnDataBase(str[0]);
                    byte[] response = "Clear data of people who belong to you!".getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void add(String arg, Object o, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    Person person = (Person) o;
                    DatabaseCommunicator.getPersonBase().addPersonToDataBase(person, -1);
                    byte[] response = CollectionManager.add().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void countLessThanBirthday(String arg, Object o, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    String birthdayStr = (String) o;
                    byte[] response = CollectionManager.countLessThanBirthDay(birthdayStr).getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | ParseException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void groupByCountingID(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.groupByCountingID().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void info(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.info().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void printFieldAscendingHeight(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.printFieldAscendingHeight().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void removeByID(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    int id = Integer.parseInt(str[2]);
                    if(PersonBase.validationPermissionEdit(id, str[0])){
                        byte[] response = CollectionManager.removeByID(id).getBytes();
                        datagramChannel.send(wrap(response), socketAddress);
                    }
                    else{
                        datagramChannel.send(wrap("You can't remove the element not belong to you!".getBytes()), socketAddress);
                    }
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void removeGreater(String arg, Object o, SocketAddress socketAddress) {
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.removeGreater((Person) o, str[0]).getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void removeLower(String arg, Object o, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.removeLower((Person) o, str[0]).getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void show(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() ->{
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    byte[] response = CollectionManager.show().getBytes();
                    datagramChannel.send(wrap(response), socketAddress);
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void update(String arg, Object o, SocketAddress socketAddress) {
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            try {
                if(PersonBase.validation(str[0], str[1])){
                    int id = Integer.parseInt(str[2]);
                    if(PersonBase.validationPermissionEdit(id, str[0])){
                        DatabaseCommunicator.getPersonBase().deleteOrganizationFromDataBase(id);
                        DatabaseCommunicator.getPersonBase().addPersonToDataBase((Person) o, id);
                        byte[] response = CollectionManager.update(id).getBytes();
                        datagramChannel.send(wrap(response), socketAddress);
                    } else{
                        datagramChannel.send(wrap("You can't update the element not belong to you!".getBytes()), socketAddress);
                    }
                }
                else{
                    datagramChannel.send(wrap("User is not logged in".getBytes()), socketAddress);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void register(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            String response;
            try {
                if(!(DatabaseCommunicator.getUsers().isExisting("handle", str[0]))){
                    DatabaseCommunicator.getUsers().addUserToDataBase(str[0], str[1]);
                    response = "Successfully";
                } else response = "Unsuccessfully";
                datagramChannel.send(wrap(response.getBytes()), socketAddress);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void login(String arg, SocketAddress socketAddress){
        forkJoinPool.submit(() -> {
            String[] str = arg.split(" ");
            String response;
            //System.out.println(str[0] + " " + str[1]);
            try {
                if(DatabaseCommunicator.getUsers().isExisting("handle", str[0])){
                    //System.out.println("handle yes!");
                    if(DatabaseCommunicator.getUsers().isExisting("password", SecretBase.HashPsw(str[1]))){
                        //System.out.println("password yes!");
                        response = "Successfully";
                    } else  response = "Unsuccessfully";
                } else response = "Unsuccessfully";
                datagramChannel.send(wrap(response.getBytes()), socketAddress);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
