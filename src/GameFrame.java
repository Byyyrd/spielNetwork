import javax.swing.*;

public class GameFrame extends JFrame {
    Panel panel;
    public GameFrame(Client client) {
        panel = new Panel(client);
        this.setSize(1925,1080);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.addKeyListener(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game");
    }
}
