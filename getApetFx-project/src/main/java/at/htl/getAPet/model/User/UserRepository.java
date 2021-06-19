package at.htl.getAPet.model.User;

import at.htl.getAPet.model.Animal.Animal;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(int id);
    void updateUser(User user);
    void addUser(User user);
    void deleteUser(User user);
    int findLastAnimal(int id);

}
