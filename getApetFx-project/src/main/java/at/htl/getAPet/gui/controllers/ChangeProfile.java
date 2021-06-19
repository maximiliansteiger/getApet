package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.User.User;
import at.htl.getAPet.model.User.UserDbRepository;
import at.htl.getAPet.model.User.UserRepository;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.security.MessageDigest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.sql.DataSource;

public class ChangeProfile {

    @FXML
    private Label nameField;
    @FXML
    private Label emailField;
    @FXML
    private Label phoneNumberField;
    @FXML
    public ToggleGroup gender = new ToggleGroup();
    @FXML
    private Label passwordField;
    @FXML
    private Button testMailButton;
    @FXML
    private Button backButton;
    @FXML
    private Label currentNameField;
    @FXML
    private Label currentEmailField;
    @FXML
    private Label currentPasswordLabel;
    @FXML
    private Label currentPhoneNumberField;
    @FXML
    private TextField newNameTextField;
    @FXML
    private TextField newEmailTextField;
    @FXML
    private TextField newPhoneNumberTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private Button saveButton;


    private UserDbRepository animalDbRepository;
    private BooleanProperty codeIsRight = new SimpleBooleanProperty(false);
    private User currentUser = App.getUser();
    private DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
    private UserRepository userRepository = new UserDbRepository(factory.createDataSource());

    private String name = "";
    private String password = "";
    private String phoneNumber = "";
    private String email = "";


    @FXML
    public void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        animalDbRepository = new UserDbRepository(dataSource);
        showUserInfos();
        initButtons();
    }

    private void showUserInfos() {
        currentNameField.setText(currentUser.getName());
        currentEmailField.setText(currentUser.getEmail());
        currentPasswordLabel.setText("••••••••••••");
        currentPhoneNumberField.setText(currentUser.getPhoneNr());
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> setScene());
        saveButton.setOnAction(actionEvent -> save());
    }

    private void save() {

        this.phoneNumber = (!newPhoneNumberTextField.getText().isEmpty())?newPhoneNumberTextField.getText():currentUser.getPhoneNr();
        this.name = (!newNameTextField.getText().isEmpty())?newNameTextField.getText():currentUser.getName();
        this.email = (!newEmailTextField.getText().isEmpty())?newEmailTextField.getText():currentUser.getEmail();

        if (!newPasswordTextField.getText().isEmpty()) {
            this.password = newPasswordTextField.getText();
            currentUser.setPassword(this.password);
        } else {
            this.password = currentUser.getPassword();
        }

        clear();

        currentUser.setName(this.name);
        currentUser.setPhoneNr(this.phoneNumber);
        currentUser.setEmail(this.email);
        currentUser.setPassword(this.password);

        userRepository.updateUser(currentUser);
        setScene();
    }

    private void clear() {
        newNameTextField.clear();
        newPhoneNumberTextField.clear();
        newPasswordTextField.clear();
        newEmailTextField.clear();
    }

    private void setScene() {
        try {
            App.setScene("MainPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertInformation() {
        User user = App.getUser();
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        phoneNumberField.setText(user.getPhoneNr());
    }


}
