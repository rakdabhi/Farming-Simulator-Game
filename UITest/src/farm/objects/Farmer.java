package farm.objects;
import exceptions.InsufficientFundsException;

public class Farmer implements java.io.Serializable {

    private String name;
    private String experienceLevel;
    private double money;
    private Inventory inventory;
    private String customSkin;
    private Field[] fields = new Field[3];
    private double nextFieldCost;
    private int fieldsSize;
    private int wateringCapacity;
    private int wateringLeft;
    private int nextWateringCost;
    private int harvestingCapacity;
    private int harvestingLeft;
    private int nextHarvestingCost;
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
        nextWateringCost = 10 + (5 * Integer.parseInt(experienceLevel));
        nextHarvestingCost = 15 + (10 * Integer.parseInt(experienceLevel));
        this.customSkin = customSkin;
        setMoney(this.experienceLevel);
        this.inventory = new Inventory(Integer.parseInt(experienceLevel));
        initFieldData();
        initHarvestAndWaterCapacity();
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

    private void initHarvestAndWaterCapacity() {
        if (experienceLevel.equals("1")) {
            harvestingCapacity = 10;
            wateringCapacity = 36;
        } else if (experienceLevel.equals("2")) {
            harvestingCapacity = 8;
            wateringCapacity = 24;
        } else {
            harvestingCapacity = 5;
            wateringCapacity = 12;
        }
        harvestingLeft = harvestingCapacity;
        wateringLeft = wateringCapacity;
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

    public int getWateringCapacity() {
        return wateringCapacity;
    }

    public int getWateringLeft() {
        return wateringLeft;
    }

    public boolean canWaterMore() {
        return wateringLeft > 0;
    }

    public void hasWatered() {
        wateringLeft--;
    }

    public double getNextWateringCost() {
        return nextWateringCost;
    }

    public void incrementNextWateringCost() {
        nextWateringCost += (Integer.parseInt(experienceLevel) * 5);
    }

    public void incrementWateringCapacity() {
        int diff = wateringCapacity - wateringLeft;
        wateringCapacity += 12;
        incrementNextWateringCost();
        wateringLeft = wateringCapacity - diff;
    }

    public int getHarvestingCapacity() {
        return harvestingCapacity;
    }

    public int getHarvestingLeft() {
        return harvestingLeft;
    }

    public boolean canHarvestMore() {
        return harvestingLeft > 0;
    }

    public void hasHarvested() {
        harvestingLeft--;
    }

    public double getNextHarvestingCost() {
        return nextHarvestingCost;
    }

    public void incrementNextHarvestingCost() {
        nextHarvestingCost += (Integer.parseInt(experienceLevel) * 5);
    }

    public void incrementHarvestingCapacity() {
        int diff = harvestingCapacity - harvestingLeft;
        harvestingCapacity += 3;
        harvestingLeft = harvestingCapacity - diff;
        incrementNextHarvestingCost();
    }

    public void resetWateringAndHarvestingLeft() {
        wateringLeft = wateringCapacity;
        harvestingLeft = harvestingCapacity;
    }

    public String getCustomSkin() {
        return customSkin;
    }
}
