package FarmUI;
import Farmer.Farmer;
import Seed.Seed;
import Season.Season;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class ConfigurationScreenController implements Initializable {

    public Button startButton;
    public TextField enterName;
    public ImageView farmerImage;
    public CheckBox corn;
    public CheckBox wheat;
    public CheckBox tomato;
    public CheckBox potato;
    public CheckBox apple;
    public CheckBox peach;
    public CheckBox lettuce;
    public CheckBox soybean;
    public CheckBox squash;
    public CheckBox pepper;
    public CheckBox radish;
    public CheckBox spinach;
    public ImageView cornImage;
    public ImageView wheatImage;
    public ImageView tomatoImage;
    public ImageView potatoImage;
    public ImageView appleImage;
    public ImageView peachImage;
    public ImageView lettuceImage;
    public ImageView soybeanImage;
    public ImageView squashImage;
    public ImageView pepperImage;
    public ImageView radishImage;
    public ImageView spinachImage;
    public ChoiceBox seasonChoiceBox;
    public ChoiceBox difficultyLevelChoiceBox;


    /**
     * This method will help create functionality to the Start button
     * and only allow the user to proceed to the next screen if the proper
     * conditions are met.
     * @param event the event
     * @throws Exception an Exception that may be thrown
     */
    public void startButton(ActionEvent event) throws Exception {
        String farmerName = enterName.getCharacters().toString();
        String difficultyLevel = (String) difficultyLevelChoiceBox.getValue();
        ArrayList<Seed> seedBag = new ArrayList<>();

        try {

            if ((farmerName.equals("")) || (farmerName == null)
                || (farmerName.strip().equals(""))) {
                throw new IllegalArgumentException();
            }

            if (corn.isSelected()) {
                Seed cornSeed = new Seed("Corn");
                seedBag.add(cornSeed);
            }
            if (wheat.isSelected()) {
                Seed wheatSeed = new Seed("Wheat");
                seedBag.add(wheatSeed);
            }
            if (tomato.isSelected()) {
                Seed tomatoSeed = new Seed("Tomato");
                seedBag.add(tomatoSeed);
            }
            if (potato.isSelected()) {
                Seed potatoSeed = new Seed("Potato");
                seedBag.add(potatoSeed);
            }
            if (apple.isSelected()) {
                Seed appleSeed = new Seed("Apple");
                seedBag.add(appleSeed);
            }
            if (peach.isSelected()) {
                Seed peachSeed = new Seed("Peach");
                seedBag.add(peachSeed);
            }
            if (lettuce.isSelected()) {
                Seed lettuceSeed = new Seed("Lettuce");
                seedBag.add(lettuceSeed);
            }
            if (soybean.isSelected()) {
                Seed soybeanSeed = new Seed("Soybean");
                seedBag.add(soybeanSeed);
            }
            if (squash.isSelected()) {
                Seed squashSeed = new Seed("Squash");
                seedBag.add(squashSeed);
            }
            if (pepper.isSelected()) {
                Seed pepperSeed = new Seed("Pepper");
                seedBag.add(pepperSeed);
            }
            if (radish.isSelected()) {
                Seed radishSeed = new Seed("Radish");
                seedBag.add(radishSeed);
            }
            if (spinach.isSelected()) {
                Seed spinachSeed = new Seed("Spinach");
                seedBag.add(spinachSeed);
            }
            if (seedBag.size() == 0) {
                throw new NoSuchElementException();
            }

            if (seasonChoiceBox.getValue().equals("Select Season")) {
                throw new NullPointerException();
            }

            if (difficultyLevel.equals("Select Difficulty")) {
                throw new ClassNotFoundException();
            }

            Farmer farmer1 = new Farmer(farmerName, difficultyLevel);
            farmer1.setSeedBag(seedBag);
            Season startingSeason = new Season((String) seasonChoiceBox.getValue());

            Parent nextPage = FXMLLoader.load(getClass().getResource("FarmUI.fxml"));
            Scene nextPageScene = new Scene(nextPage);
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
            a.setHeaderText("No Season Chosen");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image farmer = new Image(getClass().getResourceAsStream("/Farmer.png"));
        farmerImage.setImage(farmer);

        Image corn = new Image(getClass().getResourceAsStream("/CornSeed.png"));
        cornImage.setImage(corn);

        Image wheat = new Image(getClass().getResourceAsStream("/WheatSeed.png"));
        wheatImage.setImage(wheat);

        Image tomato = new Image(getClass().getResourceAsStream("/TomatoSeed.png"));
        tomatoImage.setImage(tomato);

        Image potato = new Image(getClass().getResourceAsStream("/potatoSeed.png"));
        potatoImage.setImage(potato);

        Image apple = new Image(getClass().getResourceAsStream("/AppleSeed.png"));
        appleImage.setImage(apple);

        Image peach = new Image(getClass().getResourceAsStream("/PeachSeed.png"));
        peachImage.setImage(peach);

        Image lettuce = new Image(getClass().getResourceAsStream("/LettuceSeed.png"));
        lettuceImage.setImage(lettuce);

        Image soybean = new Image(getClass().getResourceAsStream("/SoybeanSeed.png"));
        soybeanImage.setImage(soybean);

        Image squash = new Image(getClass().getResourceAsStream("/SquashSeed.png"));
        squashImage.setImage(squash);

        Image pepper = new Image(getClass().getResourceAsStream("/PepperSeed.png"));
        pepperImage.setImage(pepper);

        Image radish = new Image(getClass().getResourceAsStream("/RadishSeed.png"));
        radishImage.setImage(radish);

        Image spinach = new Image(getClass().getResourceAsStream("/SpinachSeed.png"));
        spinachImage.setImage(spinach);

        ObservableList<String> seasonList = FXCollections.observableArrayList("Select Season", "Spring", "Summer", "Fall", "Winter");
        seasonChoiceBox.setValue("Select Season");
        seasonChoiceBox.setItems(seasonList);

        ObservableList<String> difficultyLevel = FXCollections.observableArrayList("Select Difficulty", "Beginner", "Intermediate", "Advanced");
        difficultyLevelChoiceBox.setValue("Select Difficulty");
        difficultyLevelChoiceBox.setItems(difficultyLevel);
    }
}
