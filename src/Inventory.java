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
    public void updateInv(){
        for (int j = 0;j < 7;j++) {
            for (int i = 0; i < 12; i++) {
                if (allItems[j].size() > i) {
                    inventoryButtons[j][i].setItem((Item) allItems[j].get(i));
                }
            }
        }
        for (int j = 0;j < 7;j++) {
            for (int i = 0; i < 12; i++) {
                    inventoryButtons[j][i].updateButton();
            }
        }
    }
}
