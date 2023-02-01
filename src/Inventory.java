import java.util.ArrayList;

public class Inventory {
    ArrayList[] allItems = new ArrayList[7];
    InventoryButton[][] inventoryButtons = new InventoryButton[7][12];
    EquipButton[] equipButtons = new EquipButton[7];
    private double speed;
    private double healTime = 0;
    private double swordDamage = 0;
    private double bowDamage = 0;
    private double mineDamage = 0;
    private double arrowTime = 0;
    private double mineTime = 0;
    private double meleeDamageReduction = 0;
    private double bowDamageReduction = 0;
    private double mineDamageReduction = 0;
    private double arrowVelocity = 0;
    private int maxMines = 0;
    private int maxHp = 0;
    Panel panel;
    public Inventory(Panel panel) {
        allItems[0] = new ArrayList<Item>();
        allItems[1] = new ArrayList<Item>();
        allItems[2] = new ArrayList<Item>();
        allItems[3] = new ArrayList<Item>();
        allItems[4] = new ArrayList<Item>();
        allItems[5] = new ArrayList<Item>();
        allItems[6] = new ArrayList<Item>();
        this.panel = panel;
    }
    public void updateInv() {

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 12; i++) {
                if (allItems[j].size() > i) {
                    inventoryButtons[j][i].setItem((Item) allItems[j].get(i));
                    inventoryButtons[j][i].setEquipButtons(equipButtons);
                }
            }
        }
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 12; i++) {
                inventoryButtons[j][i].updateButton();
            }
        }
        if (equipButtons[0] != null) {
            speed = equipButtons[0].getButton().item.getSpeed() + equipButtons[1].getButton().item.getSpeed() + equipButtons[2].getButton().item.getSpeed() + equipButtons[3].getButton().item.getSpeed() + equipButtons[4].getButton().item.getSpeed() + equipButtons[5].getButton().item.getSpeed() + equipButtons[6].getButton().item.getSpeed();
            healTime = equipButtons[0].getButton().item.getHealTime() + equipButtons[1].getButton().item.getHealTime() + equipButtons[2].getButton().item.getHealTime() + equipButtons[3].getButton().item.getHealTime() + equipButtons[4].getButton().item.getHealTime() + equipButtons[5].getButton().item.getHealTime() + equipButtons[6].getButton().item.getHealTime();
            swordDamage = equipButtons[0].getButton().item.getSwordDamage() + equipButtons[1].getButton().item.getSwordDamage() + equipButtons[2].getButton().item.getSwordDamage() + equipButtons[3].getButton().item.getSwordDamage() + equipButtons[4].getButton().item.getSwordDamage() + equipButtons[5].getButton().item.getSwordDamage() + equipButtons[6].getButton().item.getSwordDamage();
            bowDamage = equipButtons[0].getButton().item.getBowDamage() + equipButtons[1].getButton().item.getBowDamage() + equipButtons[2].getButton().item.getBowDamage() + equipButtons[3].getButton().item.getBowDamage() + equipButtons[4].getButton().item.getBowDamage() + equipButtons[5].getButton().item.getBowDamage() + equipButtons[6].getButton().item.getBowDamage();
            mineDamage = equipButtons[0].getButton().item.getMineDamage() + equipButtons[1].getButton().item.getMineDamage() + equipButtons[2].getButton().item.getMineDamage() + equipButtons[3].getButton().item.getMineDamage() + equipButtons[4].getButton().item.getMineDamage() + equipButtons[5].getButton().item.getMineDamage() + equipButtons[6].getButton().item.getMineDamage();
            arrowTime = equipButtons[0].getButton().item.getArrowTime() + equipButtons[1].getButton().item.getArrowTime() + equipButtons[2].getButton().item.getArrowTime() + equipButtons[3].getButton().item.getArrowTime() + equipButtons[4].getButton().item.getArrowTime() + equipButtons[5].getButton().item.getArrowTime() + equipButtons[6].getButton().item.getArrowTime();
            mineTime = equipButtons[0].getButton().item.getMineTime() + equipButtons[1].getButton().item.getMineTime() + equipButtons[2].getButton().item.getMineTime() + equipButtons[3].getButton().item.getMineTime() + equipButtons[4].getButton().item.getMineTime() + equipButtons[5].getButton().item.getMineTime() + equipButtons[6].getButton().item.getMineTime();
            meleeDamageReduction = equipButtons[0].getButton().item.getMeleeDamageReduction() + equipButtons[1].getButton().item.getMeleeDamageReduction() + equipButtons[2].getButton().item.getMeleeDamageReduction() + equipButtons[3].getButton().item.getMeleeDamageReduction() + equipButtons[4].getButton().item.getMeleeDamageReduction() + equipButtons[5].getButton().item.getMeleeDamageReduction() + equipButtons[6].getButton().item.getMeleeDamageReduction();
            bowDamageReduction = equipButtons[0].getButton().item.getBowDamageReduction() + equipButtons[1].getButton().item.getBowDamageReduction() + equipButtons[2].getButton().item.getBowDamageReduction() + equipButtons[3].getButton().item.getBowDamageReduction() + equipButtons[4].getButton().item.getBowDamageReduction() + equipButtons[5].getButton().item.getBowDamageReduction() + equipButtons[6].getButton().item.getBowDamageReduction();
            mineDamageReduction = equipButtons[0].getButton().item.getMineDamageReduction() + equipButtons[1].getButton().item.getMineDamageReduction() + equipButtons[2].getButton().item.getMineDamageReduction() + equipButtons[3].getButton().item.getMineDamageReduction() + equipButtons[4].getButton().item.getMineDamageReduction() + equipButtons[5].getButton().item.getMineDamageReduction() + equipButtons[6].getButton().item.getMineDamageReduction();
            arrowVelocity = equipButtons[0].getButton().item.getArrowVelocity() + equipButtons[1].getButton().item.getArrowVelocity() + equipButtons[2].getButton().item.getArrowVelocity() + equipButtons[3].getButton().item.getArrowVelocity() + equipButtons[4].getButton().item.getArrowVelocity() + equipButtons[5].getButton().item.getArrowVelocity() + equipButtons[6].getButton().item.getArrowVelocity();
            maxMines = equipButtons[0].getButton().item.getMaxMines() + equipButtons[1].getButton().item.getMaxMines() + equipButtons[2].getButton().item.getMaxMines() + equipButtons[3].getButton().item.getMaxMines() + equipButtons[4].getButton().item.getMaxMines() + equipButtons[5].getButton().item.getMaxMines() + equipButtons[6].getButton().item.getMaxMines();
            maxHp = equipButtons[0].getButton().item.getMaxHp() + equipButtons[1].getButton().item.getMaxMines() + equipButtons[2].getButton().item.getMaxMines() + equipButtons[3].getButton().item.getMaxMines() + equipButtons[4].getButton().item.getMaxMines() + equipButtons[5].getButton().item.getMaxMines() + equipButtons[6].getButton().item.getMaxMines();
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

    public double getBowDamage() {
        return bowDamage;
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

    public double getBowDamageReduction() {
        return bowDamageReduction;
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
