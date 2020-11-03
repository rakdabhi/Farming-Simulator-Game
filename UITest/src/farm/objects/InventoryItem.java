package farm.objects;

public class InventoryItem {
    private String itemName;
    private int quantity;

    public InventoryItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public void addQuantity(int q) {
        quantity += q;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}
