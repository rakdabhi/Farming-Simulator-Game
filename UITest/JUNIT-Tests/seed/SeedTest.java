package seed;

import exceptions.InsufficientInventorySpaceException;
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
        apple = new Seed("Apple", 0);
        potato = new Seed("Potato");
        corn = new Seed("Corn", 5);

    }

    @Test
    public void getName() {
        assertEquals("Corn", corn.getName());
        assertEquals("Apple", apple.getName());
        assertEquals("Potato", potato.getName());
    }

    @Test
    public void getQuantity() {
        assertEquals(0, apple.getQuantity());
        assertEquals(1, potato.getQuantity());
        assertEquals(5, corn.getQuantity());
    }

    @Test
    public void addQuantity() {
        apple.addQuantity(10);
        assertEquals(10, apple.getQuantity());

        potato.addQuantity(100);
        assertEquals(101, potato.getQuantity());

        corn.addQuantity(50);
        assertEquals(55, corn.getQuantity());
    }

    @Test
    public void removeQuantity() {
        corn.addQuantity(50);
        corn.removeQuantity(37);
        assertEquals(18, corn.getQuantity());

        potato.removeQuantity(1);
        assertEquals(0, potato.getQuantity());
    }

    @Test(expected = InsufficientInventorySpaceException.class)
    public void removeSeedWithException() {
        corn.removeQuantity(6);
        apple.removeQuantity(10);
    }

}