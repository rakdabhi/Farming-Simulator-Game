package farm.objects;

import exceptions.EmptyPlotException;
import exceptions.ImmatureHarvestException;
import exceptions.PlotAlreadyFullException;
import exceptions.SeedChoiceNotFoundException;
import farm.ui.controllers.ConfigUIController;

import java.util.Random;

public class Plot {
    private Crop crop;
    private Random rand;

    public Plot() {
        rand = new Random();
        Seed seed;
        int seedType = rand.nextInt(3);
        int growthStage = rand.nextInt(3);
        int waterLevel = rand.nextInt(5);
        if (seedType == 0) {
            seed = new Seed("Apple", 1);
        } else if (seedType == 1) {
            seed = new Seed("Potato", 1);
        } else {
            seed = new Seed("Corn", 1);
        }
        crop = new Crop(seed, growthStage, waterLevel);
    }

    public void harvest(Farmer farmer)
            throws ImmatureHarvestException, EmptyPlotException, SeedChoiceNotFoundException {
        if (crop == null) {
            throw new EmptyPlotException();
        } else if (crop.isDead()) {
            this.crop = null;
        } else if (crop.getGrowthStage() == 2) {
            farmer.addSeed(crop.getSeed());
            this.crop = null;
        } else {
            throw new ImmatureHarvestException();
        }
    }

    public void plant(Farmer farmer) throws SeedChoiceNotFoundException, PlotAlreadyFullException {
        if (crop != null) {
            throw new PlotAlreadyFullException();
        }
        Seed seed =
                new Seed(ConfigUIController.getSeedSelect().getName(), 0, 0);
        this.crop = new Crop(seed, 0, 0);
        farmer.removeSeed(ConfigUIController.getSeedSelect());
    }

    public Crop getCrop() {
        return crop;
    }
}