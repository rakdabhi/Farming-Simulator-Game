package farm.ui.controllers;

import farmer.Farmer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import season.Season;
import seed.Seed;

public class InventoryUIController {

    private PlotUIController puc;

    private MainPanelUIController mpu;

    @FXML
    private TableView<Seed> inventoryList;

    @FXML
    private AnchorPane rightPaneMain;

    @FXML
    private AnchorPane rightPaneInventory;

    @FXML
    private Button homeScreenButton;

    @FXML
    private Button accessMarketButton;

    private Farmer farmer;

    private Season season;

    public AnchorPane getRightPaneInventory() {
        return rightPaneInventory;
    }

    public void handleHomeScreenButton(ActionEvent actionEvent) {
        puc.setRightPaneWrapper(mpu.getRightPaneMain());
    }

    public void handleMarketButton(ActionEvent actionEvent) {
    }

    public void setFarmer(Farmer f) {

    }

    public void setSeason(Season s) {

    }

    void setMpu(MainPanelUIController mpu) {
        this.mpu = mpu;
    }

    void setPuc(PlotUIController puc) {
        this.puc = puc;
    }
}
