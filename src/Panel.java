import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Panel extends JPanel implements ActionListener, KeyListener {
    Player player1;
    Player player2;
    Sword sword;
    Sword sword2;
    Timer timer;
    private int delay = 10;
    Client client;
    Image playerImage;
    Image swordImage;

    public Panel(Client client) {
        playerImage = new ImageIcon("resources/Player.png").getImage();
        swordImage = new ImageIcon("resources/Sword.png").getImage();
        this.client = client;
        client.setClientPanel(this);

        player2 = new Player(100, 100,playerImage.getWidth(null)*3,playerImage.getHeight(null)*3,player2,sword);
        player1 = new Player(10, 10,playerImage.getWidth(null)*3,playerImage.getHeight(null)*3,player2,sword2);


        sword = new Sword(this,player1);
        sword2 = new Sword(this,player2);
        this.addMouseListener(sword);

        player1.setEnemySword(sword2);
        player2.setEnemySword(sword);

        this.setBounds(0, 0, 100, 100);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void setPlayer2(int x, int y,String name) {
        player2.x = x;
        player2.y = y;
        player2.name = name;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(new Color(255, 255, 255));
        g2d.drawString(client.name,player1.x,player1.y);
        g2d.drawString(player2.name, player2.x, player2.y);
        g2d.drawImage(playerImage ,player1.x, player1.y,player1.width,player1.height, null);
        g2d.drawImage(playerImage ,player2.x, player2.y,player2.width,player2.height, null);
        g2d.rotate(sword2.rotation, player2.x + player2.width/2, player2.y + player2.height/2);
        g2d.drawImage(swordImage,sword2.x, (int) (sword2.y - (double) player2.width), player2.width, player2.height*2, null);
        g2d.rotate(-sword2.rotation, player2.x + player2.width/2, player2.y + player2.height/2);
        g2d.rotate(sword.rotation, player1.x + player1.width/2, player1.y + player1.height/2);
        g2d.drawImage(swordImage,sword.x, (int) (sword.y - (double) player1.width), player1.width, player1.height*2, null);
        g2d.rotate(-sword.rotation, player1.x + player1.width/2, player1.y + player1.height/2);
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
        client.sendMessage(player1,sword);
        player1.tick();
        sword.tick();
        repaint();
    }
}
