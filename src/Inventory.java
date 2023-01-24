import java.awt.*;

public class Inventory {
    double hp;
    int mines;
    int dashCooldown;
    int maxHp = 10;
    Player player;
    public Inventory(int maxHp,Player player) {
        this.maxHp = maxHp;
        this.player = player;
        hp = maxHp;
    }
    public void playerHit(double damage){
        System.out.println(hp);
        hp -= damage;
        player.hp = hp;
        if (hp <= 0){
            player.x = 0;
            player.y = 0;
            hp = maxHp;
        }
    }
    public void drawInventory(Graphics2D g2d,Image hpImage){
        g2d.setColor(new Color(1,1,1, 139));
        g2d.fillRect(0,900,1920,200);
        g2d.setColor(Color.red);
        g2d.fillRect(0,950, (int) (hp*50),50);
        g2d.drawImage(hpImage,0,950,hpImage.getWidth(null),hpImage.getHeight(null),null);
    }
}
