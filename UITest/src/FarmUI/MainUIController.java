package FarmUI;

import Farmer.Farmer;
import Season.Season;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.util.Duration;


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
    private Label moneyLabel;

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
    private Group sowGraphic;

    private Farmer farmer;

    private Season season;

    void setMoneyLabel(int m) {
        moneyLabel.setText("$" + m);
    }

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
    void inventoryButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(inventoryGraphic);
    }

    @FXML
    void sowButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(sowGraphic);
    }

    @FXML
    void waterButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(waterGraphic);
    }


    @FXML
    void lowerRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15936f; -fx-background-radius: 10");
    }

    @FXML
    void lowerRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15ad86; -fx-background-radius: 10;");

    }

    void lowerRightGraphicTransition(Group gr) {
        RotateTransition rt = new RotateTransition(new Duration(200), gr);
        rt.setFromAngle(0);
        rt.setToAngle(5);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        rt.play();
    }


    void setFarmer(Farmer f) {
        farmer = f;
    }

    void setSeason(Season s) {
        season = s;
    }


}


