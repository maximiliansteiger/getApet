package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.Animal.AnimalDbRepository;
import at.htl.getAPet.model.Animal.Gender;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;

public class addPetController {
    @FXML
    public TextField nameField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField speciesField;
    @FXML
    public TextField breedField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField weightField;
    @FXML
    public TextField cityField;
    @FXML
    public Button addButton;
    @FXML
    public Button closeButton;
    @FXML
    public RadioButton maleRadioButton;
    @FXML
    public RadioButton femaleRadioButton;

    public ToggleGroup gender = new ToggleGroup();
    public TextField imageURLField;
    private AnimalDbRepository animalDbRepository;

    @FXML
    private void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        animalDbRepository = new AnimalDbRepository(dataSource);

        addButton.setOnAction(actionEvent -> {
            createAnimal();
            clearFields();
        });

        closeButton.setOnAction(actionEvent -> changeSceneToMain());

        maleRadioButton.setToggleGroup(gender);
        femaleRadioButton.setToggleGroup(gender);

        addButton.disableProperty().bind(
                nameField.textProperty().isEmpty()
                        .or(ageField.textProperty().isEmpty())
                        .or(maleRadioButton.selectedProperty().not().and(femaleRadioButton.selectedProperty().not()))
                        .or(speciesField.textProperty().isEmpty())
                        .or(breedField.textProperty().isEmpty())
                        .or(heightField.textProperty().isEmpty())
                        .or(weightField.textProperty().isEmpty())
                        .or(cityField.textProperty().isEmpty())
        );

    }

    private void createAnimal() {
        Gender gender = getGenderOfAnimal();

        animalDbRepository.createAnimal(
                nameField.getText(),
                Integer.parseInt(ageField.getText()),
                gender,
                speciesField.getText(),
                breedField.getText(),
                Double.parseDouble(heightField.getText()),
                Double.parseDouble(weightField.getText()),
                cityField.getText(),
                App.getUser(),
                imageURLField.getText()
        );

    }

    private Gender getGenderOfAnimal() {
        return (maleRadioButton.isSelected()) ? Gender.MALE : Gender.FEMALE;
    }

    private void clearFields() {
        nameField.clear();
        ageField.clear();
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        speciesField.clear();
        breedField.clear();
        heightField.clear();
        weightField.clear();
        cityField.clear();
    }


    private void changeSceneToMain() {
        try {
            App.setScene("myAccount");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }

    }

}
