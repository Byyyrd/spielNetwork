import javax.swing.*;
import java.awt.event.*;

public class Sword implements MouseListener,ActionListener {
    double rotation;
    long yLenght;
    long xLenght;

    int x;
    int y;
    boolean inScreen = true;
    double offset;
    double cooldown;
    double counterOffset;

    int width;
    int height;

    Player player;

    Panel panel;

    Timer timer;

    public Sword(Panel panel, Player player) {
        this.panel = panel;
        this.x = player.x;
        this.y = player.y;
        this.player = player;
        this.width = player.width;
        this.height = player.height * 2;
        Timer timer = new Timer(25,this);
        timer.start();
    }

    public void tick() {
        x = player.x;
        y = player.y;
        if (panel.bow.mousePos != null && inScreen) {
            yLenght = (long) ((panel.bow.mousePos.getY()) - (y + player.height / 2));
            xLenght = (long) ((panel.bow.mousePos.getX()) - (x + player.width / 2));
            rotation = (Math.atan2(yLenght, xLenght)) + Math.PI / 2 + offset + counterOffset;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(offset == -Math.PI / 4) {
            offset = Math.PI / 2;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        inScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inScreen = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = player.x;
        y = player.y;
        cooldown--;
        if (offset > -Math.PI / 4) {
            counterOffset = 0;
            offset -= 0.25;
        } else {
            offset = -Math.PI / 4;
            counterOffset = Math.PI / 4;
        }
    }
}

