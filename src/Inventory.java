import java.awt.*;

public class Inventory {
    double hp;
    double enemyHp;
    int mines;
    double dashCooldown;
    int maxHp = 10;
    Player player;
    Player player2;
    int death;
    int death2;
    public Inventory(int maxHp,Player player, Player player2) {
        this.maxHp = maxHp;
        this.player = player;
        this.player2 = player2;
        hp = maxHp;
    }
    public void playerHit(double damage){
        System.out.println(hp);
        if (player.iFrame <= 0){
            hp -= damage;
            player.hp = hp;
            player.iFrame = player.iFrametime;
        }

        if (hp <= 0){
            player.x = 0;
            player.y = 0;
            death++;
            hp = maxHp;
            player.swordPickedup = false;
            player.slingshotPickedup = true;
        }
        if (enemyHp <= 0){
            death2++;
        }
    }
    public void drawInventory(Graphics2D g2d,Image hpImage){
        g2d.setColor(new Color(1,1,1, 139));
        g2d.fillRect(0,950,1920,150);
        g2d.setColor(Color.red);
        g2d.fillRect(0,975, (int) (hp*50),50);
        g2d.drawImage(hpImage,0,975,hpImage.getWidth(null),hpImage.getHeight(null),null);
        g2d.setFont(new Font("Arial", Font.PLAIN, 40));
        g2d.drawString("Minen: " + mines, 525, 1015);
        g2d.drawString("Dash: " + ((int)(dashCooldown*10f))/10f, 725, 1015);
        g2d.drawString("p1 " + death2 * 5 + " : p2 " + death * 5, 925,1015);
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void setDashCooldown(double dashCooldown) {
        this.dashCooldown = dashCooldown;
    }
}
