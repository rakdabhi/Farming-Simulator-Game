package farm.ui;

import farm.ui.controllers.WelcomeUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("style/StartScreenUI.fxml"));
        Parent root = loader.load();
        WelcomeUIController w = loader.getController();
        primaryStage.setTitle("Pro Farming Simulator 2");
        Scene startScene = new Scene(root);
        primaryStage.setScene(startScene);
        primaryStage.show();

        w.initWelcome();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
