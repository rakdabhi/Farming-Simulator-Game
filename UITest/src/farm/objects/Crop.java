package farm.objects;

import seed.Seed;

public class Crop {
    private Seed seed;
    private int growthStage;
    private int waterLevel;

    /**
     * This constructor instantiates a crop object that can be placed at each plot.
     * @param seed the seed to place
     * @param growthStage the level of growth the crop is at
     *                    (0 = seed, 1 = immature, 2 = mature)
     * @param waterLevel The amount of moisture in the soil, between 0 and 4.
     *                   0 is too little, 4 is too much.
     */
    public Crop(Seed seed, int growthStage, int waterLevel) {
        this.seed = seed;
        this.growthStage = growthStage;
        this.waterLevel = waterLevel;
    }

    public void setWaterLevel(int i) {
        if (i > 4) {
            waterLevel = 4;
        } else if (i < 0) {
            waterLevel = 0;
        } else {
            waterLevel = i;
        }
    }

    public int getWaterLevel() {return this.waterLevel;}

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
