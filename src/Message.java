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
    boolean minePlased;
    boolean explodet;
    String message;
    double hp;
    int death;
    double swordDamage;
    double slingshotDamage;
    double minenDemage;
    double minenTime;
    int maxMinen;

    public Message(int x, int y, String name, double swordRotation, double rotation, boolean bowPickedUp, double mouseX, double mouseY, boolean isClicked, boolean swordPickedUp, boolean minePlased, boolean explodet, String message, double hp, int death, double swordDamage, double slingshotDamage, double minenDemage, double minenTime, int maxMinen) {
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
        this.minePlased = minePlased;
        this.explodet = explodet;
        this.message = message;
        this.hp = hp;
        this.death = death;
        this.swordDamage = swordDamage;
        this.slingshotDamage = slingshotDamage;
        this.minenDemage = minenDemage;
        this.minenTime = minenTime;
        this.maxMinen = maxMinen;
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