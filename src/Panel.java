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
    Image slingshotImage;
    Image bgrImage;
    Image hpImage;
    Image schildImage;
    Image speedImage;
    Image arrowVelocity;
    Image hourglass;
    int bgrWidth;
    int bgrHeight;
    ArrayList<int[]> allObstacles;
    Ui ui;
    JTextArea messageOutput;
    JTextArea messageInput;
    Font font;
    boolean send;
    String message;
    boolean inChat;
    boolean serverDown;
    boolean inInv;
    JPanel inventoryPanel;
    Inventory inventory;

    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        slingshotImage = new ImageIcon("resources/bow.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        hpImage = new ImageIcon("resources/HealthBar.png").getImage();
        schildImage = new ImageIcon("resources/Shield.png").getImage();
        speedImage = new ImageIcon("resources/Speed.png").getImage();
        arrowVelocity = new ImageIcon("resources/ArrowVel.png").getImage();
        hourglass = new ImageIcon("resources/Hourglass.png").getImage();
        bgrHeight = bgrImage.getHeight(null);
        bgrWidth = bgrImage.getWidth(null);

        font = new Font("Arial", Font.PLAIN, 40);

        addKeyListener(this);



        this.client = client;
        client.setClientPanel(this);
        allObstacles = new ArrayList<>();
        addObstacles();

        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword, this);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2, this);

        slingshot = new Slingshot(player1, this, slingshotImage);
        slingshot2 = new Slingshot(player2, this, slingshotImage);
        this.addMouseListener(slingshot);

        player1.setAllArrows(slingshot2.allArrows);
        player1.setAllArrowsSelf(slingshot.allArrows);

        mine2 = new Mine(player2, null, null, null);
        mine = new Mine(player1, player2, mine2, this);

        sword = new Sword(this, player1);
        sword2 = new Sword(this, player2);
        sword.setSword2(sword2);
        sword2.setSword2(sword);
        sword.setPlayer2(player2);
        sword2.setPlayer2(player1);
        this.addMouseListener(sword);



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

        //Inventory
        inventoryPanel = new JPanel();
        inventory = new Inventory();

        //Ui
        ui = new Ui(20, player1, player2, this);

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
        for (int[] allObstacle : allObstacles) {
            g2d.setColor(new Color(48, 61, 61));
            g2d.fillRect(allObstacle[0], allObstacle[1], allObstacle[2], allObstacle[3]);
        }
        g2d.setTransform(oldXForm);
        //Mines
        mine.drawMine(g2d);
        g2d.setTransform(oldXForm);

        //Player
        player1.drawPlayer(g2d, playerImage, client, sword);
        g2d.setTransform(oldXForm);

        //Swords
        sword.drawSword(g2d, swordImage, player2);
        g2d.setTransform(oldXForm);

        //Slingshot
        slingshot.drawSlingshot(g2d, player1, player2, slingshotImage, playerImage, slingshot2);
        g2d.setTransform(oldXForm);

        //Inventory
        ui.drawInventory(g2d, hpImage);
        super.paint(g);
        if (serverDown) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 200));
            g2d.setColor(Color.red);
            g2d.setBackground(Color.BLACK);
            g2d.drawString("Server Shutdown", 100, 500);
        }
        if (inChat) {
            ui.drawIcons(g2d, schildImage, swordImage, slingshotImage, speedImage);
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
            player1.setSlingshotPickedUp(!player1.isSlingshotPickedUp());
            player1.setSwordPickedUp(!player1.isSwordPickedUp());
        }
        if (e.getKeyCode() == 10) {
            e.consume();
            send = true;
            message = messageOutput.getText();
            messageOutput.setText("");
        }
        if (e.getKeyCode() == 71) {
            ui.levelUp();
        }
        //Chat and Stats
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
        //Open Inv
        if (e.getKeyCode() == 27) {
            e.consume();
            if (inInv) {
                inInv = false;
                messageOutput.setText("");
                remove(messageOutput);
                messageInput.setFont(new Font("Arial", Font.PLAIN, 20));
                messageInput.setBounds(1600, 725, 300, 175);
                this.grabFocus();
            } else {
                inInv = true;
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
            client.setMinePlaced(mine.minePlaced);
            client.setExploded(mine.exploded);
            client.setWeapon(slingshot);
            client.sendMessage(player1, sword);
            player1.tick();
            sword.tick();
            mine.tick();
            slingshot.tick();
            player1.heal();
            mine.minePlaced = false;
            mine.exploded = false;
        }
        repaint();
    }

    public void setRotation2(double bow2) {
        this.rotation2 = bow2;
    }

    public void addObstacles() {
        var obstacles = new int[4];
        addObstacles(900, 300, 200, 30, obstacles);
        var obstacles1 = new int[4];
        addObstacles(390, 730, 50, 190, obstacles1);
        var obstacles2 = new int[4];
        addObstacles(550, 90, 30, 30, obstacles2);
        var obstacles3 = new int[4];
        addObstacles(200, 90, 40, 600, obstacles3);
        var obstacles4 = new int[4];
        addObstacles(1290, 600, 100, 90, obstacles4);
        var obstacles5 = new int[4];
        addObstacles(670, 740, 370, 50, obstacles5);
        var obstacles6 = new int[4];
        addObstacles(1300, 50, 70, 370, obstacles6);
        var obstacles7 = new int[4];
        addObstacles(1500, 470, 370, 70, obstacles7);
        var obstacles8 = new int[4];
        addObstacles(730, 190, 20, 400, obstacles8);
        var obstacles9 = new int[4];
        addObstacles(360, 470, 250, 40, obstacles9);
    }

    public void addObstacles(int x, int y, int wight, int height, int[] obstacles) {
        obstacles[0] = x;
        obstacles[1] = y;
        obstacles[2] = wight;
        obstacles[3] = height;
        allObstacles.add(obstacles);
    }
}
