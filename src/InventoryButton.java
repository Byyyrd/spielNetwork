import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryButton extends JButton implements ActionListener {

    Panel panel;
    Item item;
    ImageIcon image;
    EquipButton[] equipButtons;
    boolean equipped;
    int row;
    Color color;

    public InventoryButton(Panel panel) {
        color = new Color(24, 24, 24);
        this.setFocusable(false);
        this.setBackground(color);
        this.panel = panel;
        this.addActionListener(this);
    }

    public void updateButton() {
        if (item != null) {
            image = new ImageIcon(item.getImage().getScaledInstance(getWidth()+1, getHeight()+1, java.awt.Image.SCALE_SMOOTH));
            this.setIcon(image);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        equipButtons[row].setButton(this);
        this.setBackground(Color.green);
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public void setEquipButtons(EquipButton[] equipButtons) {
        this.equipButtons = equipButtons;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
