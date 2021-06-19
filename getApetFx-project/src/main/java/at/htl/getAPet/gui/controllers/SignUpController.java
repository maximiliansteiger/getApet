package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.SignUp.SignUpDbRepository;
import at.htl.getAPet.model.SignUp.SignUpRepository;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;

public class SignUpController {


    @FXML
    public TextField signupNameField;
    @FXML
    public TextField signupEmailField;
    @FXML
    public TextField signupPasswordField;
    @FXML
    public TextField signupTellNrButton;
    @FXML
    public Button signup_signupButton;
    @FXML
    public Button signup_loginButton;
    private SignUpRepository signUpRepository;

    @FXML
    private void initialize() {

        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        signUpRepository = new SignUpDbRepository(dataSource);

        signup_loginButton.setOnAction(actionEvent -> switchViewToLogin());

        signup_signupButton.setOnAction(actionEvent -> {
            if (signUpRepository.createAccount(signupNameField.getText(), signupEmailField.getText(),
                    signupPasswordField.getText(), signupTellNrButton.getText(), 0) != null) {
                switchViewToLogin();
            }
        });

    }


    private void switchViewToLogin() {
        try {
            App.setScene("login");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }
}
