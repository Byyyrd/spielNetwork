import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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

        this.setFont(new Font("Arial",Font.PLAIN,50));
        this.setToolTipText("Hallo");
    }

    public void updateButton() {
        if (item != null && item.getImage() != null) {
           if (item.getSpeed() != 0){
               toolTip = toolTip + item.getSpeed();
           }
            if (item.getArrowTime() != 0){
                toolTip = toolTip + item.getArrowTime();
            }
            if (item.getHealTime() != 0){
                toolTip = toolTip + item.getHealTime();
            }
            if (item.getArrowVelocity() != 0){
                toolTip = toolTip + item.getArrowVelocity();
            }
            if (item.getMaxHp() != 0){
                toolTip = toolTip + item.getMaxHp();
            }
            if (item.getMaxMines() != 0){
                toolTip = toolTip + item.getMaxMines();
            }
            if (item.getMeleeDamageReduction() != 0){
                toolTip = toolTip + item.getMeleeDamageReduction();
            }
            if (item.getMeleeDamageReduction() != 0){
                toolTip = toolTip + item.getMeleeDamageReduction();
            }
            if (item.getMineDamageReduction() != 0){
                toolTip = toolTip + item.getMineDamageReduction();
            }
            if (item.getSlingshotDamageReduction() != 0){
                toolTip = toolTip + item.getSlingshotDamageReduction();
            }
            if (item.getMineTime() != 0){
                toolTip = toolTip + item.getMineTime();
            }
            if (item.getSwordDamage() != 0){
                toolTip = toolTip + item.getSwordDamage();
            }
            if (item.getMineDamage() != 0){
                toolTip = toolTip + item.getMineDamage();
            }
            if (item.getSlingshotDamage() != 0){
                toolTip = toolTip + item.getSlingshotDamage();
            }
            this.setFont(new Font("Arial",Font.PLAIN,50));
            this.setToolTipText(toolTip);
            image = new ImageIcon(item.getImage().getScaledInstance(getWidth()+1, getHeight()+1, Image.SCALE_DEFAULT));
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
