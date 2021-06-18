package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.UncheckedIOException;

public class ShowAnimalData {
    public Label petName;
    public Label age;
    public Label gender;
    public Label species;
    public Label breed;
    public Label height;
    public Label weight;
    public Label city;
    public Label ownerName;
    public Label email;
    public Label pNumber;
    public Button backButton;
    public Button mainButton;


    @FXML
    private void initialize(){


        setupData();

        backButton.setOnAction(actionEvent -> changeSceneToMyAccount());
        backButton.setOnAction(actionEvent -> changeSceneToMain());

    }

    private void setupData() {


        //TODO get the selected animal and somehow use it here


//        petName.setText();
//          age;
//          gender;
//          species;
//          breed;
//          height;
//          weight;
//          city;
//          ownerName;
//          email;
//          pNumber;




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
