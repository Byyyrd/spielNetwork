import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Mine{
    int minesLeft = 10;
    ArrayList<int[]> allMines = new ArrayList<>();
    Player player;
    Player player2;
    boolean minePlaced;
    boolean exploded;
    Mine mine;
    Panel panel;
    double time;
    double mineTimer;
    boolean delete;
    boolean canPlace;

    public Mine(Player player,Player player2, Mine mine, Panel panel) {
        this.player = player;
        this.player2 = player2;
        this.mine = mine;
        this.panel = panel;
    }
    public void tick(){
       time = time - 0.1;
       mine.time = mine.time - 0.1;
       mineTimer -= 0.1;
       mine.mineTimer -= 0.1;
       if (explosionCollision(player.x + player.width/2, player.y + player.height/2, 1000,500, 100) && mineTimer <= 0 && minesLeft < player.maxMines){
           minesLeft++;
           mineTimer = player.mineTime + panel.inventory.getMineTime();
       }

        if (explosionCollision(player2.x + player2.width/2, player2.y + player2.height/2, 1000,500, 100) && mineTimer <= 0 && mine.minesLeft < player2.maxMines){
            mine.minesLeft++;
            mine.mineTimer = player2.mineTime;
        }
        canPlace = !explosionCollision(player.x + player.width / 2, player.y + player.height / 2, 1000, 500, 200) && !explosionCollision(player.x + player.width / 2, player.y + player.height / 2, 1745, 245, 100) && !explosionCollision(player.x + player.width / 2, player.y + player.height / 2, 195, 845, 100);
        panel.ui.setMines(minesLeft);
    }

    public void createMine(){
        int[] mine = new int[2];
        mine[0] = player.x + player.width/2 ;
        mine[1] = player.y + player.height/2;
        allMines.add(mine);
        minePlaced = true;
    }

    public void explodeMines(){
        if (mine.allMines.size() != 0 && mine.allMines != null) {
            for (int i = mine.allMines.size(); i > 0; i--) {
                if (explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x, player.y, 90) || explosionCollision(mine.allMines.get(i -1)[0], mine.allMines.get(i -1 )[1], player.x + player.width, player.y, 90) || explosionCollision(mine.allMines.get(i -1 )[0], mine.allMines.get(i-1)[1], player.x + player.width / 2, player.y, 90) || explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x + player.width, player.y + player.height / 2, 90) || explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x + player.width, player.y + player.height, 90) || explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x + player.width / 2, player.y + player.height, 90) || explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x, player.y + player.height, 90) || explosionCollision(mine.allMines.get(i-1)[0], mine.allMines.get(i-1)[1], player.x, player.y + player.height / 2, 90)) {
                    panel.ui.playerHit(panel.player2.mineDamage * (1 - panel.player1.mineDamageReduction + panel.player2.mineDamageReduction/ 100));
                }
            }
            mine.delete = true;
        }
    }
    public void drawMine(Graphics2D g2d){
        g2d.setColor(new Color(255, 0, 0));
        for(int i = 0; i < allMines.size(); i++){
            g2d.fillOval(allMines.get(i)[0], allMines.get(i)[1],10,10);
            if (time >= 0) {
                g2d.fillOval(allMines.get(i)[0]-45, allMines.get(i)[1]-45, 90, 90);
            } else if (delete) {
                allMines.removeAll(allMines);
                delete = false;
            }
        }
        if (mine != null){
            for (int i = 0; i < mine.allMines.size(); i++){
                g2d.fillOval(mine.allMines.get(i)[0], mine.allMines.get(i)[1],10,10);
                if (mine.time >= 0) {
                    g2d.fillOval(mine.allMines.get(i)[0]-45, mine.allMines.get(i)[1]-45, 90, 90);
                }else if (mine.delete) {
                    mine.allMines.removeAll(mine.allMines);
                    mine.delete = false;
                }
            }
        }
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawOval(900,400,200, 200);
        g2d.drawOval(800,300,400, 400);
        g2d.setColor(new Color(255, 0, 0, 123));
        g2d.fillOval(900,400,200, 200);
    }
    public boolean explosionCollision(int x, int y, int x2, int y2, int distance){
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }
    public void keyPressed(KeyEvent e) {
        if (time<= 0) {
            if (e.getKeyCode() == 32) {
                if (minesLeft > 0 && canPlace) {
                    minesLeft--;
                    createMine();
                }
            }
            if (e.getKeyCode() == 69) {
                exploded = true;
                time = 1;
                delete = true;
            }
        }
    }
}
