import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Color.red;


public class InventoryButton extends JButton implements ActionListener {

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
                if (item.getSpeed() > 0){
                    toolTip = toolTip + "<br>Speed: <font color=\"green\">" + item.getSpeed() + "</font>";
                }else{
                    toolTip = toolTip + "<br>Speed: <font color=\"red\">" + item.getSpeed() + "</font>";
                }

            }
            if (item.getArrowTime() != 0.00) {
                toolTip = toolTip + "<br>ArrowTime: <font color \"green\"> " + item.getArrowTime()+"</font>";
            }
            if (item.getHealTime() != 0.00) {
                toolTip = toolTip + "<br>HealTime: <font color \"green\"> " + item.getHealTime()+"</font>";
            }
            if (item.getArrowVelocity() != 0.00) {
                toolTip = toolTip + "<br>ArrowVelocity: <font color \"green\"> " + item.getArrowVelocity()+"</font>";
            }
            if (item.getMaxHp() != 0.00) {
                toolTip = toolTip + "<br>MaxHp: <font color \"green\"> " + item.getMaxHp()+"</font>";
            }
            if (item.getMaxMines() != 0.00) {
                toolTip = toolTip + "<br>MaxMines: <font color \"green\"> " + item.getMaxMines()+"</font>";
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                toolTip = toolTip + "<br>MeleeDamageReduction: <font color \"green\"> " + item.getMeleeDamageReduction()+"</font>";
            }
            if (item.getMineDamageReduction() != 0.00) {
                toolTip = toolTip + "<br>MineDmgRed: <font color \"green\"> " + item.getMineDamageReduction()+"</font>";
            }
            if (item.getSlingshotDamageReduction() != 0.00) {
                toolTip = toolTip + "<br>SlingshotDmgRed: <font color \"green\"> " + item.getSlingshotDamageReduction()+"</font>";
            }
            if (item.getMineTime() != 0.00) {
                toolTip = toolTip + "<br>MineTime: <font color \"green\"> " + item.getMineTime()+"</font>";
            }
            if (item.getSwordDamage() != 0.00) {
                toolTip = toolTip + "<br>SwordDamage: <font color \"green\"> " + item.getSwordDamage()+"</font>";
            }
            if (item.getMineDamage() != 0.00) {
                toolTip = toolTip + "<br>MineDamage: <font color \"green\"> " + item.getMineDamage()+"</font>";
            }
            if (item.getSlingshotDamage() != 0.00) {
                toolTip = toolTip + "<br>SlingshotDamage: <font color \"green\"> " + item.getSlingshotDamage()+"</font>";
            }
            this.setFont(new Font("Arial", Font.PLAIN, 10));
            this.setToolTipText("<html>" + toolTip + "</html>");
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
