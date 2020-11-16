package farm.objects;

public class Seed implements java.io.Serializable {
    private String name;
    private int seedID;
    private double baseSell;
    private double div;

    /**
     * This constructor instantiates a seed object that a farmer
     * will hold with a given quantity.
     * @param name the name of the seed
     */
    public Seed(String name) {
        this.name = name;

        if (name.toLowerCase().equals("apple")) {
            seedID = 0;
            baseSell = 2.89;
            div = 2.59;
        } else if (name.toLowerCase().equals("potato")) {
            seedID = 1;
            baseSell = 4.22;
            div = 2.33;
        } else if (name.toLowerCase().equals("corn")) {
            seedID = 2;
            baseSell = 3.35;
            div = 3.36;
        }
    }

    public Seed(int seedID) {
        this.seedID = seedID;
        if (this.seedID == 0) {
            this.name = "apple";
            baseSell = 2.89;
            div = 2.59;
        } else if (this.seedID == 1) {
            this.name = "potato";
            baseSell = 4.22;
            div = 2.33;
        } else if (this.seedID == 2) {
            this.name = "corn";
            baseSell = 3.35;
            div = 3.36;
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

    public double getBaseSell() {
        return baseSell;
    }

    public double getDiv() {
        return div;
    }
}