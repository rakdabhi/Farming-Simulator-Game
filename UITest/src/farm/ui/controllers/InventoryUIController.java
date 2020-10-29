package farm.ui.controllers;

import farm.objects.Farmer;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;
import farm.objects.Season;
import farm.objects.Seed;

import java.io.IOException;

public class InventoryUIController {

    private int appleSeedQuantity;
    private int potatoSeedQuantity;
    private int cornSeedQuantity;
    private int appleHarvestQuantity;
    private int potatoHarvestQuantity;
    private int cornHarvestQuantity;

    @FXML
    private Label appleSeedQuantityLabel;

    @FXML
    private Label potatoSeedQuantityLabel;

    @FXML
    private Label cornSeedQuantityLabel;

    @FXML
    private Label appleHarvestQuantityLabel;

    @FXML
    private Label potatoHarvestQuantityLabel;

    @FXML
    private Label cornHarvestQuantityLabel;

    private PlotUIController plotu;

    @FXML
    private SVGPath appleSeedInventory;

    @FXML
    private SVGPath potatoSeedInventory;

    @FXML
    private SVGPath cornSeedInventory;

    @FXML
    private Label numAppleSeeds;

    @FXML
    private Label numPotatoSeeds;

    @FXML
    private Label numCornSeeds;

    @FXML
    private Label cornLabel;

    private MainPanelUIController mpu;
    @FXML
    private Label potatoLabel;

    @FXML
    private Label appleLabel;

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
        updateAvailableQuantity();
    }

    /**
     * This helper method helps get the current quantities of the seeds that the farmer object has.
     */
    private void getQuantities() {
        appleSeedQuantity = farmer.getInventory().getSeedBag()[0];
        potatoSeedQuantity = farmer.getInventory().getSeedBag()[1];
        cornSeedQuantity = farmer.getInventory().getSeedBag()[2];
        appleHarvestQuantity = farmer.getInventory().getHarvestBag()[0];
        potatoHarvestQuantity = farmer.getInventory().getHarvestBag()[1];
        cornHarvestQuantity = farmer.getInventory().getHarvestBag()[2];
    }
    /**
     * This helper method helps display the quantity of seeds that this farmer has.
     */
    void updateAvailableQuantity() {
        getQuantities();
        appleSeedQuantityLabel.setText(String.format("x%02d", appleSeedQuantity));
        potatoSeedQuantityLabel.setText(String.format("x%02d", potatoSeedQuantity));
        cornSeedQuantityLabel.setText(String.format("x%02d", cornSeedQuantity));
        appleHarvestQuantityLabel.setText(String.format("x%02d", appleHarvestQuantity));
        potatoHarvestQuantityLabel.setText(String.format("x%02d", potatoHarvestQuantity));
        cornHarvestQuantityLabel.setText(String.format("x%02d", cornHarvestQuantity));
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
}
