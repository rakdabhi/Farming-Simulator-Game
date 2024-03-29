package farm.objects;

import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import exceptions.PlotAlreadyFullException;
import exceptions.SeedChoiceNotFoundException;

import java.util.Random;

public class Plot implements java.io.Serializable {
    private Crop crop;
    private Random rand;
    private int fertilizerLevel;
    private boolean fertilizerTreated;

    public Plot(Seed seed) {
        crop = new Crop(seed, 0, 0);
        this.fertilizerLevel = 0;
        this.fertilizerTreated = false;
    }

    public Plot(boolean randGen) {
        if (randGen) {
            rand = new Random();
            Seed seed;
            int seedType = rand.nextInt(3);
            int growthStage = rand.nextInt(3);
            int waterLevel = rand.nextInt(5);
            if (seedType == 0) {
                seed = new Seed("Apple");
            } else if (seedType == 1) {
                seed = new Seed("Potato");
            } else {
                seed = new Seed("Corn");
            }

            crop = new Crop(seed, growthStage, waterLevel);
        } else {
            crop = null;
            fertilizerLevel = 0;
            fertilizerTreated = false;
        }

    }

    public Seed harvest()
            throws ImmatureHarvestException, EmptyPlotException, SeedChoiceNotFoundException {
        Seed returnCropType = null;
        if (crop == null) {
            throw new EmptyPlotException();
        } else if (crop.isDead()) {
            this.crop = null;
        } else if (crop.getGrowthStage() == 2) {
            returnCropType = crop.getSeed();
            this.crop = null;
        } else {
            throw new ImmatureHarvestException();
        }

        return returnCropType;
    }

    public void plant(Seed s) throws SeedChoiceNotFoundException, PlotAlreadyFullException {
        if (crop != null) {
            throw new PlotAlreadyFullException();
        }
        this.crop = new Crop(s, 0, 0);
    }

    public boolean isFertilizerTreated() {
        return fertilizerTreated;
    }

    public void setFertilizerTreated(boolean b) {
        fertilizerTreated = b;
    }

    public void setFertilizerLevel(int i) {
        if ((fertilizerLevel + i) >= 100) {
            fertilizerLevel = 100;
        } else if ((fertilizerLevel + i) <= 0) {
            fertilizerLevel = 0;
        } else {
            fertilizerLevel += i;
        }
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public Crop getCrop() {
        return crop;
    }
}