package farm.ui.controllers;


import farmer.Farmer;
import javafx.scene.layout.AnchorPane;
import season.Season;
import javafx.fxml.FXML;
import javafx.scene.Group;


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

    private InventoryUIController invu;

    // |     Initialize Settings     |
    // |                             |

    public void initPlotUI (Farmer f, Season s, MainPanelUIController mpu, InventoryUIController invu) {
        this.farmer = f;
        this.season = s;
        this.mpu = mpu;
        this.invu = invu;

        setRightPaneWrapper(mpu.getRightPaneMain());
    }

    // |     Getters and Setters     |
    // |                             |

    void setRightPaneWrapper(AnchorPane a) {
        rightPaneWrapper.getChildren().clear();
        rightPaneWrapper.getChildren().add(a);
    }

}


