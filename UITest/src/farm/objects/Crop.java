package farm.objects;

import seed.Seed;

public class Crop {
    private Seed seed;
    private int growthStage;

    /**
     * This constructor instantiates a crop object that can be placed at each plot.
     * @param seed the seed to place
     * @param growthStage the level of growth the crop is at
     *                    (0 = seed, 1 = immature, 2 = mature)
     */
    public Crop(Seed seed, int growthStage) {
        this.seed = seed;
        this.growthStage = growthStage;
    }

    public int getGrowthStage() {
        return this.growthStage;
    }

    public Seed getSeed() {
        return seed;
    }

    @Override
    public String toString() {
        String[] growthStages = new String[]{"Seed", "Immature Plant", "Mature Plant"};
        return "Type: " + seed.getName() + "\nGrowth Stage: " + growthStages[growthStage];
    }
}