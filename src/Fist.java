import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Fist implements MouseListener {
    Panel panel;
    boolean punched;
    double x;
    double y;
    Player player;
    double time;

    public Fist(Panel panel, Player player) {
        this.panel = panel;
        this.player = player;

    }
    public void punch(){
        time = 1;
    }
    public void tick(){
        time -= 0.1;
        if (time > 0){
            y = Math.sin(panel.slingshot.playerRotation) * 21;
            x = Math.cos(panel.slingshot.playerRotation) * -25;
        }else {
            x = 0;
            y = 0;
        }
    }
    public void drawFist(Graphics2D g2d){
        if (player.fistequiped){
            g2d.rotate(panel.slingshot.playerRotation, player.x + player.width/2, player.y + player.height/2);
            g2d.drawImage(panel.fist, (int)(x + player.x + player.width/2 - panel.fist.getWidth(null)/2), (int)(y + player.y + player.height/2 - panel.fist.getHeight(null)/2), panel.fist.getWidth(null), panel.fist.getHeight(null), null);
            g2d.rotate(-panel.slingshot.playerRotation, player.x + player.width/2, player.y + player.height/2);
        }
        if (panel.player2.fistequiped){
            g2d.rotate(panel.rotation2, panel.player2.x + player.width/2, panel.player2.y + player.height/2);
            g2d.drawImage(panel.fist, panel.player2.x + panel.player2.width/2 - panel.fist.getWidth(null)/2, panel.player2.y + panel.player2.height/2 - panel.fist.getHeight(null)/2, panel.fist.getWidth(null), panel.fist.getHeight(null), null);
            g2d.rotate(-panel.rotation2, panel.player2.x + player.width/2, panel.player2.y + player.height/2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        punch();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
