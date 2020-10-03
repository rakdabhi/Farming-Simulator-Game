package farm.ui.controllers;


import farmer.Farmer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import season.Season;
import javafx.fxml.FXML;
import javafx.scene.Group;

import java.io.IOException;


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

    private Farmer farmer;

    private Season season;
    private MainPanelUIController mpu;

    private InventoryUIController iuc;

    void setFarmer(Farmer f) {
        farmer = f;
    }

    void setSeason(Season s) {
        season = s;
    }

    void setRightPaneWrapper(AnchorPane a) {
        rightPaneWrapper.getChildren().clear();
        rightPaneWrapper.getChildren().add(a);
    }

    void setMpu(MainPanelUIController mpu) {
        this.mpu = mpu;

    }

    void setIuc(InventoryUIController iuc) {
        this.iuc = iuc;
    }

    /**
     * public void changes() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../style/MainPanelUIController.fxml"));
        AnchorPane node = loader.load();
        rightPaneWrapper.getChildren().add(node);
    } */




}


