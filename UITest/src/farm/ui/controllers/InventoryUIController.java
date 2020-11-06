package farm.ui.controllers;

import farm.objects.*;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class InventoryUIController {

    @FXML
    private TableView<InventoryItem> inventoryTable;

    @FXML
    private TableColumn<InventoryItem, String> itemCol;

    @FXML
    private TableColumn<InventoryItem, String> quantityCol;

    @FXML
    private Button seedInvBtn;

    @FXML
    private Button cropInvBtn;

    @FXML
    private Button itemInvBtn;

    @FXML
    private AnchorPane rightPaneInventory;

    @FXML
    private Button homeScreenButton;

    @FXML
    private Button accessMarketButton;

    @FXML
    private Group truckGraphic;

    @FXML
    private Group homeGraphic;

    private Farmer farmer;

    private Season season;

    private PlotUIController plotu;

    private MainPanelUIController mpu;

    // |     Initialize Settings     |
    // |                             |

    public void initInventoryUI(Farmer f, Season s,
                                MainPanelUIController mpu, PlotUIController plotu) {
        this.farmer = f;
        this.season = s;
        this.mpu = mpu;
        this.plotu = plotu;

        initTable();
    }

    void initTable() {
        itemCol.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("totalQuantity"));

        for (InventoryItem item : farmer.getInventory().getSeedBag()) {
            inventoryTable.getItems().add(item);
        }


    }



    // |     Getters and Setters     |
    // |                             |

    public void setFarmer(Farmer f) {
        farmer = f;

    }

    public void setSeason(Season s) {
        season = s;

    }

    void setMpu(MainPanelUIController mpu) {
        this.mpu = mpu;
    }

    void setPuc(PlotUIController puc) {
        this.plotu = puc;
    }

    public AnchorPane getRightPaneInventory() {
        return rightPaneInventory;
    }

    // |     Button Behavior         |
    // |                             |

    public void handleHomeScreenButton(ActionEvent actionEvent) {
        plotu.setRightPaneWrapper(mpu.getRightPaneMain());
    }

    public void handleMarketButton(ActionEvent event) throws IOException {
        plotu.dayEndListen();
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

    @FXML
    void homeButtonMouseEnter() {
        lowerRightGraphicTransition(homeGraphic);
    }

    @FXML
    void marketButtonMouseEnter() {
        lowerRightGraphicTransition(truckGraphic);
    }

    void lowerRightGraphicTransition(Group gr) {
        RotateTransition rt = new RotateTransition(new Duration(200), gr);
        rt.setFromAngle(0);
        rt.setToAngle(5);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        rt.play();
    }

    @FXML
    void lowerRightMousePress(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color: #22bf92;"
                + " -fx-background-radius: 10");
    }

    @FXML
    void lowerRightMouseRelease(MouseEvent event) {
        ((Button) event.getSource()).setStyle("-fx-background-color:  #22d2a3;"
                + " -fx-background-radius: 10;");
    }

    @FXML
    void handleInvBtns(ActionEvent e) {
        inventoryTable.getItems().clear();
        InventoryItem[] arr;

        Color selected = Color.web("#feca57");
        Color unselected = Color.web("#22d2a3");


        if ((e == null) || (e.getSource() == seedInvBtn)) {
            arr = farmer.getInventory().getSeedBag();
            seedInvBtn.setTextFill(selected);
            cropInvBtn.setTextFill(unselected);
            itemInvBtn.setTextFill(unselected);
        } else if (e.getSource() == cropInvBtn) {
            arr = farmer.getInventory().getHarvestBag();
            seedInvBtn.setTextFill(unselected);
            cropInvBtn.setTextFill(selected);
            itemInvBtn.setTextFill(unselected);
        } else {
            arr = farmer.getInventory().getItemBag();
            seedInvBtn.setTextFill(unselected);
            cropInvBtn.setTextFill(unselected);
            itemInvBtn.setTextFill(selected);
        }

        for (InventoryItem item : arr) {
            inventoryTable.getItems().add(item);
        }
    }

}
