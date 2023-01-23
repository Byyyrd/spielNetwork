import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener, KeyListener {
    Player player1;
    Player player2;

    Sword sword;
    Sword sword2;

    Weapon bow;
    Weapon bow2;
    double rotation2;


    Client client;
    Timer timer;

    Image playerImage;
    Image swordImage;
    Image bowImage;
    Image bgrImage;
    int bgrWidth;
    int bgrHeight;

    ArrayList<Double[]> allArrows;

    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        bowImage = new ImageIcon("resources/bow.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        bgrHeight = bgrImage.getHeight(null);
        bgrWidth = bgrImage.getWidth(null);

        this.client = client;
        client.setClientPanel(this);

        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword, allArrows);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2, null);

        bow = new Weapon(player1, this, bowImage);
        bow2 = new Weapon(player2, this, bowImage);
        this.addMouseListener(bow);

        sword = new Sword(this, player1);
        sword2 = new Sword(this, player2);
        this.addMouseListener(sword);

        player1.setEnemySword(sword2);
        player2.setEnemySword(sword);

        this.setBounds(0, 0, 100, 100);

        int delay = 10;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void setPlayer2(int x, int y, String name) {
        player2.x = x;
        player2.y = y;
        player2.name = name;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        //Background
        for(int j = 0;j <= this.getHeight()/bgrHeight;j++){
            for (int i = 0; i <= this.getWidth() / bgrWidth; i++) {
                g2d.drawImage(bgrImage, bgrWidth * i, bgrHeight*j, bgrWidth, bgrHeight, null);
            }
        }
        //g2d.drawImage(bgrImage,0,bgrHeight,bgrWidth,bgrHeight,null);

        //Player Names
        g2d.drawString(client.name, player1.x, player1.y);
        g2d.drawString(player2.name, player2.x, player2.y);

        //Player
        g2d.drawImage(playerImage, player1.x, player1.y, player1.width, player1.height, null);
        g2d.drawImage(playerImage, player2.x, player2.y, player2.width, player2.height, null);

        //Swords
        g2d.rotate(sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
        g2d.drawImage(swordImage, sword2.x, (int) (sword2.y - (double) player2.width), player2.width, player2.height * 2, null);
        g2d.rotate(-sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
        g2d.rotate(sword.rotation, player1.x + player1.width * 0.5, player1.y + player1.height * 0.5);
        g2d.drawImage(swordImage, sword.x, (int) (sword.y - (double) player1.width), player1.width, player1.height * 2, null);
        g2d.rotate(-sword.rotation, player1.x + player1.width * 0.5, player1.y + player1.height * 0.5);

        //Slingshot
        if (player1.isBowPickedup()) {
            g2d.rotate(bow.playerRotation, player1.x + playerImage.getWidth(null) * 1.5, player1.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(bowImage, player1.x + playerImage.getWidth(null) * 3 / 2 - bowImage.getWidth(null) / 48, player1.y + playerImage.getHeight(null) * 3 / 2 - bowImage.getHeight(null) / 48, bowImage.getWidth(null) / 24, bowImage.getHeight(null) / 24, null);
            g2d.rotate(-bow.playerRotation, player1.x + playerImage.getWidth(null) * 1.5, player1.y + playerImage.getHeight(null) * 1.5);
        }
        if (player2.isBowPickedup()) {
            g2d.rotate(rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
            g2d.drawImage(bowImage, player2.x + playerImage.getWidth(null) * 3 / 2 - bowImage.getWidth(null) / 48, player2.y + playerImage.getHeight(null) * 3 / 2 - bowImage.getHeight(null) / 48, bowImage.getWidth(null) / 24, bowImage.getHeight(null) / 24, null);
            g2d.rotate(-rotation2, player2.x + playerImage.getWidth(null) * 1.5, player2.y + playerImage.getHeight(null) * 1.5);
        }
        for (int i = bow.allArrows.size(); i >= 1; i--) {
            g2d.setColor(new Color(75, 75, 75));
            g2d.fillOval((int) (bow.allArrows.get(i - 1)[0] + 5), (int) (bow.allArrows.get(i - 1)[1] + 5), 10, 10);
        }
        if (bow2.allArrows != null) {
            for (int i = bow2.allArrows.size(); i >= 1; i--) {
                g2d.setColor(new Color(75, 75, 75));
                g2d.fillOval((int) (allArrows.get(i - 1)[0] + 5), (int) (allArrows.get(i - 1)[1] + 5), 10, 10);
            }
        }
        //Sword Collision Points
        /*g2d.fillOval((int) (sword.x + 21 + Math.sin(sword.rotation) * 21), (int) (player1.y + (double) player1.width/2 + Math.cos(sword.rotation) * -25), 10, 10);
        g2d.fillOval((int) (sword.x + 21 + Math.sin(sword.rotation) * 40), (int) (player1.y + (double) player1.width/2 + Math.cos(sword.rotation) * -40), 10, 10);
        g2d.fillOval((int) (sword.x + 21 + Math.sin(sword.rotation) * 55), (int) (player1.y + (double) player1.width/2 + Math.cos(sword.rotation) * -55), 10, 10);*/
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        player1.keyPressed(e);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.getMousePosition() != null && bow.entert) {
            client.setWeapon(bow);
            client.sendMessage(player1, sword);
            player1.tick();
            sword.tick();
            repaint();
        }
    }

    public void setRotation2(double bow2) {
        this.rotation2 = bow2;
    }

    public void setAllArrows(ArrayList<Double[]> allArrows) {
        this.allArrows = allArrows;
    }
}
