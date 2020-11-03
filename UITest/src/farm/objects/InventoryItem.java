package farm.objects;

import javafx.beans.property.SimpleIntegerProperty;

public class InventoryItem {
    private String itemName;
    private int totalQuantity;
    private SimpleIntegerProperty pesticideTreatedCount;
    private SimpleIntegerProperty pesticideFreeCount;

    public InventoryItem(String itemName, int totalQuantity) {
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;

        pesticideTreatedCount = new SimpleIntegerProperty(0);
        pesticideFreeCount = new SimpleIntegerProperty(0);

    }

    /**
     * Add quantity FOR SEEDS AND ITEMS.
     * @param q
     */
    public void addQuantity(int q) {
        totalQuantity += q;
    }


    /**
     * Overloaded add quantity FOR CROPS.
     * @param q
     */
    public void addQuantity(Crop c, int q) {
        totalQuantity += q;
        if (c.isPesticideTreated()) {
            int p = pesticideTreatedCount.getValue();
            pesticideTreatedCount.setValue(p + q);
        } else {
            int p = pesticideFreeCount.getValue();
            pesticideFreeCount.setValue(p + q);
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public SimpleIntegerProperty getPesticideTreatedCount() {
        return pesticideTreatedCount;
    }

    public SimpleIntegerProperty getPesticideFreeCount() {
        return pesticideFreeCount;
    }
}
