package at.htl.getAPet.model.Animal;

import at.htl.getAPet.model.User.User;

public class Animal {

    private String name;
    private int id;
    private int age;
    private Gender gender;
    private String species;
    private String breed;
    private double height;
    private double weight;
    private String city;
    private User owner;
    private String imageURL;

    public Animal(int id, String name, int age, Gender gender, String species, String breed, double height, double weight, String city, User owner, String imageURL) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
        this.height = height;
        this.weight = weight;
        this.city = city;
        this.owner = owner;
        this.imageURL = imageURL;
    }

    public String getImgURL() {
        return imageURL;
    }

    public void setImgURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d",species,breed,name,age);

//
//                "Animal{" +
//                "name='" + name + '\'' +
//                ", id=" + id +
//                ", age=" + age +
//                ", gender=" + gender +
//                ", species='" + species + '\'' +
//                ", breed='" + breed + '\'' +
//                ", height=" + height +
//                ", weight=" + weight +
//                ", city='" + city + '\'' +
//                ", owner=" + owner +
//                '}';
    }
}
