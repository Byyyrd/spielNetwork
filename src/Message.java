import java.io.Serializable;

public class Message implements Serializable {
    private final int x;
    private final int y;
    private final String name;
    private final double swordRotation;
    double rotation;
    boolean bowPickedUp;
    double mouseX;
    double mouseY;
    boolean isClicked;
    boolean swordPickedUp;
    boolean minePlaced;
    boolean exploded;
    String message;
    double normHp;
    int death;
    double swordDamage;
    double bowDamage;
    double mineDamage;
    double mineTime;
    int maxMines;
    double fistX;
    double fistY;
    boolean fistEquipped;
    boolean controlled;
    boolean spawned;
    int bossX;
    int bossY;
    boolean bossFired;
    double bossHp;
    double bossMaxHp;
    int bosslevel;

    public Message(int x, int y, String name, double swordRotation, double rotation, boolean bowPickedUp, double mouseX, double mouseY, boolean isClicked, boolean swordPickedUp, boolean minePlaced, boolean exploded, String message, double normHp, int death, double swordDamage, double bowDamage, double mineDamage, double mineTime, int maxMines, double fistX, double fistY , boolean fistEquipped, boolean spawned, boolean controlled, int bossX, int bossY, boolean bossFired, double bossHp, double bossMaxHp, int bosslevel) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.swordRotation = swordRotation;
        this.rotation = rotation;
        this.bowPickedUp = bowPickedUp;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isClicked = isClicked;
        this.swordPickedUp = swordPickedUp;
        this.minePlaced = minePlaced;
        this.exploded = exploded;
        this.message = message;
        this.normHp = normHp;
        this.death = death;
        this.swordDamage = swordDamage;
        this.bowDamage = bowDamage;
        this.mineDamage = mineDamage;
        this.mineTime = mineTime;
        this.maxMines = maxMines;
        this.fistX = fistX;
        this.fistY = fistY;
        this.fistEquipped = fistEquipped;
        this.spawned = spawned;
        this.controlled = controlled;
        this.bossX = bossX;
        this.bossY = bossY;
        this.bossFired = bossFired;
        this.bossHp = bossHp;
        this.bossMaxHp = bossMaxHp;
        this.bosslevel =bosslevel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public double getSwordRotation() {
        return swordRotation;
    }

}