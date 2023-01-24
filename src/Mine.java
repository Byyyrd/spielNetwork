import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Mine{
    int minenUeber = 5;
    ArrayList<int[]> alleMinen = new ArrayList<int[]>();
    Player player;
    boolean minePlased;
    boolean explodet;
    Mine mine;

    public Mine(Player player, Mine mine) {
        this.player = player;
        this.mine = mine;
    }

    public void createMine(){
        int[] mine = new int[2];
        mine[0] = player.x ;
        mine[1] = player.y;
        alleMinen.add(mine);
        minePlased = true;
    }

    public void explodemines(){
        if (mine.alleMinen.size() != 0) {
            for (int i = 0; i < mine.alleMinen.size(); i++) {
                if (explosionColision(mine.alleMinen.get(i)[0], mine.alleMinen.get(i)[1], player.x, player.y, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x + player.width, player.y, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x + player.width / 2, player.y, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x + player.width, player.y + player.height / 2, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x + player.width, player.y + player.height, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x + player.width / 2, player.y + player.height, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x, player.y + player.height, 30) || explosionColision(alleMinen.get(i)[0], alleMinen.get(i)[1], player.x, player.y + player.height / 2, 30)) {
                    player.x = 0;
                    player.y = 0;
                }
                mine.alleMinen.remove(i);
            }
        }
    }
    public void drawMine(Graphics2D g2d){
        for(int i = 0; i < alleMinen.size(); i++){
            g2d.fillOval(alleMinen.get(i)[0], alleMinen.get(i)[1],10,10);
        }
        if (mine != null){
            for (int i = 0; i < mine.alleMinen.size(); i++){
                g2d.fillOval(mine.alleMinen.get(i)[0], mine.alleMinen.get(i)[1],10,10);

            }
        }
    }
    public boolean explosionColision(int x, int y, int x2, int y2, int distance){
        if (Math.abs(x-x2) < distance && Math.abs(y-y2) < distance){
            return true;
        }else{
            return false;
        }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32){
            if (minenUeber > 0){
                minenUeber--;
                createMine();
            }
        }
        if (e.getKeyCode() == 69){
            explodet = true;
            for (int i = 0; i < alleMinen.size(); i++){
                alleMinen.remove(i);
            }
        }
    }


}
