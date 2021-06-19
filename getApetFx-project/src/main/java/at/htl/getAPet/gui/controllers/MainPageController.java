package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.Animal.AnimalDbRepository;
import at.htl.getAPet.model.User.User;
import at.htl.getAPet.model.User.UserDbRepository;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import at.htl.getAPet.model.likes.LikesDbRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class MainPageController {


    @FXML
    public Label nameField;
    @FXML
    public Label ageField;
    @FXML
    public Label genderField;
    @FXML
    public Label breedField;
    @FXML
    public Button likeBtn;
    @FXML
    public Button dislikeBtn;
    @FXML
    public Label ownerField;
    @FXML
    public Label cityField;
    @FXML
    public Label weightField;
    @FXML
    public Label heightField;
    @FXML
    public ImageView mainImage;
    @FXML
    public Button accountButton;
    private int index = 0;

    @FXML
    private void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();

        AnimalDbRepository animalDbRepository = new AnimalDbRepository(dataSource);
        LikesDbRepository likesDbRepository = new LikesDbRepository(dataSource);
        UserDbRepository userDbRepository = new UserDbRepository(dataSource);

        List<Animal> animalList = animalDbRepository.findAll(userDbRepository.findLastAnimal(App.user.getId()));

        if (animalList.size() > 0) {
            Animal animal = animalList.get(index);
            displayPetStats(animal);
        }

        User user = App.getUser();
        likeBtn.setOnAction(actionEvent -> {
            App.addOneToLastAnimal();
            if (index < animalList.size() - 1) {
                Animal animal = animalList.get(index);
                likesDbRepository.insertInformation(user, animal);
                displayPetStats(animalList.get(++index));
            } else {
                displayNoMorePets();
            }
        });

        dislikeBtn.setOnAction(actionEvent -> {
            App.addOneToLastAnimal();
            if (index < animalList.size() - 1) {
                displayPetStats(animalList.get(++index));
            } else {
                displayNoMorePets();
            }
        });

        accountButton.setOnAction(actionEvent -> {
            App.getUser().setLastAnimal(App.getLastAnimal());
            userDbRepository.updateUser(App.getUser());
            toMyAccount();
        });

    }

    private void toMyAccount() {
        try {
            App.setScene("myAccount");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }


    private void displayNoMorePets() {
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        breedField.setText("");
        heightField.setText("");
        weightField.setText("");
        cityField.setText("");
        ownerField.setText("");
    }

    private void displayPetStats(Animal animal) {
        nameField.setText(animal.getName());
        ageField.setText(String.valueOf(animal.getAge()));
        genderField.setText(String.valueOf(animal.getGender()));
        breedField.setText(animal.getBreed());
        heightField.setText(String.valueOf(animal.getHeight()));
        weightField.setText(String.valueOf(animal.getWeight()));
        cityField.setText(animal.getCity());
        ownerField.setText(animal.getOwner().getName());
        mainImage.setImage(new Image(animal.getImgURL()));
    }

}
