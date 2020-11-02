package farm.objects;

public class Crop {
    private Seed seed;
    private int growthStage;
    private int waterLevel;
    private boolean pesticideSprayed;

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
        this.pesticideSprayed = false;
    }

    public void setWaterLevel(int i) {
        waterLevel = i;
        if ((waterLevel < 0) || (waterLevel > 4)) {
            setDead();
        }
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public void grow() {
        if (growthStage < 2) {
            growthStage++;
        }
    }

    public void setDead() {
        growthStage = 3;
    }

    public Boolean isDead() {
        return growthStage == 3;
    }

    public boolean getPesticideSprayed() {
        return pesticideSprayed;
    }

    public void setPesticideSprayed(boolean b) {
        pesticideSprayed = b;
    }

    public int getGrowthStage() {
        return this.growthStage;
    }

    public Seed getSeed() {
        return seed;
    }

    @Override
    public String toString() {
        String[] growthStages = new String[]{"Seed", "Immature Plant",
            "Mature Plant", "Dead Plant"};
        return "Type: " + seed.getName() + "\nGrowth Stage: " + growthStages[growthStage];
    }
}
