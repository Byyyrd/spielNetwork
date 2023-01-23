import java.io.Serializable;
import java.util.ArrayList;

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

    public Message(int x, int y, String name, double swordRotation, double rotation, boolean bowPickedUp, double mouseX, double mouseY, boolean isClicked) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.swordRotation = swordRotation;
        this.rotation = rotation;
        this.bowPickedUp = bowPickedUp;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isClicked = isClicked;


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