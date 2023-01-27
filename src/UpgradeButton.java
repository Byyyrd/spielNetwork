import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class UpgradeButton extends JButton implements ActionListener {
    String attribute;
    Panel panel;
    Player player;

    public UpgradeButton(Panel panel, int x, String attribute) {
        this.panel = panel;
        this.attribute = attribute;
        this.setBorderPainted(true);
        this.addActionListener(this);
        this.setBackground(new Color(94, 94, 94));
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        this.setBounds(x, 400, 500, 200);
        this.setText(this.attribute);
        this.player = panel.player1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(attribute, "Heal")) {
            player.healTime -= 0.19;
        }
        if (Objects.equals(attribute, "SwordDamage")) {
            player.swordDamage += 0.25;
        }
        if (Objects.equals(attribute, "Slingshotdamage")) {
            player.slingshotDamage += 0.25;
        }
        if (Objects.equals(attribute, "Minedamage")) {
            player.mineDamage += 0.5;
        }
        if (Objects.equals(attribute, "Slingshot-Cooldown")) {
            player.arrowTime -= 0.125;
        }
        if (Objects.equals(attribute, "Sword-DmgReduction")) {
            player.meleeDamageReduction += 2.5;
        }
        if (Objects.equals(attribute, "Slingshot-DmgReduction")) {
            player.slingshotDamageReduction += 2;
        }
        if (Objects.equals(attribute, "Mines-DmgReduction")) {
            player.mineDamageReduction += 4;
        }
        if (Objects.equals(attribute, "Speed")) {
            player.speed += 0.125;
        }
        if (Objects.equals(attribute, "MaxMines")) {
            player.maxMines += 5;
        }
        if (Objects.equals(attribute, "MineGain")) {
            player.mineTime -= 0.19;
        }
        panel.grabFocus();
        for (int i = 0; i <= 2; i++) {
            panel.remove(panel.p1Inv.allButtons.get(i));
        }
        panel.p1Inv.allButtons.removeAll(panel.p1Inv.allButtons);
        panel.repaint();
    }
}

