package farm.ui;

import season.Season;
import farmer.Farmer;

public class GameData {
    private Season gameSeason;
    private Farmer gameFarmer;

    public GameData(Season season, Farmer farmer) {
        gameSeason = season;
        gameFarmer = farmer;
    }


}
