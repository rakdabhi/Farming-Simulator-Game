package farm.ui.controllers;

import clock.Clock;
import farmer.Farmer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;
import season.Season;
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
                                InventoryUIController invu, PlantInspectUIController piu,
                                int day, int hour) {
        this.farmer = f;
        this.season = s;
        this.plotu = plotu;
        this.invu = invu;
        this.piu = piu;
        this.day = day;
        this.hour = hour;
        setStartingLabels();
        timer = s.createTimer(dayLabel, hourLabel, ampmLabel, day, hour);
        setMoneyLabel(f.getMoney());
    }

    // |     Getters and Setters     |
    // |

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    void setMoneyLabel(double m) {
        moneyLabel.setText(String.format("$%,.2f", m));
    }

    public void setStartingLabels() {
        if (hour == 0) {
            dayLabel.setText(String.format("%02d", day));
            hourLabel.setText(String.format("%02d", 12));
            ampmLabel.setText("AM");
        } else if (hour < 12) {
            hourLabel.setText(String.format("%02d", hour));
            ampmLabel.setText("AM");
        } else if (hour == 12) {
            hourLabel.setText(String.format("%02d", hour));
            ampmLabel.setText("PM");
        } else {
            hourLabel.setText(String.format("%02d", hour % 12));
            ampmLabel.setText("PM");
        }
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