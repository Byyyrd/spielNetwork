import java.awt.*;

public class Inventory {
    double hp;
    double enemyHp;
    int mines;
    int dashCooldown;
    int maxHp = 10;
    Player player;
    Player player2;
    public Inventory(int maxHp,Player player, Player player2) {
        this.maxHp = maxHp;
        this.player = player;
        this.player2 = player2;
        hp = maxHp;
    }
    public void playerHit(double damage){
        if (player.iFrame <= 0){
            hp -= damage;
            player.hp = hp;
            player.iFrame = 0.5;
        }

        if (hp <= 0){
            player.x = 0;
            player.y = 0;
            hp = maxHp;
            player.swordPickedup = false;
            player.slingshotPickedup = true;
        }
    }
    public void drawInventory(Graphics2D g2d,Image hpImage){
        g2d.setColor(new Color(1,1,1, 139));
        g2d.fillRect(0,950,1920,150);
        g2d.setColor(Color.red);
        g2d.fillRect(0,950, (int) (hp*50),50);
        g2d.drawImage(hpImage,0,950,hpImage.getWidth(null),hpImage.getHeight(null),null);
        g2d.setColor(Color.red);
        g2d.fillRect(player.x, player.y + player.height + 3, (int) (4.8*hp),10);
        g2d.drawImage(hpImage,player.x, player.y + player.height + 3, player.width,10,null);
        g2d.setColor(Color.red);
        g2d.fillRect(player2.x, player2.y + player2.height + 3, (int) ((4.8*enemyHp)),10);
        g2d.drawImage(hpImage,player2.x, player2.y + player2.height + 3, player2.width,10,null);
    }
}
