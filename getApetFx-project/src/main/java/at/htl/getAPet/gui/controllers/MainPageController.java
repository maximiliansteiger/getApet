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
        List<Animal> animalList = animalDbRepository.findAll();
        mainImage.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/9/9b/HTBLA_Leonding.jpg"));


        // get the user
        System.out.println(App.getUser());


        displayPetStats(animalList.get(index));

        User user = App.getUser();
        likeBtn.setOnAction(actionEvent -> {
            if (index < animalList.size() - 1) {

                likesDbRepository.insertInformation(user, animalList.get(index));
                System.out.println(animalList.get(index));
                //mainImage.setImage(new Image("../../data/images/Image_1Bello"));
                //shows the next animal
                displayPetStats(animalList.get(++index));


            } else {
                displayNoMorePets();
            }
        });

        dislikeBtn.setOnAction(actionEvent -> {
            if (index < animalList.size() - 1) {
                displayPetStats(animalList.get(++index));
            } else {
                displayNoMorePets();
            }
        });

        accountButton.setOnAction(actionEvent -> {

            //updates user when account is called so it not so much traffic all the time
            toMyAccount();
        });

        System.out.println(App.getUser());


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
        //mainImage.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/9/9b/HTBLA_Leonding.jpg"));
        //mainImage.setImage(new Image("../../../../../../data/Image_1Bello.jpg"));

    }


}
