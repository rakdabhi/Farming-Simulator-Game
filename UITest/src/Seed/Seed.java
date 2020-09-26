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
}
