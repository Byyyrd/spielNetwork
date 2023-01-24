import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sword implements MouseListener {
    double rotation;
    long yLenght;
    long xLenght;

    int x;
    int y;
    double offset;
    double cooldown = 0;
    double counterOffset;

    int width;
    int height;

    Player player;
    Panel panel;
    Timer timer;
    Sword sword2;
    Player player2;



    public Sword(Panel panel, Player player) {
        this.panel = panel;
        this.x = player.x;
        this.y = player.y;
        this.player = player;
        this.width = player.width;
        this.height = player.height * 2;

    }

    public void tick() {
        x = player.x;
        y = player.y;
        sword2.x = player2.x;
        sword2.y = player2.y;
        if (panel.slingshot.mousePos != null) {
            yLenght = (long) ((panel.slingshot.mousePos.getY()) - (y + player.height / 2));
            xLenght = (long) ((panel.slingshot.mousePos.getX()) - (x + player.width / 2));
            rotation = (Math.atan2(yLenght, xLenght)) + Math.PI / 2 + offset + counterOffset;
        }
        if (cooldown > 0) {
            cooldown -= 0.025;
        }
        if (offset > -Math.PI / 4) {
            counterOffset = 0;
            offset -= 0.25;
        } else {
            offset = -Math.PI / 4;
            counterOffset = Math.PI / 4;
        }

    }
    public void drawSword(Graphics2D g2d,Image swordImage,Player player2){
        if(player2.swordPickedup) {
            g2d.rotate(sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
            g2d.drawImage(swordImage, sword2.x, (int) (sword2.y - (double) player2.width), player2.width, player2.height * 2, null);
            g2d.rotate(-sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
        }
        if(player.swordPickedup) {
            g2d.rotate(rotation, player.x + player.width * 0.5, player.y + player.height * 0.5);
            g2d.drawImage(swordImage, x, (int) (y - (double) player.width), player.width, player.height * 2, null);
            g2d.rotate(-rotation, player.x + player.width * 0.5, player.y + player.height * 0.5);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3 && cooldown <= 0 && player.isSwordPickedup()) {
            for (int i = 0; i <= 10; i++) {
                if (!player.inRectangle(player.x + 5, player.y, player.player2.x, player.player2.y, player.player2.width, player.player2.width) && !player.inRectangle(player.x + player.player2.width - 5, player.y, player.player2.x, player.player2.y, player.player2.width, player.player2.width)
                        && !player.inRectangle(x + 5, y + player.player2.height, player.player2.x, player.player2.y, player.player2.width, player.player2.width) && !player.inRectangle(x + player.player2.width - 5, y + player.player2.height, player.player2.x, player.player2.y, player.player2.width, player.player2.width)
                        && !player.inRectangle(x, y + 5, player.player2.x, player.player2.y, player.player2.width, player.player2.width) && !player.inRectangle(x, y + player.player2.height - 5, player.player2.x, player.player2.y, player.player2.width, player.player2.width)
                        && !player.inRectangle(x + player.player2.width, y - 5, player.player2.x, player.player2.y, player.player2.width, player.player2.width) && !player.inRectangle(x + player.player2.width, y + player.player2.height - 5, player.player2.x, player.player2.y, player.player2.width, player.player2.width)
                ) {
                    player.x += (int) (Math.cos(Math.atan2(yLenght, xLenght)) * 20);
                    player.y += (int) (Math.sin(Math.atan2(yLenght, xLenght)) * 20);
                }
            }
            cooldown = 3.1;
        }
        if (offset == -Math.PI / 4) {
            offset = Math.PI / 4;
        }
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

    public void setSword2(Sword sword2) {
        this.sword2 = sword2;
    }


    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}

