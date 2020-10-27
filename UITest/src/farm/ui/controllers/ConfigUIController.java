package farm.ui.controllers;

import exceptions.DifficultyLevelChoiceNotFoundException;
import exceptions.FarmerNameNotFoundException;
import exceptions.SeasonChoiceNotFoundException;
import exceptions.SeedChoiceNotFoundException;
import farm.objects.Farmer;
import farm.objects.Season;
import farm.objects.Seed;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.Optional;

public class ConfigUIController {

    @FXML
    private HBox configurationScreen;

    @FXML
    private TextField inputName;

    @FXML
    private Circle handRight;

    @FXML
    private Circle head;

    @FXML
    private Circle handLeft;

    @FXML
    private Button skinLight;

    @FXML
    private Button skinTan;

    @FXML
    private Button skinDark;

    @FXML
    private Button appleButton;

    @FXML
    private Button potatoButton;

    @FXML
    private Button cornButton;

    @FXML
    private Button springButton;

    @FXML
    private Button summerButton;

    @FXML
    private Button fallButton;

    @FXML
    private Button winterButton;

    @FXML
    private Button difficultyOne;

    @FXML
    private Button difficultyTwo;

    @FXML
    private Button difficultyThree;

    @FXML
    private Button startButton;

    private String difficultyChoice = "0";

    private String seasonChoice = "";

    private String seedChoice = "";

    private String customSkin = "#efdfbf";

    private static Seed seedSelect = new Seed(null);

    /**
     * This method helps give a button functionality to the seed-choice options.
     * @param event the event
     */
    @FXML
    void seedOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        String oldStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String newStyle = "-fx-background-color: #15936f; -fx-background-radius: 10";
        if (btn == appleButton) {
            setSeedandSytle("Apple", newStyle, oldStyle,
                    appleButton, potatoButton, cornButton);
        } else if (btn == potatoButton) {
            setSeedandSytle("Potato", newStyle, oldStyle,
                    potatoButton, appleButton, cornButton);
        } else {
            setSeedandSytle("Corn", newStyle, oldStyle,
                    cornButton, potatoButton, appleButton);
        }
    }

    /**
     * This helper method helps set the style of a button and sets the
     * seed choice to the selected seed's name.
     * @param seedChoiceName the name of the seed
     * @param newStyle the new style
     * @param oldStyle the old style
     * @param withNewStyle the button with the new style
     * @param withOldStyle1 the button with the old style
     * @param withOldStyle2 the button with the old style
     */
    private void setSeedandSytle(String seedChoiceName, String newStyle, String oldStyle,
                                 Button withNewStyle, Button withOldStyle1, Button withOldStyle2) {
        seedChoice = seedChoiceName;
        withNewStyle.setStyle(newStyle);
        withOldStyle1.setStyle(oldStyle);
        withOldStyle2.setStyle(oldStyle);
    }

    /**
     * This method helps give a button functionality to the season-choice options.
     * @param event the event
     */
    @FXML
    void seasonOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        seasonChoice = btn.getText().strip();

        String springStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10;";
        String summerStyle = "-fx-background-color: #fcca5f; -fx-background-radius: 10;";
        String fallStyle = "-fx-background-color: #fd676b; -fx-background-radius: 10;";
        String winterStyle = "-fx-background-color: #51dcfa; -fx-background-radius: 10;";

        if (btn == springButton) {
            springButton.setStyle(springStyle + " -fx-border-color: white; -fx-border-radius: 10");
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle);
        } else if (btn == summerButton) {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle + " -fx-border-color: white; -fx-border-radius: 10");
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle);
        } else if (btn == fallButton) {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle  + " -fx-border-color: white; -fx-border-radius: 10");
            winterButton.setStyle(winterStyle);
        } else {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle + " -fx-border-color: white; -fx-border-radius: 10");
        }
    }

    /**
     * This method helps give a button functionality to the difficulty-level choices.
     * @param event the event
     */
    @FXML
    void difficultyOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        String oldStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String newStyle = "-fx-background-color: #15936f; -fx-background-radius: 10";
        difficultyChoice = btn.getText().strip();
        if (btn == difficultyOne) {
            difficultyOne.setStyle(newStyle);
            difficultyTwo.setStyle(oldStyle);
            difficultyThree.setStyle(oldStyle);
        } else if (btn == difficultyTwo) {
            difficultyOne.setStyle(oldStyle);
            difficultyTwo.setStyle(newStyle);
            difficultyThree.setStyle(oldStyle);
        } else {
            difficultyOne.setStyle(oldStyle);
            difficultyTwo.setStyle(oldStyle);
            difficultyThree.setStyle(newStyle);
        }


    }

    /**
     * This method helps change the skin of the farmer to the player's new choice.
     * @param event the event
     */
    @FXML
    void skinChange(ActionEvent event) {
        customSkin = ((Button) event.getSource()).getText().strip();
        head.setFill(Paint.valueOf(customSkin));
        handLeft.setFill(Paint.valueOf(customSkin));
        handRight.setFill(Paint.valueOf(customSkin));

    }

    /**
     * This method will help create functionality to the Start button
     * and only allow the user to proceed to the next screen if the proper
     * conditions are met.
     * @param event the event
     * @throws Exception an Exception that may be thrown
     */
    public void startButtonOnAction(ActionEvent event) throws Exception {
        String farmerName = inputName.getCharacters().toString();
        String difficultyLevel = difficultyChoice;

        try {

            if ((farmerName.equals("")) || (farmerName.strip().equals(""))) {
                throw new FarmerNameNotFoundException();
            }
            if (seedChoice.length() == 0) {
                throw new SeedChoiceNotFoundException();
            }

            if (seasonChoice.equals("")) {
                throw new SeasonChoiceNotFoundException();
            }

            if (difficultyLevel.equals("0")) {
                throw new DifficultyLevelChoiceNotFoundException();
            }

            Farmer farmer1 = new Farmer(farmerName, difficultyLevel, customSkin);
            farmer1.addSeed(new Seed(seedChoice));
            Season startingSeason = new Season(seasonChoice);

            FXMLLoader nextPage =
                    new FXMLLoader(getClass().getResource("../style/PlotUI.fxml"));
            Parent root = nextPage.load();
            PlotUIController plotController = nextPage.getController();

            FXMLLoader loadMain =
                    new FXMLLoader(getClass().getResource("../style/MainPanelUI.fxml"));
            loadMain.load();
            MainPanelUIController mainPanelController = loadMain.getController();

            FXMLLoader loadInventory =
                    new FXMLLoader(getClass().getResource("../style/InventoryUI.fxml"));
            loadInventory.load();
            InventoryUIController inventoryController = loadInventory.getController();

            FXMLLoader loadInspect =
                    new FXMLLoader(getClass().getResource("../style/PlantInspectUI.fxml"));
            loadInspect.load();
            PlantInspectUIController inspectController = loadInspect.getController();

            plotController.initPlotUI(farmer1, startingSeason,
                    mainPanelController, inventoryController, inspectController);
            mainPanelController.initMainPanelUI(farmer1, startingSeason,
                    plotController, inventoryController, inspectController, 1, 8);
            inventoryController.initInventoryUI(farmer1, startingSeason,
                    mainPanelController, plotController);
            inspectController.initPlantInspectUI(farmer1, startingSeason,
                    plotController, mainPanelController);

            Scene nextPageScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextPageScene);
            window.show();

        } catch (FarmerNameNotFoundException f) {
            alertPopUp("Invalid Name Input", f.getMessage());
        } catch (SeedChoiceNotFoundException s) {
            alertPopUp("No Seed Chosen", s.getMessage());
        } catch (SeasonChoiceNotFoundException u) {
            alertPopUp("No Season Chosen", u.getMessage());
        } catch (DifficultyLevelChoiceNotFoundException d) {
            alertPopUp("No Difficulty Chosen", d.getMessage());
        }
    }

    /**
     * This methods helps create an alert popup for the user if they don't
     * provide the proper parameters needed to proceed to the next game.
     * @param errorHeader the error header for the message
     * @param message the error message
     */
    static void alertPopUp(String errorHeader, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(errorHeader);
        a.setContentText(message);
        a.showAndWait();
    }

    /**
     * This methods helps create an alert popup for the user to
     * select which seed they want to plant.
     */
    static boolean alertPopUp(Farmer farmer) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Seed Select");
        a.setHeaderText("Which seed do you want to plant?");
        a.setContentText("Choose one:");
        ButtonType buttonTypeOne =
                new ButtonType("Apple: x" + farmer.getSeedBag()[0].getQuantity());
        ButtonType buttonTypeTwo =
                new ButtonType("Potato: x" + farmer.getSeedBag()[1].getQuantity());
        ButtonType buttonTypeThree =
                new ButtonType("Corn: x" + farmer.getSeedBag()[2].getQuantity());
        ButtonType buttonTypeCancel =
                new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
        if (farmer.getSeedBag()[0].getQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeOne).setDisable(true);
        }
        if (farmer.getSeedBag()[1].getQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeTwo).setDisable(true);
        }
        if (farmer.getSeedBag()[2].getQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeThree).setDisable(true);
        }

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == buttonTypeOne){
            seedSelect.setName("Apple");
            return true;
        } else if (result.get() == buttonTypeTwo) {
            seedSelect.setName("Potato");
            return true;
        } else if (result.get() == buttonTypeThree) {
            seedSelect.setName("Corn");
            return true;
        } else {
            return false;
        }
    }

    /**
     * This getter method returns which seed has been selected.
     * @return the seed
     */
    public static Seed getSeedSelect() {
        return seedSelect;
    }
}