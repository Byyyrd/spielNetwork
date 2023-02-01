import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class InventoryButton extends JButton implements ActionListener {

    Panel panel;
    Item item = new Item("");
    ImageIcon image;
    EquipButton[] equipButtons;
    boolean equipped;
    int row;
    Color color;
    StringBuilder toolTip = new StringBuilder();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> methodNames = new ArrayList<>();

    public InventoryButton(Panel panel) {
        color = new Color(24, 24, 24);
        this.setFocusable(false);
        this.setBackground(color);
        this.panel = panel;
        this.addActionListener(this);
        item.setImage(null);
        this.setFont(new Font("Arial", Font.PLAIN, 10));
        methodNames.add("Speed");
        methodNames.add("SwordDamage");
        methodNames.add("BowDamage");
        methodNames.add("MineDamage");
        methodNames.add("MeleeDamageReduction");
        methodNames.add("BowDamageReduction");
        methodNames.add("MineDamageReduction");
        methodNames.add("ArrowVelocity");
        methodNames.add("MaxMines");
        methodNames.add("MaxHp");

        names.add("Speed");
        names.add("Sword Damage");
        names.add("Bow Damage");
        names.add("Mine Damage");
        names.add("Melee Dmg-Red");
        names.add("Bow Dmg-Red");
        names.add("Mine Dmg-Red");
        names.add("Arrow Velocity");
        names.add("max Mines");
        names.add("max Hp");

        UIManager.put("ToolTip.background", Color.lightGray);
        UIManager.put("ToolTip.border", new LineBorder(Color.GRAY, 1));
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(500000);
    }

    public void updateButton() {

        if (item != null && item.getImage() != null) {
            //Generate Tooltips
            toolTip.delete(0,toolTip.length());
            toolTip.append("<strong>Stats:</strong>");
            for (int i = 0; i < names.size(); i++) {
                double gotValue = 0.00;
                double gotMaxValue = 0.00;
                double gotMinValue = 0.00;
                try {
                    Method getterMethod = item.getClass().getMethod("get" + methodNames.get(i));
                    Object value = getterMethod.invoke(item);
                    gotValue = (double) value;
                    Method maxMethod = item.getClass().getMethod("getMax" + methodNames.get(i));
                    Object maxValue = maxMethod.invoke(item);
                    gotMaxValue = (double) maxValue;
                    Method minMethod = item.getClass().getMethod("getMin" + methodNames.get(i));
                    Object minValue = minMethod.invoke(item);
                    gotMinValue = (double) minValue;
                } catch (Exception ignored) {
                }
                if (gotValue != 0.00) {
                    if (gotValue > 0) {
                        if (gotValue == gotMaxValue) {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"#0000ff\">").append(gotValue).append("</font>");
                        } else if (gotValue > gotMaxValue / 2) {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"green\">").append(gotValue).append("</font>");
                        } else {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"lime\">").append(gotValue).append("</font>");
                        }
                    } else {

                        if (gotValue == gotMinValue) {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"red\">").append(gotValue).append("</font>");
                        } else if (gotValue < gotMinValue / 2) {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"orange\">").append(gotValue).append("</font>");
                        } else {
                            toolTip.append("<br>").append(names.get(i)).append(": <font color = \"#F7EF8A\">").append(gotValue).append("</font>");
                        }
                    }
                }
            }
            if (item.getHealTime() != 0.00) {
                if (item.getHealTime() < 0) {

                    if (item.getHealTime() == item.getMinHealTime()) {
                        toolTip.append("<br>Heal Time: <font color = \"#0000ff\">").append(item.getHealTime()).append("</font>");
                    } else if (item.getHealTime() < item.getMinHealTime() / 2) {
                        toolTip.append("<br>Heal Time: <font color = \"green\">").append(item.getHealTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Heal Time: <font color = \"lime\">").append(item.getHealTime()).append("</font>");
                    }
                } else {

                    if (item.getHealTime() == item.getMaxHealTime()) {
                        toolTip.append("<br>Heal Time: <font color = \"red\">").append(item.getHealTime()).append("</font>");
                    } else if (item.getHealTime() > item.getMaxHealTime() / 2) {
                        toolTip.append("<br>Heal Time: <font color = \"orange\">").append(item.getHealTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Heal Time: <font color = \"#F7EF8A\">").append(item.getHealTime()).append("</font>");
                    }
                }
            }
            if (item.getArrowTime() != 0.00) {

                if (item.getArrowTime() < 0) {
                    if (item.getArrowTime() == item.getMinArrowTime()) {
                        toolTip.append("<br>Arrow Time: <font color = \"#0000ff\">").append(item.getArrowTime()).append("</font>");
                    } else if (item.getArrowTime() < item.getMinArrowTime() / 2) {
                        toolTip.append("<br>Arrow Time: <font color = \"green\">").append(item.getArrowTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Arrow Time: <font color = \"lime\">").append(item.getArrowTime()).append("</font>");
                    }
                } else {

                    if (item.getArrowTime() == item.getMaxArrowTime()) {
                        toolTip.append("<br>Arrow Time: <font color = \"red\">").append(item.getArrowTime()).append("</font>");
                    } else if (item.getArrowTime() > item.getMaxArrowTime() / 2) {
                        toolTip.append("<br>Arrow Time: <font color = \"orange\">").append(item.getArrowTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Arrow Time: <font color = \"#F7EF8A\">").append(item.getArrowTime()).append("</font>");
                    }
                }
            }
            if (item.getMineTime() != 0.00) {

                if (item.getMineTime() < 0) {

                    if (item.getMineTime() == item.getMinMineTime()) {
                        toolTip.append("<br>Mine Time: <font color = \"#0000ff\">").append(item.getMineTime()).append("</font>");
                    } else if (item.getMineTime() < item.getMaxMineTime() / 2) {
                        toolTip.append("<br>Mine Time: <font color = \"green\">").append(item.getMineTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Mine Time: <font color = \"lime\">").append(item.getMineTime()).append("</font>");
                    }
                } else {

                    if (item.getMineTime() == item.getMaxMineTime()) {
                        toolTip.append("<br>Mine Time: <font color = \"red\">").append(item.getMineTime()).append("</font>");
                    } else if (item.getMineTime() > item.getMaxMineTime() / 2) {
                        toolTip.append("<br>Mine Time: <font color = \"orange\">").append(item.getMineTime()).append("</font>");
                    } else {
                        toolTip.append("<br>Mine Time: <font color = \"#F7EF8A\">").append(item.getMineTime()).append("</font>");
                    }
                }
            }
            //ToolTip Settings
            this.setFont(new Font("Arial", Font.PLAIN, 10));
            this.setToolTipText(("<html>" + toolTip + "</html>"));
            image = new ImageIcon(item.getImage().getScaledInstance(getWidth() + 1, getHeight() + 1, Image.SCALE_DEFAULT));
            this.setIcon(image);
        }
        if (item != null && item.getImage() != null) {
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
