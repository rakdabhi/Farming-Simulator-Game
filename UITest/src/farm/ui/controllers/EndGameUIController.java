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
        Parent nextPage = FXMLLoader.load(getClass()
                .getResource("../style/ConfigurationScreenUI.fxml"));
        Scene nextPageScene = new Scene(nextPage);
        Stage window = (Stage) (newGameButton.getScene()).getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

}
