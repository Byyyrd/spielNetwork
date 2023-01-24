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
    Image projImage;
    int bgrWidth;
    int bgrHeight;
    ArrayList<Double[]> allArrows;
    ArrayList<int[]> allObsticals;


    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        bowImage = new ImageIcon("resources/bow.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        projImage = new ImageIcon("resources/Projectile.png").getImage();
        bgrHeight = bgrImage.getHeight(null);
        bgrWidth = bgrImage.getWidth(null);

        this.client = client;
        client.setClientPanel(this);
        allObsticals = new ArrayList<int[]>();
        var obstical = new int[4];
        obstical[0] = 900;
        obstical[1] = 300;
        obstical[2] = 200;
        obstical[3] = 30;
        allObsticals.add(obstical);
        var obstical1 = new int[4];
        obstical1[0] = 390;
        obstical1[1] = 730;
        obstical1[2] = 50;
        obstical1[3] = 190;
        allObsticals.add(obstical1);
        var obstical2 = new int[4];
        obstical2[0] = 550;
        obstical2[1] = 90;
        obstical2[2] = 30;
        obstical2[3] = 30;
        allObsticals.add(obstical2);
        var obstical3 = new int[4];
        obstical3[0] = 200;
        obstical3[1] = 90;
        obstical3[2] = 40;
        obstical3[3] = 600;
        allObsticals.add(obstical3);
        var obstical4 = new int[4];
        obstical4[0] = 1290;
        obstical4[1] = 600;
        obstical4[2] = 100;
        obstical4[3] = 90;
        allObsticals.add(obstical4);
        var obstical5 = new int[4];
        obstical5[0] = 670;
        obstical5[1] = 740;
        obstical5[2] = 370;
        obstical5[3] = 50;
        allObsticals.add(obstical5);
        var obstical6 = new int[4];
        obstical6[0] = 1300;
        obstical6[1] = 50;
        obstical6[2] = 70;
        obstical6[3] = 370;
        allObsticals.add(obstical6);
        var obstical7 = new int[4];
        obstical7[0] = 1500;
        obstical7[1] = 470;
        obstical7[2] = 370;
        obstical7[3] = 70;
        allObsticals.add(obstical7);
        var obstical8 = new int[4];
        obstical8[0] = 730;
        obstical8[1] = 190;
        obstical8[2] = 20;
        obstical8[3] = 400;
        allObsticals.add(obstical8);
        var obstical9 = new int[4];
        obstical9[0] = 360;
        obstical9[1] = 470;
        obstical9[2] = 250;
        obstical9[3] = 40;
        allObsticals.add(obstical9);
        
        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword,this);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2,this);

        bow = new Weapon(player1, this, bowImage);
        bow2 = new Weapon(player2, this, bowImage);
        this.addMouseListener(bow);

        player1.setAllArrows(bow2.allArrows);
        player1.setAllArrowsself(bow.allArrows);

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

        for (int i = 0; i < allObsticals.size(); i++){
            g2d.setColor(new Color(0, 21, 255));
            g2d.drawRect(allObsticals.get(i)[0],allObsticals.get(i)[1],allObsticals.get(i)[2],allObsticals.get(i)[3]);
        }
        //g2d.drawImage(bgrImage,0,bgrHeight,bgrWidth,bgrHeight,null);

        //Player Names
        g2d.setColor(Color.red);
        g2d.drawString(client.name + "  " + ((int)(sword.cooldown*10f))/10f, player1.x, player1.y);
        g2d.drawString(player2.name + "  " + ((int)(sword2.cooldown*10f))/10f, player2.x, player2.y);

        //Player
        g2d.drawImage(playerImage, player1.x, player1.y, player1.width, player1.height, null);
        g2d.drawImage(playerImage, player2.x, player2.y, player2.width, player2.height, null);

        //Swords
        if(player2.swordPickedup) {
            g2d.rotate(sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
            g2d.drawImage(swordImage, sword2.x, (int) (sword2.y - (double) player2.width), player2.width, player2.height * 2, null);
            g2d.rotate(-sword2.rotation, player2.x + player2.width * 0.5, player2.y + player2.height * 0.5);
        }
        if(player1.swordPickedup) {
            g2d.rotate(sword.rotation, player1.x + player1.width * 0.5, player1.y + player1.height * 0.5);
            g2d.drawImage(swordImage, sword.x, (int) (sword.y - (double) player1.width), player1.width, player1.height * 2, null);
            g2d.rotate(-sword.rotation, player1.x + player1.width * 0.5, player1.y + player1.height * 0.5);
        }

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
            g2d.setColor(new Color(12, 255, 0));
            g2d.fillOval((int) (bow.allArrows.get(i - 1)[0] + 5), (int) (bow.allArrows.get(i - 1)[1] + 5), 10, 10);
        }
        if (bow2.allArrows != null) {
            for (int i = bow2.allArrows.size(); i >= 1; i--) {
                g2d.setColor(new Color(12, 255, 0));
                g2d.fillOval((int) (bow2.allArrows.get(i - 1)[0] + 5), (int) (bow2.allArrows.get(i - 1)[1] + 5), 10, 10);
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
        if (e.getKeyCode() == 70){
            player1.setBowPickedup(!player1.isBowPickedup());
            player1.setSwordPickedup(!player1.isSwordPickedup());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(bow.mousePos != null && bow.entert) {
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
