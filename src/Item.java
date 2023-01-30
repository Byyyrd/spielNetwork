import javax.swing.*;
import java.awt.*;

public class Item {
    String type;
    String sort;
    Image image;
    private double speed;
    private double minSpeed = 0;
    private double maxSpeed = 0;
    private double healTime = 0;
    private double minHealTime = 0;
    private double maxHealTime = 0;
    private double swordDamage = 0;
    private double minSwordDamage = 0;
    private double maxSwordDamage = 0;
    private double slingshotDamage = 0;
    private double minSlingshotDamage = 0;
    private double maxSlingshotDamage = 0;
    private double mineDamage = 0;
    private double minMineDamage = 0;
    private double maxMineDamage = 0;
    private double arrowTime = 0;
    private double minArrowTime = 0;
    private double maxArrowTime = 0;
    private double mineTime = 0;
    private double minMineTime = 0;
    private double maxMineTime = 0;
    private double meleeDamageReduction = 0;
    private double minMeleeDamageReduction = 0;
    private double maxMeleeDamageReduction = 0;
    private double slingshotDamageReduction = 0;
    private double minSlingshotDamageReduction = 0;
    private double maxSlingshotDamageReduction = 0;
    private double mineDamageReduction = 0;
    private double minMineDamageReduction = 0;
    private double maxMineDamageReduction = 0;
    private double arrowVelocity = 0;
    private double minArrowVelocity = 0;
    private double maxArrowVelocity = 0;
    private int maxMines = 0;
    private int minMaxMines = 0;
    private int maxMaxMines = 0;
    private int maxHp = 0;
    private int minMaxHp = 0;
    private int maxMaxHp = 0;

    public Item(String type) {
        this.type = type;
        image = new ImageIcon("resources/Error.png").getImage();
    }

    public void createStats(){
        speed = random(minSpeed,maxSpeed);
        healTime = random(minHealTime,maxHealTime);
        swordDamage = random(minSwordDamage, maxSwordDamage);
        slingshotDamage =  random(minSlingshotDamage, maxSlingshotDamage);
        mineDamage = random(minMineDamage, maxMineDamage);
        arrowTime = random(minArrowTime, maxArrowTime);
        mineTime = random(minMineTime, maxMineTime);
        meleeDamageReduction = random(minMeleeDamageReduction, maxMeleeDamageReduction);
        slingshotDamageReduction = random(minSlingshotDamageReduction, maxSlingshotDamageReduction);
        mineDamageReduction = random(minMineDamageReduction, maxMineDamageReduction);
        arrowVelocity = random(minArrowVelocity, maxArrowVelocity);
        maxMines = (int) random( minMaxMines, maxMaxMines);
        maxHp = (int) random(minMaxHp, maxMaxHp);

    }

    public void setRangeSpeed(double minSpeed, double maxSpeed) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public void setRangeHealTime(double minHealTime, double maxHealTime) {
        this.minHealTime = minHealTime;
        this.maxHealTime = maxHealTime;
    }

    public void setRangeSwordDamage(double minSwordDamage, double maxSwordDamage) {
        this.minSwordDamage = minSwordDamage;
        this.maxSwordDamage = maxSwordDamage;
    }

    public void setRangeSlingshotDamage(double minSlingshotDamage, double maxSlingshotDamage) {
        this.minSlingshotDamage = minSlingshotDamage;
        this.maxSlingshotDamage = maxSlingshotDamage;
    }

    public void setRangeMineDamage(double minMineDamage, double maxMineDamage) {
        this.minMineDamage = minMineDamage;
        this.maxMineDamage = maxMineDamage;
    }

    public void setRangeArrowTime(double minArrowTime, double maxArrowTime) {
        this.minArrowTime = minArrowTime;
        this.maxArrowTime = maxArrowTime;
    }

    public void setRangeMineTime(double minMineTime, double maxMineTime) {
        this.minMineTime = minMineTime;
        this.maxMineTime = maxMineTime;
    }

    public void setRangeMeleeDamageReduction(double minMeleeDamageReduction, double maxMeleeDamageReduction) {
        this.minMeleeDamageReduction = minMeleeDamageReduction;
        this.maxMeleeDamageReduction = maxMeleeDamageReduction;
    }

    public void setRangeSlingshotDamageReduction(double minSlingshotDamageReduction, double maxSlingshotDamageReduction) {
        this.minSlingshotDamageReduction = minSlingshotDamageReduction;
        this.maxSlingshotDamageReduction = maxSlingshotDamageReduction;
    }

    public void setRangeMineDamageReduction(double minMineDamageReduction, double maxMineDamageReduction) {
        this.minMineDamageReduction = minMineDamageReduction;
        this.maxMineDamageReduction = maxMineDamageReduction;
    }

    public void setRangeArrowVelocity(double minArrowVelocity, double maxArrowVelocity) {
        this.minArrowVelocity = minArrowVelocity;
        this.maxArrowVelocity = maxArrowVelocity;
    }

    public void setRangeMaxMines(int minMaxMines, int maxMaxMines) {
        this.minMaxMines = minMaxMines;
        this.maxMaxMines = maxMaxMines;
    }

    public void setRangeMaxHp(int minMaxHp, int maxMaxHp) {
        this.minMaxHp = minMaxHp;
        this.maxMaxHp = maxMaxHp;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double random(double min, double max){
        if (min == 0 && max == 0){
            return 0;
        }
        return ((int)(((Math.random()*((max-min))+ min))*10f)+1)/10.0;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHealTime() {
        return healTime;
    }

    public double getSwordDamage() {
        return swordDamage;
    }

    public double getSlingshotDamage() {
        return slingshotDamage;
    }

    public double getMineDamage() {
        return mineDamage;
    }

    public double getArrowTime() {
        return arrowTime;
    }

    public double getMineTime() {
        return mineTime;
    }

    public double getMeleeDamageReduction() {
        return meleeDamageReduction;
    }

    public double getSlingshotDamageReduction() {
        return slingshotDamageReduction;
    }

    public double getMineDamageReduction() {
        return mineDamageReduction;
    }

    public double getArrowVelocity() {
        return arrowVelocity;
    }

    public int getMaxMines() {
        return maxMines;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
