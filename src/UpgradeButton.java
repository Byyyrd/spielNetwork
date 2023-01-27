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
            if (player.healTime > 1) {
                player.healTime -= 0.19;
            }
        }
        if (Objects.equals(attribute, "SwordDamage")) {
            if (player.swordDamage < 10) {
                player.swordDamage += 0.25;
            }
        }
        if (Objects.equals(attribute, "SlingshotDamage")) {
            if (player.slingshotDamage < 7.5) {
                player.slingshotDamage += 0.25;
            }
        }
        if (Objects.equals(attribute, "MineDamage")) {
            if (player.mineDamage < 35) {
                player.mineDamage += 0.5;
            }
        }
        if (Objects.equals(attribute, "Slingshot-CoolDown")) {
            if (player.arrowTime > 0){
                player.arrowTime -= 0.125;
            }
        }
        if (Objects.equals(attribute, "Sword-DmgReduction")) {
            if (player.meleeDamageReduction < 75) {
                player.meleeDamageReduction += 2.5;
            }
        }
        if (Objects.equals(attribute, "Slingshot-DmgReduction")) {
            if (player.slingshotDamageReduction < 75) {
                player.slingshotDamageReduction += 2;
            }
        }
        if (Objects.equals(attribute, "Mines-DmgReduction")) {
            if (player.mineDamageReduction < 75) {
                player.mineDamageReduction += 4;
            }
        }
        if (Objects.equals(attribute, "Speed")) {
            player.speed += 0.125;
        }
        if (Objects.equals(attribute, "MaxMines")) {
            if (player.maxMines < 55) {
                player.maxMines += 5;
            }
        }
        if (Objects.equals(attribute, "MineGain")) {
            if (player.mineTime > 1) {
                player.mineTime -= 0.19;
            }
        }
        panel.grabFocus();
        for (int i = 0; i <= 2; i++) {
            panel.remove(panel.p1Inv.allButtons.get(i));
        }
        panel.p1Inv.allButtons.removeAll(panel.p1Inv.allButtons);
        panel.repaint();
    }
}

