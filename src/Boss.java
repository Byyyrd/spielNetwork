import java.awt.*;

public class Boss {
    int x;
    int y;
    double hp;
    double damage;

    public Boss(int x, int y, double hp, double damage) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
    }
    public void drawBoss(Graphics2D g2d,Image bossImage){
        g2d.drawImage(bossImage,x,y,bossImage.getWidth(null)*2,bossImage.getHeight(null)*2,null);
    }
    public void tick(double dt){

    }
}
