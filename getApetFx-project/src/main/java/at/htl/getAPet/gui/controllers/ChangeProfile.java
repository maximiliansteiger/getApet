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


    private BooleanProperty codeIsRight = new SimpleBooleanProperty(false);
    private User currentUser;
    private DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
    private UserRepository userRepository = new UserDbRepository(factory.createDataSource());


    @FXML
    public void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        currentUser = App.getUser();
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
        String phoneNumber = (newPhoneNumberTextField.getText().isEmpty()) ? currentUser.getPhoneNr() : newPhoneNumberTextField.getText();
        String name = (newNameTextField.getText().isEmpty()) ? currentUser.getName() : newNameTextField.getText();
        String email = (newEmailTextField.getText().isEmpty()) ? currentUser.getEmail() : newEmailTextField.getText();

        String password = "";
        if (!newPasswordTextField.getText().isEmpty()) {
            password = newPasswordTextField.getText();
            currentUser.setPassword(password);
        } else {
            password = currentUser.getPassword();
        }

        clear();

        currentUser.setName(name);
        currentUser.setPhoneNr(phoneNumber);
        currentUser.setEmail(email);
        currentUser.setPassword(password);

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

}
