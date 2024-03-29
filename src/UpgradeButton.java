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
        this.setFocusable(false);
        this.panel = panel;
        this.attribute = attribute;
        this.setBorderPainted(true);
        this.addActionListener(this);
        this.setBackground(new Color(94, 94, 94));
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        this.setBounds(x, 400, 500, 200);
        switch (attribute) {
            case "Armor" -> {
                var image = new ImageIcon(panel.mysteryArmor.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Sword" -> {
                var image = new ImageIcon(panel.mysterySword.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Bow" -> {
                var image = new ImageIcon(panel.mysteryBow.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Mine" -> {
                var image = new ImageIcon(panel.mysteryMine.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Ring" -> {
                var image = new ImageIcon(panel.mysteryRing.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Chain" -> {
                var image = new ImageIcon(panel.mysteryCharm.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            case "Shoes" -> {
                var image = new ImageIcon(panel.mysteryShoe.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                this.setIcon(image);
            }
            default -> this.setText(attribute);
        }
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
        if (Objects.equals(attribute, "BowDamage")) {
            if (player.bowDamage < 7.5) {
                player.bowDamage += 0.25;
            }
        }
        if (Objects.equals(attribute, "MineDamage")) {
            if (player.mineDamage < 35) {
                player.mineDamage += 0.5;
            }
        }
        if (Objects.equals(attribute, "Bow-CoolDown")) {
            if (player.arrowTime > 0){
                player.arrowTime -= 0.125;
            }
        }
        if (Objects.equals(attribute, "Sword-DmgReduction")) {
            if (player.meleeDamageReduction < 75) {
                player.meleeDamageReduction += 2.5;
            }
        }
        if (Objects.equals(attribute, "Bow-DmgReduction")) {
            if (player.bowDamageReduction < 75) {
                player.bowDamageReduction += 2;
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
            if (player.maxMines < 125) {
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
                case 1 -> {
                    item.setImage(panel.armorImage2);
                    item.setRangeBowDamageReduction(5, 10);
                    item.setRangeMeleeDamageReduction(5, 10);
                    item.setRangeSpeed(-1, 0);
                    item.setRangeArrowTime(0, 1);
                    item.setRangeMaxHp(1,2);
                }
                case 2 -> {
                    item.setImage(panel.armorImage1);
                    item.setRangeBowDamageReduction(10, 15);
                    item.setRangeMeleeDamageReduction(10, 15);
                    item.setRangeMineDamageReduction(0, 10);
                    item.setRangeSpeed(-3, -1);
                    item.setRangeArrowTime(1, 2);
                    item.setRangeMaxHp(1,5);
                }
                case 3 -> {
                    item.setImage(panel.armorImage3);
                    item.setRangeBowDamageReduction(5, 7);
                    item.setRangeMeleeDamageReduction(5, 7);
                    item.setRangeSpeed(0, 1);
                    item.setRangeArrowTime(-0.5, 0);
                }
            }
            item.createStats();

            panel.inventory.allItems[0].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Sword")) {
            var item = new Item("Sword");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.swordImage1);
                    item.setRangeSwordDamage(3, 7);
                    item.setRangeMineTime(0, 1);
                    item.setRangeSpeed(-1, 0);
                    item.setRangeArrowTime(0, 1);
                }
                case 2 -> {
                    item.setImage(panel.swordImage2);
                    item.setRangeSwordDamage(2, 6);
                    item.setRangeMineTime(0, 0.5);
                    item.setRangeSpeed(-0.5, 0.5);
                    item.setRangeArrowTime(0, 0.5);
                }
                case 3 -> {
                    item.setImage(panel.swordImage3);
                    item.setRangeSwordDamage(1, 5);
                    item.setRangeMineTime(0, 0.5);
                    item.setRangeSpeed(0, 1);
                    item.setRangeArrowTime(-1, 0);
                }
            }
            item.createStats();

            panel.inventory.allItems[1].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Bow")) {
            var item = new Item("Bow");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.bowImage1);
                    item.setRangeBowDamage(3, 7);
                    item.setRangeArrowTime(-0.5, 0.5);
                    item.setRangeMeleeDamageReduction(-5, 0);
                    item.setRangeArrowVelocity(-1, 0.5);
                }
                case 2 -> {
                    item.setImage(panel.bowImage3);
                    item.setRangeBowDamage(1, 4);
                    item.setRangeArrowTime(-2, 0);
                    item.setRangeMeleeDamageReduction(-5, 3);
                    item.setRangeArrowVelocity(-0.5, 1);
                }
                case 3 -> {
                    item.setImage(panel.bowImage2);
                    item.setRangeBowDamage(3, 6);
                    item.setRangeArrowTime(-1, 0);
                    item.setRangeMeleeDamageReduction(-3, 0);
                    item.setRangeArrowVelocity(0, 1);
                }
            }
            item.createStats();
            panel.inventory.allItems[2].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Mine")) {
            var item = new Item("Mine");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.mineImage1);
                    item.setRangeMineDamage(3, 7);
                    item.setRangeMaxMines(1, 2);
                    item.setRangeMineTime(-0.5, 0.5);
                    item.setRangeMineDamageReduction(0, 5);
                }
                case 2 -> {
                    item.setImage(panel.mineImage3);
                    item.setRangeMineDamage(1, 4);
                    item.setRangeMaxMines(4, 6);
                    item.setRangeMineTime(-2, 0);
                    item.setRangeMineDamageReduction(0, 7);
                }
                case 3 -> {
                    item.setImage(panel.mineImage2);
                    item.setRangeMineDamage(3, 6);
                    item.setRangeMaxMines(2, 4);
                    item.setRangeMineTime(-1, 0);
                    item.setRangeMineDamageReduction(0, 10);
                }
            }
            item.createStats();
            panel.inventory.allItems[3].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Ring")) {
            var item = new Item("Ring");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.ringImage2);
                    item.setRangeMineTime(-1, 0);
                    item.setRangeMaxMines(2, 7);
                    item.setRangeMineDamage(4, 9);
                    item.setRangeMineDamageReduction(4, 8);
                }
                case 2 -> {
                    item.setImage(panel.ringImage3);
                    item.setRangeBowDamage(3, 7);
                    item.setRangeBowDamageReduction(4, 8);
                    item.setRangeArrowVelocity(0, 1);
                    item.setRangeArrowTime(-1, 0);
                }
                case 3 -> {
                    item.setImage(panel.ringImage1);
                    item.setRangeSwordDamage(4, 7);
                    item.setRangeMeleeDamageReduction(5, 8);
                }
            }
            item.createStats();
            panel.inventory.allItems[4].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Chain")) {
            var item = new Item("Chain");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.chainImage3);
                    item.setRangeHealTime(0, 1);
                    item.setRangeArrowTime(-1, 0);
                    item.setRangeMineTime(-0.5, 0.5);
                }
                case 2 -> {
                    item.setImage(panel.chainImage2);
                    item.setRangeHealTime(-0.5, 0.5);
                    item.setRangeArrowTime(0, 1);
                    item.setRangeMineTime(-1, 0);
                }
                case 3 -> {
                    item.setImage(panel.chainImage1);
                    item.setRangeHealTime(-1, 0);
                    item.setRangeArrowTime(-0.5, 0.5);
                    item.setRangeMineTime(0, 1);
                }
            }
            item.createStats();
            panel.inventory.allItems[5].add(item);
            panel.inventory.updateInv();
        }
        if (Objects.equals(attribute, "Shoes")) {
            var item = new Item("Shoes");
            int rand = (int) (Math.random() * 3 + 1);
            switch (rand) {
                case 1 -> {
                    item.setImage(panel.shoeImage1);
                    item.setRangeSpeed(-1, 0);
                    item.setRangeMineDamageReduction(2, 5);
                    item.setRangeMeleeDamageReduction(2, 5);
                    item.setRangeBowDamageReduction(2, 5);
                }
                case 2 -> {
                    item.setImage(panel.shoeImage2);
                    item.setRangeSpeed(-0.5, 0.5);
                    item.setRangeMineDamageReduction(1, 3);
                    item.setRangeMeleeDamageReduction(1, 3);
                    item.setRangeBowDamageReduction(1, 3);
                }
                case 3 -> {
                    item.setImage(panel.shoeImage3);
                    item.setRangeSpeed(1, 3);
                    item.setRangeMineDamageReduction(0, 2);
                    item.setRangeMeleeDamageReduction(0, 2);
                    item.setRangeBowDamageReduction(0, 2);
                }
            }
            item.createStats();
            panel.inventory.allItems[6].add(item);
            panel.inventory.updateInv();
        }
        panel.grabFocus();
        for (int i = 0; i <= 2; i++) {
            panel.remove(panel.ui.allButtons.get(i));
        }
        panel.inventory.updateInv();
        panel.ui.allButtons.removeAll(panel.ui.allButtons);
        panel.inUpgradeWindow = false;
        panel.inventory.updateInv();
        panel.repaint();
    }

}

