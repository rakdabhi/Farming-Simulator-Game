package farm.ui;

import farmer.Farmer;
import season.Season;
import seed.Seed;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ConfigUIController {

    @FXML
    private HBox configurationScreen;

    @FXML
    private TextField inputName;

    @FXML
    private Circle handRight;

    @FXML
    private Circle head;

    @FXML
    private Circle handLeft;

    @FXML
    private Button skinLight;

    @FXML
    private Button skinTan;

    @FXML
    private Button skinDark;

    @FXML
    private Button appleButton;

    @FXML
    private Button potatoButton;

    @FXML
    private Button cornButton;

    @FXML
    private Button springButton;

    @FXML
    private Button summerButton;

    @FXML
    private Button fallButton;

    @FXML
    private Button winterButton;

    @FXML
    private Button difficultyOne;

    @FXML
    private Button difficultyTwo;

    @FXML
    private Button difficultyThree;

    @FXML
    private Button startButton;

    private String difficultyChoice = "0";

    private String seasonChoice = "";

    private String seedChoice = "";

    private String customSkin = "#efdfbf";

    @FXML
    void seedOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        String oldStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String newStyle = "-fx-background-color: #15936f; -fx-background-radius: 10";
        if (btn == appleButton) {
            seedChoice = "Apple";
            appleButton.setStyle(newStyle);
            potatoButton.setStyle(oldStyle);
            cornButton.setStyle(oldStyle);
        } else if (btn == potatoButton) {
            seedChoice = "Potato";
            appleButton.setStyle(oldStyle);
            potatoButton.setStyle(newStyle);
            cornButton.setStyle(oldStyle);
        } else {
            seedChoice = "Corn";
            appleButton.setStyle(oldStyle);
            potatoButton.setStyle(oldStyle);
            cornButton.setStyle(newStyle);
        }
    }

    @FXML
    void seasonOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        seasonChoice = btn.getText().strip();

        String springStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10;";
        String summerStyle = "-fx-background-color: #fcca5f; -fx-background-radius: 10;";
        String fallStyle = "-fx-background-color: #fd676b; -fx-background-radius: 10;";
        String winterStyle = "-fx-background-color: #51dcfa; -fx-background-radius: 10;";

        if (btn == springButton) {
            springButton.setStyle(springStyle + " -fx-border-color: white; -fx-border-radius: 10");
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle);
        } else if (btn == summerButton) {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle + " -fx-border-color: white; -fx-border-radius: 10");
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle);
        } else if (btn == fallButton) {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle  + " -fx-border-color: white; -fx-border-radius: 10");
            winterButton.setStyle(winterStyle);
        } else {
            springButton.setStyle(springStyle);
            summerButton.setStyle(summerStyle);
            fallButton.setStyle(fallStyle);
            winterButton.setStyle(winterStyle + " -fx-border-color: white; -fx-border-radius: 10");
        }
    }
    @FXML
    void difficultyOnAction(ActionEvent event) {
        Button btn = ((Button) event.getSource());
        String oldStyle = "-fx-background-color: #15ad86; -fx-background-radius: 10";
        String newStyle = "-fx-background-color: #15936f; -fx-background-radius: 10";
        difficultyChoice = btn.getText().strip();
        if (btn == difficultyOne) {
            difficultyOne.setStyle(newStyle);
            difficultyTwo.setStyle(oldStyle);
            difficultyThree.setStyle(oldStyle);
        } else if (btn == difficultyTwo) {
            difficultyOne.setStyle(oldStyle);
            difficultyTwo.setStyle(newStyle);
            difficultyThree.setStyle(oldStyle);
        } else {
            difficultyOne.setStyle(oldStyle);
            difficultyTwo.setStyle(oldStyle);
            difficultyThree.setStyle(newStyle);
        }


    }


    @FXML
    void skinChange(ActionEvent event) {
        customSkin = ((Button) event.getSource()).getText().strip();
        head.setFill(Paint.valueOf(customSkin));
        handLeft.setFill(Paint.valueOf(customSkin));
        handRight.setFill(Paint.valueOf(customSkin));

    }

    /**
     * This method will help create functionality to the Start button
     * and only allow the user to proceed to the next screen if the proper
     * conditions are met.
     * @param event the event
     * @throws Exception an Exception that may be thrown
     */
    public void startButtonOnAction(ActionEvent event) throws Exception {
        String farmerName = inputName.getCharacters().toString();
        String difficultyLevel = difficultyChoice;
        ArrayList<Seed> seedBag = new ArrayList<>();

        try {

            if ((farmerName.equals("")) || (farmerName == null)
                    || (farmerName.strip().equals(""))) {
                throw new IllegalArgumentException();
            }

            if (seedChoice.equals("Apple")) {
                Seed appleSeed = new Seed("Apple");
                seedBag.add(appleSeed);
            } else if (seedChoice.equals("Potato")) {
                Seed potatoSeed = new Seed("Potato");
                seedBag.add(potatoSeed);
            } else if (seedChoice.equals("Corn")) {
                Seed cornSeed = new Seed("Corn");
                seedBag.add(cornSeed);
            }
            if (seedBag.size() == 0) {
                throw new NoSuchElementException();
            }

            if (seasonChoice.equals("")) {
                throw new NullPointerException();
            }

            if (difficultyLevel.equals("0")) {
                throw new ClassNotFoundException();
            }

            Farmer farmer1 = new Farmer(farmerName, difficultyLevel, customSkin);
            farmer1.setSeedBag(seedBag);
            Season startingSeason = new Season(seasonChoice);


            FXMLLoader nextPage = new FXMLLoader(getClass().getResource("FarmUI.fxml"));
            Parent root = nextPage.load();

            MainUIController controller2 = nextPage.getController();
            controller2.setSeason(startingSeason);
            controller2.setFarmer(farmer1);
            controller2.setMoneyLabel(farmer1.getMoney());

            Scene nextPageScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextPageScene);
            window.show();


        } catch (IllegalArgumentException i) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid Name Input");
            a.setContentText("It seems like the you haven't entered a name. "
                    + "Please enter a name!");
            a.showAndWait();
        } catch (NoSuchElementException n) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("No Seeds Chosen");
            a.setContentText("Please select at least one seed to start with!");
            a.showAndWait();
        } catch (NullPointerException u) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("No season Chosen");
            a.setContentText("Please select a season to start farming in!");
            a.showAndWait();
        } catch (ClassNotFoundException c) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("No Difficulty Chosen");
            a.setContentText("Please select your difficulty level!");
            a.showAndWait();
        }
    }

}