package farm.ui.controllers;


import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import exceptions.PlotAlreadyFullException;
import exceptions.SeedChoiceNotFoundException;
import farm.objects.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        advanceGrowthCycle(chance.nextInt(101));
        if (rightPaneWrapper.getChildren().get(0) == piu.getRightPaneInspect()) {
            updateRightPaneInspect(selectedPlot.getCrop());
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

        displayCrops();
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

    public Group getPlotGroup(int column, int row) {
        return plotArray[column][row];
    }

    public Group[][] getPlotArray() {
        return plotArray;
    }

    private void displayCrops() {
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
            } else {
                piu.setGrowthMeter(crop);
                piu.setWaterMeter(0);
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
            if (PlantInspectUIController.getSowPress() && crop != null) {
                Seed s = farmer.getField().getPlot(column, row).harvest();
                if (s != null) {
                    farmer.getInventory().addHarvest(crop, 1);
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
                crop.setWaterLevel(crop.getWaterLevel() + 1);
                updateRightPaneInspect(crop);
                displayCrops();
            }

            //handle treatmentPress
            if ((PlantInspectUIController.getTreatmentPress()) && crop != null) {
                InventoryItem item = treatmentPopUp(farmer, crop);
                if (item != null) {
                    if (item.getItemName().equals("Fertilizer")) {

                        //code for fertilizer to be added here

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

    static InventoryItem treatmentPopUp(Farmer farmer, Crop crop) {
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
        if (farmer.getInventory().getItemBag()[0].getTotalQuantity() <= 0) {
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
            randomEvent.generateNewRainAndDroughtLevels();
            for (int i = 0; i < plotArray.length; i++) {
                for (int j = 0; j < plotArray[i].length; j++) {
                    Crop crop = farmer.getField().getPlot(i, j).getCrop();
                    if (crop != null) {
                        crop.grow();
                        crop.setWaterLevel(crop.getWaterLevel() - 2);
                        randomEvent.performRandomEvent(crop, chance);
                    }
                    //String text = (crop == null) ? "This plot is empty.\n\n" : crop.toString();
                    ((Label) plotArray[i][j].getChildren().get(3)).setText("");
                }
            }
            displayCrops();
        } finally {
            if ((randomEvent.getErrorHeader().length() != 0)
                && (randomEvent.getErrorMessage().length() != 0)) {
                alertPopUp(randomEvent.getErrorHeader(), randomEvent.getErrorMessage());
                randomEvent.resetDeadFromLocusts();
            }
        }
    }

    private void randomEventHandler(int chance) {
        try {
            randomEvent.generateNewRainAndDroughtLevels();
            for (int i = 0; i < plotArray.length; i++) {
                for (int j = 0; j < plotArray[i].length; j++) {
                    Crop crop = farmer.getField().getPlot(i, j).getCrop();
                    if (crop != null) {
                        randomEvent.performRandomEvent(crop, chance);
                    }
                    ((Label) plotArray[i][j].getChildren().get(3)).setText("");
                }
            }
            displayCrops();
        } finally {
            if ((randomEvent.getErrorHeader().length() != 0)
                    && (randomEvent.getErrorMessage().length() != 0)) {
                alertPopUp(randomEvent.getErrorHeader(), randomEvent.getErrorMessage());
                randomEvent.resetDeadFromLocusts();
            }
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

    void dayStartListen() {
        day.addListener(dayChange);
    }

    void dayEndListen() {
        day.removeListener(dayChange);
    }

}