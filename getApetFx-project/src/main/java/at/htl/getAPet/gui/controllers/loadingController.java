package at.htl.getAPet.gui.controllers;

import at.htl.getAPet.gui.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.ImageView;
import javafx.scene.effect.GaussianBlur;
public class loadingController {

    @FXML
    public Label startLabel;
    @FXML
    public Label loadingLabel;
    @FXML
    public Label getAPetLabel;
    @FXML
    public ProgressBar progressBar;
    public Button toLoginButton;
    public ImageView imageView;
    Timer timer = new Timer();
    private double value = 0;

    @FXML
    private void initialize() throws InterruptedException {
        startLabel.setVisible(false);
        progressBar.setProgress(0.0);

        int time = 1000;

        progress(0.1, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.2, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.3, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.4, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.5, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.6, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.7, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.8, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(0.9, time += Math.floor(Math.random() * (300 - 500 + 1) + 500));
        progress(1,   time += Math.floor(Math.random() * (300 - 500 + 1) + 500));

    }


    private void progress(double value, int time) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progressBar.setProgress(value);

                if (value == 1) {
                    toLoginButton.setOnAction(actionEvent -> changeSceneToLogin());
                    startLabel.setVisible(true);
                    loadingLabel.setVisible(false);
                    getAPetLabel.setVisible(false);
                    progressBar.setVisible(false);
                    GaussianBlur blur = new GaussianBlur();
                    imageView.setEffect(blur);

                }


                System.out.println(value);
            }
        }, time);
        this.value = value;

        changeSceneToLogin();
    }


    private void changeSceneToLogin() {
        try {
            App.setScene("login");
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }


}
