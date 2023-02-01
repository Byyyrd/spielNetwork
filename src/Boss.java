import java.awt.*;

public class Boss {
    int x;
    int y;
    double hp;
    double damage;
    double xSpeed = 10;
    double ySpeed = 10;
    Image bossImage;
    public Boss(int x, int y, double hp, double damage) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
    }
    public void drawBoss(Graphics2D g2d,Image bossImage){
        this.bossImage = bossImage;
        g2d.drawImage(bossImage,x,y,bossImage.getWidth(null)*2,bossImage.getHeight(null)*2,null);
    }
    public void tick(double dt){
        x += xSpeed;
        y += ySpeed;
        if (x > 1900 - bossImage.getWidth(null)*2){
            xSpeed *= -1;
        }
        if (y > 900){
            ySpeed *= -1;
        }
    }
}
