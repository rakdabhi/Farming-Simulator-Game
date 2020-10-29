package farm.objects;

import java.util.Random;

public class Season {
    private String season;
    private int temperature;
    private Clock timer;
    private int day;
    private int hour;
    private Random rand;

    /**
     * This constructor creates a season with a randomized temperature that falls
     * within the range of the season.
     * @param season one of the four seasons
     */
    public Season(String season) {
        this(season, 1, 8);
    }

    public Season(String season, int day, int hour) {
        this.season = season;
        this.timer = new Clock(day, hour);
        this.rand = new Random();
        this.temperature = generateTemperature();
    }


    public Clock getTimer() {
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
     * @return the temperature
     */
    public int generateTemperature() {
        if (season.equals("Summer")) {
            return rand.nextInt(100 - 80) + 80;
        } else if (season.equals("Spring")) {
            return rand.nextInt(80 - 67) + 67;
        } else if (season.equals("Fall")) {
            return rand.nextInt(67 - 50) + 50;
        } else {
            return rand.nextInt(50);
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
