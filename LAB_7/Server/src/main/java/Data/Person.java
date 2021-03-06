package Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Person implements Comparable<Person>, Serializable {
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int height;
    private String birthday;
    private int weight;
    private Country nationality;
    private Location location;
    private String owner;

    public Person(long id, String owner, String name, Coordinates coordinates, LocalDateTime localDateTime, int height,
                  String birthday, int weight, Country nationality, Location location){
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = localDateTime;
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.nationality = nationality;
        this.location = location;
    }
    public Person(){

    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Country getNationality() {
        return nationality;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public long getId() {
        return id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String info="Let me introduce new person\n";
        info += ("Name: " + name + '\n');
        info += ("ID: " + id + '\n');
        info += ("Coordinates: \n");
        info += ("             x: " + coordinates.getX() + '\n');
        info += ("             y: " + coordinates.getY() + '\n');
        info += ("creationDate: " + creationDate + '\n');
        info += ("height: " + height + '\n');
        info += ("birthday: " + birthday + '\n');
        info += ("weight: " + weight + '\n');
        info += ("nationality " + nationality + '\n');
        info += ("location: \n");
        info += ("           x: " + getLocation().getX() + '\n');
        info += ("           y: " + getLocation().getY() + '\n');
        info += ("           name: " + getLocation().getName() + "\n\n");
        info += ("owner: " + owner + "\n\n");
        return info;
    }

    @Override
    public int compareTo(Person o) {
        return (height - o.getHeight());
    }
}