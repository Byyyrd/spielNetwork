import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Panel extends JLayeredPane implements ActionListener, KeyListener {
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
    Image schildImage;
    Image speedImage;
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
    boolean inChat;
    boolean serverDown;

    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        bowImage = new ImageIcon("resources/bow.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        projImage = new ImageIcon("resources/Projectile.png").getImage();
        hpImage = new ImageIcon("resources/Healthbar.png").getImage();
        schildImage = new ImageIcon("resources/Schield.png").getImage();
        speedImage = new ImageIcon("resources/Speed.png").getImage();
        bgrHeight = bgrImage.getHeight(null);
        bgrWidth = bgrImage.getWidth(null);

        font = new Font("Arial", Font.PLAIN, 40);

        addKeyListener(this);

        this.client = client;
        client.setClientPanel(this);
        allObsticals = new ArrayList<int[]>();
        addObstacles();

        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword, this);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2, this);

        slingshot = new Slingshot(player1, this, bowImage);
        slingshot2 = new Slingshot(player2, this, bowImage);
        this.addMouseListener(slingshot);

        player1.setAllArrows(slingshot2.allArrows);
        player1.setAllArrowsself(slingshot.allArrows);

        mine2 = new Mine(player2, null, null, null);
        mine = new Mine(player1, player2, mine2, this);

        sword = new Sword(this, player1);
        sword2 = new Sword(this, player2);
        sword.setSword2(sword2);
        sword2.setSword2(sword);
        sword.setPlayer2(player2);
        sword2.setPlayer2(player1);
        this.addMouseListener(sword);

        p1Inv = new Inventory(10, player1, player2,this);


        player1.setEnemySword(sword2);
        player2.setEnemySword(sword);

        messageOutput = new JTextArea(1, 10);
        messageInput = new JTextArea(1, 10);


        //Chat
        messageOutput.setFont(font);
        messageOutput.setBackground(new Color(94, 94, 94));
        messageOutput.setBounds(1625, 225, 300, 20);
        messageOutput.setForeground(new Color(255, 255, 255));

        messageInput.setBackground(new Color(94, 94, 94, 200));
        messageInput.setFont(font);
        messageInput.setBounds(1600, 725, 300, 175);
        messageInput.setForeground(new Color(255, 255, 255));
        messageInput.setEditable(false);
        setLayer(messageInput, DRAG_LAYER);

        add(messageInput);


        messageInput.addKeyListener(this);
        messageOutput.addKeyListener(this);


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
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldXForm = g2d.getTransform();
        //Background
        for (int j = 0; j <= this.getHeight() / bgrHeight; j++) {
            for (int i = 0; i <= this.getWidth() / bgrWidth; i++) {
                g2d.drawImage(bgrImage, bgrWidth * i, bgrHeight * j, bgrWidth, bgrHeight, null);
            }
        }
        g2d.setTransform(oldXForm);
        //Obstacles
        for (int i = 0; i < allObsticals.size(); i++) {
            g2d.setColor(new Color(48, 61, 61));
            g2d.fillRect(allObsticals.get(i)[0], allObsticals.get(i)[1], allObsticals.get(i)[2], allObsticals.get(i)[3]);
        }
        g2d.setTransform(oldXForm);
        //Minen
        mine.drawMine(g2d);
        g2d.setTransform(oldXForm);

        //Player
        player1.drawPlayer(g2d, playerImage, client, sword);
        g2d.setTransform(oldXForm);

        //Swords
        sword.drawSword(g2d, swordImage, player2);
        g2d.setTransform(oldXForm);

        //Slingshot
        slingshot.drawSlingshot(g2d, player1, player2, bowImage, playerImage, slingshot2);
        g2d.setTransform(oldXForm);

        //Inventory
        p1Inv.drawInventory(g2d, hpImage);
        super.paint(g);
        if (serverDown) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 200));
            g2d.setColor(Color.red);
            g2d.setBackground(Color.BLACK);
            g2d.drawString("Server Shutdown", 100, 500);
        }
        if (inChat){
            g2d.setColor(new Color(1,1,1));
            g2d.fillRect(0,950,1920,150);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.PLAIN, 40));
            g2d.drawImage(schildImage, 20, 956,schildImage.getWidth(null)/3, schildImage.getHeight(null)/3,null );
            g2d.rotate(Math.PI, 23 + swordImage.getWidth(null)/6,960 +swordImage.getHeight(null)/10);
            g2d.drawImage(swordImage,23, 960, swordImage.getWidth(null)/3, swordImage.getHeight(null)/5,null );
            g2d.rotate(-Math.PI, 23 + swordImage.getWidth(null)/6,960 +swordImage.getHeight(null)/10);
            g2d.drawString(":  " + player1.melieDamageReduction, 80, 1010);

            g2d.drawImage(schildImage, 200, 956,schildImage.getWidth(null)/3, schildImage.getHeight(null)/3,null );
            g2d.drawImage(bowImage,208, 970, bowImage.getWidth(null)/24, bowImage.getHeight(null)/24,null );
            g2d.drawString(":  " + ((int) (player1.slingshotDamageReduction * 10f))/10f, 260, 1010);

            g2d.drawImage(schildImage, 380, 956,schildImage.getWidth(null)/3, schildImage.getHeight(null)/3,null );
            g2d.setColor(Color.red);
            g2d.fillOval(395,975,30,30);
            g2d.setColor(Color.white);
            g2d.drawString(":  " + player1.minenDamageReduction, 440, 1010);

            g2d.drawImage(swordImage,563, 960, swordImage.getWidth(null)/3, swordImage.getHeight(null)/5,null );
            g2d.drawString(":  " + ((int) (player1.swordDamage * 10f))/10f, 610, 1010);

            g2d.drawImage(bowImage,703, 973, bowImage.getWidth(null)/24, bowImage.getHeight(null)/24,null );
            g2d.drawString(":  " + ((int) (player1.slingshotDamage * 10f))/10f, 750, 1010);

            g2d.setColor(Color.red);
            g2d.fillOval(853,985,30,30);
            g2d.setColor(Color.white);
            g2d.drawString(":  " + (int)player1.minenDamage, 900, 1010);

            g2d.setColor(new Color(12, 255,0, 142));
            g2d.fillOval(1000,980,40,40);
            g2d.setColor(Color.green);
            g2d.setFont(new Font("Arial", Font.PLAIN, 60));
            g2d.drawString("+",1002,1020);
            g2d.setFont(new Font("Arial", Font.PLAIN, 40));
            g2d.setColor(Color.white);
            g2d.drawString(":  " + ((int) (player1.healtime * 10f))/10f + "s", 1050, 1010);

            g2d.drawImage(bowImage,1155, 973, bowImage.getWidth(null)/24, bowImage.getHeight(null)/24,null );
            g2d.drawString(":  " + ((int) (player1.arrowTime * 10f))/10f + "s", 1200, 1010);

            g2d.setColor(new Color(255, 0,0, 142));
            g2d.fillOval(1320,980,40,40);
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Arial", Font.PLAIN, 60));
            g2d.drawString("+",1322,1020);
            g2d.setFont(new Font("Arial", Font.PLAIN, 40));
            g2d.setColor(Color.white);
            g2d.drawString(":  " + ((int) (player1.minenTime * 10f))/10f + "s", 1370, 1010);

            g2d.drawImage(speedImage,1500, 975, speedImage.getWidth(null)/3, speedImage.getHeight(null)/3,null );
            g2d.drawString(":  " + player1.speed , 1550, 1010);

            g2d.setColor(Color.red);
            g2d.drawString("MAX",1630,1015);
            g2d.fillOval(1730,980,40,40);
            g2d.setColor(Color.white);
            g2d.drawString(":  " + player1.maxMinen, 1780, 1010);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        mine.keyPressed(e);
        player1.keyPressed(e);
        if (e.getKeyCode() == 70) {
            player1.setSlingshotPickedup(!player1.isSlingshotPickedup());
            player1.setSwordPickedup(!player1.isSwordPickedup());
        }
        if (e.getKeyCode() == 10) {
            e.consume();
            send = true;
            message = messageOutput.getText();
            messageOutput.setText("");
        }
        if(e.getKeyCode() == 71){
            p1Inv.death2++;
            p1Inv.death2++;
            p1Inv.death2++;
            p1Inv.death2++;
        }
        if (e.getKeyCode() == 130) {
            e.consume();
            if (inChat) {
                inChat = false;
                messageOutput.setText("");
                remove(messageOutput);
                messageInput.setFont(new Font("Arial", Font.PLAIN, 20));
                messageInput.setBounds(1600, 725, 300, 175);
                this.grabFocus();
            } else {
                inChat = true;
                add(messageOutput, 1, 1);
                setLayer(messageOutput, DRAG_LAYER);
                messageOutput.setBounds(0, 850, 1900, 50);
                messageInput.setBounds(0, 0, 1900, 800);
                messageInput.setFont(font);
                messageOutput.grabFocus();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!send) {
            message = "";
        }
        if (slingshot.mousePos != null) {
            client.setMinePlased(mine.minePlased);
            client.setExplodet(mine.explodet);
            client.setWeapon(slingshot);
            client.sendMessage(player1, sword);
            player1.tick();
            sword.tick();
            mine.tick();
            slingshot.tick();
            player1.heal();
            mine.minePlased = false;
            mine.explodet = false;
        }
        this.requestFocusInWindow();
        repaint();
    }

    public void setRotation2(double bow2) {
        this.rotation2 = bow2;
    }

    public void addObstacles() {
        var obstical = new int[4];
        addObstical(900, 300, 200, 30, obstical);
        var obstical1 = new int[4];
        addObstical(390, 730, 50, 190, obstical1);
        var obstical2 = new int[4];
        addObstical(550, 90, 30, 30, obstical2);
        var obstical3 = new int[4];
        addObstical(200, 90, 40, 600, obstical3);
        var obstical4 = new int[4];
        addObstical(1290, 600, 100, 90, obstical4);
        var obstical5 = new int[4];
        addObstical(670, 740, 370, 50, obstical5);
        var obstical6 = new int[4];
        addObstical(1300, 50, 70, 370, obstical6);
        var obstical7 = new int[4];
        addObstical(1500, 470, 370, 70, obstical7);
        var obstical8 = new int[4];
        addObstical(730, 190, 20, 400, obstical8);
        var obstical9 = new int[4];
        addObstical(360, 470, 250, 40, obstical9);
    }

    public void addObstical(int x, int y, int wight, int hight, int[] obstical) {
        obstical[0] = x;
        obstical[1] = y;
        obstical[2] = wight;
        obstical[3] = hight;
        allObsticals.add(obstical);
    }
}
