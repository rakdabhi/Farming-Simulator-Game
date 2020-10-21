
package farm.ui.controllers;


import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import farm.objects.Crop;
import farmer.Farmer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import season.Season;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import static farm.ui.controllers.ConfigUIController.alertPopUp;


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

    private Farmer farmer;

    private Season season;

    private MainPanelUIController mpu;

    private InventoryUIController invu;

    private PlantInspectUIController piu;

    private Group[][] plotArray;

    // |     Initialize Settings     |
    // |                             |

    public void initPlotUI(Farmer f, Season s, MainPanelUIController mpu,
                           InventoryUIController invu, PlantInspectUIController piu) {
        this.farmer = f;
        this.season = s;
        this.mpu = mpu;
        this.invu = invu;
        this.piu = piu;
        plotArray = new Group[][]{{plotGroup00, plotGroup01, plotGroup02, plotGroup03},
                                  {plotGroup10, plotGroup11, plotGroup12, plotGroup13},
                                  {plotGroup20, plotGroup21, plotGroup22, plotGroup23}};

        setRightPaneWrapper(mpu.getRightPaneMain());

        displayCrops();
    }

    // |     Getters and Setters     |
    // |                             |

    void setRightPaneWrapper(AnchorPane a) {
        rightPaneWrapper.getChildren().clear();
        rightPaneWrapper.getChildren().add(a);
    }

    public Group getPlotGroup(int column, int row) {
        return plotArray[column][row];
    }

    private void displayCrops() {
        for (int i = 0; i < plotArray.length; i++) {
            for (int j = 0; j < plotArray[i].length; j++) {
                Crop crop = farmer.getField().getPlot(i, j).getCrop();
                String text = (crop == null) ? "This plot is empty.\n\n" : crop.toString();
                ((Label) plotArray[i][j].getChildren().get(3)).setText(text);
            }
        }
    }

    void updateRightPaneInspect(Crop crop) {
        if (rightPaneWrapper.getChildren().get(0) == piu.getRightPaneInspect()) {
            String text = (crop == null) ? "Empty\n\n" : crop.getSeed().getName();
            piu.setPlantNameLabel(text);

            if (crop != null) {
                piu.setGrowthMeter(crop.getGrowthStage());
                piu.setWaterMeter(crop.getWaterLevel());
            } else {
                piu.setGrowthMeter(-1);
                piu.setWaterMeter(0);
            }
        }
    }

    @FXML
    public void interact(MouseEvent e) {
        try {
            Node source = (Node) e.getSource();
            String s = source.toString();
            int column = Integer.parseInt(source.toString().substring(18, 19));
            int row = Integer.parseInt(source.toString().substring(19, 20));
            Crop crop = farmer.getField().getPlot(column, row).getCrop();

            updateRightPaneInspect(crop);

            //handle sowPress
            if (PlantInspectUIController.getSowPress() && crop != null) {
                farmer.getField().getPlot(column, row).harvest(farmer);
                displayCrops();
                updateRightPaneInspect(null);
                invu.updateAvailableQuantity();
            }

            //handle waterPress
            if (PlantInspectUIController.getWaterPress() && crop != null) {
                crop.setWaterLevel(crop.getWaterLevel() + 1);
                updateRightPaneInspect(crop);
            }
        } catch (ImmatureHarvestException i) {
            alertPopUp("Attempted Infanticide", i.getMessage());
        } catch (EmptyPlotException empty) {
            alertPopUp("Empty Plot Harvest", empty.getMessage());
        }
    }

}