package farm.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameUIController {

    @FXML
    private Button newGameButton;

    @FXML
    void handleNewGame(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../style/StartScreenUI.fxml"));
        Parent root = loader.load();
        WelcomeUIController w = loader.getController();
        Stage window = (Stage) (newGameButton.getScene()).getWindow();

        Scene startScene = new Scene(root);
        window.setScene(startScene);
        window.show();

        w.gameRestart();

    }

}
