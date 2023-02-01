import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Fist implements MouseListener {
    Panel panel;
    double x;
    double y;
    double x2;
    double y2;
    Player player;
    double time;

    public Fist(Panel panel, Player player) {
        this.panel = panel;
        this.player = player;

    }

    public void punch() {
        time = 1;
    }

    public void tick() {

        time -= 0.1;
        if (time > 0) {
            y = Math.sin(panel.bow.playerRotation) * 25;
            x = Math.cos(panel.bow.playerRotation) * 25;
        } else {
            x = 0;
            y = 0;
        }
        if (inRectangle((int) (x2 + panel.player2.x + panel.player2.width/2+Math.cos(panel.rotation2) * panel.player2.width/2), (int)(y2 + panel.player2.y + panel.player2.height / 2 + Math.sin(panel.rotation2)* panel.player2.width/2), player.x, player.y, player.width, player.height) || inRectangle((int) (x2 + panel.player2.x + panel.player2.width/2+Math.cos(panel.rotation2 + 0.5) * panel.player2.width/2), (int)(y2 + panel.player2.y + panel.player2.height / 2 + Math.sin(panel.rotation2 + 0.5)* panel.player2.width/2), player.x, player.y, player.width, player.height) || inRectangle((int) (x2 + panel.player2.x + panel.player2.width/2+Math.cos(panel.rotation2 - 0.5) * panel.player2.width/2), (int)(y2 + panel.player2.y + panel.player2.height / 2 + Math.sin(panel.rotation2 - 0.5)* panel.player2.width/2), player.x, player.y, player.width, player.height)) {
            panel.ui.playerHit(2);
        }
    }

    public void drawFist(Graphics2D g2d) {
        if (player.fistequiped) {
            g2d.rotate(panel.bow.playerRotation, player.x + player.width * 0.5 + x, player.y + player.height * 0.5 + y);
            g2d.drawImage(panel.fist, (int) (x + player.x + player.width * 0.5 - panel.fist.getWidth(null) * 0.5), (int) (y + player.y + player.height * 0.5 - panel.fist.getHeight(null) * 0.5), panel.fist.getWidth(null), panel.fist.getHeight(null), null);
            g2d.rotate(-panel.bow.playerRotation, player.x + player.width * 0.5 + x, player.y + player.height * 0.5 + y);


            g2d.fillOval((int) (x + player.x + player.width/2+Math.cos(panel.bow.playerRotation) * player.width/2), (int)(y + player.y + player.height / 2 + Math.sin(panel.bow.playerRotation)* player.width/2), 5, 5);
            g2d.fillOval((int) (x + player.x + player.width/2+Math.cos(panel.bow.playerRotation + 0.5) * player.width/2), (int)(y + player.y + player.height / 2 + Math.sin(panel.bow.playerRotation + 0.5)* player.width/2), 5, 5);
            g2d.fillOval((int) (x + player.x + player.width/2+Math.cos(panel.bow.playerRotation - 0.5) * player.width/2), (int)(y + player.y + player.height / 2 + Math.sin(panel.bow.playerRotation - 0.5)* player.width/2), 5, 5);
        }
        if (panel.player2.fistequiped) {
            g2d.rotate(panel.rotation2, panel.player2.x + player.width * 0.5 + x2, panel.player2.y + player.height * 0.5 + y2);
            g2d.drawImage(panel.fist, (int) (x2 + panel.player2.x + panel.player2.width * 0.5 - panel.fist.getWidth(null) * 0.5), (int) (y2 + panel.player2.y + panel.player2.height * 0.5 - panel.fist.getHeight(null) * 0.5), panel.fist.getWidth(null), panel.fist.getHeight(null), null);
            g2d.rotate(-panel.rotation2, panel.player2.x + player.width * 0.5 + x2, panel.player2.y + player.height * 0.5 + y2);
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

    public boolean inRectangle(int px, int py, int rx, int ry, int rb, int rh) {
        return rx < px && px < rx + rb && ry < py && py < ry + rh;

    }

}
