import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameFrame extends JFrame implements KeyListener {
    Panel panel;
    public GameFrame(Client client,boolean coop) {
        panel = new Panel(client,coop);
        this.setVisible(true);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game - Vs.");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                writeStats();
            }
        });
    }
    public void writeStats(){
        File file = new File("Player1.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if(panel.coop){
                writer.write("Mode: Coop\n");
            }else {
                writer.write("Mode: Vs.\n");
            }

            writer.write("Player 1: "+panel.client.name+"\n");
            if(panel.coop){
                writer.write("Score: "+ (panel.bossLevel*10-panel.ui.death+"\n"));
            }else {
                writer.write("Score: "+ ((panel.ui.death2*5)-panel.ui.death)+ "\n");
            }
            writer.write("Deaths: "+ panel.ui.death+"\n");
            if(panel.coop){
                writer.write("Boss Level: "+panel.bossLevel+"\n");
            }else {
                writer.write("Level: "+panel.ui.death2/2 +"\n");
                writer.write("Kills: "+panel.ui.death2 + 2+"\n");
            }
            writer.close();
        }
        catch(IOException ioe) {
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
