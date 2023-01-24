import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    Panel panel;
    public GameFrame(Client client) {
        panel = new Panel(client);
        this.setVisible(true);
        GraphicsEnvironment graphics =GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        this.setContentPane(panel);
        this.addKeyListener(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game");
    }
}
