import java.util.ArrayList;

public class Inventory {
    ArrayList[] allItems = new ArrayList[7];
    InventoryButton[][] inventoryButtons = new InventoryButton[7][12];
    private double speed;
    private double healTime = 0;
    private double swordDamage = 0;
    private double slingshotDamage = 0;
    private double mineDamage = 0;
    private double arrowTime = 0;
    private double mineTime = 0;
    private double meleeDamageReduction = 0;
    private double slingshotDamageReduction = 0;
    private double mineDamageReduction = 0;
    private double arrowVelocity = 0;
    private int maxMines = 0;
    private int maxHp = 0;
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

    public double getSpeed() {
        return speed;
    }

    public double getHealTime() {
        return healTime;
    }

    public double getSwordDamage() {
        return swordDamage;
    }

    public double getSlingshotDamage() {
        return slingshotDamage;
    }

    public double getMineDamage() {
        return mineDamage;
    }

    public double getArrowTime() {
        return arrowTime;
    }

    public double getMineTime() {
        return mineTime;
    }

    public double getMeleeDamageReduction() {
        return meleeDamageReduction;
    }

    public double getSlingshotDamageReduction() {
        return slingshotDamageReduction;
    }

    public double getMineDamageReduction() {
        return mineDamageReduction;
    }

    public double getArrowVelocity() {
        return arrowVelocity;
    }

    public int getMaxMines() {
        return maxMines;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
