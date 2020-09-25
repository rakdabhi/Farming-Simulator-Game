package FarmUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;

public class MainUIController {

    @FXML
    private Group plotGroup00;

    @FXML
    private Group plotGroup01;

    @FXML
    private Group plotGroup02;

    @FXML
    private Group plotGroup03;

    @FXML
    private Group plotGroup10;

    @FXML
    private Group plotGroup11;

    @FXML
    private Group plotGroup12;

    @FXML
    private Group plotGroup13;

    @FXML
    private Group plotGroup20;

    @FXML
    private Group plotGroup21;

    @FXML
    private Group plotGroup22;

    @FXML
    private Group plotGroup23;

    @FXML
    private Arc sunGraphic;

    @FXML
    private Label dayLabel;

    @FXML
    private Text moneyText;

    @FXML
    private Group pigGroup;


    @FXML
    private Button waterButton;

    @FXML
    private Button sowButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Group waterGraphic;

    @FXML
    private Group inventoryGraphic;

    @FXML
    private Group shovelGraphic;


    @FXML
    void handleInventoryButton(ActionEvent event) {

    }

    @FXML
    void handleSowButton(ActionEvent event) {

    }

    @FXML
    void handleWaterButton(ActionEvent event) {

    }

    @FXML
    void inventoryMousePress(MouseEvent event) {
        inventoryButton.setStyle("-fx-background-color: #5423b8; -fx-background-radius: 10");
    }

    @FXML
    void inventoryMouseRelease(MouseEvent event) {
        inventoryButton.setStyle("-fx-background-color: #5f27cd; -fx-background-radius: 10;");
    }

    @FXML
    void sowMousePress(MouseEvent event) {
        sowButton.setStyle("-fx-background-color: #5423b8; -fx-background-radius: 10");
    }

    @FXML
    void sowMouseRelease(MouseEvent event) {
        sowButton.setStyle("-fx-background-color: #5f27cd; -fx-background-radius: 10;");
    }

    @FXML
    void waterMousePress(MouseEvent event) {
        waterButton.setStyle("-fx-background-color: #5423b8; -fx-background-radius: 10");
    }

    @FXML
    void waterMouseRelease(MouseEvent event) {
        waterButton.setStyle("-fx-background-color: #5f27cd; -fx-background-radius: 10;");

    }

}


