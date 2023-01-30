import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipButton extends JButton implements ActionListener {
    private String attribute;
    private InventoryButton button;
    public EquipButton(String attribute) {
        this.attribute = attribute;
        this.setBackground(new Color(56, 56, 56));
        this.addActionListener(this);

        this.setFocusable(false);
    }

    public String getAttribute() {
        return attribute;
    }

    public InventoryButton getButton() {
        return button;
    }

    public void setButton(InventoryButton button) {
        this.button = button;
        this.setIcon(button.getImage());
        this.setBackground(Color.green);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setEquipped(false);
        button.setBackground(new Color(24, 24, 24));
        this.setIcon(null);
        this.setBackground(new Color(56, 56, 56));
    }
}
