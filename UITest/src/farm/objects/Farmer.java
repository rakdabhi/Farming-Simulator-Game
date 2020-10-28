package farm.objects;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;

public class Farmer {

    private String name;
    private String experienceLevel;
    private double money;
    private Inventory inventory;
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
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
        this.inventory = new Inventory(Integer.parseInt(experienceLevel));
        field = new Field(3, 4);
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

    public Inventory getInventory() { return inventory; }
}
