import java.awt.*;

public class Enemy {
    int x;
    int y;
    Panel panel;
    Player player;

    public Enemy(int x, int y, Panel panel, Player player) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.player = player;
    }

    public void tick(){
        if (explosionCollision(x,y, player.x + player.width/2, player.y + player.height/2, 20)){
            player.controled = true;
        }else{
                x= (int)(x + player.x + player.width/2 * 0.01);//xMovement
                y = (int) (y + player.y + player.height/2 * 0.01);//yMovement
        }
    }
    public void drawEnemy(Graphics2D g2d){
        g2d.drawImage(panel.enemyImage, x + panel.enemyImage.getWidth(null)/2, y + panel.enemyImage.getHeight(null)/2, panel.enemyImage.getWidth(null), panel.enemyImage.getHeight(null), null);
    }
    public boolean explosionCollision(int x, int y, int x2, int y2, int distance){
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }
}
