package farm.ui.controllers;

import farm.objects.Farmer;
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
import farm.objects.Season;

import java.io.IOException;

public class MainPanelUIOldController {

    @FXML
    private AnchorPane rightPaneMain;

    @FXML
    private Arc sunGraphic;

    @FXML
    private Label dayLabel;

    @FXML
    private Group pigGroup;

    @FXML
    private Label moneyLabel;

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

    private PlotUIController plotu;

    private InventoryUIController invu;

    private static boolean waterPress;

    private static boolean sowPress;

    // |     Initialize Settings     |
    // |                             |

    public void initMainPanelUI(Farmer f, Season s,
                                PlotUIController plotu, InventoryUIController invu) {
        this.farmer = f;
        this.season = s;
        this.plotu = plotu;
        this.invu = invu;

        setMoneyLabel(f.getMoney());
    }

    // |     Getters and Setters     |
    // |                             |

    public static boolean getWaterPress() {
        return waterPress;
    }

    public static boolean getSowPress() {
        return sowPress;
    }

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
        String notHighlighted = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        if (waterPress) {
            waterButton.setStyle(notHighlighted);
            waterPress = false;
        } else if (sowPress) {
            sowButton.setStyle(notHighlighted);
            sowPress = false;
        }
        plotu.setRightPaneWrapper(invu.getRightPaneInventory());
    }

    @FXML
    void handleSowAndWaterButton(MouseEvent event) {
        Button btn = ((Button) event.getSource());
        String notHighlighted = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String highlighted = "-fx-background-color: #15936f; -fx-background-radius: 10";
        if (btn == waterButton) {
            if (!waterPress) {
                waterButton.setStyle(highlighted);
                sowButton.setStyle(notHighlighted);
                sowPress = false;
            } else {
                waterButton.setStyle(notHighlighted);
            }
            waterPress = !waterPress;
        } else if (btn == sowButton) {
            if (!sowPress) {
                waterButton.setStyle(notHighlighted);
                sowButton.setStyle(highlighted);
                waterPress = false;
            } else {
                sowButton.setStyle(notHighlighted);
            }
            sowPress = !sowPress;
        }

    }

    @FXML
    void inventoryButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(inventoryGraphic);
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

    @FXML
    void sowButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(sowGraphic);

    }

    @FXML
    void waterButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(waterGraphic);

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