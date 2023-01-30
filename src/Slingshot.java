import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Slingshot implements MouseListener, ActionListener {
    double y1;
    double x1;
    long yLength;
    long xLength;
    double playerRotation;
    double time;
    Player player;
    Panel panel;
    Image image;
    Timer timer;
    Point mousePos;
    Client client;
    ArrayList<Double[]> allArrows = new ArrayList<>();
    boolean mouseDown;

    public Slingshot(Player player, Panel panel, Image image) {
        this.player = player;
        this.panel = panel;
        client = panel.client;
        this.image = image;
        timer = new Timer(10, this);
        timer.start();
    }

    public void drawSlingshot(Graphics2D g2d, Player player, Player player2, Image image, Image playerImage, Slingshot slingshot2) {
        if (player.isSlingshotPickedUp()) {
            g2d.rotate(this.playerRotation, player.x + playerImage.getWidth(null) * 1.5, player.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(image, player.x + playerImage.getWidth(null) * 3 / 2 - image.getWidth(null) / 48, player.y + playerImage.getHeight(null) * 3 / 2 - image.getHeight(null) / 48, image.getWidth(null) / 24, image.getHeight(null) / 24, null);
            g2d.rotate(-this.playerRotation, player.x + playerImage.getWidth(null) * 1.5, player.y + playerImage.getHeight(null) * 1.5);
        }
        for (int i = allArrows.size(); i >= 1; i--) {
            g2d.rotate(this.allArrows.get(i - 1)[4] + Math.PI/2, (int) (allArrows.get(i - 1)[0] +1-1), (int) (allArrows.get(i - 1)[1] + 1 - 1));
            g2d.drawImage(panel.arrow, (int) (allArrows.get(i - 1)[0] +1-1) - panel.arrow.getWidth(null) / 2, (int) (allArrows.get(i - 1)[1] + 1 - 1) -panel.arrow.getHeight(null) / 2, panel.arrow.getWidth(null), panel.arrow.getHeight(null), null);
            g2d.rotate(-this.allArrows.get(i - 1)[4] - Math.PI/2, (int) (allArrows.get(i - 1)[0] +1-1), (int) (allArrows.get(i - 1)[1] + 1 - 1));
        }
        if (player2.isSlingshotPickedUp()) {
            g2d.rotate(panel.rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(image, player2.x + playerImage.getWidth(null) * 3 / 2 - image.getWidth(null) / 48, player2.y + playerImage.getHeight(null) * 3 / 2 - image.getHeight(null) / 48, image.getWidth(null) / 24, image.getHeight(null) / 24, null);
            g2d.rotate(-panel.rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
        }
        if (slingshot2.allArrows != null) {
            for (int i = slingshot2.allArrows.size(); i >= 1; i--) {
                g2d.setColor(new Color(12, 255, 0));
                g2d.fillOval((int) (slingshot2.allArrows.get(i - 1)[0] + 1 - 1), (int) (slingshot2.allArrows.get(i - 1)[1] + 1 - 1), 10, 10);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setMousePos(panel.getMousePosition());
        x1 = player.x;
        y1 = player.y;
        if (mousePos != null) {
            yLength = (long) ((mousePos.getY()) - (y1 + player.height / 2));
            xLength = (long) ((mousePos.getX()) - (x1 + player.width / 2));
            playerRotation = Math.atan2(yLength, xLength);
        }

        for (int i = allArrows.size(); i >= 1; i--) {
            if (allArrows.get(i - 1)[0] >= panel.getWidth() || allArrows.get(i - 1)[0] <= 0) {
                allArrows.remove(i - 1);
            } else if (allArrows.get(i - 1)[1] >= panel.getHeight() || allArrows.get(i - 1)[1] <= 0) {
                allArrows.remove(i - 1);
            }
        }
        for (int i = allArrows.size(); i >= 1; i--) {
            allArrows.get(i - 1)[0] = (allArrows.get(i - 1)[0] + allArrows.get(i - 1)[2] * 1500 * 0.01 * (player.arrowVelocity + panel.inventory.getArrowVelocity()));//xMovement
            allArrows.get(i - 1)[1] = (allArrows.get(i - 1)[1] + allArrows.get(i - 1)[3] * 1500 * 0.01 * (player.arrowVelocity + panel.inventory.getArrowVelocity()));//yMovement
        }
    }

    public void CreateArrow() {
        Double[] Arrow = new Double[5];
        Arrow[0] = ((double) player.x + player.width / 2);// x1 Index = 0
        Arrow[1] = ((double) player.y + player.height / 2);// y1 Index = 1
        Arrow[2] = Math.cos(playerRotation);//xVel
        Arrow[3] = Math.sin(playerRotation);//yVel
        Arrow[4] = playerRotation;
        allArrows.add(Arrow);
    }

    public void player2CreateArrow(double mouseX, double mouseY) {

        if (mousePos != null) {
            long yLength = (long) (mouseY - player.y);
            long xLength = (long) (mouseX - player.x);
            double Rotation = Math.atan2(yLength, xLength);
            Double[] Arrow = new Double[5];
            Arrow[0] = ((double) player.x + player.width);// x1 Index = 0
            Arrow[1] = ((double) player.y + player.height);// y1 Index = 1
            Arrow[2] = Math.cos(Rotation);//xVel
            Arrow[3] = Math.sin(Rotation);//yVel
            Arrow[4] = playerRotation;
            allArrows.add(Arrow);
        }
    }

    public void tick() {
        time -= 0.1;
        if (mouseDown) {
            if (!explosionCollision(player.x + player.width / 2, player.y + player.height / 2, 1745, 245, 100) && !explosionCollision(player.x + player.width / 2, player.y + player.height / 2, 195, 845, 100)) {
                if (time <= 0) {
                    CreateArrow();
                    client.setClicked(true);
                    time = player.arrowTime + panel.inventory.getArrowTime();
                }
            }
        }
    }

    public void setMousePos(Point mousePos) {
        if (mousePos != null) {
            this.mousePos = mousePos;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (player.isSlingshotPickedUp()) {
            mouseDown = true;
        }
    }

    public boolean explosionCollision(int x, int y, int x2, int y2, int distance) {
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}