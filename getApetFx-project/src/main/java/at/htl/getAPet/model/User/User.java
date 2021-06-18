package at.htl.getAPet.model.User;

import at.htl.getAPet.model.Animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String phoneNr;
    private List<Animal> animalArrayList = new ArrayList<>();


    public User(int id, String name, String email, String password,String phoneNr) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNr = phoneNr;
    }
    public User(){

    }


    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public List<Animal> getAnimalArrayList(){
        return animalArrayList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addAnimal(Animal animal){
        animalArrayList.add(animal);
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
