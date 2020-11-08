package farm.objects;

import javafx.scene.control.Alert;

import java.util.Random;

public class RandomEvent {

    private Random num = new Random();
    private Season season;
    private String seasonString;
    private int rainMin;
    private int rainMax;
    private int rainLevel;
    private int droughtMin;
    private int droughtMax;
    private int droughtLevel;
    private int locustMin;
    private int locustMax;
    private String errorHeader;
    private String errorMessage;
    private int deadFromLocusts;

    public RandomEvent(String season) {
        this.seasonString = season;
        this.errorHeader = "";
        this.errorMessage = "";
        this.deadFromLocusts = 0;
        setRanges();
        this.rainLevel = num.nextInt(4) + 1;
        this.droughtLevel = num.nextInt(4) + 1;
    }
    public RandomEvent(Season season) {
        this.season = season;
        this.errorHeader = "";
        this.errorMessage = "";
        this.deadFromLocusts = 0;
        setRanges();
        this.rainLevel = num.nextInt(4) + 1;
        this.droughtLevel = num.nextInt(4) + 1;
    }

    private void setRanges() {
        if (seasonString.equals("Spring")
            || season.getSeason().equals("Spring")) {
            rainMin = 0;
            rainMax = 20;
            droughtMin = 30;
            droughtMax = 37;
            locustMin = 60;
            locustMax = 67;
        } else if (seasonString.equals("Summer")
            || season.getSeason().equals("Summer")) {
            rainMin = 0;
            rainMax = 10;
            droughtMin = 30;
            droughtMax = 50;
            locustMin = 60;
            locustMax = 75;
        } else if (seasonString.equals("Fall")
            || season.getSeason().equals("Fall")) {
            rainMin = 0;
            rainMax = 7;
            droughtMin = 30;
            droughtMax = 37;
            locustMin = 60;
            locustMax = 67;
        } else {
            rainMin = 0;
            rainMax = 5;
            droughtMin = 30;
            droughtMax = 35;
            locustMin = 60;
            locustMax = 65;
        }
    }

    public void generateNewRainAndDroughtLevels() {
        rainLevel = num.nextInt(4) + 1;
        droughtLevel = num.nextInt(4) + 1;
    }

    public void performRandomEvent(Crop crop, int chance) {
        if (chance >= rainMin && chance <= rainMax) {
            performRain(crop);
        } else if (chance >= droughtMin && chance <= droughtMax) {
            performDrought(crop);
        } else if (chance >= locustMin && chance <= locustMax) {
            performLocustAttack(crop);
        }
    }

    public void performRain(Crop crop) {
        crop.rain(rainLevel);
        errorHeader = "New Day Update: Hit with rain!";
        errorMessage = "Today, rain hit your farm and \n"
            + "raised the crops' water level by " + rainLevel + "!";
    }

    public void performDrought(Crop crop) {
        crop.drought(droughtLevel);
        errorHeader = "New Day Update: Hit with drought!";
        errorMessage = "Today, a drought hit your farm and \n"
            + "lowered the crops' water level by " + droughtLevel + "!";
    }

    public void performLocustAttack(Crop crop) {
        if (!crop.isPesticideTreated() && !crop.isDead()) {
            crop.setDead();
            deadFromLocusts++;
        }
        errorHeader = "New Day Update: Hit with locusts!";
        errorMessage = "Today, a swarm of locusts ate and killed " + deadFromLocusts + " crops!";
    }

    public String getErrorHeader() {
        return errorHeader;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void resetDeadFromLocusts() {
        deadFromLocusts = 0;
    }

    static void alertPopUp(String errorHeader, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("ATTENTION!");
        a.setHeaderText(errorHeader);
        a.setContentText(message);
        a.showAndWait();
    }
}
