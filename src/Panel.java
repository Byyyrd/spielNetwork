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

    Slingshot slingshot;
    Slingshot slingshot2;
    double rotation2;

    Mine mine;
    Mine mine2;

    Client client;
    Timer timer;

    Image playerImage;
    Image swordImage;
    Image bowImage;
    Image bgrImage;
    Image projImage;
    Image hpImage;
    int bgrWidth;
    int bgrHeight;
    ArrayList<Double[]> allArrows;
    ArrayList<int[]> allObsticals;
    Inventory p1Inv;
    JTextArea messageOutput;
    JTextArea messageInput;
    Font font;
    boolean send;
    String message;

    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        bowImage = new ImageIcon("resources/bow.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        projImage = new ImageIcon("resources/Projectile.png").getImage();
        hpImage = new ImageIcon("resources/Healthbar.png").getImage();
        bgrHeight = bgrImage.getHeight(null);
        bgrWidth = bgrImage.getWidth(null);

        font = new Font("Arial", Font.PLAIN, 20);

        this.client = client;
        client.setClientPanel(this);
        allObsticals = new ArrayList<int[]>();
        addObstacles();
        
        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword,this);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2,this);

        slingshot = new Slingshot(player1, this, bowImage);
        slingshot2 = new Slingshot(player2, this, bowImage);
        this.addMouseListener(slingshot);

        player1.setAllArrows(slingshot2.allArrows);
        player1.setAllArrowsself(slingshot.allArrows);

        mine2 = new Mine(player2, null,null, null);
        mine = new Mine(player1, player2, mine2, this);

        sword = new Sword(this, player1);
        sword2 = new Sword(this, player2);
        sword.setSword2(sword2);
        sword2.setSword2(sword);
        sword.setPlayer2(player2);
        sword2.setPlayer2(player1);
        this.addMouseListener(sword);

        p1Inv = new Inventory(10,player1);


        player1.setEnemySword(sword2);
        player2.setEnemySword(sword);

        messageOutput = new JTextArea(1, 10);
        messageInput = new JTextArea(1, 10);


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
        //Obstacles
        for (int i = 0; i < allObsticals.size(); i++){
            g2d.setColor(new Color(48, 61, 61));
            g2d.fillRect(allObsticals.get(i)[0],allObsticals.get(i)[1],allObsticals.get(i)[2],allObsticals.get(i)[3]);
        }

        //Player
        player1.drawPlayer(g2d,playerImage,client,sword);

        //Swords
        sword.drawSword(g2d,swordImage,player2);

        //Slingshot
        slingshot.drawSlingshot(g2d,player1,player2,bowImage,playerImage,slingshot2 );

        //Minen
        mine.drawMine(g2d);

        //Inventory
        p1Inv.drawInventory(g2d,hpImage);

        //Chat
        messageOutput.setBackground(new Color(94, 94, 94));
        messageOutput.setFont(font);
        messageOutput.setBounds(25, 225, 275, 20);
        messageOutput.setForeground(new Color(255, 255, 255));

        messageInput.setBackground(new Color(94, 94, 94));
        messageInput.setFont(font);
        messageInput.setBounds(25, 25, 300, 175);
        messageInput.setForeground(new Color(255, 255, 255));
        messageInput.setEditable(false);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        mine.keyPressed(e);
        player1.keyPressed(e);
        if (e.getKeyCode() == 70){
            player1.setSlingshotPickedup(!player1.isSlingshotPickedup());
            player1.setSwordPickedup(!player1.isSwordPickedup());
        }
        if(e.getKeyCode() == 13){
            message = messageOutput.getText();
            messageOutput.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(slingshot.mousePos != null) {
            client.setMinePlased(mine.minePlased);
            client.setExplodet(mine.explodet);
            client.setWeapon(slingshot);
            client.sendMessage(player1, sword);
            player1.tick();
            sword.tick();
            mine.tick();
            repaint();
            mine.minePlased = false;
            mine.explodet = false;
        }
    }

    public void setRotation2(double bow2) {
        this.rotation2 = bow2;
    }

    public void setAllArrows(ArrayList<Double[]> allArrows) {
        this.allArrows = allArrows;
    }
    public void addObstacles(){
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
    }
}
