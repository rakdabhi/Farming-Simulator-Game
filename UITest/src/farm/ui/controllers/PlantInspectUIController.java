package farm.ui.controllers;

import farm.objects.Crop;
import farmer.Farmer;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import season.Season;

public class PlantInspectUIController {

    @FXML
    private AnchorPane rightPaneInspect;

    @FXML
    private Pane upperPane;

    @FXML
    private Circle growthStage1;

    @FXML
    private Circle growthStage2;

    @FXML
    private Circle growthStage3;

    @FXML
    private Group waterMeter;

    @FXML
    private SVGPath darkWaterLevel;

    @FXML
    private SVGPath lightWaterLevel;

    @FXML
    private Group plantPlotGraphic;

    @FXML
    private Label plantNameLabel;

    @FXML
    private Button waterButton;

    @FXML
    private Button sowButton;

    @FXML
    private Button homeButton;

    @FXML
    private Group waterGraphic;

    @FXML
    private Group homeGraphic;

    @FXML
    private Group sowGraphic;

    @FXML
    private Group growthGraphicPotato;

    @FXML
    private Group growthGraphicCorn;

    @FXML
    private Group growthGraphicApple;

    @FXML
    private SVGPath growthGraphic0;

    @FXML
    private SVGPath growthGraphic1;

    @FXML
    private SVGPath testPath;

    private Farmer farmer;

    private Season season;

    private PlotUIController plotu;

    private MainPanelUIController mpu;

    private static boolean waterPress;

    private static boolean sowPress;

    public static boolean getWaterPress() {
        return waterPress;
    }

    public static boolean getSowPress() {
        return sowPress;
    }

    AnchorPane getRightPaneInspect() {
        return rightPaneInspect;
    }

    // |     Initialize Settings     |
    // |                             |

    public void initPlantInspectUI(Farmer f, Season s,
                                PlotUIController plotu, MainPanelUIController mpu) {
        this.farmer = f;
        this.season = s;
        this.plotu = plotu;
        this.mpu = mpu;

        growthGraphic0.setVisible(false);
        growthGraphic1.setVisible(false);
        growthGraphicCorn.setVisible(false);
        growthGraphicPotato.setVisible(false);
        growthGraphicApple.setVisible(false);

        waterMeterAnimation();
    }

    void setPlantNameLabel(String s) {
        plantNameLabel.setText(s);
    }

    void setGrowthMeter(Crop c) {

        if (c == null) {
            growthStage1.setFill(Color.web("#ff9f43"));
            growthStage2.setFill(Color.web("#ff9f43"));
            growthStage3.setFill(Color.web("#ff9f43"));

            growthGraphic0.setVisible(false);
            growthGraphic1.setVisible(false);
            growthGraphicCorn.setVisible(false);
            growthGraphicPotato.setVisible(false);
            growthGraphicApple.setVisible(false);

            return;
        }

        int stage = c.getGrowthStage();

        if (stage == 0) {
            growthStage1.setFill(Color.web("#15ad86"));
            growthStage2.setFill(Color.web("#ff9f43"));
            growthStage3.setFill(Color.web("#ff9f43"));

            growthGraphic0.setVisible(true);
            growthGraphic1.setVisible(false);
            growthGraphicCorn.setVisible(false);
            growthGraphicPotato.setVisible(false);
            growthGraphicApple.setVisible(false);

        } else if (stage == 1) {
            growthStage1.setFill(Color.web("#15ad86"));
            growthStage2.setFill(Color.web("#15ad86"));
            growthStage3.setFill(Color.web("#ff9f43"));

            growthGraphic0.setVisible(false);
            growthGraphic1.setVisible(true);
            growthGraphicCorn.setVisible(false);
            growthGraphicPotato.setVisible(false);
            growthGraphicApple.setVisible(false);
        } else {
            growthStage1.setFill(Color.web("#15ad86"));
            growthStage2.setFill(Color.web("#15ad86"));
            growthStage3.setFill(Color.web("#15ad86"));

            growthGraphic0.setVisible(false);
            growthGraphic1.setVisible(false);
            growthGraphicCorn.setVisible(false);
            growthGraphicPotato.setVisible(false);
            growthGraphicApple.setVisible(false);

            if (c.getSeed().getName().equals("Corn")) {
                growthGraphicCorn.setVisible(true);
            } else if (c.getSeed().getName().equals("Potato")) {
                growthGraphicPotato.setVisible(true);
            } else if (c.getSeed().getName().equals("Apple")) {
                growthGraphicApple.setVisible(true);
            }
        }
    }

    void setWaterMeter(int i) {
        if (i == 0) {
            lightWaterLevel.setLayoutY(147);
            darkWaterLevel.setLayoutY(142);
        } else if (i == 1) {
            lightWaterLevel.setLayoutY(119);
            darkWaterLevel.setLayoutY(116);
        } else if (i == 2) {
            lightWaterLevel.setLayoutY(70);
            darkWaterLevel.setLayoutY(67);
        } else if (i == 3) {
            lightWaterLevel.setLayoutY(35);
            darkWaterLevel.setLayoutY(32);
        } else if (i == 4) {
            lightWaterLevel.setLayoutY(3);
            darkWaterLevel.setLayoutY(0);
        }
    }


    @FXML
    void interact(MouseEvent event) {

    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        plotu.setRightPaneWrapper(mpu.getRightPaneMain());
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
    void homeButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(homeGraphic);
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

    void waterMeterAnimation() {

       /* PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setPath(testPath);
        pt.setNode(lightWaterLevel);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.setAutoReverse(true);

        pt.play();
        */

    }

}
