package farmer;

import exceptions.InsufficientFundsException;
import seed.Seed;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FarmerTest {
    private Farmer farmer1;
    private Farmer farmer2;
    private Farmer farmer3;
    private Seed seed1;
    private Seed seed2;
    private Seed seed3;
    private ArrayList<Seed> seedArrayList;

    @Before
    public void setUp() throws Exception {
        farmer1 = new Farmer("Rakshit", "1", "#efdfbf");
        farmer2 = new Farmer("Bob", "2", " #d3b485");
        farmer3 = new Farmer("Steve", "3", " #a58862");

        seed1 = new Seed("Corn");
        seed2 = new Seed("Wheat");
        seed3 = new Seed("Tomato");

        seedArrayList = new ArrayList<Seed>();
        seedArrayList.add(seed2);
        seedArrayList.add(seed1);
        seedArrayList.add(seed3);
    }

    @Test
    public void testInitialConfig() {
        assertEquals(10000, farmer1.getMoney());
        assertEquals(0, farmer1.numOfSeeds());

        assertEquals(5000, farmer2.getMoney());
        assertEquals(0, farmer2.numOfSeeds());

        assertEquals(1000, farmer3.getMoney());
        assertEquals(0, farmer3.numOfSeeds());
    }


    @Test
    public void setMoney() {
        farmer1.setMoney("2");
        assertEquals(5000, farmer1.getMoney());

        farmer2.setMoney("3");
        assertEquals(1000, farmer2.getMoney());

        farmer3.setMoney("1");
        assertEquals(10000, farmer3.getMoney());
    }

    @Test
    public void addMoney() {
        farmer1.addMoney(652);
        assertEquals(10652, farmer1.getMoney());
    }

    @Test
    public void negativeAddMoney() {
        farmer1.addMoney((-500));
        assertEquals(9500, farmer1.getMoney());
    }

    @Test
    public void pay() {
        farmer1.pay(5932);
        assertEquals(4068, farmer1.getMoney());
    }

    @Test
    public void addSeed() {
        farmer1.addSeed(seed2);
        farmer1.addSeed(seed1);
        farmer1.addSeed(seed3);
        assertEquals(seedArrayList, farmer1.getSeedBag());
    }

    @Test
    public void setSeedBag() {
        farmer2.setSeedBag(seedArrayList);
        assertEquals(seedArrayList, farmer2.getSeedBag());
    }

    @Test
    public void numOfSeeds() {
        farmer3.setSeedBag(seedArrayList);
        assertEquals(3, farmer3.numOfSeeds());
    }

    @Test(expected = InsufficientFundsException.class)
    public void insufficientMoney() {
        farmer1.pay(500000);
        farmer2.pay(189633);
        farmer3.pay(4545758);
        farmer1.addMoney(-10000590);
    }
}
