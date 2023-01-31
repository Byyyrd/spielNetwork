import java.awt.*;
import java.util.ArrayList;

public class Ui {
    double hp;
    double enemyHp;
    int mines;
    double dashCoolDown;

    int maxHp;
    Player player;
    Player player2;
    int death;
    int death2;
    double expSpend;
    double exp;
    int level;
    Panel panel;
    double normHp;
    double p2normHp;
    ArrayList<UpgradeButton> allButtons = new ArrayList<>();

    public Ui(int maxHp, Player player, Player player2, Panel panel) {
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
            hp = maxHp + panel.inventory.getMaxHp();
            player.swordPickedUp = false;
            player.slingshotPickedUp = true;
        }
    }

    public void drawUi(Graphics2D g2d, Image hpImage) {
        exp = death2 * 5 - expSpend;
        if (exp >= 20) {
            levelUp();
        }
        //Background
        g2d.setColor(new Color(1, 1, 1, 139));
        g2d.fillRect(0, 950, 1920, 150);
        //Hp
        g2d.setColor(Color.red);
        //g2d.fillRect(0, 975, (int) (hp * 50), 50);
        normHp = hp/(maxHp + panel.inventory.getMaxHp());
        g2d.fillRect(0, 975, (int) (normHp * 500), 50);
        g2d.drawImage(hpImage, 0, 975, hpImage.getWidth(null), hpImage.getHeight(null), null);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString(hp + "/" + maxHp, 525, 1015);
        //DashCoolDown & Mines left
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Mines: " + mines + "/" + player.maxMines, 1375, 1015);
        g2d.drawString("Dash: " + ((int) (dashCoolDown * 10f)) / 10f, 1635, 1015);
        //Points
        g2d.setColor(Color.green);
        g2d.fillRect(700, 975, (int) (exp * 25), 50);
        g2d.drawImage(hpImage, 700, 975, hpImage.getWidth(null), hpImage.getHeight(null), null);
        g2d.drawString(exp + "/" + "20", 1225, 1015);
    }

    public void levelUp() {
        expSpend += 20;
        level++;
        for (int i = 0; i <= 2; i++) {
            int rand = (int) (Math.random() * 20 + 1);
            switch (rand) {
                case 1 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Heal"));
                case 2 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "SwordDamage"));
                case 3 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "SlingshotDamage"));
                case 4 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MineDamage"));
                case 5 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-CoolDown"));
                case 6 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Sword-DmgReduction"));
                case 7 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot-DmgReduction"));
                case 8 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Mines-DmgReduction"));
                case 9 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Speed"));
                case 10 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MaxMines"));
                case 11 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MineGain"));
                case 12 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "MaxHp"));
                case 13 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "ArrowVelocity"));
                case 14 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Armor"));
                case 15 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Sword"));
                case 16 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Slingshot"));
                case 17 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Mine"));
                case 18 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Ring"));
                case 19 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Chain"));
                case 20 -> allButtons.add(new UpgradeButton(panel, i * 600 + 100, "Shoes"));

            }
            allButtons.get(i).addKeyListener(panel);
            panel.add(allButtons.get(i));
        }
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setDashCoolDown(double dashCoolDown) {
        this.dashCoolDown = dashCoolDown;
    }
    public void drawIcons(Graphics2D g2d, Image schildImage, Image swordImage, Image slingshotImage, Image speedImage) {
        g2d.setColor(new Color(1, 1, 1));
        g2d.fillRect(0, 950, 1920, 150);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        g2d.drawImage(schildImage, 20, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.rotate(Math.PI, 23 + swordImage.getWidth(null) / 6.0, 960 + swordImage.getHeight(null) / 10.0);
        g2d.drawImage(swordImage, 23, 960, swordImage.getWidth(null) / 3, swordImage.getHeight(null) / 5, null);
        g2d.rotate(-Math.PI, 23 + swordImage.getWidth(null) / 6.0, 960 + swordImage.getHeight(null) / 10.0);
        if (player.meleeDamageReduction >= 75){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + player.meleeDamageReduction, 80, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " +(int)( panel.inventory.getMeleeDamageReduction()*10f)/10f, 87, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(schildImage, 200, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.drawImage(slingshotImage, 203, 965, slingshotImage.getWidth(null) / 30, slingshotImage.getHeight(null) /30, null);
        if (player.slingshotDamageReduction >= 75){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.slingshotDamageReduction * 10f)) / 10f, 260, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " + (int)(panel.inventory.getSlingshotDamageReduction()*10f)/10f, 267, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(schildImage, 380, 956, schildImage.getWidth(null) / 3, schildImage.getHeight(null) / 3, null);
        g2d.setColor(Color.red);
        g2d.fillOval(395, 975, 30, 30);
        g2d.setColor(Color.white);
        if (player.mineDamageReduction >= 75){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + player.mineDamageReduction, 440, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " + (int)(panel.inventory.getMineDamageReduction()*10f)/10f, 447, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(swordImage, 563, 960, swordImage.getWidth(null) / 3, swordImage.getHeight(null) / 5, null);
        if (player.swordDamage >= 10){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.swordDamage * 10f)) / 10f, 610, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " +(int)( panel.inventory.getSwordDamage()*10f)/10f, 617, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(slingshotImage, 728, 968, slingshotImage.getWidth(null) / 30, slingshotImage.getHeight(null) / 30, null);
        if (player.slingshotDamage >= 7.5){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.slingshotDamage * 10f)) / 10f, 780, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " + (int)(panel.inventory.getSlingshotDamage()*10f)/10f, 787, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.setColor(Color.red);
        g2d.fillOval(883, 985, 30, 30);
        g2d.setColor(Color.white);
        if (player.mineDamage >= 75){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + (int) player.mineDamage, 930, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " +(int)( panel.inventory.getMineDamage()*10f)/10f, 937, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.setColor(new Color(12, 255, 0, 142));
        g2d.fillOval(1030, 980, 40, 40);
        g2d.setColor(Color.green);
        g2d.setFont(new Font("Arial", Font.PLAIN, 60));
        g2d.drawString("+", 1032, 1020);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        g2d.setColor(Color.white);
        if (player.healTime + panel.inventory.getHealTime() <= 1){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.healTime * 10f)) / 10f + "s", 1080, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("-  " +(int)( Math.abs(panel.inventory.getHealTime())*10f)/10f, 1087, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(slingshotImage, 1200, 973, slingshotImage.getWidth(null) / 30, slingshotImage.getHeight(null) / 30, null);
        g2d.drawImage(panel.hourglass, 1245, 968, slingshotImage.getWidth(null) / 24, slingshotImage.getHeight(null) / 24, null);
        if (player.arrowTime <= 0){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.arrowTime * 10f)) / 10f + "s", 1300, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        if (panel.inventory.getArrowTime() > 0 ){
            g2d.setColor(Color.red);
            g2d.drawString("+  " +(int)( Math.abs(panel.inventory.getArrowTime())*10f)/10f, 1307, 1020);
        } else {
            g2d.setColor(Color.green);
            g2d.drawString("-  " +(int)( Math.abs(panel.inventory.getArrowTime())*10f)/10f, 1307, 1020);
        }
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));


        g2d.setColor(new Color(255, 0, 0, 142));
        g2d.fillOval(1420, 980, 40, 40);
        g2d.setColor(Color.red);
        g2d.setFont(new Font("Arial", Font.PLAIN, 60));
        g2d.drawString("+", 1422, 1020);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        g2d.setColor(Color.white);
        if (player.mineTime <= 1){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + ((int) (player.mineTime * 10f)) / 10f + "s", 1470, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("-  " + (int)(Math.abs(panel.inventory.getMineTime())*10f)/10f, 1477, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(speedImage, 1600, 975, speedImage.getWidth(null) / 3, speedImage.getHeight(null) / 3, null);
        g2d.setColor(Color.white);
        g2d.drawString(":  " + ((int) (player.speed * 10f)) / 10f, 1650, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        if (panel.inventory.getSpeed() < 0){
            g2d.setColor(Color.red);
            g2d.drawString("-  " + (int)(Math.abs(panel.inventory.getSpeed())*10f)/10f, 1657, 1020);
        } else {
            g2d.setColor(Color.green);
            g2d.drawString("+  " + (int)(panel.inventory.getSpeed()*10f)/10f, 1657, 1020);
        }
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        g2d.drawImage(panel.arrowVelocity, 1750, 980, speedImage.getWidth(null) / 3, speedImage.getHeight(null) / 4, null);
        if (player.arrowVelocity >= 4){
            g2d.setColor(Color.yellow);
        }else {
            g2d.setColor(Color.white);
        }
        g2d.drawString(":  " + player.arrowVelocity, 1800, 1000);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.green);
        g2d.drawString("+  " + (int)(panel.inventory.getArrowVelocity()*10f)/10f, 1807, 1020);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
    }
}
