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
        if (Objects.equals(attribute, "MaxHp")) {
            panel.ui.maxHp++;
        }
        if (Objects.equals(attribute, "ArrowVelocity")) {
            if(player.arrowVelocity < 4){
                player.arrowVelocity += 0.125;
            }
        }
        if (Objects.equals(attribute, "Armor")) {
            var item = new Item("Armor");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1:
                    item.sort = "chain";
                    item.setRangeSlingshotDamageReduction(5,10);
                    item.setRangeMeleeDamageReduction(5,10);
                    item.setRangeSpeed(-1,0);
                    item.setRangeArrowTime(0,1);
                    break;
                case 2:
                    item.sort = "heavy";
                    item.setRangeSlingshotDamageReduction(10,15);
                    item.setRangeMeleeDamageReduction(10,15);
                    item.setRangeMineDamageReduction(0,10);
                    item.setRangeSpeed(-3,-1);
                    item.setRangeArrowTime(1,2);
                    break;
                case 3:
                    item.sort = "light";
                    item.setRangeSlingshotDamageReduction(5,10);
                    item.setRangeMeleeDamageReduction(5,10);
                    item.setRangeSpeed(0,1);
                    item.setRangeArrowTime(-0.5,0);
                    break;
            }
            item.createStats();
            panel.inventory.allItams[0].add(item);
        }
        if (Objects.equals(attribute, "Sword")) {
            var item = new Item("Sword");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1:
                    item.sort = "lang";
                    item.setRangeSwordDamage(3,7);
                    item.setRangeMineTime(0,1);
                    item.setRangeSpeed(-1,0);
                    item.setRangeArrowTime(0,1);
                    break;
                case 2:
                    item.sort = "short";
                    item.setRangeSwordDamage(2,6);
                    item.setRangeMineTime(0,0.5);
                    item.setRangeSpeed(-0.5,0.5);
                    item.setRangeArrowTime(0,0.5);
                    break;
                case 3:
                    item.sort = "dager";
                    item.setRangeSwordDamage(1,5);
                    item.setRangeMineTime(0,0.5);
                    item.setRangeSpeed(-1,0);
                    item.setRangeArrowTime(-1,0);
                    break;
            }
            item.createStats();
            panel.inventory.allItams[1].add(item);
        }
        if (Objects.equals(attribute, "Slingshot")) {
            var item = new Item("Slingshot");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1:
                    item.sort = "";
                    item.setRangeSlingshotDamage(3,7);
                    item.setRangeMaxMines(1,2);
                    item.setRangeArrowTime(-0.5,0.5);
                    item.setRangeMeleeDamageReduction(-5,0);
                    break;
                case 2:
                    item.sort = "";
                    item.setRangeSlingshotDamage(1,4);
                    item.setRangeMaxMines(4,6);
                    item.setRangeArrowTime(-2,0);
                    item.setRangeMeleeDamageReduction(-5,3);
                    break;
                case 3:
                    item.sort = "";
                    item.setRangeSlingshotDamage(3,6);
                    item.setRangeMaxMines(2,4);
                    item.setRangeArrowTime(-1,0);
                    item.setRangeMeleeDamageReduction(-3,0);
                    break;
            }
            item.createStats();
            panel.inventory.allItams[2].add(item);
        }
        if (Objects.equals(attribute, "Mine")) {
            var item = new Item("Mine");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1:
                    item.sort = "";
                    item.setRangeMineDamage(3,7);
                    item.setRangeMaxMines(1,2);
                    item.setRangeMineTime(-0.5,0.5);
                    item.setRangeMineDamageReduction(0,5);
                    break;
                case 2:
                    item.sort = "";
                    item.setRangeMineDamage(1,4);
                    item.setRangeMaxMines(4,6);
                    item.setRangeMineTime(-2,0);
                    item.setRangeMineDamageReduction(0,7);
                    break;
                case 3:
                    item.sort = "";
                    item.setRangeMineDamage(3,6);
                    item.setRangeMaxMines(2,4);
                    item.setRangeMineTime(-1,0);
                    item.setRangeMineDamageReduction(0,10);
                    break;
            }
            item.createStats();
            panel.inventory.allItams[3].add(item);
        }
        if (Objects.equals(attribute, "Ring")) {
            var item = new Item("Ring");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1:
                    item.sort = "";
                    item.setRangeMineDamage(3,7);
                    item.setRangeMaxMines(1,2);
                    item.setRangeMineTime(-0.5,0.5);
                    item.setRangeMineDamageReduction(0,5);
                    break;
                case 2:
                    item.sort = "";
                    item.setRangeMineDamage(1,4);
                    item.setRangeMaxMines(4,6);
                    item.setRangeMineTime(-2,0);
                    item.setRangeMineDamageReduction(0,7);
                    break;
                case 3:
                    item.sort = "";
                    item.setRangeMineDamage(3,6);
                    item.setRangeMaxMines(2,4);
                    item.setRangeMineTime(-1,0);
                    item.setRangeMineDamageReduction(0,10);
                    break;
            }
            item.createStats();
            panel.inventory.allItams[3].add(item);
        }
        panel.grabFocus();
        for (int i = 0; i <= 2; i++) {
            panel.remove(panel.ui.allButtons.get(i));
        }
        panel.ui.allButtons.removeAll(panel.ui.allButtons);
        panel.repaint();
    }

}

