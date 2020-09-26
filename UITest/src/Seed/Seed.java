package Seed;

public class Seed {
    private String name;
    private int quantity;

    /**
     * This constructor instantiates a Seed object that a Farmer will hold with a predetermined quantity.
     * @param name the name of the seed
     */
    public Seed(String name) {
        this(name, 1);
    }

    /**
     * This constructor instantiates a Seed object that a Farmer will hold with a given quantity.
     * @param name the name of the seed
     * @param quantity the number of this specific type of seed
     */
    public Seed(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * This getter method retrieves the name of this Seed.
     * @return the name of this Seed
     */
    public String getName() {
        return name;
    }

    /**
     * This getter method retrieves the quantity of this particular Seed.
     * @return the quantity of this Seed
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method helps a Farmer add more quantity of this particular Seed to their seedBag.
     * @param amount the amount of seeds to add
     */
    public void addQuantity(int amount) {
        quantity += amount;
    }

    /**
     * This method helps a Farmer remove a certain quantity of this particular Seed from their seedBag.
     * @param amount the amount of seeds to remove
     */
    public void removeQuantity(int amount) {
        quantity -= amount;
    }
}

