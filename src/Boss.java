import java.awt.*;

public class Boss {
    int x;
    int y;
    double hp;
    double damage;
    double speed = 10;
    double rotation = 0;
    Image bossImage;
    int width;
    int height;
    public Boss(int x, int y, double hp, double damage) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
    }
    public void drawBoss(Graphics2D g2d,Image bossImage){
        this.bossImage = bossImage;
        width = bossImage.getWidth(null)*2;
        height = bossImage.getHeight(null)*2;
        g2d.drawImage(bossImage,x,y,width,height,null);
    }
    public void tick(double dt){
        /*double xLength = ;
        double yLength = ;
        rotation = Math.atan2(yLength,xLength);*/
        x += Math.cos(rotation) * speed;
        y += Math.sin(rotation) * speed;
        if(x + width > 1900){
            rotation = random(2,6);
        }
        if(x < 0){
            //rotation = random(6,);
        }
        if (y + height > 900){
            //rotation = random(,-6);
        }
    }
    public double random(double min, double max){
        if (min == 0 && max == 0){
            return 0;
        }
        return ((int)(((Math.random()*((max-min))+ min))*10f)+1)/10.0;
    }
}
