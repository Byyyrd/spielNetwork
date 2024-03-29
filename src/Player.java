import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Player implements Serializable {
    int x;
    int y;
    int width;
    int height;
    double speed = 5;
    boolean downPressed, rightPressed, leftPressed, upPressed;
    boolean downCollides, rightCollides, leftCollides, upCollides;
    double xVel, yVel;
    double xDir, yDir;
    double iFrame = 0.5;
    String name = "-------";
    Player player2;
    Sword enemySword;
    ArrayList<Double[]> allArrows;
    ArrayList<Double[]> allArrowsSelf;
    boolean bowEquipped;
    boolean swordEquipped;
    boolean bowPickedUp;
    boolean swordPickedUp;
    boolean fistEquipped = true;
    Panel panel;
    double hp;
    double healTimer;
    double healTime = 4;
    double swordDamage =2.5;
    double bowDamage =1;
    double mineDamage =10;
    double arrowTime = 1;
    double mineTime = 4;
    double iFrameTime = 1;
    double meleeDamageReduction = 0;
    double bowDamageReduction = 0;
    double mineDamageReduction = 0;
    double arrowVelocity = 1;
    int maxMines = 10;
    boolean controled = false;



    public Player(int x, int y, int width, int height, Player player2, Sword enemySword, Panel panel) {
        bowEquipped = false;
        swordEquipped = false;
        if (!panel.coop){
            swordPickedUp = false;
            bowPickedUp = false;
        }else {
            swordPickedUp = true;
            bowPickedUp = true;
        }
        this.player2 = player2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enemySword = enemySword;
        this.panel = panel;
    }

    public void tick() {
        checkCollision();
        if (!panel.inChat){
            inputs();
            applyVel();
        }

        iFrame -= 0.1;
        healTimer -= 0.1;
        if (x > panel.getWidth()) {
            x = -width;
        }
        if (x < -width) {
            x = panel.getWidth();
        }
        if (y > panel.getHeight()) {
            y = -height;
        }
        if (y < -height) {
            y = panel.getHeight();
        }
        if (controled){
            bowEquipped = true;
            swordEquipped = false;
            fistEquipped = false;
        }
    }

    public void checkCollision() {
        if (enemySword != null && player2.swordEquipped) {
            double reduction;
            if (panel.player1.meleeDamageReduction + panel.inventory.getMeleeDamageReduction() <= 100) {
                reduction = panel.player1.meleeDamageReduction + panel.inventory.getMeleeDamageReduction();
            } else {
                reduction = 100;
            }
            if (inRectangle((int) (enemySword.x + 21 + Math.sin(enemySword.rotation) * 21), (int) (enemySword.y + width / 2 + Math.cos(enemySword.rotation) * -25), x, y, width, height) || inRectangle((int) (enemySword.x + 21 + Math.sin(enemySword.rotation) * 40), (int) (enemySword.y + width / 2 + Math.cos(enemySword.rotation) * -40), x, y, width, height) || inRectangle((int) (enemySword.x + 21 + Math.sin(enemySword.rotation) * 55), (int) (enemySword.y + width / 2 + Math.cos(enemySword.rotation) * -55), x, y, width, height)) {
                panel.ui.playerHit(player2.swordDamage * (1 - (reduction / 100)));
                controled = false;
            }
        }
        if (allArrows != null) {
            for (Double[] allArrow : allArrows) {
                double reduction;
                if (panel.player1.bowDamageReduction + panel.inventory.getBowDamageReduction() <= 100) {
                    reduction = panel.player1.bowDamageReduction + panel.inventory.getBowDamageReduction();
                } else {
                    reduction = 100;
                }
                if (inRectangle((int) (allArrow[0] + 1 - 1), (int) (allArrow[1] + 1 - 1), x, y, width, height)) {
                    if (!controled) {
                        panel.ui.playerHit(player2.bowDamage * (1 - (reduction / 100)));
                    }else {
                        panel.ui.playerHitEnemy(player2.bowDamage * (1 - (reduction / 100)));
                    }
                    controled = false;
                }
            }
        }
        for (int i = 0; i < panel.boss.allFireballs.size(); i++){
            if (explosionCollision(x + width/2, y + height/2, (int)(panel.boss.allFireballs.get(i).get("X") + panel.fireballImage.getHeight(null)/8), (int)(panel.boss.allFireballs.get(i).get("Y") + panel.fireballImage.getHeight(null)/8), width/2 + panel.fireballImage.getHeight(null)/8)){
                if(!controled) {
                    panel.ui.playerHitEnemy(panel.boss.damage);
                    panel.boss.allFireballs.remove(i);
                    controled = false;
                }
            }
        }
        if (!panel.coop) {
            for (int j = 0; j < panel.allObstacles.size(); j++) {
                for (int i = 0; i < Objects.requireNonNull(allArrows).size(); i++) {
                    if (inRectangle((int) (allArrows.get(i)[0] + 5), (int) (allArrows.get(i)[1] + 5), panel.allObstacles.get(j)[0], panel.allObstacles.get(j)[1], panel.allObstacles.get(j)[2], panel.allObstacles.get(j)[3])) {
                        allArrows.remove(i);
                    }
                }
            }
            for (int j = 0; j < panel.allObstacles.size(); j++) {
                for (int i = 0; i < allArrowsSelf.size(); i++) {
                    if (inRectangle((int) (allArrowsSelf.get(i)[0] + 5), (int) (allArrowsSelf.get(i)[1] + 5), panel.allObstacles.get(j)[0], panel.allObstacles.get(j)[1], panel.allObstacles.get(j)[2], panel.allObstacles.get(j)[3])) {
                        allArrowsSelf.remove(i);
                    }
                }
            }
        }
    }

    public void drawPlayer(Graphics2D g2d, Image playerImage, Client client, Sword sword) {
        //Heal
        g2d.setColor(new Color(0, 255, 13));
        g2d.drawOval(150, 800, 90, 90);
        g2d.drawOval(95, 745, 200, 200);
        g2d.setColor(new Color(0, 255, 13, 123));
        g2d.fillOval(150, 800, 90, 90);
        g2d.setColor(new Color(0, 255, 13));
        g2d.drawOval(1700, 200, 90, 90);
        g2d.drawOval(1645, 145, 200, 200);
        g2d.setColor(new Color(0, 255, 13, 123));
        g2d.fillOval(1700, 200, 90, 90);

        //Player Names
        g2d.setColor(Color.red);
        g2d.drawString(client.name + "  " + ((int) (sword.coolDown * 10f)) / 10f, x, y);
        g2d.drawString(player2.name + "  " + ((int) (enemySword.coolDown * 10f)) / 10f, player2.x, player2.y);

        g2d.setColor(Color.red);
        g2d.fillRect(x, y + height + 3, (int) (48*panel.ui.normHp),10);
        g2d.drawImage(panel.hpImage,x, y + height + 3, width,10,null);
        g2d.setColor(Color.red);
        g2d.fillRect(player2.x, player2.y + player2.height + 3, (int) ((48*panel.ui.p2normHp)),10);
        g2d.drawImage(panel.hpImage,player2.x, player2.y + player2.height + 3, player2.width,10,null);

        //Player
        g2d.drawImage(playerImage, x, y, width, height, null);
        g2d.drawImage(playerImage, player2.x, player2.y, player2.width, player2.height, null);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 83) {
            downPressed = true;
        }
        if (e.getKeyCode() == 87) {
            upPressed = true;
        }
        if (e.getKeyCode() == 65) {
            leftPressed = true;
        }
        if (e.getKeyCode() == 68) {
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 83) {
            downPressed = false;
        }
        if (e.getKeyCode() == 87) {
            upPressed = false;
        }
        if (e.getKeyCode() == 65) {
            leftPressed = false;
        }
        if (e.getKeyCode() == 68) {
            rightPressed = false;
        }

    }

    public void applyVel() {
        if (!controled) {
            if (xDir != 0) {
                xVel = Lerp(xVel, (speed + panel.inventory.getSpeed()) * xDir, 1);
            } else {
                xVel = Lerp(xVel, 0, 1);
            }
            x += xVel;
            if (yDir != 0) {
                yVel = Lerp(yVel, (speed + panel.inventory.getSpeed()) * yDir, 1);
            } else {
                yVel = Lerp(yVel, 0, 1);
            }
            y += yVel;
        }
    }

    public void inputs() {
        if (!panel.coop) {
            for (int i = 0; i < panel.allObstacles.size(); i++) {
                if (inRectangle(x + 5, y, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width - 5, y, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width / 2 - 5, y, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3])) {
                    upCollides = true;
                }
                if (inRectangle(x + 5, y + player2.height, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width - 5, y + player2.height, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width / 2 - 5, y + player2.height / 2, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3])) {
                    downCollides = true;
                }
                if (inRectangle(x, y + 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x, y + player2.height - 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x, y + player2.height / 2 - 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3])) {
                    leftCollides = true;
                }
                if (inRectangle(x + player2.width, y - 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width, y + player2.height - 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3]) || inRectangle(x + player2.width / 2, y + player2.height / 2 - 5, panel.allObstacles.get(i)[0], panel.allObstacles.get(i)[1], panel.allObstacles.get(i)[2], panel.allObstacles.get(i)[3])) {
                    rightCollides = true;
                }
            }
        }
        if (upPressed && !inRectangle(x + 5, y, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y, player2.x, player2.y, player2.width, player2.width) && !upCollides) {
            yDir = -1;
        } else if (downPressed && !inRectangle(x + 5, y + player2.height, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width - 5, y + player2.height, player2.x, player2.y, player2.width, player2.width) && !downCollides) {
            yDir = 1;
        } else {
            yDir = 0;
        }
        if (leftPressed && !inRectangle(x, y + 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width) && !leftCollides) {
            xDir = -1;
        } else if (rightPressed && !inRectangle(x + player2.width, y - 5, player2.x, player2.y, player2.width, player2.width) && !inRectangle(x + player2.width, y + player2.height - 5, player2.x, player2.y, player2.width, player2.width) && !rightCollides) {
            xDir = 1;
        } else {
            xDir = 0;
        }
        upCollides = false;
        downCollides = false;
        leftCollides = false;
        rightCollides = false;
    }

    public void heal() {
        if (explosionCollision(x + width / 2, y + height / 2, 195, 845, 45) && healTimer <= 0) {
            if (panel.ui.hp < panel.ui.maxHp + panel.inventory.getMaxHp()) {
                panel.ui.hp++;
            }
            healTimer = healTime + panel.inventory.getHealTime();
        }
        if (explosionCollision(x + width / 2, y + height / 2, 1745, 245, 45) && healTimer <= 0) {
            if (panel.ui.hp < panel.ui.maxHp + panel.inventory.getMaxHp()) {
                panel.ui.hp++;
            }
            healTimer = healTime + panel.inventory.getHealTime();
        }
    }

    public void setEnemySword(Sword enemySword) {
        this.enemySword = enemySword;
    }

    public double Lerp(double start, double end, double amt) {
        return (1 - amt) * start + amt * end;
    }

    public boolean inRectangle(int px, int py, int rx, int ry, int rb, int rh) {
        return rx < px && px < rx + rb && ry < py && py < ry + rh;

    }

    public boolean isBowEquipped() {
        return bowEquipped;
    }

    public void setBowEquipped(boolean bowEquipped) {
        this.bowEquipped = bowEquipped;
    }

    public void setAllArrows(ArrayList<Double[]> allArrows) {
        this.allArrows = allArrows;
    }

    public void setAllArrowsSelf(ArrayList<Double[]> allArrowsSelf) {
        this.allArrowsSelf = allArrowsSelf;
    }

    public boolean isSwordEquipped() {
        return swordEquipped;
    }

    public void setSwordEquipped(boolean swordEquipped) {
        this.swordEquipped = swordEquipped;
    }

    public boolean explosionCollision(int x, int y, int x2, int y2, int distance) {
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHealTime(double healTime) {
        this.healTime = healTime;
    }

    public void setSwordDamage(double swordDamage) {
        this.swordDamage = swordDamage;
    }

    public void setBowDamage(double bowDamage) {
        this.bowDamage = bowDamage;
    }

    public void setMineDamage(double mineDamage) {
        this.mineDamage = mineDamage;
    }

    public void setArrowTime(double arrowTime) {
        this.arrowTime = arrowTime;
    }

    public void setMineTime(double mineTime) {
        this.mineTime = mineTime;
    }


    public void setMeleeDamageReduction(double meleeDamageReduction) {
        this.meleeDamageReduction = meleeDamageReduction;
    }

    public void setBowDamageReduction(double bowDamageReduction) {
        this.bowDamageReduction = bowDamageReduction;
    }

    public void setMineDamageReduction(double mineDamageReduction) {
        this.mineDamageReduction = mineDamageReduction;
    }

    public void setArrowVelocity(double arrowVelocity) {
        this.arrowVelocity = arrowVelocity;
    }

    public void setMaxMines(int maxMines) {
        this.maxMines = maxMines;
    }
}
