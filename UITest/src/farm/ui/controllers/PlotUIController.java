package farm.ui.controllers;


import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import exceptions.PlotAlreadyFullException;
import exceptions.SeedChoiceNotFoundException;
import farm.objects.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class PlotUIController {

    // Main Plot Elements //
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
    private AnchorPane rightPaneWrapper;

    @FXML
    private GridPane plotGrid;

    @FXML
    private Button rainButton;

    @FXML
    private Button droughtButton;

    @FXML
    private Button locustButton;

    @FXML
    private Button previousFieldButton;

    @FXML
    private Button nextFieldButton;

    @FXML
    private Label waterLeftLabel;

    @FXML
    private Label waterCapacityLabel;

    @FXML
    private Label harvestingLeftLabel;

    @FXML
    private Label harvestingCapacityLabel;

    @FXML
    private Group harvestAvailableContainer;

    @FXML
    private Group waterAvailableContainer;

    private Farmer farmer;

    private Season season;

    private MainPanelUIController mpu;

    private InventoryUIController invu;

    private PlantInspectUIController piu;

    private Group[][] plotArray;

    private Plot selectedPlot;

    private Random chance = new Random();

    private RandomEvent randomEvent;

    private SimpleIntegerProperty day = new SimpleIntegerProperty(this, "day");

    private ChangeListener<Number> dayChange = (observable, oldValue, newValue) -> {
        farmer.resetWateringAndHarvestingLeft();
        updateWaterAndHarvestLabels();
        advanceGrowthCycle(chance.nextInt(101));
        if (rightPaneWrapper.getChildren().get(0) == piu.getRightPaneInspect()) {
            updateRightPaneInspect(selectedPlot.getCrop());
        }

        Farmhand fh = farmer.getFarmhand();
        if (fh.isActive()) {
            fh.incrementDaysActive(-1);
            if (farmer.getMoney() - fh.getWage() >= 0) {
                farmer.pay(fh.getWage());
                mpu.setMoneyLabel(farmer.getMoney());
                farmhandWork(farmer.getFarmhand(), true);
                mpu.updateFarmhandStatus();
            } else {
                farmhandWork(farmer.getFarmhand(), false);
                fh.incrementDaysActive(-(fh.getDaysActive()));
                mpu.updateFarmhandStatus(true);
            }
        } else {
            mpu.updateFarmhandStatus();
        }
    };

    // |     Initialize Settings     |
    // |                             |

    public void initPlotUI(Farmer f, Season s, MainPanelUIController mpu,
                           InventoryUIController invu, PlantInspectUIController piu) {
        this.farmer = f;
        this.season = s;
        this.randomEvent = new RandomEvent(s);
        this.mpu = mpu;
        this.invu = invu;
        this.piu = piu;
        plotArray = new Group[][]{{plotGroup00, plotGroup01, plotGroup02, plotGroup03},
                                  {plotGroup10, plotGroup11, plotGroup12, plotGroup13},
                                  {plotGroup20, plotGroup21, plotGroup22, plotGroup23}};

        setRightPaneWrapper(mpu.getRightPaneMain());
        selectedPlot = null;

        day.bind(s.getTimer().dayProperty());

        dayStartListen();

        updateWaterAndHarvestLabels();

        handleFieldIterate(new ActionEvent());

    }

    public void updateWaterAndHarvestLabels() {
        waterLeftLabel.setText(String.format("%02d", farmer.getWateringLeft()));
        waterCapacityLabel.setText(String.format("%02d", farmer.getWateringCapacity()));
        harvestingLeftLabel.setText(String.format("%02d", farmer.getHarvestingLeft()));
        harvestingCapacityLabel.setText(String.format("%02d", farmer.getHarvestingCapacity()));
    }

    // |     Getters and Setters     |
    // |                             |

    void setRightPaneWrapper(AnchorPane a) {
        rightPaneWrapper.getChildren().clear();
        rightPaneWrapper.getChildren().add(a);
    }

    void setSelectedPlot(Plot pl) {
        selectedPlot = pl;
        displayCrops();
    }

    void setHarvestWaterContainersVisible(boolean b) {
        harvestAvailableContainer.setVisible(b);
        waterAvailableContainer.setVisible(b);
    }

    public Group getPlotGroup(int column, int row) {
        return plotArray[column][row];
    }

    public Group[][] getPlotArray() {
        return plotArray;
    }

    private void displayCrops() {
        Crop cropCheck = null;
        for (int i = 0; i < plotArray.length; i++) {
            for (int j = 0; j < plotArray[i].length; j++) {
                Crop crop = farmer.getField().getPlot(i, j).getCrop();

                if (crop == null) {
                    plotArray[i][j].getChildren().get(4).setVisible(false);
                    plotArray[i][j].getChildren().get(5).setVisible(false);
                    plotArray[i][j].setEffect(null);
                } else {
                    if (crop.isDead()) {
                        plotArray[i][j].getChildren().get(4).setVisible(false);
                        plotArray[i][j].getChildren().get(5).setVisible(true);
                    } else {
                        cropCheck = crop;
                        plotArray[i][j].getChildren().get(4).setVisible(true);
                        plotArray[i][j].getChildren().get(5).setVisible(false);
                        if (farmer.getField().getPlot(i, j) == selectedPlot
                                && rightPaneWrapper.getChildren().get(0)
                                == piu.getRightPaneInspect()) {
                            plotArray[i][j].setEffect(new DropShadow(5, Color.BLUE));
                        } else {
                            plotArray[i][j].setEffect(null);
                        }
                    }
                }
                //String text = (crop == null) ? "This plot is empty.\n\n" : crop.toString();
                ((Label) plotArray[i][j].getChildren().get(3)).setText("");
            }
        }
        if (cropCheck == null) {
            checkEndGame();
        }
    }

    public void updateRightPaneInspect(Crop crop) {
        if (rightPaneWrapper.getChildren().get(0) == piu.getRightPaneInspect()) {
            String text = (crop == null) ? "Empty\n\n" : crop.getSeed().getName();
            if (crop != null && crop.isDead()) {
                piu.setPlantNameLabel("Dead " + text);
            } else {
                piu.setPlantNameLabel(text);
            }

            if (crop != null && !crop.isDead()) {
                piu.setGrowthMeter(crop);
                piu.setWaterMeter(crop.getWaterLevel());
                piu.setFertilizerLabel(selectedPlot.getFertilizerLevel());
                piu.setPesticideIndicator(crop.isPesticideTreated());
            } else {
                piu.setGrowthMeter(crop);
                piu.setWaterMeter(0);
                piu.setPesticideIndicator(false);
            }
        }
    }


    @FXML
    public void interact(MouseEvent e) {
        try {
            Node source = (Node) e.getSource();
            int column = Integer.parseInt(source.toString().substring(18, 19));
            int row = Integer.parseInt(source.toString().substring(19, 20));
            Crop crop = farmer.getField().getPlot(column, row).getCrop();
            Plot currPlot = farmer.getField().getPlot(column, row);
            if (selectedPlot != currPlot) {
                selectedPlot = currPlot;
                displayCrops();
                updateRightPaneInspect(selectedPlot.getCrop());
                if (PlantInspectUIController.getWaterPress()
                        || PlantInspectUIController.getSowPress()
                        || PlantInspectUIController.getTreatmentPress()) {
                    return;
                }
            }

            updateRightPaneInspect(crop);

            //handle sowPress
            //harvests crop, updates PlotUI plant graphic to be invisible
            if (PlantInspectUIController.getSowPress() && crop != null && !crop.isDead()) {
                if (farmer.canHarvestMore()) {
                    if (crop.getGrowthStage() == 2) {
                        farmer.hasHarvested();
                        harvestingLeftLabel.setText(String.format("%02d",
                            farmer.getHarvestingLeft()));
                    }
                    Seed s = farmer.getField().getPlot(column, row).harvest();
                    Random rand = new Random();
                    if (s != null) {
                        if (farmer.getField().getPlot(column, row).isFertilizerTreated()) {
                            int yieldChance = rand.nextInt(2);
                            farmer.getInventory().addHarvest(crop, 2 + yieldChance);
                        } else {
                            farmer.getInventory().addHarvest(crop, 1);
                        }
                    }
                    displayCrops();
                    ((Group) source).getChildren().get(4).setVisible(false);
                    updateRightPaneInspect(null);
                } else {
                    String errorHeader = "Daily Harvesting Maximum Reached!";
                    String errorMessage = "Your daily harvesting maximum has been reached.\n"
                        + "If you want to harvest more crops, please purchase a\n"
                        + "tractor from the market!";
                    alertPopUp(errorHeader, errorMessage);
                }
            }

            if (PlantInspectUIController.getSowPress() && crop != null && crop.isDead()) {
                Seed s = farmer.getField().getPlot(column, row).harvest();
                Random rand = new Random();
                if (s != null) {
                    if (farmer.getField().getPlot(column, row).isFertilizerTreated()) {
                        int yieldChance = rand.nextInt(2);
                        farmer.getInventory().addHarvest(crop, 2 + yieldChance);
                    } else {
                        farmer.getInventory().addHarvest(crop, 1);
                    }
                }
                displayCrops();
                ((Group) source).getChildren().get(4).setVisible(false);
                updateRightPaneInspect(null);
            }

            //handle sowPress on empty plot
            //plants crop, updates PlotUI plant graphic to be visible
            if (PlantInspectUIController.getSowPress() && crop == null) {
                Seed newSeed = seedPopUp(farmer);
                if (newSeed != null) {
                    farmer.getField().getPlot(column, row).plant(newSeed);
                    farmer.getInventory().removeSeed(newSeed, 1);
                    displayCrops();
                    ((Group) source).getChildren().get(4).setVisible(true);
                    updateRightPaneInspect(farmer.getField().getPlot(column, row).getCrop());
                }
            }

            //handle waterPress
            if (PlantInspectUIController.getWaterPress() && crop != null && !crop.isDead()) {
                if (farmer.canWaterMore()) {
                    crop.setWaterLevel(crop.getWaterLevel() + 1);
                    farmer.hasWatered();
                    waterLeftLabel.setText(String.format("%02d", farmer.getWateringLeft()));
                    updateRightPaneInspect(crop);
                    displayCrops();
                } else {
                    String errorHeader = "Daily Watering Maximum Reached!";
                    String errorMessage = "Your daily watering maximum has been reached.\n"
                            + "If you want to water more crops, please purchase irrigation \n"
                            + "from the market!";
                    alertPopUp(errorHeader, errorMessage);
                }
            }

            //handle treatmentPress
            if ((PlantInspectUIController.getTreatmentPress()) && crop != null) {
                InventoryItem item = treatmentPopUp(farmer, crop, currPlot);
                if (item != null) {
                    if (item.getItemName().equals("Fertilizer")) {
                        farmer.getField().getPlot(column, row).setFertilizerTreated(true);
                        farmer.getField().getPlot(column, row).setFertilizerLevel(15);
                        farmer.getInventory().getItemBag()[0].addQuantity(-1);
                    } else if (item.getItemName().equals("Pesticide")) {
                        farmer.getField().getPlot(column, row).getCrop().setPesticideTreated(true);
                        farmer.getInventory().getItemBag()[1].addQuantity(-1);
                    }

                    displayCrops();
                    updateRightPaneInspect(farmer.getField().getPlot(column, row).getCrop());
                }

            }
        } catch (ImmatureHarvestException i) {
            alertPopUp("Attempted Infanticide", i.getMessage());
        } catch (EmptyPlotException empty) {
            alertPopUp("Empty Plot Harvest", empty.getMessage());
        } catch (SeedChoiceNotFoundException seedChoiceNotFoundException) {
            seedChoiceNotFoundException.printStackTrace();
        } catch (PlotAlreadyFullException p) {
            alertPopUp("Plot Full", p.getMessage());
        }
    }

    static InventoryItem treatmentPopUp(Farmer farmer, Crop crop, Plot plot) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Treatment Select");
        a.setHeaderText("Would you like to treat a plant with pesticide or fertilizer?");
        a.setContentText("Choose one:");
        ButtonType buttonTypeOne = new ButtonType("Fertilizer: x"
            + farmer.getInventory().getItemBag()[0].getTotalQuantity());
        ButtonType buttonTypeTwo = new ButtonType("Pesticide: x"
            + farmer.getInventory().getItemBag()[1].getTotalQuantity());
        ButtonType buttonTypeCancel =
                new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        if (farmer.getInventory().getItemBag()[0].getTotalQuantity() <= 0
                || plot.getFertilizerLevel() == 100) {
            a.getDialogPane().lookupButton(buttonTypeOne).setDisable(true);
        }
        if (farmer.getInventory().getItemBag()[1].getTotalQuantity() <= 0
                || crop.isPesticideTreated()) {
            a.getDialogPane().lookupButton(buttonTypeTwo).setDisable(true);
        }

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == buttonTypeOne) {
            return new InventoryItem("Fertilizer", 0);
        } else if (result.get() == buttonTypeTwo) {
            return new InventoryItem("Pesticide", 0);
        } else {
            return null;
        }
    }


    /**
     * This methods helps create an alert popup for the user to
     * select which seed they want to plant.
     * @param farmer the farmer
     * @return whether a seed was chosen or not
     */
    static Seed seedPopUp(Farmer farmer) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Seed Select");
        a.setHeaderText("Which seed do you want to plant?");
        a.setContentText("Choose one:");
        ButtonType buttonTypeOne = new ButtonType("Apple: x"
                    + farmer.getInventory().getSeedBag()[0].getTotalQuantity());
        ButtonType buttonTypeTwo = new ButtonType("Potato: x"
                    + farmer.getInventory().getSeedBag()[1].getTotalQuantity());
        ButtonType buttonTypeThree = new ButtonType("Corn: x"
                    + farmer.getInventory().getSeedBag()[2].getTotalQuantity());
        ButtonType buttonTypeCancel = new ButtonType("Cancel",
            ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
        if (farmer.getInventory().getSeedBag()[0].getTotalQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeOne).setDisable(true);
        }
        if (farmer.getInventory().getSeedBag()[1].getTotalQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeTwo).setDisable(true);
        }
        if (farmer.getInventory().getSeedBag()[2].getTotalQuantity() <= 0) {
            a.getDialogPane().lookupButton(buttonTypeThree).setDisable(true);
        }

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == buttonTypeOne) {
            return new Seed("Apple");
        } else if (result.get() == buttonTypeTwo) {
            return new Seed("Potato");
        } else if (result.get() == buttonTypeThree) {
            return new Seed("Corn");
        } else {
            return null;
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

    public void advanceGrowthCycle(int chance) {
        try {
            int origFieldIndex = farmer.getCurrFieldIndex();
            randomEvent.generateNewRainAndDroughtLevels();
            for (int h = 0; h < farmer.getFieldsSize(); h++) {
                farmer.setCurrFieldIndex(h);
                for (int i = 0; i < plotArray.length; i++) {
                    for (int j = 0; j < plotArray[i].length; j++) {
                        Plot currPlot = farmer.getField().getPlot(i, j);
                        Crop crop = currPlot.getCrop();
                        if (crop != null) {
                            if (currPlot.getFertilizerLevel() == 0) {
                                currPlot.setFertilizerTreated(false);
                            }
                            if (currPlot.isFertilizerTreated()) {
                                crop.grow();
                            }
                            crop.grow();
                            currPlot.setFertilizerLevel(-5);
                            crop.setWaterLevel(crop.getWaterLevel() - 2);
                            randomEvent.performRandomEvent(crop, chance);
                        }
                        //String text =
                        // (crop == null) ? "This plot is empty.\n\n" : crop.toString();
                        ((Label) plotArray[i][j].getChildren().get(3)).setText("");
                    }
                }
            }
            farmer.setCurrFieldIndex(origFieldIndex);
        } finally {
            if ((randomEvent.getErrorHeader().length() != 0)
                && (randomEvent.getErrorMessage().length() != 0)) {
                alertPopUp(randomEvent.getErrorHeader(), randomEvent.getErrorMessage());
                randomEvent.resetDeadFromLocusts();
                randomEvent.resetErrorHeaderAndMessage();
            }
            displayCrops();
        }
    }

    private void farmhandWork(Farmhand fh, boolean isPaid)  {
        int origFieldIndex = farmer.getCurrFieldIndex();
        for (int h = 0; h < farmer.getFieldsSize(); h++) {
            for (int i = 0; i < plotArray.length; i++) {
                for (int j = 0; j < plotArray[i].length; j++) {
                    if (farmer.canHarvestMore()) {
                        Crop crop = farmer.getField().getPlot(i, j).getCrop();
                        if (crop != null && crop.getGrowthStage() == 2) {
                            try {
                                farmer.getField().getPlot(i, j).harvest();
                                farmer.hasHarvested();
                                updateWaterAndHarvestLabels();
                            } catch (ImmatureHarvestException | EmptyPlotException
                                    | SeedChoiceNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (isPaid) {
                                try {
                                    farmer.getInventory().addHarvest(crop, 1);
                                } catch (SeedChoiceNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
                if (!farmer.canHarvestMore()) {
                    break;
                }
            }
            if (!farmer.canHarvestMore()) {
                break;
            }

        }
        farmer.setCurrFieldIndex(origFieldIndex);
        displayCrops();
        updateRightPaneInspect(null);

        if (isPaid && fh.getSkillLevel() == 1) {
            InventoryItem[] harvestBag = farmer.getInventory().getHarvestBag();

            for (int i = 0; i < harvestBag.length; i++) {

                Crop c = new Crop(new Seed(i), true);
                int noPestCount = harvestBag[i].getPesticideFreeCount().getValue();
                int pestCount = harvestBag[i].getPesticideTreatedCount().getValue();

                farmer.addMoney(calculateSellPrice(c, pestCount));
                harvestBag[i].addQuantity(c,  -pestCount);

                c.setPesticideTreated(false);
                farmer.addMoney(calculateSellPrice(c, noPestCount));
                harvestBag[i].addQuantity(c,  -noPestCount);


            }
            mpu.setMoneyLabel(farmer.getMoney());
        }

    }

    private double calculateSellPrice(Crop c, int quantity) {
        Seed s = c.getSeed();
        double price = Math.round((s.getBaseSell() + (season.getSeason().length() / s.getDiv())
                + (Math.random() * Integer.parseInt(farmer.getExperienceLevel()))) * 100.0) / 100.0;
        if (c.isPesticideTreated()) {
            price -= 2;
        }

        price *= quantity;

        return price;
    }

    private void randomEventHandler(int chance) {
        try {
            randomEvent.generateNewRainAndDroughtLevels();
            int origFieldIndex = farmer.getCurrFieldIndex();
            for (int h = 0; h < farmer.getFieldsSize(); h++) {
                farmer.setCurrFieldIndex(h);
                for (int i = 0; i < plotArray.length; i++) {
                    for (int j = 0; j < plotArray[i].length; j++) {
                        Crop crop = farmer.getField().getPlot(i, j).getCrop();
                        if (crop != null) {
                            randomEvent.performRandomEvent(crop, chance);
                        }
                        ((Label) plotArray[i][j].getChildren().get(3)).setText("");
                    }
                }
            }
            farmer.setCurrFieldIndex(origFieldIndex);
        } finally {
            if ((randomEvent.getErrorHeader().length() != 0)
                    && (randomEvent.getErrorMessage().length() != 0)) {
                alertPopUp(randomEvent.getErrorHeader(), randomEvent.getErrorMessage());
                randomEvent.resetDeadFromLocusts();
                randomEvent.resetErrorHeaderAndMessage();
            }
            displayCrops();
        }
    }

    @FXML
    void handleRainButton(ActionEvent event) {
        randomEventHandler(0);
    }

    @FXML
    void handleDroughtButton(ActionEvent event) {
        randomEventHandler(30);
    }

    @FXML
    void handleLocustButton(ActionEvent event) {
        randomEventHandler(60);
    }

    @FXML
    void handleFieldIterate(ActionEvent event) {

        if (event.getSource() == nextFieldButton) {
            farmer.setCurrFieldIndex(farmer.getCurrFieldIndex() + 1);
        } else  if (event.getSource() == previousFieldButton) {
            farmer.setCurrFieldIndex(farmer.getCurrFieldIndex() - 1);
        }

        if (farmer.getCurrFieldIndex() - 1 >= 0
                && farmer.getAllFields()[farmer.getCurrFieldIndex() - 1] != null)  {
            previousFieldButton.setVisible(true);
        } else {
            previousFieldButton.setVisible(false);
        }

        if (((farmer.getCurrFieldIndex() + 1) < farmer.getFieldsSize())
                && farmer.getAllFields()[farmer.getCurrFieldIndex() + 1] != null) {
            nextFieldButton.setVisible(true);
        } else {
            nextFieldButton.setVisible(false);
        }

        displayCrops();

    }

    void checkEndGame() {
        if (farmer.getMoney() < 6
                && farmer.getInventory().isEmpty(farmer.getInventory().getHarvestBag())
            && farmer.getInventory().isEmpty(farmer.getInventory().getSeedBag())) {

            int currField = farmer.getCurrFieldIndex();
            Crop cropCheck = null;
            for (int h = 0; h < farmer.getFieldsSize(); h++) {
                farmer.setCurrFieldIndex(h);
                for (int i = 0; i < plotArray.length; i++) {
                    for (int j = 0; j < plotArray[i].length; j++) {
                        Crop c = farmer.getField().getPlot(i, j).getCrop();
                        if (c != null && !c.isDead()) {
                            cropCheck = c;
                        }
                    }
                }
            }

            if (cropCheck == null && nextFieldButton.getScene() != null) {
                alertPopUp("No Crops, No Money, No Seeds",
                        "Aww Shucks. Pa's not gonna like this.");
                triggerEndGame();
            } else {
                farmer.setCurrFieldIndex(currField);
            }
        }
    }

    void triggerEndGame() {
        dayEndListen();

        FXMLLoader loadEndGame =
                new FXMLLoader(getClass().getResource("../style/EndGameUI.fxml"));
        Parent root = null;
        try {
            root = loadEndGame.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        Scene nextPageScene = new Scene(root);

        Stage window = (Stage) nextFieldButton.getScene().getWindow();

        window.setScene(nextPageScene);
        window.show();
    }

    void dayStartListen() {
        day.addListener(dayChange);
    }

    void dayEndListen() {
        day.removeListener(dayChange);
    }

}