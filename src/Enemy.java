import java.awt.*;
import java.io.Serializable;

public class Enemy implements Serializable {
    int x;
    int y;
    double xVel;
    double yVel;
    double xLength;
    double yLength;
    double playerRotation;
    int i;
    private Panel panel;
    private Player player;

    public Enemy(int x, int y, Panel panel, Player player, int i) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.player = player;
        this.i = i;
    }

    public void tick(){
        if (explosionCollision(x + panel.enemyImage.getWidth(null)/10,y + panel.enemyImage.getHeight(null)/10, player.x + player.width/2, player.y + player.height/2, 10)){
            player.controled = true;
            panel.enemies.remove(i);
        }else{
            yLength = (long) ((player.y + player.height/2) - (y + panel.enemyImage.getHeight(null)/10));
            xLength = (long) ((player.x + player.width/2) - (x + panel.enemyImage.getWidth(null)/10));
            playerRotation = Math.atan2(yLength, xLength);
            xVel = Math.cos(playerRotation);//xVel
            yVel = Math.sin(playerRotation);//yVel
            x= (int) (x + xVel * 5);//xMovement
            y = (int) (y + yVel * 5);//yMovement
        }
    }
    public void drawEnemy(Graphics2D g2d){
        g2d.drawImage(panel.enemyImage, x + panel.enemyImage.getWidth(null)/10, y + panel.enemyImage.getHeight(null)/10, panel.enemyImage.getWidth(null)/5, panel.enemyImage.getHeight(null)/5, null);
    }
    public boolean explosionCollision(int x, int y, int x2, int y2, int distance){
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }
}
