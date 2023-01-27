import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryButton extends JButton implements ActionListener {

    Panel panel;
    Item item;
    ImageIcon image;

    public InventoryButton(Panel panel) {
        this.setFocusable(false);
        this.setBackground(new Color(24, 24, 24));
        this.panel = panel;
    }

    public void updateButton() {
        if (item != null) {
            image = new ImageIcon(item.getImage().getScaledInstance(getWidth()+1, getHeight()+1, java.awt.Image.SCALE_SMOOTH));
            this.setIcon(image);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setItem(Item item) {
        this.item = item;
    }
}
