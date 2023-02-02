import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Boss {

    int x;
    int y;
    double hp;
    double damage;
    double speed = 10;
    double rotation = 0;
    Image bossImage;
    Player player;
    Panel panel;
    int width;
    int height;
    int range = 2000;
    ArrayList<HashMap<String,Double>> allFireballs = new ArrayList<>();

    public Boss(int x, int y, double hp, double damage, Player player, Panel panel) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
        this.player = player;
        this.panel = panel;
    }

    public void drawBoss(Graphics2D g2d, Image bossImage,Image fireballImage) {
        this.bossImage = bossImage;
        width = bossImage.getWidth(null) * 2;
        height = bossImage.getHeight(null) * 2;
        g2d.drawImage(bossImage, x, y, width, height, null);
        //g2d.drawOval(x+width/2-range,y+height/2-range,range*2,range*2);
        for(HashMap<String,Double> h: allFireballs){
            int width = fireballImage.getWidth(null)/4;
            int height = fireballImage.getHeight(null)/4;
            g2d.rotate(h.get("Rotation"),h.get("X") + width * 0.5,h.get("Y")+ width * 0.5);
            g2d.drawImage(fireballImage, (int) (h.get("X") + width/2), (int) (h.get("Y") + height/2),width,height,null);
            g2d.setTransform(panel.oldXForm);
        }
    }

    public void tick(double dt) {

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
        if (calcRange(x + width / 2, y + height / 2, player.x, player.y) < range) {
            fireBullet();
        }
        for(HashMap<String,Double> h: allFireballs){
            h.put("X",h.get("X")+h.get("xVel")*-50);
            h.put("Y",h.get("Y")+h.get("yVel")*-50);
        }
    }

    public void fireBullet() {
        Player target;
        if (calcRange(x + width / 2, y + height / 2, player.x, player.y) > calcRange(x + width / 2, y + height / 2, panel.player2.x, panel.player2.y)) {
            target = player;
        } else {
            target = panel.player2;
        }
        double xLength = ((x + width * 0.5) - target.x);
        double yLength = ((y + height * 0.5) - target.y);
        double rotation = Math.atan2(yLength, xLength);

        HashMap<String, Double> fireball = new HashMap<>();
        fireball.put("X", (double) x + width/4);
        fireball.put("Y", (double) y + height/4);
        fireball.put("xVel", Math.cos(rotation));
        fireball.put("yVel", Math.sin(rotation));
        fireball.put("Rotation",rotation);


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
}
