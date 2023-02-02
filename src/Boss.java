import java.awt.*;

public class Boss {

    int x;
    int y;
    double hp;
    double damage;
    double speed = 10;
    double rotation = 0;
    Image bossImage;
    Player player;
    int width;
    int height;
    int range = 700;
    public Boss(int x, int y, double hp, double damage,Player player) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
        this.player = player;
    }
    public void drawBoss(Graphics2D g2d,Image bossImage){
        this.bossImage = bossImage;
        width = bossImage.getWidth(null)*2;
        height = bossImage.getHeight(null)*2;
        g2d.drawImage(bossImage,x,y,width,height,null);
        //g2d.drawOval(x+width/2-range,y+height/2-range,range*2,range*2);
    }
    public void tick(double dt){

        x += Math.cos(rotation) * speed;
        y += Math.sin(rotation) * speed;
        if(x + width > 1900){
            rotation = random(2,6);
        }
        if(x < 0){
            rotation = random(-2,2);
        }
        if (y + height > 1080){
            rotation = random(Math.PI+0.5,Math.PI*2-0.5);
        }
        if (y < 0){
            rotation = random(0.5,Math.PI-0.5);
        }
        if (inRange(x,y,player.x,player.y,range)){
            fireBullet();
        }
    }
    public void fireBullet(){
        double xLength = 0;
        double yLength = 0;
        rotation = Math.atan2(yLength,xLength);
    }
    public boolean inRange(int x1,int y1,int x2,int y2,int range){
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)) < range;
    }
    public double random(double min, double max){
        if (min == 0 && max == 0){
            return 0;
        }
        return ((int)(((Math.random()*((max-min))+ min))*10f)+1)/10.0;
    }
}
