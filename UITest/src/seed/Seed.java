package seed;

import exceptions.InsufficientInventorySpaceException;

public class Seed {
    private String name;
    private int quantity;
    private int harvestable;

    /**
     * This constructor instantiates a seed object that a farmer
     * will hold with a predetermined quantity.
     * @param name the name of the seed
     */
    public Seed(String name) {
        this(name, 1, 0);
    }

    /**
     * This constructor instantiates a seed object that a farmer
     * will hold with a given quantity.
     * @param name the name of the seed
     * @param quantity the number of this specific type of seed
     */
    public Seed(String name, int quantity) {
        this(name, quantity, 0);
    }

    /**
     * This constructor instantiates a seed object that a farmer
     * will hold with a given quantity.
     * @param name the name of the seed
     * @param quantity the number of this specific type of seed
     * @param harvestable the number of harvestable plants of this type of seed
     */
    public Seed(String name, int quantity, int harvestable) {
        this.name = name;
        this.quantity = quantity;
        this.harvestable = harvestable;
    }

    /**
     * This getter method retrieves the name of this seed.
     * @return the name of this seed
     */
    public String getName() {
        return name;
    }

    /**
     * This getter method retrieves the quantity of this particular seed.
     * @return the quantity of this seed
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This getter method retrieves the quantity of harvestable plants of this particular seed.
     * @return the quantity harvestable plants
     */
    public int getHarvestable() {
        return harvestable;
    }

    /**
     * This method helps a farmer add more quantity of this particular seed to their seedBag.
     * @param amount the amount of seeds to add
     */
    public void addQuantity(int amount) {
        quantity += amount;
    }

    /**
     * This method helps a farmer add more quantity of this particular seed to their seedBag.
     * @param amount the amount of seeds to add
     */
    public void addHarvestable(int amount) {
        harvestable += amount;
    }

    /**
     * This method helps a farmer remove a certain quantity of this
     * particular seed from their seedBag.
     * @param amount the amount of seeds to remove
     */
    public void removeQuantity(int amount)  {
        if (amount > quantity) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " seeds of this kind in your inventory to remove!");
        } else {
            quantity -= amount;
        }
    }

    /**
     * This method helps a farmer remove a certain quantity of this
     * particular seed from their seedBag.
     * @param amount the amount of seeds to remove
     */
    public void removeHarvestable(int amount)  {
        if (amount > harvestable) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " seeds of this kind in your inventory to remove!");
        } else {
            harvestable -= amount;
        }
    }
}