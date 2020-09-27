package farmer;

import seed.Seed;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FarmerTestRakshit {
    private Farmer farmer1;
    private Farmer farmer2;
    private Farmer farmer3;
    private Seed seed1;
    private Seed seed2;
    private Seed seed3;
    private ArrayList<Seed> seedArrayList;

    @Before
    public void setUp() throws Exception {
        farmer1 = new Farmer("Rakshit", "Beginner", "");
        farmer2 = new Farmer("Bob", "Intermediate", "");
        farmer3 = new Farmer("Steve", "Advanced", "");

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
        assertEquals(1000, farmer1.getMoney());
        assertEquals(0, farmer1.numOfSeeds());

        assertEquals(5000, farmer2.getMoney());
        assertEquals(0, farmer2.numOfSeeds());

        assertEquals(10000, farmer3.getMoney());
        assertEquals(0, farmer3.numOfSeeds());
    }


    @Test
    public void setMoney() {
        farmer1.setMoney("Intermediate");
        assertEquals(5000, farmer1.getMoney());

        farmer2.setMoney("Advanced");
        assertEquals(10000, farmer2.getMoney());

        farmer3.setMoney("Beginner");
        assertEquals(1000, farmer3.getMoney());
    }

    @Test
    public void addMoney() {
        farmer1.addMoney(652);
        assertEquals(1652, farmer1.getMoney());
    }

    @Test
    public void pay() {
        farmer3.pay(5932);
        assertEquals(4068, farmer3.getMoney());
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
}