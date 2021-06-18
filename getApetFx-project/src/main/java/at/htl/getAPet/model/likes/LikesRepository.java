package at.htl.getAPet.model.likes;

import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.User.User;

import java.util.List;

public interface LikesRepository {

    void insertInformation(User user, Animal animal);
    List<Integer> getLikesPerUser(User user);
}
