package seed;

import farm.objects.Seed;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeedTest {
    private Seed apple;
    private Seed potato;
    private Seed corn;

    @Before
    public void setUp() throws Exception {
        apple = new Seed("Apple");
        potato = new Seed("Potato");
        corn = new Seed("Corn");

    }

    @Test
    public void getName() {
        assertEquals("Corn", corn.getName());
        assertEquals("Apple", apple.getName());
        assertEquals("Potato", potato.getName());
    }

    @Test
    public void getID() {
        assertEquals(0, apple.getSeedID());
        assertEquals(1, potato.getSeedID());
        assertEquals(2, corn.getSeedID());
    }

}