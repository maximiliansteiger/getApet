package at.htl.getAPet.gui;

import at.htl.getAPet.model.User.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Platform.exit;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage mainStage;
    public static User user;
    public static int code;

    @Override
    public void start(Stage stage) {
        try {
            mainStage = stage;
            setScene("loading");
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.show();
        } catch (Exception e) {
            handleException(e);
        }
        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> handleException(e));
    }

    public static void setScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent rootNode = fxmlLoader.load();
        mainStage.setScene(new Scene(rootNode));
        mainStage.resizableProperty().setValue(Boolean.FALSE);
//        mainStage.initStyle(StageStyle.UTILITY);


    }

    private void handleException(Throwable e) {
        e.printStackTrace();
        exit();
    }

    public static void main(String[] args) {
        launch();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    public static int getCode() {
        return App.code;
    }

    public static void setCode(int code) {
        App.code = code;
    }
}