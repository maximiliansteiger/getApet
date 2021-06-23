package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.User.User;
import at.htl.getAPet.model.User.UserDbRepository;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ShowAnimalData {


    public Label petNameLabel;
    public Label petAgeLabel;
    public Label petGenderLabel;
    public Label petSpeciesLabel;
    public Label petBreedLabel;
    public Label petHeightLabel;
    public Label petWeightLabel;
    public Label petCityLabel;

    public Button backButton;
    public Button backToMainButton;

    public Label userNameLabel;
    public Label userMailLabel;
    public Label userPhoneNumberLabel;
    public ImageView animalImageView;

    private Animal animal;
    private User user;


    @FXML
    private void initialize(){

        animal = App.getAnimal();
        user = animal.getOwner();

        showAnimalData();
        showUserData();
        initButtons();
        
    }

    private void showAnimalData() {
        petNameLabel.setText(animal.getName());
        petAgeLabel.setText(animal.getAge()+"");
        petGenderLabel.setText(animal.getGender().name());
        petSpeciesLabel.setText(animal.getSpecies());
        petBreedLabel.setText(animal.getBreed());
        petCityLabel.setText(animal.getCity());
        petWeightLabel.setText(animal.getWeight()+"");
        petHeightLabel.setText(animal.getHeight()+"");
        animalImageView.setImage(new Image(animal.getImgURL()));
    }

    private void showUserData() {
        userNameLabel.setText(user.getName());
        userMailLabel.setText(user.getEmail());
        userPhoneNumberLabel.setText(user.getPhoneNr());
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> changeSceneToMyAccount());
        backToMainButton.setOnAction(actionEvent -> changeSceneToMain());
    }

    private void changeSceneToMyAccount() {
        try {
            App.setScene("myAccount");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }

    }

    private void changeSceneToMain() {
        try {
            App.setScene("MainPage");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }

    }
}