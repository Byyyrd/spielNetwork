import java.util.ArrayList;

public class Inventory {
    ArrayList[] allItems = new ArrayList[7];
    InventoryButton[][] inventoryButtons = new InventoryButton[7][12];
    public Inventory() {
        allItems[0] = new ArrayList<Item>();
        allItems[1] = new ArrayList<Item>();
        allItems[2] = new ArrayList<Item>();
        allItems[3] = new ArrayList<Item>();
        allItems[4] = new ArrayList<Item>();
        allItems[5] = new ArrayList<Item>();
        allItems[6] = new ArrayList<Item>();
    }
}
