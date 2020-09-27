package Farmer;
import Seed.Seed;
import java.util.ArrayList;

public class Farmer {

    private String name;
    private String experienceLevel;
    private int money;
    private ArrayList<Seed> seedBag;
    private String customSkin;

    /**
     * This constructor instantiates a Farmer object for the game.
     * @param name the name of this farmer
     * @param experienceLevel the experience level of this farmer
     */
    public Farmer(String name, String experienceLevel, String customSkin) {
        this.name = name;
        this.experienceLevel = experienceLevel;
        seedBag = new ArrayList<Seed>();
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
    }

    /**
     * This method sets the starting money of the Farmer based on their experience level.
     * @param experienceLevel the experience level of the Farmer
     */
    public void setMoney(String experienceLevel) {
        if (experienceLevel.equals("Beginner")) {
            this.money = 1000;
        } else if (experienceLevel.equals("Intermediate")) {
            this.money = 5000;
        } else {
            money = 10000;
        }
    }

    /**
     * This method adds money to the Farmer's current wealth.
     * @param amount the amount of money to add
     */
    public void addMoney(int amount) {
        money += amount;
    }

    /**
     * This method allows a Farmer to pay for items related to the game.
     * @param amount the amount of money to pay
     */
    public void pay(int amount) {
        money -= amount;
    }

    /**
     * This method returns the current wealth of a Farmer.
     * @return the amount of money that the farmer has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * This method helps a Farmer add a new seed to their collection.
     * @param seed the new seed to add
     */
    public void addSeed(Seed seed) {
        seedBag.add(seed);
    }

    /**
     * This helper method helps assign a predetermined bag of seeds for this Farmer.
     * @param seedBag the bag of seeds assigned to this Farmer
     */
    public void setSeedBag(ArrayList<Seed> seedBag) {
        this.seedBag = seedBag;
    }

    /**
     * This method retrieves all of the seeds that this Farmer currently has.
     * @return a collection of all the seeds that this Farmer currently has
     */
    public ArrayList<Seed> getSeedBag() {
        return seedBag;
    }

    /**
     * This method returns the number of seeds that this Farmer currently has.
     * @return the number of seeds
     */
    public int numOfSeeds() {
        return seedBag.size();
    }

    public String getName() {
        return this.name;
    }

}
