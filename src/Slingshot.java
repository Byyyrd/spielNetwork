import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class Slingshot implements MouseListener, ActionListener {
    double y1;
    double x1;
    long yLenght;
    long xLenght;
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
        if (player.isSlingshotPickedup()) {
            g2d.rotate(this.playerRotation, player.x + playerImage.getWidth(null) * 1.5, player.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(image, player.x + playerImage.getWidth(null) * 3 / 2 - image.getWidth(null) / 48, player.y + playerImage.getHeight(null) * 3 / 2 - image.getHeight(null) / 48, image.getWidth(null) / 24, image.getHeight(null) / 24, null);
            g2d.rotate(-this.playerRotation, player.x + playerImage.getWidth(null) * 1.5, player.y + playerImage.getHeight(null) * 1.5);
        }
        for (int i = allArrows.size(); i >= 1; i--) {
            g2d.setColor(new Color(12, 255, 0));
            g2d.fillOval((int) (this.allArrows.get(i - 1)[0] + 5), (int) (this.allArrows.get(i - 1)[1] + 5), 10, 10);
        }
        if (player2.isSlingshotPickedup()) {
            g2d.rotate(panel.rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(image, player2.x + playerImage.getWidth(null) * 3 / 2 - image.getWidth(null) / 48, player2.y + playerImage.getHeight(null) * 3 / 2 - image.getHeight(null) / 48, image.getWidth(null) / 24, image.getHeight(null) / 24, null);
            g2d.rotate(-panel.rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
        }
        if (slingshot2.allArrows != null) {
            for (int i = slingshot2.allArrows.size(); i >= 1; i--) {
                g2d.setColor(new Color(12, 255, 0));
                g2d.fillOval((int) (slingshot2.allArrows.get(i - 1)[0] + 5), (int) (slingshot2.allArrows.get(i - 1)[1] + 5), 10, 10);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setMousePos(panel.getMousePosition());
        x1 = player.x;
        y1 = player.y;
        if (mousePos != null) {
            yLenght = (long) ((mousePos.getY()) - y1);
            xLenght = (long) ((mousePos.getX()) - x1);
            playerRotation = Math.atan2(yLenght, xLenght);
        }
        for (int i = allArrows.size(); i >= 1; i--) {
            if (Objects.equals(allArrows.get(i - 1)[0], allArrows.get(i - 1)[2]) && allArrows.get(i - 1)[3] < allArrows.get(i - 1)[1]) {
                allArrows.get(i - 1)[3] = 2500 + (allArrows.get(i - 1)[7] * allArrows.get(i - 1)[2] + allArrows.get(i - 1)[8]);
            } else if (Objects.equals(allArrows.get(i - 1)[0], allArrows.get(i - 1)[2]) && allArrows.get(i - 1)[3] > allArrows.get(i - 1)[1]) {
                allArrows.get(i - 1)[3] = 2500 - (allArrows.get(i - 1)[7] * allArrows.get(i - 1)[2] + allArrows.get(i - 1)[8]);
            } else {
                allArrows.get(i - 1)[3] = allArrows.get(i - 1)[7] * allArrows.get(i - 1)[2] + allArrows.get(i - 1)[8];
            }
        }

        for (int i = allArrows.size(); i >= 1; i--) {
            allArrows.get(i - 1)[5] = (allArrows.get(i - 1)[2]) - (allArrows.get(i - 1)[0]);//xLength
            allArrows.get(i - 1)[6] = (allArrows.get(i - 1)[3]) - (allArrows.get(i - 1)[1]);//yLength
            allArrows.get(i - 1)[4] = (Math.atan2(allArrows.get(i - 1)[6], allArrows.get(i - 1)[5]));//Rotation
        }
        for (int i = allArrows.size(); i >= 1; i--) {
            if (allArrows.get(i - 1)[6] > -3 && allArrows.get(i - 1)[6] < 3 || allArrows.get(i - 1)[5] < 3 && allArrows.get(i - 1)[5] > -3) {
                allArrows.remove(i - 1);
            } else {
                allArrows.get(i - 1)[0] = (allArrows.get(i - 1)[0] + (Math.cos(allArrows.get(i - 1)[4]) * 1500 * 0.01));//xMovement
                allArrows.get(i - 1)[1] = (allArrows.get(i - 1)[1] + (Math.sin(allArrows.get(i - 1)[4]) * 1500 * 0.01));//yMovement
            }
        }

        x1 = x1 + (int) (Math.cos(playerRotation) * 500 * 0.01);
        y1 = y1 + (int) (Math.sin(playerRotation) * 500 * 0.01);
        for (int i = allArrows.size(); i >= 1; i--) {
            if (allArrows.get(i - 1)[0] >= panel.getWidth() || allArrows.get(i - 1)[0] <= 0) {
                allArrows.remove(i - 1);
            } else if (allArrows.get(i - 1)[1] >= panel.getHeight() || allArrows.get(i - 1)[1] <= 0) {
                allArrows.remove(i - 1);
            }
        }


    }

    public void CreateArrow() {
        if (mousePos != null) {
            Double[] Arrow = new Double[9];
            Arrow[0] = ((double) player.x);// x1 Index = 0
            Arrow[1] = ((double) player.y);// y1 Index = 1
            if (x1 > mousePos.getX()) {
                Arrow[2] = ((mousePos.getX()) - 500);//x2 Index = 2
            } else if (x1 < mousePos.getX()) {
                Arrow[2] = ((mousePos.getX()) + 500);//x2 Index = 2
            } else {
                Arrow[2] = ((mousePos.getX()));//x2 Index = 2
            }
            Arrow[3] = ((mousePos.getY()));//y2 Index = 3
            Arrow[4] = (0.0); //Rotation Index = 4
            Arrow[5] = (0.0); //xLength Index = 5
            Arrow[6] = (0.0); //yLength Index = 6
            Arrow[7] = ((mousePos.getY() - y1) / (mousePos.getX() - x1));//LineareFunktion : m Index = 7
            Arrow[8] = (y1 - ((mousePos.getY() - y1) / (mousePos.getX() - x1)) * x1); //LineareFunktion : b Index = 8
            allArrows.add(Arrow);
        }
    }

    public void player2CreateArrow(double mouseX, double mouseY) {
        Double[] Arrow = new Double[9];
        Arrow[0] = ((double) player.x);// x1 Index = 0
        Arrow[1] = ((double) player.y);// y1 Index = 1
        if (x1 > mouseX) {
            Arrow[2] = ((mouseX) - 500);//x2 Index = 2
        } else if (x1 < mouseX) {
            Arrow[2] = (mouseX + 500);//x2 Index = 2
        } else {
            Arrow[2] = (mouseX);//x2 Index = 2
        }

        Arrow[3] = (mouseY);//y2 Index = 3
        Arrow[4] = (0.0); //Rotation Index = 4
        Arrow[5] = (0.0); //xLength Index = 5
        Arrow[6] = (0.0); //yLength Index = 6
        Arrow[7] = ((mouseY - y1) / (mouseX - x1));//LineareFunktion : m Index = 7
        Arrow[8] = (y1 - ((mouseY - y1) / (mouseX - x1)) * x1); //LineareFunktion : b Index = 8
        this.allArrows.add(Arrow);
    }

    public void tick(){
        time -= 0.1;
        if (mouseDown) {
            if (!explosionColision(player.x + player.width / 2, player.y + player.height / 2, 1745, 245, 100) && !explosionColision(player.x + player.width / 2, player.y + player.height / 2, 195, 845, 100)) {
                if (time <= 0) {
                    CreateArrow();
                    client.setClicked(true);
                    time = player.arrowTime;
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
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (player.isSlingshotPickedup()) {
            mouseDown = true;
        }
    }

    public boolean explosionColision(int x, int y, int x2, int y2, int distance) {
        return Math.abs(x - x2) < distance && Math.abs(y - y2) < distance;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}