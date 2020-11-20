package farm.ui.controllers;

import farm.objects.*;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class MainPanelUIController {

    @FXML
    private AnchorPane rightPaneMain;

    @FXML
    private Label dayLabel;

    @FXML
    private Label hourLabel;

    @FXML
    private Label ampmLabel;

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

    @FXML
    private Label farmhandStatus;

    @FXML
    Circle head;

    @FXML
    Circle handLeft;

    @FXML
    Circle handRight;

    @FXML
    Label nameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Rectangle seasonRectangle;

    @FXML
    private Group springGraphic;

    @FXML
    private Group summerGraphic;

    @FXML
    private Group fallGraphic;

    @FXML
    private Group winterGraphic;

    private Farmer farmer;

    private Season season;

    private PlotUIController plotu;

    private InventoryUIController invu;

    private PlantInspectUIController piu;

    private File file;

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

        updateFarmhandStatus();
        updateSeasonIcon();

        nameLabel.setText(farmer.getName());
        head.setFill(Paint.valueOf(farmer.getCustomSkin()));
        handLeft.setFill(Paint.valueOf(farmer.getCustomSkin()));
        handRight.setFill(Paint.valueOf(farmer.getCustomSkin()));

    }

    // |     Getters and Setters     |
    // |


    void setMoneyLabel(double m) {
        moneyLabel.setText(String.format("$%,.2f", m));
    }

    AnchorPane getRightPaneMain() {
        return rightPaneMain;
    }

    void updateFarmhandStatus(boolean unpaid) {
        farmhandStatus.setText("You didn't have enough money to pay your farmhand! "
            + "All harvests in inventory stolen.");
        farmhandStatus.setTextFill(Color.web("#ffffff"));
    }

    void updateFarmhandStatus() {
        Farmhand fh = farmer.getFarmhand();
        String s;
        String lvl;
        if (fh.isActive()) {
            if (fh.getSkillLevel() == 0) {
                lvl = "Amateur";
            } else {
                lvl = "Expert";
            }

            s = lvl + " farmhand is employed for " + fh.getDaysActive() + " more days.";

        } else {
            s = "No farmhands currently hired.";
        }
        farmhandStatus.setText(s);
        farmhandStatus.setTextFill(Color.web("#22d2a3"));
    }

    void updateSeasonIcon() {
        springGraphic.setVisible(false);
        summerGraphic.setVisible(false);
        fallGraphic.setVisible(false);
        winterGraphic.setVisible(false);

        switch(season.getSeason()) {
            case ("Spring"):
                springGraphic.setVisible(true);
                seasonRectangle.setFill(Color.web("#15ad86"));
                break;
            case ("Summer") :
                summerGraphic.setVisible(true);
                seasonRectangle.setFill(Color.web("#fcca5f"));
                break;
            case ("Fall"):
                fallGraphic.setVisible(true);
                seasonRectangle.setFill(Color.web("#fd676b"));
                break;
            case("Winter"):
                winterGraphic.setVisible(true);
                seasonRectangle.setFill(Color.web("#51dcfa"));
                break;
        }
    }


    // |     Button Behavior         |
    // |                             |

    @FXML
    void handleInventoryButton(ActionEvent event) throws IOException {
        plotu.setRightPaneWrapper(invu.getRightPaneInventory());
        invu.handleInvBtns(null);
    }

    @FXML
    void handleInspectButton(ActionEvent event) throws IOException {
        plotu.setRightPaneWrapper(piu.getRightPaneInspect());
        plotu.setHarvestWaterContainersVisible(true);
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

    @FXML
    void saveButtonMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #ff9f43;"
                + " -fx-background-radius: 10");
    }

    @FXML
    void saveButtonMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #feca57;"
                + " -fx-background-radius: 10;");
    }

    @FXML
    void handleSaveButton(ActionEvent e) {
        season.setSaveTime();
        SaveGame sg = new SaveGame(farmer, season);
        if (file == null) {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showSaveDialog(saveButton.getScene().getWindow());
        }
        sg.fileOut(file);
    }
}