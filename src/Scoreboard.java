import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Scoreboard extends JFrame {
    StringBuilder stats = new StringBuilder();
    //String stats = "<html>zdfcvfhj<br> dtzuifghjkl</html>";

    public Scoreboard() {
        JPanel panel = new JPanel();
        this.setVisible(true);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        this.setContentPane(panel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Score");
        this.setBounds(0,0,100,300);
        readStats();
        JLabel label = new JLabel("<html>"+stats+"</html>");
        panel.add(label);

    }

    public void readStats() {
        File file = new File("Player1.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                stats.append(line).append("<br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}