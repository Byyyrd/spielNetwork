import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class InventoryButton extends JButton implements ActionListener {

    Panel panel;
    public InventoryButton(Panel panel) {
        this.setFocusable(false);
        this.setBackground(new Color(24, 24, 24));
        this.panel = panel;
        ImageIcon sword = new ImageIcon(panel.swordImage.getScaledInstance(panel.player1.width,panel.player1.height*2,java.awt.Image.SCALE_SMOOTH));
        this.setIcon(sword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
