package crop;

import farm.objects.Crop;
import farm.objects.Seed;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CropTest {
    private Seed seed;
    private Crop crop;

    @Before
    public void setUp() throws Exception {
        seed = new Seed("Apple");
        crop = new Crop(seed, 0, 0);
    }

    @Test
    public void waterLevelRainTest() {
        crop.setWaterLevel(1);
        assertFalse(crop.isDead());
        crop.rain(3);
        assertFalse(crop.isDead());
        crop.rain(1);
        assertTrue(crop.isDead());
    }

    @Test
    public void waterLevelDroughtTest() {
        crop.setWaterLevel(4);
        assertFalse(crop.isDead());
        crop.drought(4);
        assertFalse(crop.isDead());
        crop.drought(1);
        assertTrue(crop.isDead());
    }

}
