package farm.ui.controllers;

import farmer.Farmer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import season.Season;

import java.io.IOException;

public class MarketBuyUIController {

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
    private Button buyButton;

    @FXML
    private Label itemName;

    @FXML
    private Label itemDescription;

    @FXML
    private Button buyTab;

    @FXML
    private Button sellTab;

    private Farmer farmer;

    private Season season;


    public void initMarketBuy(Farmer f, Season s) {
        this.farmer = f;
        this.season = s;
    }

    @FXML
    void handleSellTab(ActionEvent event) throws IOException {
        FXMLLoader loadMarketSell = new FXMLLoader(getClass().getResource("../style/MarketSellUI.fxml"));
        Parent root = loadMarketSell.load();
        MarketSellUIController msu = loadMarketSell.getController();
        msu.initMarketSell(farmer, season);

        Scene nextPageScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    @FXML
    void handleHomeScreenButton(ActionEvent event) throws IOException {
        FXMLLoader nextPage = new FXMLLoader(getClass().getResource("../style/PlotUI.fxml"));
        Parent root = nextPage.load();
        PlotUIController plotController = nextPage.getController();

        FXMLLoader loadMain = new FXMLLoader(getClass().getResource("../style/MainPanelUI.fxml"));
        loadMain.load();
        MainPanelUIController mainPanelController = loadMain.getController();

        FXMLLoader loadInventory = new FXMLLoader(getClass().getResource("../style/InventoryUI.fxml"));
        loadInventory.load();
        InventoryUIController inventoryController = loadInventory.getController();

        plotController.initPlotUI(farmer, season, mainPanelController, inventoryController);
        mainPanelController.initMainPanelUI(farmer, season, plotController, inventoryController);
        inventoryController.initInventoryUI(farmer, season, mainPanelController, plotController);

        Scene nextPageScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }


    @FXML
    void handleBuyButton(ActionEvent event) {

    }


    @FXML
    void handleMinusButton(ActionEvent event) {

    }

    @FXML
    void handlePlusButton(ActionEvent event) {

    }

    @FXML
    void homeButtonMouseEnter(MouseEvent event) {

    }

    @FXML
    void lowerRightMousePress(MouseEvent event) {

    }

    @FXML
    void lowerRightMouseRelease(MouseEvent event) {

    }

}
