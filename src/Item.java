import javax.swing.*;
import java.awt.*;

public class Item {
    String type;
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
    private double bowDamage = 0;
    private double minBowDamage = 0;
    private double maxBowDamage = 0;
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
    private double bowDamageReduction = 0;
    private double minBowDamageReduction = 0;
    private double maxBowDamageReduction = 0;
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
        bowDamage =  random(minBowDamage, maxBowDamage);
        mineDamage = random(minMineDamage, maxMineDamage);
        arrowTime = random(minArrowTime, maxArrowTime);
        mineTime = random(minMineTime, maxMineTime);
        meleeDamageReduction = random(minMeleeDamageReduction, maxMeleeDamageReduction);
        bowDamageReduction = random(minBowDamageReduction, maxBowDamageReduction);
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

    public void setRangeBowDamage(double minBowDamage, double maxBowDamage) {
        this.minBowDamage = minBowDamage;
        this.maxBowDamage = maxBowDamage;
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

    public void setRangeBowDamageReduction(double minBowDamageReduction, double maxBowDamageReduction) {
        this.minBowDamageReduction = minBowDamageReduction;
        this.maxBowDamageReduction = maxBowDamageReduction;
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

    public double getBowDamage() {
        return bowDamage;
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

    public double getBowDamageReduction() {
        return bowDamageReduction;
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

    public double getMinSpeed() {
        return minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMinHealTime() {
        return minHealTime;
    }

    public double getMaxHealTime() {
        return maxHealTime;
    }

    public double getMinSwordDamage() {
        return minSwordDamage;
    }

    public double getMaxSwordDamage() {
        return maxSwordDamage;
    }

    public double getMinBowDamage() {
        return minBowDamage;
    }

    public double getMaxBowDamage() {
        return maxBowDamage;
    }

    public double getMinMineDamage() {
        return minMineDamage;
    }

    public double getMaxMineDamage() {
        return maxMineDamage;
    }

    public double getMinArrowTime() {
        return minArrowTime;
    }

    public double getMaxArrowTime() {
        return maxArrowTime;
    }

    public double getMinMineTime() {
        return minMineTime;
    }

    public double getMaxMineTime() {
        return maxMineTime;
    }

    public double getMinMeleeDamageReduction() {
        return minMeleeDamageReduction;
    }

    public double getMaxMeleeDamageReduction() {
        return maxMeleeDamageReduction;
    }

    public double getMinBowDamageReduction() {
        return minBowDamageReduction;
    }

    public double getMaxBowDamageReduction() {
        return maxBowDamageReduction;
    }

    public double getMinMineDamageReduction() {
        return minMineDamageReduction;
    }

    public double getMaxMineDamageReduction() {
        return maxMineDamageReduction;
    }

    public double getMinArrowVelocity() {
        return minArrowVelocity;
    }

    public double getMaxArrowVelocity() {
        return maxArrowVelocity;
    }

    public int getMinMaxMines() {
        return minMaxMines;
    }

    public int getMaxMaxMines() {
        return maxMaxMines;
    }

    public int getMinMaxHp() {
        return minMaxHp;
    }

    public int getMaxMaxHp() {
        return maxMaxHp;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHealTime(double healTime) {
        this.healTime = healTime;
    }

    public void setSwordDamage(double swordDamage) {
        this.swordDamage = swordDamage;
    }

    public void setBowDamage(double bowDamage) {
        this.bowDamage = bowDamage;
    }

    public void setMineDamage(double mineDamage) {
        this.mineDamage = mineDamage;
    }

    public void setArrowTime(double arrowTime) {
        this.arrowTime = arrowTime;
    }

    public void setMineTime(double mineTime) {
        this.mineTime = mineTime;
    }

    public void setMeleeDamageReduction(double meleeDamageReduction) {
        this.meleeDamageReduction = meleeDamageReduction;
    }

    public void setBowDamageReduction(double bowDamageReduction) {
        this.bowDamageReduction = bowDamageReduction;
    }

    public void setMineDamageReduction(double mineDamageReduction) {
        this.mineDamageReduction = mineDamageReduction;
    }

    public void setMaxMines(int maxMines) {
        this.maxMines = maxMines;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxSwordDamage(double maxSwordDamage) {
        this.maxSwordDamage = maxSwordDamage;
    }

    public void setMaxBowDamage(double maxBowDamage) {
        this.maxBowDamage = maxBowDamage;
    }

    public void setMaxMineDamage(double maxMineDamage) {
        this.maxMineDamage = maxMineDamage;
    }

    public void setMaxMeleeDamageReduction(double maxMeleeDamageReduction) {
        this.maxMeleeDamageReduction = maxMeleeDamageReduction;
    }

    public void setMaxBowDamageReduction(double maxBowDamageReduction) {
        this.maxBowDamageReduction = maxBowDamageReduction;
    }

    public void setMaxMineDamageReduction(double maxMineDamageReduction) {
        this.maxMineDamageReduction = maxMineDamageReduction;
    }

    public void setMaxMaxMines(int maxMaxMines) {
        this.maxMaxMines = maxMaxMines;
    }

    public void setMaxMaxHp(int maxMaxHp) {
        this.maxMaxHp = maxMaxHp;
    }

    public void setMinHealTime(double minHealTime) {
        this.minHealTime = minHealTime;
    }

    public void setMinArrowTime(double minArrowTime) {
        this.minArrowTime = minArrowTime;
    }

    public void setMinMineTime(double minMineTime) {
        this.minMineTime = minMineTime;
    }
}
