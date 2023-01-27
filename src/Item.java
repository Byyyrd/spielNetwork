public class Item {
    String type;
    String sort;
    private double speed;
    private double minSpeed;
    private double maxSpeed;
    private double healTime;
    private double minHealTime;
    private double maxHealTime;
    private double swordDamage;
    private double minSwordDamage;
    private double maxSwordDamage;
    private double slingshotDamage;
    private double minSlingshotDamage;
    private double maxSlingshotDamage;
    private double mineDamage;
    private double minMineDamage;
    private double maxMineDamage;
    private double arrowTime;
    private double minArrowTime;
    private double maxArrowTime;
    private double mineTime;
    private double minMineTime;
    private double maxMineTime;
    private double meleeDamageReduction;
    private double minMeleeDamageReduction;
    private double maxMeleeDamageReduction;
    private double slingshotDamageReduction;
    private double minSlingshotDamageReduction;
    private double maxSlingshotDamageReduction;
    private double mineDamageReduction;
    private double minMineDamageReduction;
    private double maxMineDamageReduction;
    private double arrowVelocity;
    private double minArrowVelocity;
    private double maxArrowVelocity;
    private int maxMines;
    private int minMaxMines;
    private int maxMaxMines;
    private int maxHp;
    private int minMaxHp;
    private int maxMaxHp;

    public Item(String type) {
        this.type = type;
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
        this.maxSlingshotDamage = maxSlingshotDamageReduction;
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

    public double random(double min, double max){
        return ((int)(((Math.random()*((max-min))+ min))*10f)+1)/10.0;
    }
}
