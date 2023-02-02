import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Boss {

    int x;
    int y;
    double hp;
    double maxHp;
    double lastspwn;
    double damage;
    double speed = 5;
    double rotation = 0;
    Image bossImage;
    Player player;
    Panel panel;
    int width;
    int height;
    boolean fired;
    int range = 2000;
    double timer;
    double iFrame = 0.5;
    ArrayList<HashMap<String, Double>> allFireballs = new ArrayList<>();

    public Boss(int x, int y, double maxHp, double damage, Player player, Panel panel) {
        this.x = x;
        this.y = y;
        this.maxHp = maxHp;
        hp = maxHp;
        this.damage = damage;
        this.player = player;
        this.panel = panel;
    }

    public void drawBoss(Graphics2D g2d, Image bossImage, Image fireballImage) {
        this.bossImage = bossImage;
        width = bossImage.getWidth(null) * 4;
        height = bossImage.getHeight(null) * 4;
        g2d.drawImage(bossImage, x, y, width, height, null);
        for (HashMap<String, Double> h : allFireballs) {
            int width = fireballImage.getWidth(null) / 4;
            int height = fireballImage.getHeight(null) / 4;
            g2d.rotate(h.get("Rotation"), h.get("X"), h.get("Y"));
            g2d.drawImage(fireballImage, (int) (h.get("X") - width / 2), (int) (h.get("Y") - height / 2), width, height, null);
            g2d.setTransform(panel.oldXForm);
        }
        g2d.setColor(Color.red);
        var normHp = hp / maxHp;
        g2d.fillRect(x, y + height + 3, (int) (600 * normHp), 10);
        g2d.drawImage(panel.hpImage, x, y + height + 3, width, 10, null);
    }

    public void tick(double dt) {
        timer -= dt;
        iFrame -= 0.1;
        x += Math.cos(rotation) * speed;
        y += Math.sin(rotation) * speed;
        if (x + width > 1900) {
            rotation = random(2, 6);
        }
        if (x < 0) {
            rotation = random(-2, 2);
        }
        if (y + height > 1080) {
            rotation = random(Math.PI + 0.5, Math.PI * 2 - 0.5);
        }
        if (y < 0) {
            rotation = random(0.5, Math.PI - 0.5);
        }
        if (calcRange(x + width / 2, y + height / 2, player.x, player.y) < range && timer <= 0) {
            fireBullet();
            timer = 30;
        }
        for (HashMap<String, Double> h : allFireballs) {
            h.put("X", h.get("X") + h.get("xVel") * -10);
            h.put("Y", h.get("Y") + h.get("yVel") * -10);

        }
        for(int i = 0;i < allFireballs.size() ;i++) {
            if (allFireballs.get(i).get("X") > 1950 || allFireballs.get(i).get("X") < -50 || allFireballs.get(i).get("Y") > 1100 || allFireballs.get(i).get("Y") < -50) {
                allFireballs.remove(i);
            }
        }
        if (hp < maxHp - lastspwn) {
            lastspwn += maxHp / 10;
            panel.spawnEnemy();
            panel.sparned = true;
            panel.ui.levelUp();
            panel.ui.expSpend -= 10;

        }
        if (panel.enemies.size()<1) {
            if (panel.player1.enemySword != null && panel.player2.swordEquipped) {
                if (inRectangle((int) (panel.player1.enemySword.x + 21 + Math.sin(panel.player1.enemySword.rotation) * 21), (int) (panel.player1.enemySword.y + width / 2 + Math.cos(panel.player1.enemySword.rotation) * -25), x, y, width, height) || inRectangle((int) (panel.player1.enemySword.x + 21 + Math.sin(panel.player1.enemySword.rotation) * 40), (int) (panel.player1.enemySword.y + width / 2 + Math.cos(panel.player1.enemySword.rotation) * -40), x, y, width, height) || inRectangle((int) (panel.player1.enemySword.x + 21 + Math.sin(panel.player1.enemySword.rotation) * 55), (int) (panel.player1.enemySword.y + width / 2 + Math.cos(panel.player1.enemySword.rotation) * -55), x, y, width, height)) {
                    playerHit(panel.player2.swordDamage);
                }
            }
            if (panel.player1.swordEquipped) {
                if (inRectangle((int) (panel.sword.x + 21 + Math.sin(panel.sword.rotation) * 21), (int) (panel.sword.y + width / 2 + Math.cos(panel.sword.rotation) * -25), x, y, width, height) || inRectangle((int) (panel.sword.x + 21 + Math.sin(panel.sword.rotation) * 40), (int) (panel.sword.y + width / 2 + Math.cos(panel.sword.rotation) * -40), x, y, width, height) || inRectangle((int) (panel.sword.x + 21 + Math.sin(panel.sword.rotation) * 55), (int) (panel.sword.y + width / 2 + Math.cos(panel.sword.rotation) * -55), x, y, width, height)) {
                    playerHit(panel.player1.swordDamage + panel.inventory.getSwordDamage());
                }
            }
            if (panel.player1.allArrows != null) {
                for (Double[] allArrow : panel.player1.allArrows) {
                    if (inRectangle((int) (allArrow[0] + 1 - 1), (int) (allArrow[1] + 1 - 1), x, y, width, height)) {
                        playerHit(panel.player2.bowDamage);
                    }
                }
            }
            if (panel.player1.allArrowsSelf != null) {
                for (Double[] allArrow : panel.player1.allArrowsSelf) {
                    if (inRectangle((int) (allArrow[0] + 1 - 1), (int) (allArrow[1] + 1 - 1), x, y, width, height)) {
                        playerHit(panel.player1.bowDamage + panel.inventory.getBowDamage());
                    }
                }
            }
        }
    }

    public void fireBullet() {
        Player target;
        if (calcRange(x + width / 2, y + height / 2, player.x, player.y) < calcRange(x + width / 2, y + height / 2, panel.player2.x, panel.player2.y)) {
            target = player;
        } else {
            target = panel.player2;
        }
        double xLength = ((x + width * 0.5) - (target.x + target.width / 2));
        double yLength = ((y + height * 0.5) - (target.y + target.height / 2));
        double rotation = Math.atan2(yLength, xLength);

        HashMap<String, Double> fireball = new HashMap<>();
        fireball.put("X", (double) x + width / 2);
        fireball.put("Y", (double) y + height / 2);
        fireball.put("xVel", Math.cos(rotation));
        fireball.put("yVel", Math.sin(rotation));
        fireball.put("Rotation", rotation);


        allFireballs.add(fireball);
    }

    public double calcRange(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double random(double min, double max) {
        if (min == 0 && max == 0) {
            return 0;
        }
        return ((int) (((Math.random() * ((max - min)) + min)) * 10f) + 1) / 10.0;
    }

    public void playerHit(double damage) {
        if (iFrame <= 0) {
            hp -= damage;
            iFrame = 1;
        }

        if (hp <= 0) {
            panel.boss = new Boss(900,600, maxHp*2, damage*2,player, panel);
        }
    }

    public boolean inRectangle(int px, int py, int rx, int ry, int rb, int rh) {
        return rx < px && px < rx + rb && ry < py && py < ry + rh;

    }
}
