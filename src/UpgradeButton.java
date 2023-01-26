import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class UpgradeButton extends JButton implements ActionListener {
    String attribute;
    Panel panel;
    public UpgradeButton(Panel panel, int x, String attribute) {
        this.panel = panel;
        this.attribute = attribute;
        this.setBorderPainted(true);
        this.addActionListener(this);
        this.setBackground(new Color(94, 94, 94));
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        this.setBounds(x,300,200,100);
        this.setText(this.attribute);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(attribute, "Heal")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "SwordDamage")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Slingshotdamage")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Minedamage")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Slingshot-projectiles")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Sword-DmgReduction")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Slingshot-DmgReduction")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Mines-DmgReduction")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "Speed")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "MaxMines")){

            panel.remove(this);
        }
        if(Objects.equals(attribute, "MineGain")){

            panel.remove(this);
        }
    }
}

