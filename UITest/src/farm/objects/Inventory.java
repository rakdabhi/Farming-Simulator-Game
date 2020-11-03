package farm.objects;

import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;

public class Inventory {
    private int availableSeedBagCapacity;
    private int availableHarvestBagCapacity;
    private int availableItemBagCapacity;
    private InventoryItem[] seedBag;
    private InventoryItem[] harvestBag;
    private InventoryItem[] itemBag;
    private int totalCapacity;

    public Inventory(int experienceLevel) {
        if (experienceLevel == 1) {
            totalCapacity = 75;
        } else if (experienceLevel == 2) {
            totalCapacity = 50;
        } else {
            totalCapacity = 25;
        }

        seedBag = new InventoryItem[3];
        harvestBag = new InventoryItem[3];
        itemBag = new InventoryItem[2];

        availableHarvestBagCapacity = totalCapacity;
        availableSeedBagCapacity = totalCapacity;
        availableItemBagCapacity = totalCapacity;

        initInventoryBags();
    }

    private void initInventoryBags() {
        seedBag[0] = new InventoryItem("Apple Seed", 0);
        seedBag[1] = new InventoryItem("Potato Seed", 0);
        seedBag[2] = new InventoryItem("Corn Seed", 0);

        harvestBag[0] = new InventoryItem("Apple", 0);
        harvestBag[1] = new InventoryItem("Potato", 0);
        harvestBag[2] = new InventoryItem("Corn", 0);

        itemBag[0] = new InventoryItem("Fertilizer", 0);
        itemBag[1] = new InventoryItem("Pesticide", 0);
    }

    public InventoryItem[] getSeedBag() {
        return seedBag;
    }

    public InventoryItem[] getHarvestBag() {
        return harvestBag;
    }

    public InventoryItem[] getItemBag() {
        return itemBag;
    }

    public int getAvailableSeedBagCapacity() {
        return availableSeedBagCapacity;
    }

    public int getAvailableHarvestBagCapacity() {
        return availableHarvestBagCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    /**
     * This method helps a farmer add a Seed to their inventory.
     * @param seed the Seed to add
     * @param quantity the quantity to add
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void addSeed(Seed seed, int quantity) throws SeedChoiceNotFoundException {
        if (quantity > availableSeedBagCapacity) {
            throw new InsufficientInventorySpaceException();
        } else {
            int seedID = seed.getSeedID();
            seedBag[seedID].addQuantity(quantity);
            availableSeedBagCapacity -= quantity;
        }
    }

    /**
     * This method helps a farmer remove a seed from their inventory.
     * @param seed the seed to remove
     * @param quantity the quantity to remove
     * @throws InsufficientInventorySpaceException if there is insufficient space in the inventory
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void removeSeed(Seed seed, int quantity)
            throws InsufficientInventorySpaceException, SeedChoiceNotFoundException {
        int index = seed.getSeedID();
        if ((seedBag[index].getQuantity() == 0)
                || (seedBag[index].getQuantity() < quantity)) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " seeds of this kind in your inventory to remove!");
        } else {
            seedBag[index].addQuantity(-quantity);
            availableSeedBagCapacity += quantity;
        }
    }

    /**
     * This method helps a farmer add a Seed to their inventory.
     * @param seed the Crop to add
     * @param quantity the quantity to add
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void addHarvest(Seed seed, int quantity) throws SeedChoiceNotFoundException {
        if (quantity > availableHarvestBagCapacity) {
            throw new InsufficientInventorySpaceException();
        } else {
            int seedID = seed.getSeedID();
            harvestBag[seedID].addQuantity(quantity);
            availableHarvestBagCapacity -= quantity;
        }
    }

    /**
     * This method helps a farmer remove a seed from their inventory.
     * @param seed the seed to remove
     * @param quantity the quantity to remove
     * @throws InsufficientInventorySpaceException if there is insufficient space in the inventory
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void removeHarvest(Seed seed, int quantity)
            throws InsufficientInventorySpaceException, SeedChoiceNotFoundException {
        int index = seed.getSeedID();
        if ((harvestBag[index].getQuantity() == 0)
                || (harvestBag[index].getQuantity() < quantity)) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " crops of this kind in your inventory to remove!");
        } else {
            harvestBag[index].addQuantity(-quantity);
            availableHarvestBagCapacity += quantity;
        }
    }

}
