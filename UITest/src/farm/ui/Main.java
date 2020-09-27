package farm.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScreenUI.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Farming Simulator");
        Scene startScene = new Scene(root);
        primaryStage.setScene(startScene);
        primaryStage.show();
        startScene.setOnKeyTyped(event -> {
            WelcomeUIController welcome = new WelcomeUIController();
            try {
                welcome.keyPress(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
