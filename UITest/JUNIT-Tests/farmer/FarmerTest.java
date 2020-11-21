package farmer;

import exceptions.InsufficientFundsException;
import exceptions.InsufficientInventorySpaceException;
import exceptions.SeedChoiceNotFoundException;
import farm.objects.Farmer;
import farm.objects.Seed;
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

    @Before
    public void setUp() throws Exception {
        farmer1 = new Farmer("Rakshit", "1", "#efdfbf");
        farmer2 = new Farmer("Bob", "2", " #d3b485");
        farmer3 = new Farmer("Steve", "3", " #a58862");

        apple = new Seed("Apple");
        potato = new Seed("Potato");
        corn = new Seed("Corn");
    }

    @Test
    public void testInitialConfig() {
        assertEquals(200.0, farmer1.getMoney(), 0.01);
        assertEquals(75, farmer1.getInventory().getTotalCapacity());
        assertEquals(100.0, farmer2.getMoney(), 0.01);
        assertEquals(50, farmer2.getInventory().getTotalCapacity());
        assertEquals(50.0, farmer3.getMoney(), 0.01);
        assertEquals(25, farmer3.getInventory().getTotalCapacity());
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
        assertEquals(75, farmer1.getInventory().getAvailableSeedBagCapacity());
        assertEquals(75, farmer1.getInventory().getTotalCapacity());
        assertEquals(50, farmer2.getInventory().getAvailableSeedBagCapacity());
        assertEquals(50, farmer2.getInventory().getTotalCapacity());
        assertEquals(25, farmer3.getInventory().getAvailableSeedBagCapacity());
        assertEquals(25, farmer3.getInventory().getTotalCapacity());
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
        farmer1.getInventory().addSeed(potato, 1);
        farmer1.getInventory().addSeed(apple, 1);
        farmer1.getInventory().addSeed(corn, 1);
        assertEquals(1, farmer1.getInventory().getSeedBag()[0].getTotalQuantity());
        assertEquals(1, farmer1.getInventory().getSeedBag()[1].getTotalQuantity());
        assertEquals(1, farmer1.getInventory().getSeedBag()[2].getTotalQuantity());
        assertEquals(72, farmer1.getInventory().getAvailableSeedBagCapacity());
        assertEquals(75, farmer1.getInventory().getTotalCapacity());

        Seed potato = new Seed("Potato");
        farmer2.getInventory().addSeed(potato, 37);
        assertEquals(13, farmer2.getInventory().getAvailableSeedBagCapacity());
        assertEquals(50, farmer2.getInventory().getTotalCapacity());
    }

    @Test
    public void removeSeed() throws SeedChoiceNotFoundException {
        Seed corn = new Seed("Corn");
        farmer3.getInventory().addSeed(corn, 10);
        assertEquals(10, farmer3.getInventory().getSeedBag()[2].getTotalQuantity());
        assertEquals(15, farmer3.getInventory().getAvailableSeedBagCapacity());
        assertEquals(25, farmer3.getInventory().getTotalCapacity());

        farmer3.getInventory().removeSeed(new Seed("Corn"), 5);
        assertEquals(5, farmer3.getInventory().getSeedBag()[2].getTotalQuantity());
        assertEquals(20, farmer3.getInventory().getAvailableSeedBagCapacity());
        assertEquals(25, farmer3.getInventory().getTotalCapacity());
    }

    @Test
    public void testInitialWaterCapacity() {
        assertEquals(farmer1.getWateringCapacity(), 36);
        assertEquals(farmer2.getWateringCapacity(), 24);
        assertEquals(farmer3.getWateringCapacity(), 12);
    }

    @Test
    public void testIncrementWaterCapacity() {
        assertEquals(farmer1.getNextWateringCost(), 15, 0.01);
        farmer1.incrementWateringCapacity();
        assertEquals(farmer1.getWateringCapacity(), 48);
        assertEquals(farmer1.getNextWateringCost(), 20, 0.01);
        farmer1.incrementWateringCapacity();
        assertEquals(farmer1.getWateringCapacity(), 60);
        assertEquals(farmer1.getNextWateringCost(), 25, 0.01);

        assertEquals(farmer2.getNextWateringCost(), 20, 0.01);
        farmer2.incrementWateringCapacity();
        assertEquals(farmer2.getWateringCapacity(), 36);
        assertEquals(farmer2.getNextWateringCost(), 30, 0.01);
        farmer2.incrementWateringCapacity();
        assertEquals(farmer2.getWateringCapacity(), 48);
        assertEquals(farmer2.getNextWateringCost(), 40, 0.01);

        assertEquals(farmer3.getNextWateringCost(), 25, 0.01);
        farmer3.incrementWateringCapacity();
        assertEquals(farmer3.getWateringCapacity(), 24);
        assertEquals(farmer3.getNextWateringCost(), 40, 0.01);
        farmer3.incrementWateringCapacity();
        assertEquals(farmer3.getWateringCapacity(), 36);
        assertEquals(farmer3.getNextWateringCost(), 55, 0.01);
    }

    @Test
    public void testInitialHarvestingCapacity() {
        assertEquals(farmer1.getHarvestingCapacity(), 10);
        assertEquals(farmer2.getHarvestingCapacity(), 8);
        assertEquals(farmer3.getHarvestingCapacity(), 5);
    }

    @Test
    public void testIncrementHarvestingCapacity() {
        assertEquals(farmer1.getNextHarvestingCost(), 25, 0.01);
        farmer1.incrementHarvestingCapacity();
        assertEquals(farmer1.getHarvestingCapacity(), 13);
        assertEquals(farmer1.getNextHarvestingCost(), 30, 0.01);
        farmer1.incrementHarvestingCapacity();
        assertEquals(farmer1.getHarvestingCapacity(), 16);
        assertEquals(farmer1.getNextHarvestingCost(), 35, 0.01);

        assertEquals(farmer2.getNextHarvestingCost(), 35, 0.01);
        farmer2.incrementHarvestingCapacity();
        assertEquals(farmer2.getHarvestingCapacity(), 11);
        assertEquals(farmer2.getNextHarvestingCost(), 45, 0.01);
        farmer2.incrementHarvestingCapacity();
        assertEquals(farmer2.getHarvestingCapacity(), 14);
        assertEquals(farmer2.getNextHarvestingCost(), 55, 0.01);

        assertEquals(farmer3.getNextHarvestingCost(), 45, 0.01);
        farmer3.incrementHarvestingCapacity();
        assertEquals(farmer3.getHarvestingCapacity(), 8);
        assertEquals(farmer3.getNextHarvestingCost(), 60, 0.01);
        farmer3.incrementHarvestingCapacity();
        assertEquals(farmer3.getHarvestingCapacity(), 11);
        assertEquals(farmer3.getNextHarvestingCost(), 75, 0.01);
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
        Seed apple = new Seed("Apple");
        farmer3.getInventory().addSeed(apple, 50);
        farmer1.getInventory().addSeed(new Seed("Apple"), 10);
        farmer1.getInventory().removeSeed(new Seed("Apple"), 20);
    }

    @Test(expected = InsufficientInventorySpaceException.class)
    public void removeSeedWithException() throws SeedChoiceNotFoundException {
        Seed apple = new Seed("Apple");
        farmer1.getInventory().addSeed(apple, 10);
        farmer1.getInventory().removeSeed(new Seed("Corn"), 5);
    }
}