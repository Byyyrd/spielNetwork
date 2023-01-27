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
            player.iFrame = player.iFrameTime;
        }

        if (hp <= 0) {
            player.x = 0;
            player.y = 0;
            death++;
            hp = maxHp;
            player.swordPickedUp = false;
            player.slingshotPickedUp = true;
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
        g2d.drawString("Minen: " + mines, 1225, 1015);
        g2d.drawString("Dash: " + ((int) (dashCooldown * 10f)) / 10f, 1425, 1015);
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
                case 1 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Heal"));
                case 2 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "SwordDamage"));
                case 3 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshotdamage"));
                case 4 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Minedamage"));
                case 5 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-Cooldown"));
                case 6 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Sword-DmgReduction"));
                case 7 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-DmgReduction"));
                case 8 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Mines-DmgReduction"));
                case 9 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Speed"));
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

    public void drawIcons(Graphics2D g2d, Image schildImage, Image swordImage, Image slingshotImage, Image speedImage) {
        g2d.setColor(new Color(1, 1, 1));
        g2d.fillRect(0, 950, 1920, 150);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawImage(schildImage, 20, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.rotate(Math.PI, 23 + swordImage.getWidth(null) / 6, 960 + swordImage.getHeight(null) / 10);
        g2d.drawImage(swordImage, 23, 960, swordImage.getWidth(null) / 3, swordImage.getHeight(null) / 5, null);
        g2d.rotate(-Math.PI, 23 + swordImage.getWidth(null) / 6, 960 + swordImage.getHeight(null) / 10);
        g2d.drawString(":  " + player.meleeDamageReduction, 80, 1010);

        g2d.drawImage(schildImage, 200, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.drawImage(slingshotImage, 208, 970, slingshotImage.getWidth(null) / 24, slingshotImage.getHeight(null) / 24, null);
        g2d.drawString(":  " + ((int) (player.slingshotDamageReduction * 10f)) / 10f, 260, 1010);

        g2d.drawImage(schildImage, 380, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.setColor(Color.red);
        g2d.fillOval(395, 975, 30, 30);
        g2d.setColor(Color.white);
        g2d.drawString(":  " + player.mineDamageReduction, 440, 1010);

        g2d.drawImage(swordImage, 563, 960, swordImage.getWidth(null) / 3, swordImage.getHeight(null) / 5, null);
        g2d.drawString(":  " + ((int) (player.swordDamage * 10f)) / 10f, 610, 1010);

        g2d.drawImage(slingshotImage, 703, 973, slingshotImage.getWidth(null) / 24, slingshotImage.getHeight(null) / 24, null);
        g2d.drawString(":  " + ((int) (player.slingshotDamage * 10f)) / 10f, 750, 1010);

        g2d.setColor(Color.red);
        g2d.fillOval(853, 985, 30, 30);
        g2d.setColor(Color.white);
        g2d.drawString(":  " + (int) player.mineDamage, 900, 1010);

        g2d.setColor(new Color(12, 255, 0, 142));
        g2d.fillOval(1000, 980, 40, 40);
        g2d.setColor(Color.green);
        g2d.setFont(new Font("Arial", Font.PLAIN, 60));
        g2d.drawString("+", 1002, 1020);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.setColor(Color.white);
        g2d.drawString(":  " + ((int) (player.healTime * 10f)) / 10f + "s", 1050, 1010);

        g2d.drawImage(slingshotImage, 1155, 973, slingshotImage.getWidth(null) / 24, slingshotImage.getHeight(null) / 24, null);
        g2d.drawString(":  " + ((int) (player.arrowTime * 10f)) / 10f + "s", 1200, 1010);

        g2d.setColor(new Color(255, 0, 0, 142));
        g2d.fillOval(1320, 980, 40, 40);
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.PLAIN, 60));
        g2d.drawString("+", 1322, 1020);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.setColor(Color.white);
        g2d.drawString(":  " + ((int) (player.mineTime * 10f)) / 10f + "s", 1370, 1010);

        g2d.drawImage(speedImage, 1500, 975, speedImage.getWidth(null) / 3, speedImage.getHeight(null) / 3, null);
        g2d.drawString(":  " + ((int) (player.speed * 10f)) / 10f, 1550, 1010);

        g2d.setColor(Color.red);
        g2d.drawString("MAX", 1650, 1015);
        g2d.fillOval(1760, 980, 40, 40);
        g2d.setColor(Color.white);
        g2d.drawString(":  " + player.maxMines, 1800, 1010);
    }
}
