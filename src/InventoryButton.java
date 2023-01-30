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
    }

    public void updateButton() {
        if (item != null && item.getImage() != null) {
            toolTip = "<strong>Stats:</strong>";
            if (item.getSpeed() != 0.00) {
                toolTip = toolTip +"<br>Speed: "+ item.getSpeed();
            }
            if (item.getArrowTime() != 0.00) {
                toolTip = toolTip +"<br>ArrowTime: "+item.getArrowTime();
            }
            if (item.getHealTime() != 0.00) {
                toolTip = toolTip +"<br>HealTime: "+ item.getHealTime();
            }
            if (item.getArrowVelocity() != 0.00) {
                toolTip = toolTip +"<br>ArrowVelocity: "+ item.getArrowVelocity();
            }
            if (item.getMaxHp() != 0.00) {
                toolTip = toolTip +"<br>MaxHp: "+ item.getMaxHp();
            }
            if (item.getMaxMines() != 0.00) {
                toolTip = toolTip +"<br>MaxMines: "+ item.getMaxMines();
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                toolTip = toolTip +"<br>MeleeDamageReduction: "+ item.getMeleeDamageReduction();
            }
            if (item.getMineDamageReduction() != 0.00) {
                toolTip = toolTip +"<br>MineDmgRed: "+ item.getMineDamageReduction();
            }
            if (item.getSlingshotDamageReduction() != 0.00) {
                toolTip = toolTip +"<br>SlingshotDmgRed: "+ item.getSlingshotDamageReduction();
            }
            if (item.getMineTime() != 0.00) {
                toolTip = toolTip +"<br>MineTime: "+ item.getMineTime();
            }
            if (item.getSwordDamage() != 0.00) {
                toolTip = toolTip +"<br>SwordDamage: "+ item.getSwordDamage();
            }
            if (item.getMineDamage() != 0.00) {
                toolTip = toolTip +"<br>MineDamage: "+ item.getMineDamage();
            }
            if (item.getSlingshotDamage() != 0.00) {
                toolTip = toolTip +"<br>SlingshotDamage: "+ item.getSlingshotDamage();
            }
            this.setFont(new Font("Arial",Font.PLAIN,10));
            this.setToolTipText("<html>"+toolTip+"</html>");
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
