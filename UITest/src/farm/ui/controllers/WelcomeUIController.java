package farm.ui.controllers;

import farm.objects.Farmer;
import farm.objects.InventoryItem;
import farm.objects.LoadGame;
import farm.objects.Season;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;


/**
 * Controls Welcome Screen events.
 */
public class WelcomeUIController {

    @FXML
    private Text pressAnyKey;

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Group cloud1;

    @FXML
    private Group cloud2;

    @FXML
    private Group cloud3;

    private Scene s;

    public void initWelcome() {
        Scene s = loadGameButton.getScene();

        s.addEventFilter(KeyEvent.ANY, KeyEvent -> {
            try {
                keyPress(KeyEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cloudAnimation();
    }

    @FXML
    public void keyPress(KeyEvent event) {
        pressAnyKey.setVisible(false);
        newGameButton.setVisible(true);
        loadGameButton.setVisible(true);
    }

    @FXML
    void handleNewGame(ActionEvent e) throws IOException {
        Parent nextPage = FXMLLoader.load(getClass()
                .getResource("../style/ConfigurationScreenUI.fxml"));
        Scene nextPageScene = new Scene(nextPage);
        Stage window = (Stage) (newGameButton.getScene()).getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    @FXML
    void handleLoadGame(ActionEvent e) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(loadGameButton.getScene().getWindow());
            LoadGame lg = new LoadGame();

            if (file != null) {
                lg.fileIn(file);
            }


            Farmer farmer1 = lg.getFarmer();
            Season s = lg.getSeason();
            Season startingSeason = new Season(s.getSeason(), s.getSaveDay(), s.getSaveHour());

            for (InventoryItem i : farmer1.getInventory().getHarvestBag()) {
                i.setIntegerPropertiesFromSave();
            }

            FXMLLoader nextPage =
                    new FXMLLoader(getClass().getResource("../style/PlotUI.fxml"));
            Parent root = nextPage.load();
            PlotUIController plotController = nextPage.getController();

            FXMLLoader loadMain =
                    new FXMLLoader(getClass().getResource("../style/MainPanelUI.fxml"));
            loadMain.load();
            MainPanelUIController mainPanelController = loadMain.getController();

            FXMLLoader loadInventory =
                    new FXMLLoader(getClass().getResource("../style/InventoryUI.fxml"));
            loadInventory.load();
            InventoryUIController inventoryController = loadInventory.getController();

            FXMLLoader loadInspect =
                    new FXMLLoader(getClass().getResource("../style/PlantInspectUI.fxml"));
            loadInspect.load();
            PlantInspectUIController inspectController = loadInspect.getController();

            plotController.initPlotUI(farmer1, startingSeason,
                    mainPanelController, inventoryController, inspectController);
            mainPanelController.initMainPanelUI(farmer1, startingSeason,
                    plotController, inventoryController, inspectController);
            inventoryController.initInventoryUI(farmer1, startingSeason,
                    mainPanelController, plotController);
            inspectController.initPlantInspectUI(farmer1, startingSeason,
                    plotController, mainPanelController);

            Scene nextPageScene = new Scene(root);
            Stage window = (Stage) (loadGameButton.getScene()).getWindow();
            window.setScene(nextPageScene);
            window.show();
        } catch (NullPointerException i) {
            System.out.println("File not found; null pointer exception. ");
        }

    }

    void cloudAnimation() {
        TranslateTransition t1x = new TranslateTransition(Duration.seconds(80));
        t1x.setToX(1100);
        t1x.setCycleCount(1);
        t1x.setNode(cloud1);

        TranslateTransition t1y = new TranslateTransition(Duration.seconds(10));
        t1y.setToY(-15);
        t1y.setCycleCount(Animation.INDEFINITE);
        t1y.setAutoReverse(true);
        t1y.setNode(cloud1);

        TranslateTransition t2x = new TranslateTransition(Duration.seconds(90));
        t2x.setToX(-500);
        t2x.setCycleCount(1);
        t2x.setNode(cloud2);

        TranslateTransition t2y = new TranslateTransition(Duration.seconds(10));
        t2y.setToY(-15);
        t2y.setCycleCount(Animation.INDEFINITE);
        t2y.setAutoReverse(true);
        t2y.setNode(cloud2);

        TranslateTransition t3x = new TranslateTransition(Duration.seconds(70));
        t3x.setToX(-500);
        t3x.setCycleCount(1);
        t3x.setNode(cloud3);

        TranslateTransition t3y = new TranslateTransition(Duration.seconds(10));
        t3y.setToY(20);
        t3y.setCycleCount(Animation.INDEFINITE);
        t3y.setAutoReverse(true);
        t3y.setNode(cloud3);

        ParallelTransition pt = new ParallelTransition(t1x, t1y, t2x, t2y, t3x, t3y);
        pt.play();
    }
}
