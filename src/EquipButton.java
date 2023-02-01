import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipButton extends JButton implements ActionListener {
    private final String attribute;
    private InventoryButton button;
    Panel panel;
    public EquipButton(String attribute,Panel panel) {
        this.attribute = attribute;
        this.panel = panel;
        this.setBackground(new Color(56, 56, 56));
        this.addActionListener(this);

        this.setFocusable(false);
        button = new InventoryButton(panel);
        button.setItem( new Item(""));
    }

    public String getAttribute() {
        return attribute;
    }

    public InventoryButton getButton() {
        return button;
    }

    public void setButton(InventoryButton button) {
        this.button = button;
        button.updateButton();
        this.setIcon(button.getImage());
        this.setBackground(Color.green);
        panel.inventory.updateInv();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setEquipped(false);
        button.setBackground(new Color(24, 24, 24));
        button.updateButton();
        this.setIcon(null);
        this.setBackground(new Color(56, 56, 56));
        button = new InventoryButton(panel);
        panel.inventory.updateInv();

    }
}
