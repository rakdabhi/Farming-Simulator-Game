package farm.objects;

import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;

public class Inventory {
    private int availableSeedBagCapacity;
    private int availableHarvestBagCapacity;
    private int[] seedBag;
    private int[] harvestBag;
    private int totalCapacity;

    public Inventory(int experienceLevel) {
        int totalCapacity;
        if (experienceLevel == 1) {
            totalCapacity = 75;
        } else if (experienceLevel == 2) {
            totalCapacity = 50;
        } else {
            totalCapacity = 25;
        }

        seedBag = new int[3];
        harvestBag = new int[3];

        for (int i = 0; i < seedBag.length; i++) {
            seedBag[i] = 0;
            harvestBag[i] = 0;
        }

        availableHarvestBagCapacity = totalCapacity;
        availableSeedBagCapacity = totalCapacity;
    }

    public int[] getSeedBag() {
        return seedBag;
    }

    public int[] getHarvestBag() {
        return harvestBag;
    }

    public int getAvailableSeedBagCapacity() {
        return availableSeedBagCapacity;
    }

    public int getAvailableHarvestBagCapacity() {
        return availableHarvestBagCapacity;
    }

    /**
     * This method helps a farmer add a Seed to their inventory.
     * @param seed the Seed to add
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void addSeed(Seed seed, int quantity) throws SeedChoiceNotFoundException {
        if (quantity > availableSeedBagCapacity) {
            throw new InsufficientInventorySpaceException();
        } else {
            int seedID = seed.getSeedID();
            seedBag[seedID] += quantity;
            availableSeedBagCapacity -= quantity;
        }
    }

    /**
     * This method helps a farmer remove a seed from their inventory.
     * @param seed the seed to remove
     * @throws InsufficientInventorySpaceException if there is insufficient space in the inventory
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void removeSeed(Seed seed, int quantity)
            throws InsufficientInventorySpaceException, SeedChoiceNotFoundException {
        int index = seed.getSeedID();
        if ((seedBag[index] == 0)
                || (seedBag[index] < quantity)) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " seeds of this kind in your inventory to remove!");
        } else {
            seedBag[index] -= quantity;
            availableSeedBagCapacity += quantity;
        }
    }

    /**
     * This method helps a farmer add a Seed to their inventory.
     * @param seed the Crop to add
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void addHarvest(Seed seed, int quantity) throws SeedChoiceNotFoundException {
        if (quantity > availableHarvestBagCapacity) {
            throw new InsufficientInventorySpaceException();
        } else {
            int seedID = seed.getSeedID();
            harvestBag[seedID] += quantity;
            availableHarvestBagCapacity -= quantity;
        }
    }

    /**
     * This method helps a farmer remove a seed from their inventory.
     * @param seed the seed to remove
     * @throws InsufficientInventorySpaceException if there is insufficient space in the inventory
     * @throws SeedChoiceNotFoundException if the seed is not found in seedBag
     */
    public void removeHarvest(Seed seed, int quantity)
            throws InsufficientInventorySpaceException, SeedChoiceNotFoundException {
        int index = seed.getSeedID();
        if ((harvestBag[index] == 0)
                || (harvestBag[index] < quantity)) {
            throw new InsufficientInventorySpaceException("You currently don't have enough"
                    + " crops of this kind in your inventory to remove!");
        } else {
            harvestBag[index] -= quantity;
            availableHarvestBagCapacity += quantity;
        }
    }

}
