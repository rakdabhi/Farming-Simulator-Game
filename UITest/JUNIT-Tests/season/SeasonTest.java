package season;

import farm.objects.Season;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeasonTest {
    private Season spring;
    private Season summer;
    private Season fall;
    private Season winter;

    @Before
    public void setUp() throws Exception {
        spring = new Season("Spring");
        summer = new Season("Summer");
        fall = new Season("Fall");
        winter = new Season("Winter");
    }

    @Test
    public void generateTemperature() {
        boolean inSpringTempRange = false;
        spring.generateTemperature();
        boolean inSummerTempRange = false;
        summer.generateTemperature();
        boolean inFallTempRange = false;
        fall.generateTemperature();
        boolean inWinterTempRange = false;
        winter.generateTemperature();

        if ((spring.getTemperature() <= 79.9) && (spring.getTemperature() >= 67)) {
            inSpringTempRange = true;
        }
        if ((summer.getTemperature() <= 100) && (summer.getTemperature() >= 80)) {
            inSummerTempRange = true;
        }
        if ((fall.getTemperature() <= 66.9) && (fall.getTemperature() >= 50)) {
            inFallTempRange = true;
        }
        if ((winter.getTemperature() <= 49.9) && (winter.getTemperature() >= 0)) {
            inWinterTempRange = true;
        }

        assertEquals(true, inSpringTempRange);
        assertEquals(true, inSummerTempRange);
        assertEquals(true, inFallTempRange);
        assertEquals(true, inWinterTempRange);
    }

    @Test
    public void setSeason() {
        spring.setSeason("Summer");
        boolean inSummerTempRange = false;
        if ((spring.getTemperature() <= 100) && (spring.getTemperature() >= 80)) {
            inSummerTempRange = true;
        }
        assertEquals(true, inSummerTempRange);
    }

    @Test
    public void getSeason() {
        assertEquals("Spring", spring.getSeason());
        assertEquals("Summer", summer.getSeason());
        assertEquals("Fall", fall.getSeason());
        assertEquals("Winter", winter.getSeason());
    }
}