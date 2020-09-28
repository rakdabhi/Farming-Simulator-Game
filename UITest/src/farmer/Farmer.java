package farmer;
import exceptions.InsufficientFundsException;
import seed.Seed;
import java.util.ArrayList;

public class Farmer {

    private String name;
    private String experienceLevel;
    private int money;
    private ArrayList<Seed> seedBag;
    private String customSkin;

    /**
     * This constructor instantiates a farmer object for the game.
     * @param name the name of this farmer
     * @param experienceLevel the experience level of this farmer
     * @param customSkin the skin color of this farmer
     */
    public Farmer(String name, String experienceLevel, String customSkin) {
        this.name = name;
        this.experienceLevel = experienceLevel;
        seedBag = new ArrayList<Seed>();
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
    }

    /**
     * This method sets the starting money of the farmer based on their experience level.
     * @param experienceLevel the experience level of the farmer
     */
    public void setMoney(String experienceLevel) {
        if (experienceLevel.equals("1")) {
            this.money = 10000;
        } else if (experienceLevel.equals("2")) {
            this.money = 5000;
        } else {
            money = 1000;
        }
    }

    /**
     * This method adds money to the farmer's current wealth.
     * @param amount the amount of money to add
     */
    public void addMoney(int amount) {
        if (amount < 0) {
            pay(-1 * amount);
        } else {
            money += amount;
        }
    }

    /**
     * This method allows a farmer to pay for items related to the game.
     * @param amount the amount of money to pay
     */
    public void pay(int amount) {
        if (amount > money) {
            throw new InsufficientFundsException();
        } else {
            money -= amount;
        }
    }

    /**
     * This method returns the current wealth of a farmer.
     * @return the amount of money that the farmer has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * This method helps a farmer add a new seed to their collection.
     * @param seed the new seed to add
     */
    public void addSeed(Seed seed) {
        seedBag.add(seed);
    }

    /**
     * This helper method helps assign a predetermined bag of seeds for this farmer.
     * @param seedBag the bag of seeds assigned to this farmer
     */
    public void setSeedBag(ArrayList<Seed> seedBag) {
        this.seedBag = seedBag;
    }

    /**
     * This method retrieves all of the seeds that this farmer currently has.
     * @return a collection of all the seeds that this farmer currently has
     */
    public ArrayList<Seed> getSeedBag() {
        return seedBag;
    }

    /**
     * This method returns the number of seeds that this farmer currently has.
     * @return the number of seeds
     */
    public int numOfSeeds() {
        return seedBag.size();
    }

    /**
     * This method returns the name of the Farmer.
     * @return the name of the farmer
     */
    public String getName() {
        return this.name;
    }
}
