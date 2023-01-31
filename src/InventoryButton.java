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
                    if (item.getSpeed() == item.getMaxSpeed()){
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getSpeed() + "</font>";
                    }
                    else if(item.getSpeed() > item.getMaxSpeed()/2){
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getSpeed() + "</font>";
                    }
                    else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getSpeed() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getSpeed() == item.getMinSpeed()){
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getSpeed() + "</font>";
                    }
                    else if(item.getSpeed() < item.getMinSpeed()/2){
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getSpeed() + "</font>";
                    }
                    else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getSpeed() + "</font>";
                    }
                }
            }
            if (item.getHealTime() != 0.00) {
                String color;
                if (item.getHealTime() > 0) {
                    //color = "\"green\">";
                    if (item.getHealTime() == item.getMaxHealTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getHealTime() + "</font>";
                    } else if (item.getHealTime() > item.getMaxHealTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getHealTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getHealTime() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getHealTime() == item.getMinHealTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getHealTime() + "</font>";
                    } else if (item.getHealTime() < item.getMinHealTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getHealTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getHealTime() + "</font>";
                    }
                }
            }
            if (item.getSwordDamage() != 0.00) {
                String color;
                if (item.getSwordDamage() > 0) {
                    //color = "\"green\">";
                    if (item.getSwordDamage() == item.getMaxSwordDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getSwordDamage() + "</font>";
                    } else if (item.getSwordDamage() > item.getMaxSwordDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getSwordDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getSwordDamage() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getSwordDamage() == item.getMinSwordDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getSwordDamage() + "</font>";
                    } else if (item.getSwordDamage() < item.getMinSwordDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getSwordDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getSwordDamage() + "</font>";
                    }
                }
            }
            if (item.getSlingshotDamage() != 0.00) {
                String color;
                if (item.getSlingshotDamage() > 0) {
                    //color = "\"green\">";
                    if (item.getSlingshotDamage() == item.getMaxSlingshotDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getSlingshotDamage() + "</font>";
                    } else if (item.getSlingshotDamage() > item.getMaxSlingshotDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getSlingshotDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getSlingshotDamage() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getSlingshotDamage() == item.getMinSlingshotDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getSlingshotDamage() + "</font>";
                    } else if (item.getSlingshotDamage() < item.getMinSlingshotDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getSlingshotDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getSlingshotDamage() + "</font>";
                    }
                }
            }
            if (item.getMineDamage() != 0.00) {
                String color;
                if (item.getMineDamage() > 0) {
                    //color = "\"green\">";
                    if (item.getMineDamage() == item.getMaxMineDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMineDamage() + "</font>";
                    } else if (item.getMineDamage() > item.getMaxMineDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMineDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMineDamage() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMineDamage() == item.getMinMineDamage()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMineDamage() + "</font>";
                    } else if (item.getMineDamage() < item.getMinMineDamage() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMineDamage() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMineDamage() + "</font>";
                    }
                }
            }
            if (item.getArrowTime() != 0.00) {
                String color;
                if (item.getArrowTime() > 0) {
                    //color = "\"green\">";
                    if (item.getArrowTime() == item.getMaxArrowTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getArrowTime() + "</font>";
                    } else if (item.getArrowTime() > item.getMaxArrowTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getArrowTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getArrowTime() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getArrowTime() == item.getMinArrowTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getArrowTime() + "</font>";
                    } else if (item.getArrowTime() < item.getMinArrowTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getArrowTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getArrowTime() + "</font>";
                    }
                }
            }
            if (item.getMineTime() != 0.00) {
                String color;
                if (item.getMineTime() > 0) {
                    //color = "\"green\">";
                    if (item.getMineTime() == item.getMaxMineTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMineTime() + "</font>";
                    } else if (item.getMineTime() > item.getMaxMineTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMineTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMineTime() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMineTime() == item.getMinMineTime()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMineTime() + "</font>";
                    } else if (item.getMineTime() < item.getMinMineTime() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMineTime() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMineTime() + "</font>";
                    }
                }
            }
            if (item.getSlingshotDamageReduction() != 0.00) {
                String color;
                if (item.getSlingshotDamageReduction() > 0) {
                    //color = "\"green\">";
                    if (item.getSlingshotDamageReduction() == item.getMaxSlingshotDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getSlingshotDamageReduction() + "</font>";
                    } else if (item.getSlingshotDamageReduction() > item.getMaxSlingshotDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getSlingshotDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getSlingshotDamageReduction() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getSlingshotDamageReduction() == item.getMinSlingshotDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getSlingshotDamageReduction() + "</font>";
                    } else if (item.getSlingshotDamageReduction() < item.getMinSlingshotDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getSlingshotDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getSlingshotDamageReduction() + "</font>";
                    }
                }
            }
            if (item.getMeleeDamageReduction() != 0.00) {
                String color;
                if (item.getMeleeDamageReduction() > 0) {
                    //color = "\"green\">";
                    if (item.getMeleeDamageReduction() == item.getMaxMeleeDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMeleeDamageReduction() + "</font>";
                    } else if (item.getMaxArrowVelocity() > item.getMaxMeleeDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMeleeDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMeleeDamageReduction() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMeleeDamageReduction() == item.getMinMeleeDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMeleeDamageReduction() + "</font>";
                    } else if (item.getMeleeDamageReduction() < item.getMinMeleeDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMeleeDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMeleeDamageReduction() + "</font>";
                    }
                }
            }
            if (item.getMineDamageReduction() != 0.00) {
                String color;
                if (item.getMineDamageReduction() > 0) {
                    //color = "\"green\">";
                    if (item.getMineDamageReduction() == item.getMaxMineDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMineDamageReduction() + "</font>";
                    } else if (item.getMaxArrowVelocity() > item.getMaxMineDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMineDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMineDamageReduction() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMineDamageReduction() == item.getMinMineDamageReduction()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMineDamageReduction() + "</font>";
                    } else if (item.getMineDamageReduction() < item.getMinMineDamageReduction() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMineDamageReduction() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMineDamageReduction() + "</font>";
                    }
                }
            }
            if (item.getArrowVelocity() != 0.00) {
                String color;
                if (item.getArrowVelocity() > 0) {
                    //color = "\"green\">";
                    if (item.getArrowVelocity() == item.getMaxArrowVelocity()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getArrowVelocity() + "</font>";
                    } else if (item.getMaxArrowVelocity() > item.getMaxArrowVelocity() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getArrowVelocity() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getArrowVelocity() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getArrowVelocity() == item.getMinArrowVelocity()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getArrowVelocity() + "</font>";
                    } else if (item.getArrowVelocity() < item.getMinArrowVelocity() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getArrowVelocity() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getArrowVelocity() + "</font>";
                    }
                }
            }
            if (item.getMaxMines() != 0.00) {
                String color;
                if (item.getMaxMines() > 0) {
                    //color = "\"green\">";
                    if (item.getMaxMines() == item.getMaxMaxMines()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMaxMines() + "</font>";
                    } else if (item.getMaxMines() > item.getMaxMaxMines() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMaxMines() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMaxHp() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMaxMines() == item.getMinMaxMines()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMaxMines() + "</font>";
                    } else if (item.getMaxMines() < item.getMinMaxMines() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMaxMines() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMaxMines() + "</font>";
                    }
                }
            }
            if (item.getMaxHp() != 0.00) {
                String color;
                if (item.getMaxHp() > 0) {
                    //color = "\"green\">";
                    if (item.getMaxHp() == item.getMaxMaxHp()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"deepyellow\">" + item.getMaxHp() + "</font>";
                    } else if (item.getMaxHp() > item.getMaxMaxHp() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"green\">" + item.getMaxHp() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"lime\">" + item.getMaxHp() + "</font>";
                    }
                } else {
                    //color = "\"red\">";
                    if (item.getMaxHp() == item.getMinMaxHp()) {
                        toolTip = toolTip + "<br>Speed: <font color = \"#551606\">" + item.getMaxHp() + "</font>";
                    } else if (item.getMaxHp() < item.getMinMaxHp() / 2) {
                        toolTip = toolTip + "<br>Speed: <font color = \"orange\">" + item.getMaxHp() + "</font>";
                    } else {
                        toolTip = toolTip + "<br>Speed: <font color = \"yellow\">" + item.getMaxHp() + "</font>";
                    }
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
