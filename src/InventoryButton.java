import com.sun.jdi.event.Event;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

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
                String color;
                if (item.getSpeed() > 0) {
                    //color = "\"green\">";
                    toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getSpeed() + "</font>";
                } else {
                    //color = "\"red\">";
                    toolTip = toolTip + "<br>Speed: <font color = \"red\">" + item.getSpeed() + "</font>";
                }
            }
            if (item.getArrowTime() != 0.00) {
                if (item.getArrowTime() < 0) {
                    toolTip = toolTip + "<br>ArrowTime: <font color = \"green\"> " + item.getArrowTime() + "</font>";
                } else {
                    toolTip = toolTip + "<br>ArrowTime: <font color = \"red\"> " + item.getArrowTime() + "</font>";
                }
            }
            if (item.getHealTime() != 0.00) {
                if (item.getHealTime() < 0) {
                    toolTip = toolTip + "<br>HealTime: <font color = \"green\"> " + item.getHealTime() + "</font>";
                } else {
                    toolTip = toolTip + "<br>HealTime: <font color = \"red\"> " + item.getHealTime() + "</font>";
                }
            }
            if (item.getArrowVelocity() != 0.00) {
                if (item.getArrowVelocity() > 0) {
                    toolTip = toolTip + "<br>ArrowVelocity: <font color = \"green\"> " + item.getArrowVelocity() + "</font>";
                } else {
                    toolTip = toolTip + "<br>ArrowVelocity: <font color = \"red\"> " + item.getArrowVelocity() + "</font>";
                }
            }
            if (item.getMaxHp() != 0.00) {
                if (item.getMaxHp() > 0) {
                    toolTip = toolTip + "<br>MaxHp: <font color = \"green\"> " + item.getMaxHp() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MaxHp: <font color = \"red\"> " + item.getMaxHp() + "</font>";
                }
            }
            if (item.getMaxMines() != 0.00) {
                if (item.getMaxMines() > 0) {
                    toolTip = toolTip + "<br>MaxMines: <font color = \"green\"> " + item.getMaxMines() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MaxMines: <font color = \"red\"> " + item.getMaxMines() + "</font>";
                }
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                if (item.getMeleeDamageReduction() > 0) {
                    toolTip = toolTip + "<br>MeleeDamageReduction: <font color = \"green\"> " + item.getMeleeDamageReduction() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MeleeDamageReduction: <font color = \"red\"> " + item.getMeleeDamageReduction() + "</font>";
                }
            }
            if (item.getMineDamageReduction() != 0.00) {
                if (item.getMineDamageReduction() > 0) {
                    toolTip = toolTip + "<br>MineDmgRed: <font color = \"green\"> " + item.getMineDamageReduction() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MineDmgRed: <font color = \"red\"> " + item.getMineDamageReduction() + "</font>";
                }
            }
            if (item.getSlingshotDamageReduction() != 0.00) {
                if (item.getSlingshotDamageReduction() > 0) {
                    toolTip = toolTip + "<br>SlingshotDmgRed: <font color = \"green\"> " + item.getSlingshotDamageReduction() + "</font>";
                } else {
                    toolTip = toolTip + "<br>SlingshotDmgRed: <font color = \"red\"> " + item.getSlingshotDamageReduction() + "</font>";
                }
            }
            if (item.getMineTime() != 0.00) {
                if (item.getMineTime() < 0) {
                    toolTip = toolTip + "<br>MineTime: <font color = \"green\"> " + item.getMineTime() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MineTime: <font color = \"red\"> " + item.getMineTime() + "</font>";
                }
            }
            if (item.getSwordDamage() != 0.00) {
                if (item.getSwordDamage() > 0) {
                    toolTip = toolTip + "<br>SwordDamage: <font color = \"green\"> " + item.getSwordDamage() + "</font>";
                } else {
                    toolTip = toolTip + "<br>SwordDamage: <font color = \"red\"> " + item.getSwordDamage() + "</font>";
                }
            }
            if (item.getMineDamage() != 0.00) {
                if (item.getMineDamage() > 0) {
                    toolTip = toolTip + "<br>MineDamage: <font color = \"green\"> " + item.getMineDamage() + "</font>";
                } else {
                    toolTip = toolTip + "<br>MineDamage: <font color = \"red\"> " + item.getMineDamage() + "</font>";
                }
            }
            if (item.getSlingshotDamage() != 0.00) {
                if (item.getSlingshotDamage() > 0) {
                    toolTip = toolTip + "<br>SlingshotDamage: <font color = \"green\"> " + item.getSlingshotDamage() + "</font>";
                } else {
                    toolTip = toolTip + "<br>SlingshotDamage: <font color = \"red\"> " + item.getSlingshotDamage() + "</font>";
                }
            }
            UIManager.put("ToolTip.background", Color.lightGray);
            UIManager.put("ToolTip.border", new LineBorder(Color.GRAY, 1));
            ToolTipManager.sharedInstance().setInitialDelay(0);
            ToolTipManager.sharedInstance().setDismissDelay(500000);
            this.setFont(new Font("Arial", Font.PLAIN, 10));
            this.setToolTipText(("<html>" + toolTip + "</html>"));
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
