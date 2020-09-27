package seed;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeedTest {
    private Seed corn;
    private Seed wheat;
    private Seed tomato;

    @Before
    public void setUp() throws Exception {
        corn = new Seed("Corn", 5);
        wheat = new Seed("Wheat", 0);
        tomato = new Seed("Tomato");
    }

    @Test
    public void getName() {
        assertEquals("Corn", corn.getName());
        assertEquals("Wheat", wheat.getName());
        assertEquals("Tomato", tomato.getName());
    }

    @Test
    public void getQuantity() {
        assertEquals(5, corn.getQuantity());
        assertEquals(0, wheat.getQuantity());
        assertEquals(1, tomato.getQuantity());
    }

    @Test
    public void addQuantity() {
        corn.addQuantity(50);
        assertEquals(55, corn.getQuantity());

        wheat.addQuantity(10);
        assertEquals(10, wheat.getQuantity());

        tomato.addQuantity(100);
        assertEquals(101, tomato.getQuantity());
    }

    @Test
    public void removeQuantity() {
        corn.addQuantity(50);
        corn.removeQuantity(37);
        assertEquals(18, corn.getQuantity());

        tomato.removeQuantity(1);
        assertEquals(0, tomato.getQuantity());
    }
}