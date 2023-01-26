import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Mine{
    int minenUeber = 10;
    ArrayList<int[]> alleMinen = new ArrayList<int[]>();
    Player player;
    Player player2;
    boolean minePlased;
    boolean explodet;
    Mine mine;
    Panel panel;
    double time;
    double minenTimer;
    boolean loesche;
    boolean canPlase;

    public Mine(Player player,Player player2, Mine mine, Panel panel) {
        this.player = player;
        this.player2 = player2;
        this.mine = mine;
        this.panel = panel;
    }
    public void tick(){
       time = time - 0.1;
       mine.time = mine.time - 0.1;
       minenTimer -= 0.1;
       mine.minenTimer -= 0.1;
       if (explosionColision(player.x + player.width/2, player.y + player.height/2, 1000,500, 100) && minenTimer <= 0 && minenUeber < 25){
           minenUeber++;
           minenTimer = 4;
       }

        if (explosionColision(player2.x + player2.width/2, player2.y + player2.height/2, 1000,500, 100) && minenTimer <= 0 && mine.minenUeber < 25){
            mine.minenUeber++;
            mine.minenTimer = player.minenTime;
        }
        if (explosionColision(player.x + player.width/2, player.y + player.height/2, 1000,500, 200)||explosionColision(player.x + player.width/2, player.y + player.height/2, 1745,245, 100)||explosionColision(player.x + player.width/2, player.y +player.height/2, 195,845, 100)){
            canPlase = false;
        } else {
            canPlase = true;

        }
        panel.p1Inv.setMines(minenUeber);
    }

    public void createMine(){
        int[] mine = new int[2];
        mine[0] = player.x + player.width/2 ;
        mine[1] = player.y + player.height/2;
        alleMinen.add(mine);
        minePlased = true;
    }

    public void explodemines(){
        if (mine.alleMinen.size() != 0 && mine.alleMinen != null) {
            for (int i = mine.alleMinen.size(); i > 0; i--) {
                if (explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x, player.y, 90) || explosionColision(mine.alleMinen.get(i -1)[0], mine.alleMinen.get(i -1 )[1], player.x + player.width, player.y, 90) || explosionColision(mine.alleMinen.get(i -1 )[0], mine.alleMinen.get(i-1)[1], player.x + player.width / 2, player.y, 90) || explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x + player.width, player.y + player.height / 2, 90) || explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x + player.width, player.y + player.height, 90) || explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x + player.width / 2, player.y + player.height, 90) || explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x, player.y + player.height, 90) || explosionColision(mine.alleMinen.get(i-1)[0], mine.alleMinen.get(i-1)[1], player.x, player.y + player.height / 2, 90)) {
                    panel.p1Inv.playerHit(panel.player2.minenDemage * (1 - panel.player1.minenDamageReduction / 100));
                }
            }
            mine.loesche = true;
        }
    }
    public void drawMine(Graphics2D g2d){
        g2d.setColor(new Color(255, 0, 0));
        for(int i = 0; i < alleMinen.size(); i++){
            g2d.fillOval(alleMinen.get(i)[0], alleMinen.get(i)[1],10,10);
            if (time >= 0) {
                g2d.fillOval(alleMinen.get(i)[0]-45, alleMinen.get(i)[1]-45, 90, 90);
            } else if (loesche) {
                alleMinen.removeAll(alleMinen);
                loesche = false;
            }
        }
        if (mine != null){
            for (int i = 0; i < mine.alleMinen.size(); i++){
                g2d.fillOval(mine.alleMinen.get(i)[0], mine.alleMinen.get(i)[1],10,10);
                if (mine.time >= 0) {
                    g2d.fillOval(mine.alleMinen.get(i)[0]-45, mine.alleMinen.get(i)[1]-45, 90, 90);
                }else if (mine.loesche) {
                    mine.alleMinen.removeAll(mine.alleMinen);
                    mine.loesche = false;
                }
            }
        }
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawOval(900,400,200, 200);
        g2d.drawOval(800,300,400, 400);
        g2d.setColor(new Color(255, 0, 0, 123));
        g2d.fillOval(900,400,200, 200);
    }
    public boolean explosionColision(int x, int y, int x2, int y2, int distance){
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }
    public void keyPressed(KeyEvent e) {
        if (time<= 0) {
            if (e.getKeyCode() == 32) {
                if (minenUeber > 0 && canPlase) {
                    minenUeber--;
                    createMine();
                }
            }
            if (e.getKeyCode() == 69) {
                explodet = true;
                time = 1;
                loesche = true;
            }
        }
    }
}
