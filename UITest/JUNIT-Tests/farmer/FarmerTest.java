package farmer;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;
import seed.Seed;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmerTest {
    private Farmer farmer1;
    private Farmer farmer2;
    private Farmer farmer3;
    private Seed apple;
    private Seed potato;
    private Seed corn;
    private Seed[] seedArray;

    @Before
    public void setUp() throws Exception {
        farmer1 = new Farmer("Rakshit", "1", "#efdfbf");
        farmer2 = new Farmer("Bob", "2", " #d3b485");
        farmer3 = new Farmer("Steve", "3", " #a58862");

        apple = new Seed("Apple");
        potato = new Seed("Potato");
        corn = new Seed("Corn");

        seedArray = new Seed[]{apple, potato, corn};
    }

    @Test
    public void testInitialConfig() {
        assertEquals(200.0, farmer1.getMoney(), 0.01);
        assertEquals(0, farmer1.numOfSeeds());

        assertEquals(100.0, farmer2.getMoney(), 0.01);
        assertEquals(0, farmer2.numOfSeeds());

        assertEquals(50.0, farmer3.getMoney(), 0.01);
        assertEquals(0, farmer3.numOfSeeds());
    }


    @Test
    public void setMoney() {
        farmer1.setMoney("2");
        assertEquals(100.0, farmer1.getMoney(), 0.01);

        farmer2.setMoney("3");
        assertEquals(50.0, farmer2.getMoney(), 0.01);

        farmer3.setMoney("1");
        assertEquals(200.0, farmer3.getMoney(), 0.01);
    }

    @Test
    public void inventoryCapacity() {
        assertEquals(100, farmer1.getAvailableCapacity());
        assertEquals(100, farmer1.getInventoryCapacity());
        assertEquals(50, farmer2.getAvailableCapacity());
        assertEquals(50, farmer2.getInventoryCapacity());
        assertEquals(25, farmer3.getAvailableCapacity());
        assertEquals(25, farmer3.getInventoryCapacity());
    }

    public void testCapacityAfterAddSeed() {
        farmer3.setSeedBag(seedArray);
        assertEquals(22, farmer3.getAvailableCapacity());
        assertEquals(25, farmer3.getInventoryCapacity());
    }

    @Test
    public void addMoney() {
        farmer1.addMoney(652.51);
        assertEquals(852.51, farmer1.getMoney(), 0.01);
    }

    @Test
    public void negativeAddMoney() {
        farmer1.addMoney((-180.23));
        assertEquals(19.77, farmer1.getMoney(), 0.01);
    }

    @Test
    public void pay() {
        farmer1.pay(0.01);
        assertEquals(199.99, farmer1.getMoney(), 0.01);
    }

    @Test
    public void addSeed() throws SeedChoiceNotFoundException {
        farmer1.addSeed(potato);
        farmer1.addSeed(apple);
        farmer1.addSeed(corn);
        assertEquals(seedArray[0].getName(), farmer1.getSeedBag()[0].getName());
        assertEquals(seedArray[0].getQuantity(), farmer1.getSeedBag()[0].getQuantity());
        assertEquals(seedArray[1].getName(), farmer1.getSeedBag()[1].getName());
        assertEquals(seedArray[1].getQuantity(), farmer1.getSeedBag()[1].getQuantity());
        assertEquals(seedArray[2].getName(), farmer1.getSeedBag()[2].getName());
        assertEquals(seedArray[2].getQuantity(), farmer1.getSeedBag()[2].getQuantity());
        assertEquals(97, farmer1.getAvailableCapacity());
        assertEquals(100, farmer1.getInventoryCapacity());

        Seed potato = new Seed("Potato", 37);
        farmer2.addSeed(potato);
        assertEquals(13, farmer2.getAvailableCapacity());
        assertEquals(50, farmer2.getInventoryCapacity());
    }

    @Test
    public void removeSeed() throws SeedChoiceNotFoundException {
        Seed corn = new Seed("Corn", 10);
        farmer3.addSeed(corn);
        assertEquals(corn.getName(), farmer3.getSeedBag()[2].getName());
        assertEquals(corn.getQuantity(), farmer3.getSeedBag()[2].getQuantity());
        assertEquals(15, farmer3.getAvailableCapacity());
        assertEquals(25, farmer3.getInventoryCapacity());

        farmer3.removeSeed(new Seed("Corn", 5));
        assertEquals(corn.getName(), farmer3.getSeedBag()[2].getName());
        assertEquals(5, farmer3.getSeedBag()[2].getQuantity());
        assertEquals(20, farmer3.getAvailableCapacity());
        assertEquals(25, farmer3.getInventoryCapacity());
    }

    @Test
    public void setSeedBag() {
        farmer2.setSeedBag(seedArray);
        assertEquals(seedArray, farmer2.getSeedBag());
    }

    @Test
    public void numOfSeeds() {
        farmer3.setSeedBag(seedArray);
        assertEquals(3, farmer3.numOfSeeds());
    }

    @Test(expected = InsufficientFundsException.class)
    public void insufficientMoney() {
        farmer1.pay(500000);
        farmer2.pay(189633);
        farmer3.pay(4545758);
        farmer1.addMoney(-10000590);
    }

    @Test(expected = InsufficientInventorySpaceException.class)
    public void insufficientInventory() throws SeedChoiceNotFoundException {
        Seed apple = new Seed("Apple", 50);
        farmer3.addSeed(apple);
        farmer1.addSeed(new Seed("Apple", 10));
        farmer1.removeSeed(new Seed("Apple", 20));
    }

    @Test(expected = InsufficientInventorySpaceException.class)
    public void removeSeedWithException() throws SeedChoiceNotFoundException {
        Seed apple = new Seed("Apple", 10);
        farmer1.addSeed(apple);
        farmer1.removeSeed(new Seed("Corn", 5));
    }
}