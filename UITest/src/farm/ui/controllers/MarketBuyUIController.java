package farm.ui.controllers;

import exceptions.ExcessSeedBuyException;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;
import farm.objects.*;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class MarketBuyUIController {

    private Seed seedChoice;
    private InventoryItem itemChoice;
    private double seedCost;
    private double itemCost;
    private double appleSeedCost;
    private double potatoSeedCost;
    private double cornSeedCost;
    private double fertilizerCost;
    private double pesticideCost;
    private double waterCost = 10.00;
    private int appleQuantity;
    private int potatoQuantity;
    private int cornQuantity;
    private int fertilizerQuantity;
    private int pesticideQuantity;

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
    private Group pesticideImage;

    @FXML
    private Label pesticideQuantityLabel;

    @FXML
    private Group fertilizerImage;

    @FXML
    private Label fertilizerQuantityLabel;

    @FXML
    private Group waterGraphic;

    @FXML
    private Button appleButton;

    @FXML
    private Button potatoButton;

    @FXML
    private Button cornButton;

    @FXML
    private Button hireButton;

    @FXML
    private Button button4;

    @FXML
    private Button pesticideButton;

    @FXML
    private Button fertilizerButton;

    @FXML
    private Button waterButton;

    @FXML
    private Group hireImage;

    @FXML
    private Group plotImage;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button10;

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
    private Group tractorGraphic;

    @FXML
    private Button minusButton;

    @FXML
    private Text quantityMinusButton;

    @FXML
    private Button plusButton;

    @FXML
    private Text quantityAddButton;

    @FXML
    private Button buyButton;

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
    private Pane itemPane;

    @FXML
    private Pane farmhandPane;

    @FXML
    private Button amateurButton;

    @FXML
    private Button expertButton;

    @FXML
    private Label farmhandDescription;

    @FXML
    private Button finishHireButton;

    @FXML
    private Label totalCostFarmhand;

    @FXML
    private Button dayPlusButton;

    @FXML
    private Button dayMinusButton;

    @FXML
    private Label dayCountLabel;

    @FXML
    private Button fieldsButton;

    @FXML
    private Button tractorButton;

    private Button selectedFarmhandExperience = null;

    @FXML
    private Button buyTab;

    @FXML
    private Button sellTab;

    @FXML
    private Button buyFieldSpaceButton;

    private Farmer farmer;

    private Season season;


    /**
     * This constructor initializes a buying market screen for the game.
     * @param f the farmer that want to buy seeds
     * @param s the season that the farmer is farming in
     */
    public void initMarketBuy(Farmer f, Season s) {
        Random rand = new Random();
        initMarketBuy(f, s, rand.nextInt(6) + 10,
                rand.nextInt(6) + 10, rand.nextInt(6) + 10,
                rand.nextInt(6) + 10,
                rand.nextInt(6) + 10);
    }

    public void initMarketBuy(Farmer f, Season s, int appleQuantity, int potatoQuantity,
                              int cornQuantity, int pesticideQuantity, int fertilizerQuantity) {
        Random rand = new Random();
        this.appleQuantity = appleQuantity;
        this.potatoQuantity = potatoQuantity;
        this.cornQuantity = cornQuantity;
        this.pesticideQuantity = pesticideQuantity;
        this.fertilizerQuantity = fertilizerQuantity;

        this.farmer = f;
        this.season = s;
        this.seedChoice = null;
        updateAvailableQuantity();
        updateBankAmount();
        updateAvailableCapacity();
        updateAvailableItemQuantity();
        appleSeedCost = calculateSeedCost(new Seed("Apple").getBaseSell(), 2.59);
        potatoSeedCost = calculateSeedCost(new Seed("Potato").getBaseSell(), 2.33);
        cornSeedCost = calculateSeedCost(new Seed("Corn").getBaseSell(), 3.36);
        fertilizerCost = calculateFertilizerCost();
        pesticideCost = calculatePesticideCost();
        waterCost = calculateWaterCost();
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
     * This helper method generates the price of fertilizer, depending on the current difficulty.
     *
     * @return the cost of fertilizer
     */
    private double calculateFertilizerCost() {
        switch (farmer.getExperienceLevel()) {
        case "1":
            fertilizerCost = .5 * 3.49;
            break;
        case "2":
            fertilizerCost = 3.49;
            break;
        case "3":
            fertilizerCost = 1.5 * 3.49;
            break;
        default:
            fertilizerCost = 6.00;
            break;
        }
        return fertilizerCost;
    }

    /**
     * This helper method generates the price of pesticide, depending on the current difficulty.
     *
     * @return the cost of pesticide
     */
    private double calculatePesticideCost() {
        switch (farmer.getExperienceLevel()) {
        case "1":
            pesticideCost = .5 * 5.53;
            break;
        case "2":
            pesticideCost = 5.53;
            break;
        case "3":
            pesticideCost = 1.5 * 5.53;
            break;
        default:
            pesticideCost = 10.00;
            break;
        }
        return pesticideCost;
    }

    /**
     * This helper method generates the price of water, depending on the current difficulty.
     *
     * @return the cost of water
     */
    private double calculateWaterCost() {
        switch (farmer.getExperienceLevel()) {
        case "1":
            waterCost = 3.25;
            break;
        case "2":
            waterCost = 4.15;
            break;
        case "3":
            waterCost = 5.75;
            break;
        default:
            waterCost = 10.00;
            break;
        }
        return waterCost;
    }

    /**
     * This helper method helps display the current amount of money that the farmer has.
     */
    private void updateBankAmount() {
        bankAmount.setText(String.format("$%,.2f",
            farmer.getMoney()));
    }

    /**
     * This helper method helps display the current inventory capacity of this farmer.
     */
    private void updateAvailableCapacity() {
        availableInventory.setText("" + farmer.getInventory().getAvailableSeedBagCapacity()
            + " seeds");
    }

    /**
     * This helper method helps display the quantity of seeds that this farmer has.
     */
    private void updateAvailableQuantity() {
        appleQuantityLabel.setText(String.format("x%02d", appleQuantity));
        potatoQuantityLabel.setText(String.format("x%02d", potatoQuantity));
        cornQuantityLabel.setText(String.format("x%02d", cornQuantity));
    }

    private void updateAvailableQuantity(Seed s, int quantity) {
        if (s.getSeedID() == 0) {
            appleQuantity -= quantity;
        } else if (s.getSeedID() == 1) {
            potatoQuantity -= quantity;
        } else if (s.getSeedID() == 2) {
            cornQuantity -= quantity;
        }

        appleQuantityLabel.setText(String.format("x%02d", appleQuantity));
        potatoQuantityLabel.setText(String.format("x%02d", potatoQuantity));
        cornQuantityLabel.setText(String.format("x%02d", cornQuantity));
    }

    private void updateAvailableItemQuantity() {
        fertilizerQuantityLabel.setText(String.format("x%02d", fertilizerQuantity));
        pesticideQuantityLabel.setText(String.format("x%02d", pesticideQuantity));
    }

    private void updateAvailableItemQuantity(InventoryItem i, int quantity) {
        if (i.getItemName().equals("Fertilizer")) {
            fertilizerQuantity -= quantity;
        } else if (i.getItemName().equals("Pesticide")) {
            pesticideQuantity -= quantity;
        }

        fertilizerQuantityLabel.setText(String.format("x%02d", fertilizerQuantity));
        pesticideQuantityLabel.setText(String.format("x%02d", pesticideQuantity));
    }

    private int getQuantityOfType(Seed s) {
        if (s != null) {
            if (s.getSeedID() == 0) {
                return appleQuantity;
            } else if (s.getSeedID() == 1) {
                return potatoQuantity;
            } else if (s.getSeedID() == 2) {
                return cornQuantity;
            }
        }
        return -99;
    }

    private int getQuantityOfType(InventoryItem i) {
        if (i != null) {
            if (i.getItemName().equals("Fertilizer")) {
                return fertilizerQuantity;
            }
            return pesticideQuantity;
        }
        return -99;
    }

    public int getAppleQuantity() {
        return appleQuantity;
    }

    public int getPotatoQuantity() {
        return potatoQuantity;
    }

    public int getCornQuantity() {
        return cornQuantity;
    }

    public int getFertilizerQuantity() {
        return fertilizerQuantity;
    }

    public int getPesticideQuantity() {
        return pesticideQuantity;
    }

    /**
     * This method helps the player switch from buying-mode in the market to selling-mode.
     * @param event the event
     * @throws IOException the exception that may be thrown
     */
    @FXML
    void handleSellTab(ActionEvent event) throws IOException {
        FXMLLoader loadMarketSell =
                new FXMLLoader(getClass().getResource("../style/MarketSellUI.fxml"));
        Parent root = loadMarketSell.load();
        MarketSellUIController msu = loadMarketSell.getController();
        msu.initMarketSell(farmer, season, appleQuantity, potatoQuantity, cornQuantity,
                           pesticideQuantity, fertilizerQuantity);

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
     * This method handles what happens when the Buy button is pressed.
     * @param event the event
     */
    @FXML
    void handleBuyButton(ActionEvent event) {
        try {
            if (itemName.getText().equals("Item")) {
                throw new SeedChoiceNotFoundException("Please select a seed to buy!");
            } else if (quantityLabel.getText().equals("0")) {
                throw new SeedChoiceNotFoundException("Please add some quantity of items "
                        + "you'd like to purchase!");
            }
            if (itemName.getText().equals("Fertilizer") || itemName.getText().equals("Pesticide")) {
                int merchQuantity = getQuantityOfType(itemChoice);
                int buyQuantity = Integer.parseInt(quantityLabel.getText());
                if (seedCost * buyQuantity > farmer.getMoney()) {
                    throw new InsufficientFundsException();
                } else {
                    int item = (itemChoice.getItemName().equals("Pesticide") ? 1 : 0);
                    farmer.getInventory().addItem(item, buyQuantity);
                    farmer.pay(itemCost * buyQuantity);
                    updateAvailableItemQuantity(itemChoice, buyQuantity);
                    updateBankAmount();
                    resetQuantityAndCostLabels();
                }
            } else {
                int merchQuantity = getQuantityOfType(seedChoice);
                int buyQuantity = Integer.parseInt(quantityLabel.getText());
                if (buyQuantity > farmer.getInventory().getAvailableSeedBagCapacity()) {
                    throw new InsufficientInventorySpaceException();
                } else if (seedCost * buyQuantity > farmer.getMoney()) {
                    throw new InsufficientFundsException();
                } else if (buyQuantity > merchQuantity) {
                    throw new ExcessSeedBuyException();
                } else {
                    farmer.getInventory().addSeed(seedChoice, buyQuantity);
                    farmer.pay(seedCost * buyQuantity);
                    updateAvailableQuantity(seedChoice, buyQuantity);
                    updateAvailableCapacity();
                    updateBankAmount();
                    resetQuantityAndCostLabels();
                }
            }
        } catch (SeedChoiceNotFoundException s) {
            if (quantityLabel.getText().equals("0") && !itemName.getText().equals("Item")) {
                alertPopUp("No Quantity Chosen", s.getMessage());
            } else {
                alertPopUp("No Seed Chosen", s.getMessage());
            }
        } catch (InsufficientInventorySpaceException i) {
            alertPopUp("Not Enough Space In Inventory", i.getMessage());
        } catch (InsufficientFundsException i) {
            alertPopUp("Not Enough Money", i.getMessage());
        } catch (ExcessSeedBuyException e) {
            alertPopUp("Too many seeds!", e.getMessage());
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
                throw new SeedChoiceNotFoundException("Please select a seed to buy!");
            }
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (quantity > 0) {
                if (itemName.getText().equals("Apple Seed")
                        || itemName.getText().equals("Potato Seed")
                        || itemName.getText().equals("Corn Seed")) {
                    quantityLabel.setText("" + (quantity - 1));
                    totalCost.setText(String.format("$%,.2f", ((quantity - 1) * seedCost)));
                } else {
                    quantityLabel.setText("" + (quantity - 1));
                    totalCost.setText(String.format("$%,.2f", ((quantity - 1) * itemCost)));
                }
            }
        } catch (SeedChoiceNotFoundException s) {
            alertPopUp("No Seed Chosen", s.getMessage());
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
                throw new SeedChoiceNotFoundException("Please select a seed to buy!");
            }
            int availableSeeds = 0;
            int availableItems = 0;
            if (itemName.getText().equals("Apple Seed")) {
                availableSeeds = appleQuantity;
            } else if (itemName.getText().equals("Potato Seed")) {
                availableSeeds = potatoQuantity;
            } else if (itemName.getText().equals("Corn Seed")) {
                availableSeeds = cornQuantity;
            } else if (itemName.getText().equals("Fertilizer")) {
                availableItems = fertilizerQuantity;
            } else if (itemName.getText().equals("Pesticide")) {
                availableItems = pesticideQuantity;
            } else if (itemName.getText().equals("Water")) {
                availableItems = 1000;
            }
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (availableSeeds > quantity) {
                quantityLabel.setText("" + (quantity + 1));
                totalCost.setText(String.format("$%,.2f", ((quantity + 1) * seedCost)));
            } else if (availableItems > quantity) {
                quantityLabel.setText("" + (quantity + 1));
                totalCost.setText(String.format("$%,.2f", ((quantity + 1) * itemCost)));
            }
        } catch (SeedChoiceNotFoundException s) {
            String message = "Please select something to buy first!";
            alertPopUp("No Item Chosen", message);
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
        if (!itemPane.isVisible()) {
            itemPane.setVisible(true);
            farmhandPane.setVisible(false);
        }
        buyFieldSpaceButton.setVisible(false);
        Button btn = ((Button) event.getSource());
        resetQuantityAndCostLabels();
        if (btn == appleButton) {
            seedChoice = new Seed("Apple");
            seedCost = appleSeedCost;
            selectActions(appleButton, potatoButton, cornButton, pesticideButton,
                    fertilizerButton, waterButton, hireButton, fieldsButton, tractorButton);
        } else if (btn == potatoButton) {
            seedChoice = new Seed("Potato");
            seedCost = potatoSeedCost;
            selectActions(potatoButton, appleButton, cornButton, pesticideButton,
                    fertilizerButton, waterButton, hireButton, fieldsButton, tractorButton);
        } else {
            seedChoice = new Seed("Corn");
            seedCost = cornSeedCost;
            selectActions(cornButton, appleButton, potatoButton, pesticideButton,
                    fertilizerButton, waterButton, hireButton, fieldsButton, tractorButton);
        }
        itemName.setText(seedChoice.getName() + " Seed");
        itemDescription.setText(String.format("The current price for "
                        + "one %s Seed in the %s Season is $%,.2f!",
                seedChoice.getName(), season.getSeason(), seedCost));
    }

    @FXML
    void hireOnAction(ActionEvent e) {
        itemPane.setVisible(false);
        farmhandPane.setVisible(true);
        selectActions(hireButton, pesticideButton, fertilizerButton, waterButton,
                appleButton, potatoButton, cornButton, fieldsButton, tractorButton);
    }

    @FXML
    void handleFarmhandSkill(ActionEvent e) {
        Color selected = Color.web("#feca57");
        Color unselected = Color.web("#22d2a3");
        String description;
        if (!(farmer.getFarmhand().isActive())) {
            if (e.getSource() == amateurButton) {
                selectedFarmhandExperience = amateurButton;
                description = "An amateur farmhand will:\n* harvest crops once mature "
                       + "\nWage: $" + (Integer.parseInt(farmer.getExperienceLevel()) * 10)
                        + " per day.";
                finishHireButton.setDisable(false);
                amateurButton.setTextFill(selected);
                expertButton.setTextFill(unselected);
            } else {
                selectedFarmhandExperience = expertButton;
                description = "An expert farmhand will:\n* harvest crops once mature"
                        + "\n* sell harvested crops at the market."
                        + "\nWage: $" + (Integer.parseInt(farmer.getExperienceLevel()) * 20)
                        + " per day.";
                finishHireButton.setDisable(false);
                amateurButton.setTextFill(unselected);
                expertButton.setTextFill(selected);
            }
        } else {
            description = "A farmhand is currently employed at your farm for "
                    + farmer.getFarmhand().getDaysActive() + " more day(s).";
            finishHireButton.setDisable(true);
            amateurButton.setTextFill(unselected);
            expertButton.setTextFill(unselected);
        }

        farmhandDescription.setText(description);
    }


    @FXML
    void handleDayIncrementButton(ActionEvent e) {
        if (selectedFarmhandExperience != null && (!farmer.getFarmhand().isActive())) {
            int days = Integer.parseInt(dayCountLabel.getText());
            if (e.getSource() == dayPlusButton) {
                days++;
            } else {
                days--;
            }

            if (days >= 0) {
                dayCountLabel.setText(days + "");
            } else {
                dayCountLabel.setText("0");
            }
        }
    }

    @FXML
    void handleFinishHireButton() {
        if (selectedFarmhandExperience != null && (!farmer.getFarmhand().isActive())) {
            int days = Integer.parseInt(dayCountLabel.getText());
            if (days > 0) {
                int skillLevel;
                if (selectedFarmhandExperience == amateurButton) {
                    skillLevel = 0;
                } else {
                    skillLevel = 1;
                }

                farmer.setNewFarmhand(skillLevel, days);
                finishHireButton.setDisable(true);
                farmhandDescription.setText("A farmhand is currently employed at your farm for "
                        + farmer.getFarmhand().getDaysActive() + " more day(s).");
                amateurButton.setTextFill(Color.web("#22d2a3"));
                expertButton.setTextFill(Color.web("#22d2a3"));
            }

        }
    }

    /**
     * This helper method helps set the opacity and clickability of a button.
     * @param selected the button that is selected
     * @param notSelected the buttons that aren't selected
     */
    private void selectActions(Button selected, Button... notSelected) {
        selected.setOpacity(1.0);
        selected.setDisable(true);
        for (Button button : notSelected) {
            button.setOpacity(.5);
            button.setDisable(false);
        }
    }

    /**
     * This method helps give functionality to the buttons that hold the item options.
     * @param event the event
     */
    @FXML
    void itemOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        if (!itemPane.isVisible()) {
            itemPane.setVisible(true);
            farmhandPane.setVisible(false);
        }
        buyFieldSpaceButton.setVisible(false);
        resetQuantityAndCostLabels();
        if (btn == fertilizerButton) {
            itemChoice = new InventoryItem("Fertilizer", 1);
            itemCost = fertilizerCost;
            selectActions(fertilizerButton, pesticideButton, waterButton, appleButton,
                    potatoButton, cornButton, hireButton, fieldsButton, tractorButton);
        } else {
            itemChoice = new InventoryItem("Pesticide", 1);
            itemCost = pesticideCost;
            selectActions(pesticideButton, fertilizerButton, waterButton, appleButton,
                    potatoButton, cornButton, hireButton, fieldsButton, tractorButton);
        }
        itemName.setText(itemChoice.getItemName());
        itemDescription.setText(String.format("The current price for "
                        + "one unit of %s in the %s Season is $%,.2f!",
                itemChoice.getItemName(), season.getSeason(), itemCost));
    }

    /**
     * This method helps give functionality to the buttons that hold the Farm Machinery options.
     * @param event the event
     */
    @FXML
    void farmMachineryOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        if (!itemPane.isVisible()) {
            itemPane.setVisible(true);
            farmhandPane.setVisible(false);
        }
        buyFieldSpaceButton.setVisible(true);
        String name = "";
        String description = "";
        if (btn == waterButton) {
            name = "Water/Irrigation";
            description = String.format("The cost of increasing daily watering capacity "
                + "by 12 gallons is $%,.2f!", farmer.getNextWateringCost());
            selectActions(waterButton, fertilizerButton, pesticideButton, appleButton,
                    potatoButton, cornButton, hireButton, fieldsButton, tractorButton);
        } else {
            name = "Tractor";
            description = String.format("The cost of increasing daily harvesting capacity "
                    + "by 3 crops is $%,.2f!", farmer.getNextHarvestingCost());
            itemChoice = new InventoryItem("Pesticide", 1);
            itemCost = pesticideCost;
            selectActions(tractorButton, pesticideButton, fertilizerButton, waterButton,
                    appleButton, potatoButton, cornButton, hireButton, fieldsButton);
        }
        itemName.setText(name);
        itemDescription.setText(description);
    }

    @FXML
    void handleFieldsButton(ActionEvent e) {
        if (!itemPane.isVisible()) {
            itemPane.setVisible(true);
            farmhandPane.setVisible(false);
        }
        buyFieldSpaceButton.setVisible(true);

        selectActions(fieldsButton, appleButton, potatoButton, cornButton, pesticideButton,
                fertilizerButton, waterButton, hireButton, tractorButton);

        itemName.setText("Field Space");

        itemDescription.setText(String.format("The cost of buying 12 more field plots is $%,.2f!",
                farmer.getNextFieldCost()));
    }

    @FXML
    void handleBuyFieldSpaceAndFarmMachinery(ActionEvent e) {
        try {
            if (itemName.getText().equals("Water/Irrigation")) {
                if (farmer.getMoney() < farmer.getNextWateringCost()) {
                    throw new InsufficientFundsException(
                        "You don't have enough money to buy more irrigation!");
                } else {
                    farmer.pay(farmer.getNextWateringCost());
                    farmer.incrementWateringCapacity();
                    farmer.incrementNextWateringCost();
                    itemDescription.setText(String.format("The cost of increasing daily "
                        + "watering capacity by 12 gallons is $%,.2f!",
                        farmer.getNextWateringCost()));
                }
            } else if (itemName.getText().equals("Tractor")) {
                if (farmer.getMoney() < farmer.getNextHarvestingCost()) {
                    throw new InsufficientFundsException("You don't have enough money to buy more\n"
                        + "harvesting capacity!");
                } else {
                    farmer.pay(farmer.getNextHarvestingCost());
                    farmer.incrementHarvestingCapacity();
                    farmer.incrementNextHarvestingCost();
                    itemDescription.setText(String.format("The cost of increasing daily "
                        + "harvesting capacity by 3 crops is $%,.2f!",
                        farmer.getNextHarvestingCost()));
                }
            } else {
                if (farmer.getMoney() < farmer.getNextFieldCost()) {
                    throw new InsufficientFundsException(
                        "You don't have enough money to buy more field space!");
                } else {
                    farmer.getAllFields()[farmer.getFieldsSize()] = new Field(3, 4, false);
                    farmer.pay(farmer.getNextFieldCost());
                    farmer.incrementFieldSize();
                    handleFieldsButton(new ActionEvent());
                }
            }
        } catch (InsufficientFundsException i) {
            alertPopUp("Not Enough Money", i.getMessage());
        } finally {
            updateBankAmount();
        }
    }

    /**
     * This method helps show a moving graphic when a mouse enters the button.
     * @param e the event
     */
    @FXML
    void invButtonMouseEnter(MouseEvent e) {
        Group g = null;
        if (e.getSource() == appleButton) {
            g = appleImage;
        } else if (e.getSource() == potatoButton) {
            g = potatoImage;
        } else if (e.getSource() == cornButton) {
            g = cornImage;
        } else if (e.getSource() == pesticideButton) {
            g = pesticideImage;
        } else if (e.getSource() == fertilizerButton) {
            g = fertilizerImage;
        } else if (e.getSource() == waterButton) {
            g = waterGraphic;
        } else if (e.getSource() == hireButton) {
            g = hireImage;
        } else if (e.getSource() == fieldsButton) {
            g = plotImage;
        } else if (e.getSource() ==  homeScreenButton) {
            g = homeGraphic;
        } else if (e.getSource() == tractorButton) {
            g = tractorGraphic;
        }

        if (g != null) {
            lowerRightGraphicTransition(g);
        }
    }

    /**
     * This method helps change the color of the button when it is pressed.
     * @param event the event
     */
    @FXML
    void seedRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setOpacity(0.5);
        ((Button) event.getSource()).setStyle("-fx-background-color: #434341;"
                + " -fx-background-radius: 10");
    }

    /**
     * This method helps change the color of the button when it is released.
     * @param event the event
     */
    @FXML
    void seedRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #8495a6;"
                + " -fx-background-radius: 10;");
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