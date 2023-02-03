import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JFrame {
    String stats = "<html>zdfcvfhj<br> dtzuifghjkl</html>";

    public Scoreboard() {
        JPanel panel = new JPanel();
        this.setVisible(true);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Score");
        JLabel label = new JLabel(stats);
        panel.add(label);
    }
    public void readStats(){

    }
}
