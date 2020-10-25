package season;

import clock.Clock;
import farm.ui.controllers.PlotUIController;
import farmer.Farmer;
import javafx.scene.control.Label;

public class Season {
    private String season;
    private int temperature;
    private Clock timer;
    private int day;
    private int hour;

    /**
     * This constructor creates a season with a randomized temperature that falls
     * within the range of the season.
     * @param season one of the four seasons
     */
    public Season(String season) {
        this(season, 1, 0);
    }

    public Season(String season, int day, int hour) {
        this.season = season;
        this.day = day;
        this.hour = hour;
        generateTemperature();
    }

    public Clock createTimer(Farmer farmer, PlotUIController plotu, Label dayLabel,
                             Label hourLabel, Label ampmLabel, int day, int hour) {
        timer = new Clock(farmer, plotu, dayLabel, hourLabel, ampmLabel, day, hour);
        return timer;
    }

    public int getDay() {
        day = timer.getDay();
        return day;
    }

    public int getHour() {
        hour = timer.getHour();
        return hour;
    }

    /**
     * This method helps create a randomized temperature value that falls within
     * the range of the given season.
     */
    public void generateTemperature() {
        if (season.equals("Summer")) {
            temperature = (int) (Math.random() * ((100 - 80) + 1)) + 80;
        } else if (season.equals("Spring")) {
            temperature = (int) (Math.random() * ((79.9 - 67) + 1)) + 67;
        } else if (season.equals("Fall")) {
            temperature = (int) (Math.random() * ((66.9 - 50) + 1)) + 50;
        } else {
            temperature = (int) (Math.random() * (49.9 + 1));
        }
    }

    /**
     * This getter method retrieves the current temperature;
     * @return the current temperature in this season
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * This setter method helps change the current season on the farm and sets the
     * temperature accordingly.
     * @param season the new season
     */
    public void setSeason(String season) {
        this.season = season;
        generateTemperature();
    }

    /**
     * This getter method retrieves the current season.
     * @return the current season
     */
    public String getSeason() {
        return season;
    }

}
