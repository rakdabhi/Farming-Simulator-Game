package farm.ui.controllers;

import farm.objects.Clock;
import farm.objects.Farmer;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;
import farm.objects.Season;
import java.io.IOException;

public class MainPanelUIController {

    @FXML
    private AnchorPane rightPaneMain;

    @FXML
    private Arc sunGraphic;

    @FXML
    private Label dayTitleLabel;

    @FXML
    private Label dayLabel;

    @FXML
    private Label hourLabel;

    @FXML
    private Label minuteLabel;

    @FXML
    private Label ampmLabel;

    @FXML
    private Group pigGroup;

    @FXML
    private Label moneyLabel;

    @FXML
    private Button inventoryButton;

    @FXML
    private Group inventoryGraphic;

    @FXML
    private Group inspectGraphic;

    @FXML
    private Button nextDayButton;

    private Farmer farmer;

    private Season season;

    private PlotUIController plotu;

    private InventoryUIController invu;

    private PlantInspectUIController piu;

    private Clock timer;
    private int day;
    private int hour;


    // |     Initialize Settings     |
    // |                             |


    public void initMainPanelUI(Farmer f, Season s, PlotUIController plotu,
                                InventoryUIController invu, PlantInspectUIController piu) {
        this.farmer = f;
        this.season = s;
        this.plotu = plotu;
        this.invu = invu;
        this.piu = piu;
        timer = season.getTimer();

        setMoneyLabel(f.getMoney());

        hourLabel.textProperty().bind(timer.hourProperty().textProperty());
        dayLabel.textProperty().bind(Bindings.convert(timer.dayProperty()));
        ampmLabel.textProperty().bind(timer.amPmProperty().textProperty());
    }

    // |     Getters and Setters     |
    // |


    void setMoneyLabel(double m) {
        moneyLabel.setText(String.format("$%,.2f", m));
    }

    AnchorPane getRightPaneMain() {
        return rightPaneMain;
    }


    // |     Button Behavior         |
    // |                             |

    @FXML
    void handleInventoryButton(ActionEvent event) throws IOException {
        plotu.setRightPaneWrapper(invu.getRightPaneInventory());
    }

    @FXML
    void handleInspectButton(ActionEvent event) throws IOException {
        plotu.setRightPaneWrapper(piu.getRightPaneInspect());
    }

    @FXML
    void handleNextDayButton(ActionEvent event) {
        season.getTimer().advanceDay();
    }

    @FXML
    void inventoryButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(inventoryGraphic);
    }

    @FXML
    void inspectButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(inspectGraphic);
    }

    @FXML
    void lowerRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15936f;"
                + " -fx-background-radius: 10");
    }

    @FXML
    void lowerRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15ad86;"
                + " -fx-background-radius: 10;");
    }


    void lowerRightGraphicTransition(Group gr) {
        RotateTransition rt = new RotateTransition(new Duration(200), gr);
        rt.setFromAngle(0);
        rt.setToAngle(5);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        rt.play();
    }
}