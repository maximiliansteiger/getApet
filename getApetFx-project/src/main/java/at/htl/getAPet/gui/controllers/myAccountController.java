package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.Animal.Animal;
import at.htl.getAPet.model.Animal.AnimalDbRepository;
import at.htl.getAPet.model.User.User;
import at.htl.getAPet.model.User.UserDbRepository;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import at.htl.getAPet.model.likes.LikesDbRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.PopupWindow;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectProperty;

public class myAccountController {
    @FXML
    public Label profileUsername;
    @FXML
    public ListView<Animal> yourPetList;
    @FXML
    public ListView<Animal> likedList;
    @FXML
    public Label profileEmailField;
    @FXML
    public Label profilePhoneNrField;
    @FXML
    public Button addPetButton;
    public Button backToMainPage;
    public Button signOutButton;
    public Button showMoreButton;
    @FXML
    Button editProfileButton;
    ReadOnlyObjectProperty<Animal> animal;

    ObservableList<Animal> animalsOwner = FXCollections.observableArrayList();
    ObservableList<Animal> animalsLiked = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        AnimalDbRepository animalDbRepository = new AnimalDbRepository(dataSource);
        LikesDbRepository likesDbRepository = new LikesDbRepository(dataSource);
        User user = App.getUser();
        animal = likedList.getSelectionModel().selectedItemProperty();

        //get the pets where user is owner
        animalsOwner.addAll(animalDbRepository.findByUser(user));
        yourPetList.setItems(animalsOwner);

        //get the pets the user liked

//        String animals = likesDbRepository.getLikesPerUser(user);

        List<Animal> animals1 = new ArrayList<>();
        for (Integer i :likesDbRepository.getLikesPerUser(user)) {
            Animal animal = animalDbRepository.findById(i).get();
            animals1.add(animal);
            animalsLiked.add(animal);
        }

        likedList.setItems(animalsLiked);
        animalsLiked.addAll();
        likedList.setItems(animalsLiked);


        profileUsername.setText(user.getName());
        profileEmailField.setText(user.getEmail());
        profilePhoneNrField.setText(user.getPhoneNr());


        addPetButton.setOnAction(actionEvent ->

                changeSceneToAddPet());
        backToMainPage.setOnAction(actionEvent ->

                changeSceneToMainPage());
        signOutButton.setOnAction(actionEvent ->

                changeSceneToLogIn());
        editProfileButton.setOnAction(actionEvent ->

                changeSceneToChangeProfile());


//        binding ob etwas ausgewählt is und dann die ausgewählten daten anzeigen!!!!!!!!!
        showMoreButton.setOnAction(actionEvent ->

                changeSceneToRegCode());

        System.out.println(user);

//        showMoreButton.disableProperty().bind(animal.get());
    }

    private void changeSceneToRegCode() {
        try {
            App.setScene("animalData");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    private void changeSceneToChangeProfile() {
        try {
            App.setScene("regCode");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    private void changeSceneToLogIn() {
        try {
            App.setScene("login");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    private void changeSceneToAddPet() {
        try {
            App.setScene("createPet");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }

    }

    private void changeSceneToMainPage() {
        try {
            App.setScene("mainPage");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }

    }

}
