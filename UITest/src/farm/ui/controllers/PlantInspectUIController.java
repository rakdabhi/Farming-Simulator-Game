
package farm.ui.controllers;

import farm.objects.Crop;
import farm.objects.Farmer;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import farm.objects.Season;

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
    private Group growthGraphic0;

    @FXML
    private Group growthGraphic1;

    @FXML
    private Group growthGraphicGrave;

    @FXML
    private Button plantTreatmentButton;

    @FXML
    private Label fertilizerLabel;

    private Farmer farmer;

    private Season season;

    private PlotUIController plotu;

    private MainPanelUIController mpu;

    @FXML
    private Group waterLevelGFX;

    // Variables used in the water level animations //
    // ___________________________________________//
    private double centerY;

    private double currentWaterLevel = 0;
    // ___________________________________________//

    private static boolean waterPress;

    private static boolean sowPress;

    private static boolean treatmentPress;

    public static boolean getWaterPress() {
        return waterPress;
    }

    public static boolean getSowPress() {
        return sowPress;
    }

    public static boolean getTreatmentPress() {
        return treatmentPress;
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
        growthGraphicGrave.setVisible(false);

        waterPress = false;
        sowPress = false;
        treatmentPress = false;

        centerY = ((Bounds) waterLevelGFX.getLayoutBounds()).getCenterY();

        setFertilizerLabel(0);

    }

    void setPlantNameLabel(String s) {
        plantNameLabel.setText(s);
    }

    void setGrowthMeter(Crop c) {
        Color unfilled = Color.web("#ff9f43");
        Color filled = Color.web("#15ad86");
        Group[] gfx = {growthGraphic0, growthGraphic1, growthGraphicCorn,
            growthGraphicApple, growthGraphicPotato, growthGraphicGrave};
        Circle[] meter = {growthStage1, growthStage2, growthStage3};

        if (c == null || c.isDead()) {
            for (Circle circle : meter) {
                circle.setFill(unfilled);
            }

            for (Group graphic : gfx) {
                graphic.setVisible(false);
            }

            if (c != null && c.isDead()) {
                growthGraphicGrave.setVisible(true);
            }

            return;
        }

        int stage = c.getGrowthStage();

        if (stage == 0) {
            for (Circle circle : meter) {
                if (circle == growthStage1) {
                    circle.setFill(filled);
                } else {
                    circle.setFill(unfilled);
                }
            }

            for (Group graphic : gfx) {
                if (graphic == growthGraphic0) {
                    graphic.setVisible(true);
                } else {
                    graphic.setVisible(false);
                }
            }


        } else if (stage == 1) {
            for (Circle circle : meter) {
                if (circle != growthStage3) {
                    circle.setFill(filled);
                } else {
                    circle.setFill(unfilled);
                }
            }

            for (Group graphic : gfx) {
                if (graphic == growthGraphic1) {
                    graphic.setVisible(true);
                } else {
                    graphic.setVisible(false);
                }
            }

        } else {
            for (Circle circle : meter) {
                circle.setFill(filled);
            }

            for (Group graphic : gfx) {
                graphic.setVisible(false);
            }

            switch (c.getSeed().getName()) {
            case "Corn":
                growthGraphicCorn.setVisible(true);
                break;
            case "Potato":
                growthGraphicPotato.setVisible(true);
                break;
            case "Apple":
                growthGraphicApple.setVisible(true);
                break;
            default:
                break;
            }
        }
    }

    @FXML
    void interact(MouseEvent event) {

    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        plotu.setRightPaneWrapper(mpu.getRightPaneMain());
        plotu.setSelectedPlot(null);
        sowPress = false;
        waterPress = false;
        treatmentPress = false;
        waterButton.setStyle("-fx-background-color: #15ad86; -fx-background-radius: 10");
        sowButton.setStyle("-fx-background-color: #15ad86; -fx-background-radius: 10");
        plantTreatmentButton.setStyle("-fx-background-color: #15ad86; -fx-background-radius: 10");
    }

    @FXML
    void handleFarmToolButtons(MouseEvent event) {
        Button btn = ((Button) event.getSource());
        String notHighlighted = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String highlighted = "-fx-background-color: #15936f; -fx-background-radius: 10";
        if (btn == waterButton) {
            if (!waterPress) {
                waterButton.setStyle(highlighted);
                sowButton.setStyle(notHighlighted);
                plantTreatmentButton.setStyle(notHighlighted);
                sowPress = false;
                treatmentPress = false;
            } else {
                waterButton.setStyle(notHighlighted);
            }
            waterPress = !waterPress;
        } else if (btn == sowButton) {
            if (!sowPress) {
                waterButton.setStyle(notHighlighted);
                sowButton.setStyle(highlighted);
                plantTreatmentButton.setStyle(notHighlighted);
                waterPress = false;
                treatmentPress = false;
            } else {
                sowButton.setStyle(notHighlighted);
            }
            sowPress = !sowPress;
        } else if (btn == plantTreatmentButton) {
            if (!treatmentPress) {
                waterButton.setStyle(notHighlighted);
                sowButton.setStyle(notHighlighted);
                plantTreatmentButton.setStyle(highlighted);
                waterPress = false;
                sowPress = false;
            } else {
                plantTreatmentButton.setStyle(notHighlighted);
            }
            treatmentPress = !treatmentPress;
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

    void setWaterMeter(int i) {
        double yShift = 0;

        switch (i) {
        case 0:
            yShift = 0;
            break;
        case 1:
            yShift = 30;
            break;
        case 2:
            yShift = 70;
            break;
        case 3:
            yShift = 110;
            break;
        case 4:
            yShift = 140;
            break;
        default:
            break;
        }

        double temp = currentWaterLevel;
        currentWaterLevel = yShift;
        yShift = yShift - temp;
        waterMeterAnimation(yShift);
    }

    void setFertilizerLabel(int i) {
        fertilizerLabel.setText("Fertilizer Level: " + i + "%");
    }

    void waterMeterAnimation(double y) {
        Bounds b = waterLevelGFX.getLayoutBounds();
        Line l = new Line();

        l.setStartX(b.getCenterX());
        l.setEndX(b.getCenterX());
        l.setStartY(centerY);
        l.setEndY(centerY - y);

        centerY = l.getEndY();

        if (y != 0) {
            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setPath(l);
            pt.setNode(waterLevelGFX);
            pt.setCycleCount(1);

            pt.play();
        }
    }

}