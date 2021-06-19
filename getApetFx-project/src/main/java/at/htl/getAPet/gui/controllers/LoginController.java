package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import at.htl.getAPet.model.datasource.DataSourceFactory;
import at.htl.getAPet.model.datasource.SimpleDataSourceFactory;
import at.htl.getAPet.model.login.LoginDbRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;

public class LoginController {


    @FXML
    public TextField loginNameField;
    @FXML
    public TextField loginPasswordField;
    @FXML
    public Button login_loginButton;
    @FXML
    public Button login_signUpButton;
    @FXML
    public LoginDbRepository loginDbRepository;
    public Label errorMessageLabel;


    @FXML
    private void initialize() {
        DataSourceFactory factory = new SimpleDataSourceFactory("get_a_pet");
        DataSource dataSource = factory.createDataSource();
        loginDbRepository = new LoginDbRepository(dataSource);

        login_signUpButton.setOnAction(actionEvent -> switchViewToSignUp());
        login_loginButton.setOnAction(actionEvent -> {

            if (loginDbRepository.login(loginNameField.getText(), loginPasswordField.getText())) {
                loginSuccess();
            } else {
                loginFailed();
            }

        });

    }

    private void loginFailed() {
        errorMessageLabel.setText("could not log in");
        loginPasswordField.clear();
    }


    private void switchViewToSignUp() {
        try {
            App.setScene("signUp");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    private void loginSuccess() {
        int value = (int) (10000 + (Math.random() * 999999));
        App.setCode(value);
        try {
            App.setScene("MainPage");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

}

