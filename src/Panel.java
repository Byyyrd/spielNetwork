import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;

public class Panel extends JLayeredPane implements ActionListener, KeyListener, Serializable {
    Player player1;
    Player player2;

    Sword sword;
    Sword sword2;

    Bow bow;
    Bow bow2;
    double rotation2;

    Fist fist1;
    Fist fist2;

    Mine mine;
    Mine mine2;

    Boss boss;

    Client client;
    Timer timer;

    Image playerImage;
    Image swordImage;
    Image bowImage;
    Image bgrImage;
    Image hpImage;
    Image schildImage;
    Image speedImage;
    Image arrowVelocity;
    Image hourglass;
    Image armorImage1;
    Image armorImage2;
    Image armorImage3;
    Image swordImage1;
    Image swordImage2;
    Image swordImage3;
    Image bowImage1;
    Image bowImage2;
    Image bowImage3;
    Image mineImage1;
    Image mineImage2;
    Image mineImage3;
    Image ringImage1;
    Image ringImage2;
    Image ringImage3;

    Image chainImage1;
    Image chainImage2;
    Image chainImage3;
    Image shoeImage1;
    Image shoeImage2;
    Image shoeImage3;
    Image errorImage;
    Image arrow;
    Image fist;
    Image mysteryArmor;
    Image mysterySword;
    Image mysteryBow;
    Image mysteryMine;
    Image mysteryRing;
    Image mysteryCharm;
    Image mysteryShoe;
    Image enemyImage;
    Image bossImage;
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
    JPanel equipPanel;
    Inventory inventory;

    AffineTransform oldXForm;
    int weapon = 1;
    int bowX;
    int bowY;
    int swordX;
    int swordY;

    int delay = 10;

    boolean coop;
    boolean sparned;
    ArrayList<Enemy> enemies = new ArrayList<>();

    public Panel(Client client, boolean coop) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        bowImage = new ImageIcon("resources/bow2.png").getImage();
        bgrImage = new ImageIcon("resources/Background.jpg").getImage();
        hpImage = new ImageIcon("resources/HealthBar.png").getImage();
        schildImage = new ImageIcon("resources/Shield.png").getImage();
        speedImage = new ImageIcon("resources/Speed.png").getImage();
        arrowVelocity = new ImageIcon("resources/ArrowVel.png").getImage();
        hourglass = new ImageIcon("resources/Hourglass.png").getImage();
        swordImage1 = new ImageIcon("resources/Heavy_Sword.png").getImage();
        swordImage2 = new ImageIcon("resources/Normal_Sword.png").getImage();
        swordImage3 = new ImageIcon("resources/Light_Sword.png").getImage();
        armorImage1 = new ImageIcon("resources/Heavy_Armor.png").getImage();
        armorImage2 = new ImageIcon("resources/Armor.png").getImage();
        armorImage3 = new ImageIcon("resources/Light_Armor.png").getImage();
        shoeImage1 = new ImageIcon("resources/Heavy_Boots.png").getImage();
        shoeImage2 = new ImageIcon("resources/Boots.png").getImage();
        shoeImage3 = new ImageIcon("resources/Light_Boots.png").getImage();
        bowImage1 = new ImageIcon("resources/Heavy_Bow.png").getImage();
        bowImage2 = new ImageIcon("resources/Normal_Bow.png").getImage();
        bowImage3 = new ImageIcon("resources/Light_Bow.png").getImage();
        ringImage1 = new ImageIcon("resources/Green_Ring.png").getImage();
        ringImage2 = new ImageIcon("resources/Red_Ring.png").getImage();
        ringImage3 = new ImageIcon("resources/Blue_Ring.png").getImage();
        chainImage1 = new ImageIcon("resources/Green_Charm.png").getImage();
        chainImage2 = new ImageIcon("resources/Red_Charm.png").getImage();
        chainImage3 = new ImageIcon("resources/Blue_Charm.png").getImage();
        mineImage1 = new ImageIcon("resources/Heavy_Mine.png").getImage();
        mineImage2 = new ImageIcon("resources/Mine.png").getImage();
        mineImage3 = new ImageIcon("resources/Light_Mine.png").getImage();
        errorImage = new ImageIcon("resources/Error.png").getImage();
        arrow = new ImageIcon("resources/Arrow.png").getImage();
        fist = new ImageIcon("resources/Fist.png").getImage();
        mysteryArmor = new ImageIcon("resources/Mystery_Armor.png").getImage();
        mysterySword = new ImageIcon("resources/Mystery_Sword.png").getImage();
        mysteryBow = new ImageIcon("resources/Mystery_Bow.png").getImage();
        mysteryMine = new ImageIcon("resources/Mystery_Mine.png").getImage();
        mysteryRing = new ImageIcon("resources/Mystery_Ring.png").getImage();
        mysteryCharm = new ImageIcon("resources/Mystery_Charm.png").getImage();
        mysteryShoe = new ImageIcon("resources/Mystery_Shoe.png").getImage();
        bossImage = new ImageIcon("resources/Boss.png").getImage();
        enemyImage = new ImageIcon("resources/Enemy.png").getImage();
        bgrHeight = bgrImage.getHeight(null) / 2;
        bgrWidth = bgrImage.getWidth(null) / 2;

        font = new Font("Arial", Font.PLAIN, 40);

        addKeyListener(this);

        this.coop = coop;

        this.client = client;
        client.setClientPanel(this);
        if (!coop) {
            allObstacles = new ArrayList<>();
            addObstacles();
        }
        player2 = new Player(100, 100, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword, this);
        player1 = new Player(10, 10, playerImage.getWidth(null) * 3, playerImage.getHeight(null) * 3, player2, sword2, this);

        bow = new Bow(player1, this, bowImage);
        bow2 = new Bow(player2, this, bowImage);
        this.addMouseListener(bow);

        player1.setAllArrows(bow2.allArrows);
        player1.setAllArrowsSelf(bow.allArrows);

        mine2 = new Mine(player2, null, null, null);
        mine = new Mine(player1, player2, mine2, this);

        sword = new Sword(this, player1);
        sword2 = new Sword(this, player2);
        sword.setSword2(sword2);
        sword2.setSword2(sword);
        sword.setPlayer2(player2);
        sword2.setPlayer2(player1);
        this.addMouseListener(sword);

        fist1 = new Fist(this, player1);
        fist2 = new Fist(this, player2);

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
        setLayer(messageInput, POPUP_LAYER);

        add(messageInput);


        messageInput.addKeyListener(this);
        messageOutput.addKeyListener(this);


        //Ui
        ui = new Ui(10, player1, player2, this);


        //Inventory
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(0, 150, 1900, 800);
        inventoryPanel.setLayout(new GridLayout(7, 7));

        equipPanel = new JPanel();
        equipPanel.setBounds(0, 0, 1900, 150);
        equipPanel.setLayout(new GridLayout(1, 7));

        inventory = new Inventory(this);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                InventoryButton button = new InventoryButton(this);
                button.setRow(i, j);
                inventoryPanel.add(button);
                inventory.inventoryButtons[i][j] = button;
            }
        }
        inventory.updateInv();

        EquipButton button = new EquipButton(this);
        equipPanel.add(button);
        inventory.equipButtons[0] = button;
        EquipButton button1 = new EquipButton(this);
        equipPanel.add(button1);
        inventory.equipButtons[1] = button1;
        EquipButton button2 = new EquipButton(this);
        equipPanel.add(button2);
        inventory.equipButtons[2] = button2;
        EquipButton button3 = new EquipButton(this);
        equipPanel.add(button3);
        inventory.equipButtons[3] = button3;
        EquipButton button4 = new EquipButton(this);
        equipPanel.add(button4);
        inventory.equipButtons[4] = button4;
        EquipButton button5 = new EquipButton(this);
        equipPanel.add(button5);
        inventory.equipButtons[5] = button5;
        EquipButton button6 = new EquipButton(this);
        equipPanel.add(button6);
        inventory.equipButtons[6] = button6;
        if (coop) {
            boss = new Boss(500, 200, 100, 5);
        }
        enemies.add(new Enemy(500, 500, this, player1, enemies.size()));
        timer = new Timer(delay, this);
        timer.start();

        bowX = (int) random(1150, 1450);
        bowY = (int) random(725, 900);
        swordX = (int) random(250, 675);
        swordY = (int) random(150, 425);
    }

    public void setPlayer2(int x, int y, String name) {
        player2.x = x;
        player2.y = y;
        player2.name = name;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        oldXForm = g2d.getTransform();

        //Background
        if (!coop) {
            for (int j = 0; j <= this.getHeight() / bgrHeight; j++) {
                for (int i = 0; i <= this.getWidth() / bgrWidth; i++) {
                    g2d.drawImage(bgrImage, bgrWidth * i, bgrHeight * j, bgrWidth, bgrHeight, null);
                }
            }
        }
        g2d.setTransform(oldXForm);
        //Obstacles
        if (!coop) {
            for (int[] allObstacle : allObstacles) {
                g2d.setColor(new Color(48, 61, 61));
                g2d.fillRect(allObstacle[0], allObstacle[1], allObstacle[2], allObstacle[3]);
            }
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

        //Fist
        fist1.drawFist(g2d);
        this.addMouseListener(fist1);

        //Bow
        bow.drawBow(g2d, player1, player2, bowImage, playerImage, bow2);
        g2d.setTransform(oldXForm);

        if (!player1.bowPickedUp && !coop) {
            g2d.drawImage(bowImage, bowX, bowY, bowImage.getWidth(null) / 24, bowImage.getHeight(null) / 24, null);
        }
        if (!player1.swordPickedUp && !coop) {
            g2d.drawImage(swordImage, swordX, swordY, player1.width, player1.height * 2, null);
        }

        //Boss
        if (coop) {
            boss.drawBoss(g2d, bossImage);
        }
        for (Enemy enemy : enemies) {
            enemy.drawEnemy(g2d);
        }
        //Ui
        ui.drawUi(g2d, hpImage);

        if (serverDown) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 200));
            g2d.setColor(Color.red);
            g2d.setBackground(Color.BLACK);
            g2d.drawString("Server Shutdown", 100, 500);
        }
        if (inChat || inInv) {
            ui.drawIcons(g2d, schildImage, swordImage, bowImage, speedImage);
        }


        super.paint(g);
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
            weapon++;
            if (weapon > 2) {
                weapon = 1;
            }
            if (weapon == 1 && player1.swordPickedUp) {
                player1.fistEquipped = false;
                player1.setBowEquipped(false);
                player1.setSwordEquipped(true);
            } else if (weapon == 1) {
                player1.fistEquipped = true;
                player1.bowEquipped = false;
                player1.swordEquipped = false;
            }
            if (weapon == 2 && player1.bowPickedUp) {
                player1.fistEquipped = false;
                player1.setBowEquipped(true);
                player1.setSwordEquipped(false);
            } else if (weapon == 2) {
                player1.fistEquipped = true;
                player1.bowEquipped = false;
                player1.swordEquipped = false;
            }

        }
        if (e.getKeyCode() == 81) {
            player1.fistEquipped = true;
            player1.bowEquipped = false;
            player1.swordEquipped = false;
        }
        if (e.getKeyCode() == 10) {
            e.consume();
            send = true;
            message = messageOutput.getText();
            messageOutput.setText("");
        }
        if (e.getKeyCode() == 71) {
            ui.expSpend -= 10;
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
                setLayer(messageOutput, POPUP_LAYER);
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
                remove(inventoryPanel);
                remove(equipPanel);
                repaint();
                this.grabFocus();
            } else {
                inInv = true;
                add(equipPanel, 1, 1);
                add(inventoryPanel, 1, 1);
                setLayer(inventoryPanel, DRAG_LAYER);
                setLayer(equipPanel, DRAG_LAYER);
                inventory.updateInv();
            }
        }
        if (e.getKeyCode() == 80) {
            var item = new Item("Chain");
            item.setImage(chainImage2);
            item.setSpeed(2);
            item.setHealTime(-2);
            item.setSwordDamage(10);
            item.setBowDamage(7.5);
            item.setMineDamage(35);
            item.setArrowTime(-2);
            item.setMineTime(-2);
            item.setMeleeDamageReduction(25);
            item.setBowDamageReduction(25);
            item.setMineDamageReduction(25);
            item.setMaxMines(50);
            item.setMaxHp(40);

            item.setMaxSpeed(2);
            item.setMinHealTime(-2);
            item.setMaxSwordDamage(10);
            item.setMaxBowDamage(7.5);
            item.setMaxMineDamage(35);
            item.setMinArrowTime(-2);
            item.setMinMineTime(-2);
            item.setMaxMeleeDamageReduction(25);
            item.setMaxBowDamageReduction(25);
            item.setMaxMineDamageReduction(25);
            item.setMaxMaxMines(50);
            item.setMaxMaxHp(30);
            inventory.allItems[5].add(item);

            inventory.updateInv();
            player1.setHealTime(1);
            player1.setSwordDamage(10);
            player1.setBowDamage(7.5);
            player1.setMineDamage(35);
            player1.setArrowTime(0);
            player1.setMeleeDamageReduction(75);
            player1.setBowDamageReduction(75);
            player1.setMineDamageReduction(75);
            player1.setSpeed(8);
            player1.setMaxMines(125);
            player1.setMineTime(1);
            ui.maxHp = 30;
            player1.setArrowVelocity(4);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.hp > ui.maxHp + inventory.getMaxHp()) {
            ui.hp = ui.maxHp + inventory.getMaxHp();
        }
        if (mine.minesLeft > player1.maxMines + inventory.getMaxMines()) {
            mine.minesLeft = player1.maxMines + inventory.getMaxMines();
        }
        if (!send) {
            message = "";
        }
        if (bow.mousePos != null) {
            int[] array = new int[5];
            array[0] = 1;
            array[1] = 2;
            client.sendArray(player1);
            if (coop) {
                boss.tick(delay / 100);
            }
            client.setMinePlaced(mine.minePlaced);
            client.setExploded(mine.exploded);
            client.setWeapon(bow);
            client.sendMessage(player1, sword);
            player1.tick();
            sword.tick();
            mine.tick();
            bow.tick();
            fist1.tick();
            player1.heal();
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).tick();
            }
            mine.minePlaced = false;
            mine.exploded = false;
            if (!player1.bowPickedUp && inRectangle(bowX + bowImage.getWidth(null) / 48, bowY + bowImage.getHeight(null) / 48, player1.x, player1.y, player1.width, player1.height)) {
                player1.bowPickedUp = true;
            }
            if (!player1.swordPickedUp && inRectangle(swordX + player2.width / 2, swordY + player2.height, player1.x, player1.y, player1.width, player1.height)) {
                player1.swordPickedUp = true;
            }
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

    public double random(double min, double max) {
        if (min == 0 && max == 0) {
            return 0;
        }
        return ((int) (((Math.random() * ((max - min)) + min)) * 10f) + 1) / 10.0;
    }

    public boolean inRectangle(int px, int py, int rx, int ry, int rb, int rh) {
        return rx < px && px < rx + rb && ry < py && py < ry + rh;

    }
}
