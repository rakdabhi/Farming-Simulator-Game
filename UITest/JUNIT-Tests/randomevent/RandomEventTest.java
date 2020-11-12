package randomevent;

import farm.objects.Crop;
import farm.objects.RandomEvent;
import farm.objects.Seed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomEventTest {
    private Crop crop;
    private Seed seed;
    private RandomEvent randomEvent;

    @Before
    public void setUp() throws Exception {
        seed = new Seed("Apple");
        crop = new Crop(seed, 0, 0);
        randomEvent = new RandomEvent("Spring");
    }

    @Test
    public void rainTest() {
        int waterLevelBefore = crop.getWaterLevel();
        randomEvent.generateNewRainAndDroughtLevels();
        randomEvent.performRandomEvent(crop, 0);
        assertTrue(waterLevelBefore < crop.getWaterLevel());
    }

    @Test
    public void droughtTest() {
        crop.setWaterLevel(4);
        randomEvent.generateNewRainAndDroughtLevels();
        randomEvent.performRandomEvent(crop, 30);
        assertTrue(crop.getWaterLevel() < 4);
    }

    @Test
    public void pesticideTreatedLocustTest() {
        crop.setPesticideTreated(true);
        randomEvent.generateNewRainAndDroughtLevels();
        randomEvent.performRandomEvent(crop, 60);
        assertFalse(crop.isDead());
    }
}
