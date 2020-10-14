package farm.ui.controllers;


import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import farm.objects.Crop;
import farm.objects.Plot;
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

    private Group[][] plotArray;

    // |     Initialize Settings     |
    // |                             |

    public void initPlotUI(Farmer f, Season s, MainPanelUIController mpu,
                           InventoryUIController invu) {
        this.farmer = f;
        this.season = s;
        this.mpu = mpu;
        this.invu = invu;
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

    @FXML
    public void interact(MouseEvent e) {
        try {
            Node source = (Node) e.getSource();
            int column = 0;
            int row = 0;
            boolean found = false;
            for (row = 0; row < farmer.getField().getRows(); row++) {
                for (column = 0; column < farmer.getField().getColumns(); column++) {
                    int index = farmer.getField().getRows() * column + row;
                    if (source == plotGrid.getChildren().get(index)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!MainPanelUIController.getSowPress() && !MainPanelUIController.getWaterPress()) {
                farmer.getField().getPlot(column, row).harvest();
                displayCrops();
            }
            //handle sow and water press
        } catch (ImmatureHarvestException i) {
            alertPopUp("Attempted Infanticide", i.getMessage());
        } catch (EmptyPlotException empty) {
            alertPopUp("Empty Plot Harvest", empty.getMessage());
        }
    }
}


