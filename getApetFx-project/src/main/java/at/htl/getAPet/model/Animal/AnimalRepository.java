package at.htl.getAPet.model.Animal;

import at.htl.getAPet.model.User.User;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {


    List<Animal> findAll(int startValue);


    Optional<Animal> findById(int id);

    List<Animal> findByUser(User user);

    Animal createAnimal(String name,int age,Gender gender,String species,String breed,double height,double weight
            ,String city,User owner,String imageURL);


    void update(Animal animal);


    void remove(Animal animal);
}
