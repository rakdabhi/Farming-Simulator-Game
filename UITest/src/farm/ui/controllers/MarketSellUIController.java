package farm.ui.controllers;

import exceptions.SeedChoiceNotFoundException;
import farm.objects.Farmer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import farm.objects.Season;
import farm.objects.Seed;

import java.io.IOException;

public class MarketSellUIController {

    private String seedChoice;
    private double seedCost;
    private double appleSeedCost;
    private double potatoSeedCost;
    private double cornSeedCost;
    private int appleQuantity;
    private int potatoQuantity;
    private int cornQuantity;

    @FXML
    private Group appleImage;

    @FXML
    private Label appleQuantityLabel;

    @FXML
    private Group potatoImage;

    @FXML
    private Label potatoQuantityLabel;

    @FXML
    private Group cornImage;

    @FXML
    private Label cornQuantityLabel;

    @FXML
    private Button appleButton;

    @FXML
    private Button potatoButton;

    @FXML
    private Button cornButton;

    private Button[] cropArr = {appleButton, potatoButton, cornButton};

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button13;

    @FXML
    private Button button14;

    @FXML
    private Button button15;

    @FXML
    private Button button16;

    @FXML
    private AnchorPane rightPaneInventory;

    @FXML
    private Button homeScreenButton;

    @FXML
    private Group homeGraphic;

    @FXML
    private Label quantityLabel;

    @FXML
    private Button minusButton;

    @FXML
    private Text quantityMinusButton;

    @FXML
    private Button plusButton;

    @FXML
    private Text quantityAddButton;

    @FXML
    private Button sellButton;

    @FXML
    private Label itemName;

    @FXML
    private Label itemDescription;

    @FXML
    private Label totalCostTag;

    @FXML
    private Label totalCost;

    @FXML
    private Label bankTag;

    @FXML
    private Label bankAmount;

    @FXML
    private Label availableInventoryTag;

    @FXML
    private Label availableInventory;

    @FXML
    private Button buyTab;

    @FXML
    private Button sellTab;

    private Farmer farmer;

    private Season season;

    /**
     * This method initializes the market sell screen with a given Farmer and Season.
     * @param f the farmer
     * @param s the season
     */
    public void initMarketSell(Farmer f, Season s) {
        this.farmer = f;
        this.season = s;
        updateAvailableQuantity();
        updateBankAmount();
        updateAvailableCapacity();
        appleSeedCost = calculateSeedCost(2.89, 2.59);
        potatoSeedCost = calculateSeedCost(4.22, 2.33);
        cornSeedCost = calculateSeedCost(3.35, 3.36);
    }

    /**
     * This helper method helps generate the price of a particular seed using an algorithm.
     * @param basePrice the starting price of the seed
     * @param divideFactor divides the length of the season
     * @return the price of the seed
     */
    private double calculateSeedCost(double basePrice, double divideFactor) {
        return Math.round((basePrice + (season.getSeason().length() / divideFactor)
                + (Math.random() * Integer.parseInt(farmer.getExperienceLevel()))) * 100.0) / 100.0;
    }

    /**
     * This helper method helps display the current amount of money that the farmer has.
     */
    private void updateBankAmount() {
        bankAmount.setText(String.format("$%,.2f", farmer.getMoney()));
    }

    /**
     * This helper method helps display the current inventory capacity of this farmer.
     */
    private void updateAvailableCapacity() {
        availableInventory.setText("" + farmer.getInventory().getAvailableHarvestBagCapacity()
            + " crops");
    }

    /**
     * This helper method helps get the current quantities of salable
     * crops that the farmer object has.
     */
    private void getQuantities() {
        appleQuantity = farmer.getInventory().getHarvestBag()[0].getQuantity();
        potatoQuantity = farmer.getInventory().getHarvestBag()[1].getQuantity();
        cornQuantity = farmer.getInventory().getHarvestBag()[2].getQuantity();
    }
    /**
     * This helper method helps display the quantities of salable crops that this farmer has.
     */
    private void updateAvailableQuantity() {
        getQuantities();
        appleQuantityLabel.setText(String.format("x%02d", appleQuantity));
        potatoQuantityLabel.setText(String.format("x%02d", potatoQuantity));
        cornQuantityLabel.setText(String.format("x%02d", cornQuantity));
    }

    /**
     * This method helps the player switch from selling-mode in the market to buying-mode.
     * @param event the event
     * @throws IOException the exception that may be thrown
     */
    @FXML
    void handleBuyTab(ActionEvent event) throws IOException {
        FXMLLoader loadMarketBuy =
                new FXMLLoader(getClass().getResource("../style/MarketBuyUI.fxml"));
        Parent root = loadMarketBuy.load();
        MarketBuyUIController mbu = loadMarketBuy.getController();
        mbu.initMarketBuy(farmer, season);

        Scene nextPageScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    /**
     * This method helps the player reach the home screen when the Home Screen button is pressed.
     * @param event the event
     * @throws IOException the exception that may be thrown
     */
    @FXML
    void handleHomeScreenButton(ActionEvent event) throws IOException {
        FXMLLoader nextPage = new FXMLLoader(getClass().getResource("../style/PlotUI.fxml"));
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

        plotController.initPlotUI(farmer, season, mainPanelController,
                inventoryController, inspectController);
        mainPanelController.initMainPanelUI(farmer, season, plotController,
                inventoryController, inspectController);
        inventoryController.initInventoryUI(farmer, season, mainPanelController, plotController);
        inspectController.initPlantInspectUI(farmer, season, plotController, mainPanelController);

        Scene nextPageScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    /**
     * This method handles what happens when the Sell button is pressed.
     * @param event the event
     */
    @FXML
    void handleSellButton(ActionEvent event) {
        try {
            if (itemName.getText().equals("Item")) {
                throw new SeedChoiceNotFoundException("Please select a seed to sell!");
            } else if (quantityLabel.getText().equals("0")) {
                throw new SeedChoiceNotFoundException("You can't sell 0 seeds!");
            }

            int quantity = Integer.parseInt(quantityLabel.getText());
            farmer.getInventory().removeHarvest(new Seed(seedChoice), quantity);
            farmer.addMoney(seedCost * quantity);
            updateAvailableQuantity();
            updateAvailableCapacity();
            updateBankAmount();
            resetQuantityAndCostLabels();
        } catch (SeedChoiceNotFoundException s) {
            if (quantityLabel.getText().equals("0") && !itemName.getText().equals("Item")) {
                alertPopUp("Can't Sell On Market", s.getMessage());
            } else {
                alertPopUp("No Crop Chosen", s.getMessage());
            }
        }
    }
    /**
     * This method handles what happens when the minus button is pressed.
     * @param event the event
     */
    @FXML
    void handleMinusButton(ActionEvent event) {
        try {
            if (itemName.getText().equals("Item")) {
                throw new SeedChoiceNotFoundException("Please select a crop to sell!");
            }
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (quantity > 0) {
                quantityLabel.setText("" + (quantity - 1));
                totalCost.setText(String.format("$%,.2f", ((quantity - 1) * seedCost)));
            }
        } catch (SeedChoiceNotFoundException s) {
            alertPopUp("No Crop Chosen", s.getMessage());
        }
    }

    /**
     * This method handles what happens when the plus button is pressed.
     * @param event the event
     */
    @FXML
    void handlePlusButton(ActionEvent event) {
        try {
            if (itemName.getText().equals("Item")) {
                throw new SeedChoiceNotFoundException("Please select a seed to sell!");
            }
            int availableSeeds = 0;
            if (itemName.getText().equals("Apple")) {
                availableSeeds = Integer.parseInt(appleQuantityLabel.getText().substring(1));
            } else if (itemName.getText().equals("Potato")) {
                availableSeeds = Integer.parseInt(potatoQuantityLabel.getText().substring(1));
            } else if (itemName.getText().equals("Corn")) {
                availableSeeds = Integer.parseInt(cornQuantityLabel.getText().substring(1));
            }
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (availableSeeds > quantity) {
                quantityLabel.setText("" + (quantity + 1));
                totalCost.setText(String.format("$%,.2f", ((quantity + 1) * seedCost)));
            }
        } catch (SeedChoiceNotFoundException s) {
            alertPopUp("No Seed Chosen", s.getMessage());
        }
    }

    /**
     * This methods helps create an alert popup for the user if they don't
     * provide the proper parameters needed to proceed to the next game.
     * @param errorHeader the error header for the message
     * @param message the error message
     */
    private void alertPopUp(String errorHeader, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(errorHeader);
        a.setContentText(message);
        a.showAndWait();
    }

    /**
     * This helper method helps reset the quantity and cost labels.
     */
    private void resetQuantityAndCostLabels() {
        quantityLabel.setText("0");
        totalCost.setText("$0.00");
    }

    /**
     * This method helps give functionality to the buttons that hold the seed options.
     * @param event the event
     */
    @FXML
    void seedOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        resetQuantityAndCostLabels();
        if (btn == appleButton) {
            seedChoice = "Apple";
            seedCost = appleSeedCost;
            seedSelectActions(appleButton);
        } else if (btn == potatoButton) {
            seedChoice = "Potato";
            seedCost = potatoSeedCost;
            seedSelectActions(potatoButton);
        } else {
            seedChoice = "Corn";
            seedCost = cornSeedCost;
            seedSelectActions(cornButton);
        }
        itemName.setText(seedChoice);
        itemDescription.setText(String.format("The current selling price for "
                        + "one %s Seed in the %s Season is $%,.2f!",
                seedChoice, season.getSeason(), seedCost));
    }

    /**
     * This helper method helps set the opacity and clickability of a button.
     * @param selected the button that is selected
     */
    private void seedSelectActions(Button selected) {
        for (Button btn : cropArr) {
            if (btn != null) {
                if (btn == selected) {
                    btn.setOpacity(0.75);
                    btn.setDisable(true);
                } else {
                    btn.setOpacity(0.5);
                    btn.setDisable(false);

                }
            }
        }
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param event the event
     */
    @FXML
    void appleButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(appleImage);
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param event the event
     */
    @FXML
    void potatoButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(potatoImage);
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param event the event
     */
    @FXML
    void cornButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(cornImage);
    }

    /**
     * This method helps change the color of the button when it is pressed.
     * @param event the event
     */
    @FXML
    void seedRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setOpacity(0.5);
        ((Button) event.getSource()).setStyle("-fx-background-color: #E98737;"
                + " -fx-background-radius: 10");
    }

    /**
     * This method helps change the color of the button when it is released.
     * @param event the event
     */
    @FXML
    void seedRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #fd9e49;"
                + " -fx-background-radius: 10;");
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param event the event
     */
    @FXML
    void homeButtonMouseEnter(MouseEvent event) {
        lowerRightGraphicTransition(homeGraphic);
    }

    /**
     * This method helps change the color of the button when it is pressed.
     * @param event the event
     */
    @FXML
    void lowerRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15936f;"
                + " -fx-background-radius: 10");
    }

    /**
     * This method helps change the color of the button when it is released.
     * @param event the event
     */
    @FXML
    void lowerRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15ad86;"
                + " -fx-background-radius: 10;");
    }

    /**
     * This method helps change the color of the button when it is pressed.
     * @param event the event
     */
    @FXML
    void homeMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #15936f;"
                + " -fx-background-radius: 10");
    }

    /**
     * This method helps change the color of the button when it is released.
     * @param event the event
     */
    @FXML
    void homeMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #22d2a3;"
                + " -fx-background-radius: 10;");
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param gr the group
     */
    void lowerRightGraphicTransition(Group gr) {
        RotateTransition rt = new RotateTransition(new Duration(200), gr);
        rt.setFromAngle(0);
        rt.setToAngle(5);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        rt.play();
    }

}