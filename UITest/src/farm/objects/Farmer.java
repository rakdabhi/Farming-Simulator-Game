package farm.objects;
import exceptions.InsufficientFundsException;

public class Farmer {

    private String name;
    private String experienceLevel;
    private double money;
    private Inventory inventory;
    private String customSkin;
    private Field[] fields = new Field[3];
    private double nextFieldCost;
    private int fieldsSize;
    private int currFieldIndex;
    private Farmhand fh;

    /**
     * This constructor instantiates a farmer object for the game.
     * @param name the name of this farmer
     * @param experienceLevel the experience level of this farmer
     * @param customSkin the skin color of this farmer
     */
    public Farmer(String name, String experienceLevel, String customSkin) {
        this.name = name;
        this.experienceLevel = experienceLevel;
        nextFieldCost = 200 * Integer.parseInt(experienceLevel);
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
        this.inventory = new Inventory(Integer.parseInt(experienceLevel));
        initFieldData();
        fh = new Farmhand();
    }

    private void initFieldData() {
        for (Field f : fields) {
            f = null;
        }
        currFieldIndex = 0;
        fields[currFieldIndex] = new Field(3, 4, true);
        fieldsSize = 1;
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
     * This method returns the current field.
     * @return the field
     */
    public Field getField() {
        return fields[currFieldIndex];
    }

    /**
     * This method returns the current field index in the fields array.
     * @return the field index.
     */
    public int getCurrFieldIndex() {
        return currFieldIndex;
    }

    /**
     * This method returns the fields array.
     * @return the fields array.
     */
    public Field[] getAllFields() {
        return fields;
    }

    public void setCurrFieldIndex(int i) {
        if (i >= 0 && i < fieldsSize) {
            currFieldIndex = i;
        }
    }

    public double getNextFieldCost() {
        return nextFieldCost;
    }

    public void incrementNextFieldCost() {
        nextFieldCost += (Integer.parseInt(experienceLevel) * 50);
    }

    public int getFieldsSize() {
        return fieldsSize;
    }

    public void incrementFieldSize() {
        fieldsSize += 1;
        incrementNextFieldCost();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setNewFarmhand(int skillLevel, int daysActive) {
        fh = new Farmhand(skillLevel, daysActive, Integer.parseInt(experienceLevel));
    }

    public Farmhand getFarmhand() {
        return fh;
    }
}
