import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InventoryButton extends JButton implements ActionListener{

    Panel panel;
    Item item = new Item("");
    ImageIcon image;
    EquipButton[] equipButtons;
    boolean equipped;
    int row;
    Color color;
    String toolTip;

    public InventoryButton(Panel panel) {
        color = new Color(24, 24, 24);
        this.setFocusable(false);
        this.setBackground(color);
        this.panel = panel;
        this.addActionListener(this);
        item.setImage(null);
        this.setFont(new Font("Arial", Font.PLAIN, 10));
        this.setToolTipText("Hallo");
    }

    public void updateButton() {
        if (item != null && item.getImage() != null) {
            if (item.getSpeed() != 0.00) {
                toolTip = toolTip + "\n"+ item.getSpeed();
            }
            if (item.getArrowTime() != 0.00) {
                toolTip = toolTip + "\n"+item.getArrowTime();
            }
            if (item.getHealTime() != 0.00) {
                toolTip = toolTip + "\n"+ item.getHealTime();
            }
            if (item.getArrowVelocity() != 0.00) {
                toolTip = toolTip + "\n"+ item.getArrowVelocity();
            }
            if (item.getMaxHp() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMaxHp();
            }
            if (item.getMaxMines() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMaxMines();
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMeleeDamageReduction();
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMeleeDamageReduction();
            }
            if (item.getMineDamageReduction() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMineDamageReduction();
            }
            if (item.getSlingshotDamageReduction() != 0.00) {
                toolTip = toolTip + "\n"+ item.getSlingshotDamageReduction();
            }
            if (item.getMineTime() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMineTime();
            }
            if (item.getSwordDamage() != 0.00) {
                toolTip = toolTip + "\n"+ item.getSwordDamage();
            }
            if (item.getMineDamage() != 0.00) {
                toolTip = toolTip + "\n"+ item.getMineDamage();
            }
            if (item.getSlingshotDamage() != 0.00) {
                toolTip = toolTip + "\n"+ item.getSlingshotDamage();
            }
            this.setFont(new Font("Arial",Font.PLAIN,10));
            this.setToolTipText(toolTip);
            image = new ImageIcon(item.getImage().getScaledInstance(getWidth() + 1, getHeight() + 1, Image.SCALE_DEFAULT));
            this.setIcon(image);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (item != null && item.getImage() != null) {
            equipButtons[row].getButton().setBackground(color);
            equipButtons[row].setButton(this);
            this.setBackground(Color.green);
            panel.inventory.updateInv();
        }
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
