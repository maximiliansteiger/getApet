package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.UncheckedIOException;

public class ShowAnimalData {


    public Label petName;
    public Label petAgeLabel;
    public Label petGenderLabel;
    public Label petSpeciesLabel;
    public Label petBreedLabel;
    public Label heightLabel;
    public Label weightLabel;
    public Label petCityLabel;
    public Label petNameLabel;
    public Button backButton;
    public Button backtomainButton;
    public Label userNameLabel;
    public Label userMailLabel;
    public Label userPhoneNumberLabel;

    private Animal animal;
    private User user;

    @FXML
    private void initialize(){
        user = App.getUser();
        animal = App.getAnimal();
        System.out.println("----------------");
        System.out.println(user);
        System.out.println(animal);
        showAnimalData();
        showUserData();
        initButtons();
        backButton.setOnAction(actionEvent -> changeSceneToMyAccount());
        backButton.setOnAction(actionEvent -> changeSceneToMain());

    }

    private void showAnimalData() {
        petNameLabel.setText(animal.getName());
        petAgeLabel.setText(animal.getAge()+"");
        petGenderLabel.setText(animal.getGender().name());
        petSpeciesLabel.setText(animal.getSpecies());
        petBreedLabel.setText(animal.getBreed());
        petCityLabel.setText(animal.getCity());
        weightLabel.setText(animal.getWeight()+"");
        heightLabel.setText(animal.getHeight()+"");
    }

    private void showUserData() {
        userNameLabel.setText(user.getName());
        userMailLabel.setText(user.getEmail());
        userPhoneNumberLabel.setText(user.getPhoneNr());
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> changeSceneToMyAccount());
        backtomainButton.setOnAction(actionEvent -> changeSceneToMain());
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
