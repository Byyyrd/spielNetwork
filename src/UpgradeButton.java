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
        this.setBounds(x,400,400,200);
        this.setText(this.attribute);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(attribute, "Heal")){


        }
        if(Objects.equals(attribute, "SwordDamage")){


        }
        if(Objects.equals(attribute, "Slingshotdamage")){


        }
        if(Objects.equals(attribute, "Minedamage")){


        }
        if(Objects.equals(attribute, "Slingshot-projectiles")){


        }
        if(Objects.equals(attribute, "Sword-DmgReduction")){


        }
        if(Objects.equals(attribute, "Slingshot-DmgReduction")){


        }
        if(Objects.equals(attribute, "Mines-DmgReduction")){


        }
        if(Objects.equals(attribute, "Speed")){


        }
        if(Objects.equals(attribute, "MaxMines")){


        }
        if(Objects.equals(attribute, "MineGain")){


        }
        panel.grabFocus();
        panel.addKeyListener(panel);
        for(int i = 0; i <= 2;i++){
            panel.remove(panel.p1Inv.allButtons.get(i));
        }
        panel.p1Inv.allButtons.removeAll(panel.p1Inv.allButtons);
    }
}

