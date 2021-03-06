package Core;

import Data.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class CommandAsker {
    private final InputChecker inputChecker;

    public CommandAsker(InputChecker ic){
        this.inputChecker = ic;
    }


    private  static final Scanner scanner = new Scanner(System.in);


    public Person createPerson() {
        Person newPerson = new Person();
        System.out.println("Let's create new person");

        newPerson.setId(idAsker());

        newPerson.setName(nameAsker());

        newPerson.setCoordinates(coordinatesAsker());

        newPerson.setCreationDate(dateAsker());

        newPerson.setHeight(heightAsker());

        newPerson.setBirthday(birthdayAsker());

        newPerson.setWeight(weightAsker());

        newPerson.setNationality(countryAsker());

        newPerson.setLocation(locationAsker());
        return newPerson;
    }

    public Long idAsker(){
        System.out.println("Id is automatically generated.");
        Long newID = new Random().nextLong();
        if(CollectionManager.IDChecker.contains(newID) || newID < 0){
            System.out.println("Input is invalid. Let's generate the new one!");
            return idAsker();
        }
        else{
            CollectionManager.IDChecker.add(newID);
            System.out.println("ID = " + newID + " is successfully generated!");
            return newID;
        }
    }

    public String nameAsker(){
        System.out.println("Insert name: ");
        return (scanner.nextLine());
    }

    public Coordinates coordinatesAsker(){
        System.out.println("Insert coordinates: ");
        while(true){
            System.out.println("Insert x and y:");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 2 ){
                System.out.println("please insert exactly two number!");
            }
            else{
                if(!inputChecker.integerValidCheck(inputNumber[0], -801, Integer.MAX_VALUE)) continue;
                if(!inputChecker.doubleValidCheck(inputNumber[1], Double.MIN_VALUE, 687.0)) continue;
                int x = Integer.parseInt(inputNumber[0]);
                Double y = Double.parseDouble(inputNumber[1]);
                return new Coordinates(x, y);
            }
        }
    }

    public LocalDateTime dateAsker(){
        return java.time.LocalDateTime.now();
    }

    public Long heightAsker(){
        while(true) {
            System.out.println("Insert height: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("Please insert exactly one number!");
            }
            else{
                if(!inputChecker.longValidCheck(inputNumber[0], Long.MIN_VALUE, Long.MAX_VALUE)) continue;
                return Long.parseLong(inputNumber[0]);
            }
        }
    }

    public Date birthdayAsker() {
        while(true) {
            System.out.println("Insert birthday: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1){
                System.out.println("Please insert one date only!");
            }
            else {
                SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    return birthdayFormatter.parse(inputNumber[0]);
                } catch (ParseException e){
                    System.out.println("Input is invalid. Correct birthday's format is dd-MM-yyyy");
                }
            }
        }
    }

    public Integer weightAsker(){
        System.out.println("Insert weight: ");
        return intAsker(0,Integer.MAX_VALUE);
    }

    public Country countryAsker(){
        while(true) {
            System.out.println("Insert Country: ");
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1) {
                System.out.println("Please insert exactly one country!");
                continue;
            }
            try {
                return Country.valueOf(inputNumber[0]);
            } catch(IllegalArgumentException e){
                System.out.println("Invalid country! The country is not in the list!");
                System.out.println("Please insert one of these following countries");
                for (Country country : Country.values()){
                    System.out.println(country);
                }
            }
        }
    }

    public Location locationAsker(){
        System.out.println("Insert Location: ");
        System.out.println("Insert X: ");
        Integer LocationX = intAsker(Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println("Insert Y: ");
        long LocationY = longAsker(Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.println("Insert name: ");
        String name = scanner.nextLine();
        return new Location(LocationX, LocationY, name);
    }

    public Integer intAsker(int min, int max){
        while(true){
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("please enter exactly one integer: ");
            }
            else{
                int x;
                try {
                    x = Integer.parseInt(inputNumber[0]);
                    if( x < min ) continue;
                    if( x > max) continue;
                    return x;
                } catch (NumberFormatException e){
                    System.out.println("please insert an integer number");
                }
            }
        }
    }

    public Long longAsker(long min, long max){
        while(true){
            String[] inputNumber = scanner.nextLine().trim().split(" ");
            if(inputNumber.length != 1 ){
                System.out.println("please enter exactly one Long number: ");
            }
            else{
                long x;
                try {
                    x = Long.parseLong(inputNumber[0]);
                    if( x < min ) continue;
                    if( x > max) continue;
                    return x;
                } catch (NumberFormatException e){
                    System.out.println("please insert an Long number");
                }
            }
        }
    }
}

