package farm.ui.controllers;

import farmer.Farmer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import season.Season;
import seed.Seed;

import java.io.IOException;

public class InventoryUIController {

    private PlotUIController plotu;

    private MainPanelUIController mpu;

    @FXML
    private TableView<Seed> inventoryList;

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

    // |     Initialize Settings     |
    // |                             |

    public void initInventoryUI(Farmer f, Season s,
                                MainPanelUIController mpu, PlotUIController plotu) {
        this.farmer = f;
        this.season = s;
        this.mpu = mpu;
        this.plotu = plotu;
    }


    // |     Getters and Setters     |
    // |                             |

    public void setFarmer(Farmer f) {

    }

    public void setSeason(Season s) {

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

    // |  Table Population +  Behavior  |
    // |                                |


}
