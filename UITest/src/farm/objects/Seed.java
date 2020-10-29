package farm.objects;

public class Seed {
    private String name;
    private int seedID;

    /**
     * This constructor instantiates a seed object that a farmer
     * will hold with a given quantity.
     * @param name the name of the seed
     */
    public Seed(String name) {
        this.name = name;

        if (name.toLowerCase().equals("apple")) {
            seedID = 0;
        } else if (name.toLowerCase().equals("potato")) {
            seedID = 1;
        } else if (name.toLowerCase().equals("corn")) {
            seedID = 2;
        }
    }

    /**
     * This getter method retrieves the name of this seed.
     * @return the name of this seed
     */
    public String getName() {
        return name;
    }

    public int getSeedID() {
        return seedID;
    }


    /**
     * This setter method sets the type of seed.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}