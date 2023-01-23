import java.io.Serializable;

public class Message implements Serializable {
    private int x;
    private int y;
    private String name;
    private double swordRotation;
    double rotation;
    boolean bowPickedUp;
    double mouseX;
    double mouseY;
    boolean isClicked;
    boolean swordPickedUp;

    public Message(int x, int y, String name, double swordRotation, double rotation, boolean bowPickedUp, double mouseX, double mouseY, boolean isClicked, boolean swordPickedUp) {
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