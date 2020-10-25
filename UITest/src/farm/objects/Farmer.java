package farm.objects;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;

public class Farmer {

    private String name;
    private String experienceLevel;
    private double money;
    private Seed[] seedBag;
    private Seed apple;
    private Seed potato;
    private Seed corn;
    private int inventoryCapacity;
    private int availableCapacity;
    private String customSkin;
    private Field field;

    /**
     * This constructor instantiates a farmer object for the game.
     * @param name the name of this farmer
     * @param experienceLevel the experience level of this farmer
     * @param customSkin the skin color of this farmer
     */
    public Farmer(String name, String experienceLevel, String customSkin) {
        this.name = name;
        this.experienceLevel = experienceLevel;
        apple = new Seed("Apple", 0);
        potato = new Seed("Potato", 0);
        corn = new Seed("Corn", 0);
        seedBag = new Seed[]{apple, potato, corn};
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
        setInventoryCapacity(this.experienceLevel);
        field = new Field(3, 4);
    }

    /**
     * This method sets the starting inventory capacity of the farmer
     * based on their experience level.
     * @param experienceLevel the experience level of the farmer
     */
    public void setInventoryCapacity(String experienceLevel) {
        if (experienceLevel.equals("1")) {
            this.inventoryCapacity = 100;
            this.availableCapacity = 100;
        } else if (experienceLevel.equals("2")) {
            this.inventoryCapacity = 50;
            this.availableCapacity = 50;
        } else {
            this.inventoryCapacity = 25;
            this.availableCapacity = 25;
        }
    }

    /**
     * This method helps the player retrieve the current inventory capacity of their farmer.
     * @return the current inventory capacity of the farmer
     */
    public int getAvailableCapacity() {
        return availableCapacity;
    }

    /**
     * This method helps the player retrieve the total inventory capacity of their farmer.
     * @return the total inventory capacity of the farmer
     */
    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    /**
     * This method sets the starting money of the farmer based on their experience level.
     * @param experienceLevel the experience level of the farmer
     */
    public void setMoney(String experienceLevel) {
        if (experienceLevel.equals("1")) {
            this.money = 200.0;
        } else if (experienceLevel.equals("2")) {
            this.money = 100.0;
        } else {
            money = 50.0;
        }
    }

    /**
     * This method adds money to the farmer's current wealth.
     * @param amount the amount of money to add
     */
    public void addMoney(double amount) {
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
    public void pay(double amount) {
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
    public double getMoney() {
        return money;
    }

    /**
     * This private method helps retrieve the index of a certain seed in the seedBag.
     * @param seed the seed we want an index for
     * @return the index of that seed in seedBag
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    private int getSeedIndex(Seed seed) throws SeedChoiceNotFoundException {
        if (seed.getName().toLowerCase().equals("apple")) {
            return 0;
        } else if (seed.getName().toLowerCase().equals("potato")) {
            return 1;
        } else if (seed.getName().toLowerCase().equals("corn")) {
            return 2;
        } else {
            throw new SeedChoiceNotFoundException("You currently don't have any seeds of this kind "
                    + "in your inventory!");
        }
    }

    /**
     * This method helps a farmer add a Seed to their inventory.
     * @param seed the Seed to add
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void addSeed(Seed seed) throws SeedChoiceNotFoundException {
        if (seed.getQuantity() > availableCapacity) {
            throw new InsufficientInventorySpaceException();
        } else {
            int index = getSeedIndex(seed);
            seedBag[index].addQuantity((seed.getQuantity()));
            seedBag[index].addHarvestable((seed.getHarvestable()));
            availableCapacity -= seed.getQuantity();
        }
    }

    /**
     * This method helps a farmer remove a seed from their inventory.
     * @param seed the seed to remove
     * @throws InsufficientInventorySpaceException if there is insufficient space in the inventory
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void removeSeed(Seed seed)
            throws InsufficientInventorySpaceException, SeedChoiceNotFoundException {
        int index = getSeedIndex(seed);
        if ((seedBag[index].getQuantity() == 0)
                || (seedBag[index].getQuantity() < seed.getQuantity())) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " seeds of this kind in your inventory to remove!");
        } else {
            seedBag[index].removeQuantity(seed.getQuantity());
            seedBag[index].removeHarvestable(seed.getHarvestable());
            availableCapacity += seed.getQuantity();
        }
    }

    /**
     * This helper method helps assign a predetermined bag of seeds for this farmer.
     * @param seeds the bag of seeds assigned to this farmer
     */
    public void setSeedBag(Seed[] seeds) {
        int numOfSeeds = seeds[0].getQuantity() + seeds[1].getQuantity() + seeds[2].getQuantity();

        if (numOfSeeds > availableCapacity) {
            throw new InsufficientInventorySpaceException();
        }
        this.seedBag = seeds;
        availableCapacity -= numOfSeeds;
    }

    /**
     * This method retrieves all of the seeds that this farmer currently has.
     * @return a collection of all the seeds that this farmer currently has
     */
    public Seed[] getSeedBag() {
        return seedBag;
    }

    /**
     * This method returns the number of seeds that this farmer currently has.
     * @return the number of seeds
     */
    public int numOfSeeds() {
        return seedBag[0].getQuantity() + seedBag[1].getQuantity() + seedBag[2].getQuantity();
    }

    /**
     * This method returns the name of the Farmer.
     * @return the name of the farmer
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the experience level of the Farmer.
     * @return the experience level of the farmer
     */
    public String getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * This method returns the field.
     * @return the field
     */
    public Field getField() {
        return field;
    }
}
