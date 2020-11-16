package farm.objects;

import javafx.beans.property.SimpleIntegerProperty;

public class InventoryItem implements java.io.Serializable {
    private String itemName;
    private int totalQuantity;
    private transient SimpleIntegerProperty pesticideTreatedCount;
    private transient SimpleIntegerProperty pesticideFreeCount;
    private int pTC;
    private int pFC;

    public InventoryItem(String itemName, int totalQuantity) {
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;

        pesticideTreatedCount = new SimpleIntegerProperty(0);
        pesticideFreeCount = new SimpleIntegerProperty(0);

        pTC = pesticideTreatedCount.getValue();
        pFC = pesticideFreeCount.getValue();

    }

    public void setIntegerPropertiesFromSave() {
        pesticideTreatedCount = new SimpleIntegerProperty(pTC);
        pesticideFreeCount = new SimpleIntegerProperty(pFC);
    }

    /**
     * Add quantity FOR SEEDS AND ITEMS.
     * @param q quantity for seeds
     */
    public void addQuantity(int q) {
        totalQuantity += q;
    }


    /**
     * Overloaded add quantity FOR CROPS.
     * @param c the crop
     * @param q quantity for crops
     */
    public void addQuantity(Crop c, int q) {
        totalQuantity += q;
        if (c.isPesticideTreated()) {
            int p = pesticideTreatedCount.getValue();
            pesticideTreatedCount.setValue(p + q);
            pTC = pesticideTreatedCount.getValue();
        } else {
            int p = pesticideFreeCount.getValue();
            pesticideFreeCount.setValue(p + q);
            pFC = pesticideFreeCount.getValue();
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
