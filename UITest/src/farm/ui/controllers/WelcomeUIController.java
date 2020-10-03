package farm.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controls Welcome Screen events.
 */
public class WelcomeUIController {
    public void keyPress(KeyEvent event) throws Exception {
        Parent nextPage = FXMLLoader.load(getClass()
                .getResource("../style/ConfigurationScreenUI.fxml"));
        Scene nextPageScene = new Scene(nextPage);
        Stage window = (Stage) ((Scene) event.getSource()).getWindow();
        window.setScene(nextPageScene);
        window.show();
    }
}
