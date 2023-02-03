import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;

public class GameFrame extends JFrame implements KeyListener {
    Panel panel;
    File file = new File("Player1.txt");
    public GameFrame(Client client, boolean coop) {
        panel = new Panel(client, coop);
        this.setVisible(true);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Game - Vs.");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                writeStats();
                writeHighScore();
                var scoreboard = new Scoreboard();
                scoreboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }

    public void writeHighScore() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((reader.readLine()) != null) {
                System.out.println(extractInt(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static String extractInt(String str)
    {
        str = str.replaceAll("[^0-9]","");
        str = str.trim();
        str = str.replaceAll(" + ", "");
        return str;
    }
    public void writeStats() {


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if (panel.coop) {
                writer.write("Mode: Coop\n");
            } else {
                writer.write("Mode: Vs.\n");
            }

            writer.write("Player 1: " + panel.client.name + "\n");

            writer.write("Deaths: " + panel.ui.death + "\n");
            if (panel.coop) {
                writer.write("Boss Level: " + panel.bossLevel + "\n");
            } else {
                writer.write("Level: " + panel.ui.death2 / 2 + "\n");
                writer.write("Kills: " + panel.ui.death2 + "\n");
            }
            writer.write("Cheated: " + panel.cheated + "\n");
            if (panel.coop) {
                if ((panel.bossLevel * 10 - panel.ui.death) > 0) {
                    writer.write("Score: <font color = green>" + (panel.bossLevel * 10 - panel.ui.death + "</font>\n"));
                } else {
                    writer.write("Score: <font color = red>" + (panel.bossLevel * 10 - panel.ui.death + "</font>\n"));
                }
            } else {
                if (((panel.ui.death2 * 5) - panel.ui.death) > 0) {
                    writer.write("Score: <font color = green>" + ((panel.ui.death2 * 5) - panel.ui.death) + "</font>\n");
                } else {
                    writer.write("Score: <font color = red>" + ((panel.ui.death2 * 5) - panel.ui.death) + "</font>\n");
                }
            }
            writer.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
