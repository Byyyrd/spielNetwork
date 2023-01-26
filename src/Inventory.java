import java.awt.*;
import java.util.ArrayList;

public class Inventory {
    double hp;
    double enemyHp;
    int mines;
    double dashCooldown;

    int maxHp = 10;
    Player player;
    Player player2;
    int death;
    int death2;
    double expSpend;
    double exp;
    int level;
    Panel panel;
    UpgradeButton button;
    ArrayList<UpgradeButton> allButtons = new ArrayList<>();

    public Inventory(int maxHp, Player player, Player player2, Panel panel) {
        this.maxHp = maxHp;
        this.player = player;
        this.player2 = player2;
        this.panel = panel;
        hp = maxHp;
    }

    public void playerHit(double damage) {
        if (player.iFrame <= 0) {
            hp -= damage;
            player.hp = hp;
            player.iFrame = player.iFrametime;
        }

        if (hp <= 0) {
            player.x = 0;
            player.y = 0;
            death++;
            hp = maxHp;
            player.swordPickedup = false;
            player.slingshotPickedup = true;
        }
        if (enemyHp <= 0) {
            death2++;

        }
    }

    public void drawInventory(Graphics2D g2d, Image hpImage) {
        exp = death2 * 5 - expSpend;
        if (exp >= 20) {
            levelUp();
        }
        //Background
        g2d.setColor(new Color(1, 1, 1, 139));
        g2d.fillRect(0, 950, 1920, 150);
        //Hp
        g2d.setColor(Color.red);
        g2d.fillRect(0, 975, (int) (hp * 50), 50);
        g2d.drawImage(hpImage, 0, 975, hpImage.getWidth(null), hpImage.getHeight(null), null);
        //DashCooldown & Mines left
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Minen: " + mines, 525, 1015);
        g2d.drawString("Dash: " + ((int) (dashCooldown * 10f)) / 10f, 725, 1015);
        //Points
        g2d.setColor(Color.green);
        g2d.fillRect(600, 975, (int) (exp * 25), 50);
        g2d.drawImage(hpImage, 600, 975, hpImage.getWidth(null), hpImage.getHeight(null), null);
    }

    public void levelUp() {
        expSpend += 20;
        level++;
        for (int i = 0; i <= 2; i++) {
            int rand = (int) (Math.random() * 11 + 1);
            switch (rand) {
                case 1 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Heal"));
                case 2 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "SwordDamage"));
                case 3 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshotdamage"));
                case 4 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Minedamage"));
                case 5 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-Cooldown"));
                case 6 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Sword-DmgReduction"));
                case 7 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-DmgReduction"));
                case 8 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Mines-DmgReduction"));
                case 9 ->  allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Speed"));
                case 10 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MaxMines"));
                case 11 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MineGain"));
            }
            allButtons.get(i).addKeyListener(panel);
            panel.add(allButtons.get(i));
        }
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setDashCooldown(double dashCooldown) {
        this.dashCooldown = dashCooldown;
    }
}
