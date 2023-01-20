import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private int x;
    private int y;
    private String name;
    private double swordRotation;
    ArrayList<ArrayList<Double>> allArrows;
    double rotation;
    boolean bowPickedUp;

    public Message(int x, int y, String name, double swordRotation, ArrayList<ArrayList<Double>> allArrows, double rotation, boolean bowPickedUp) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.swordRotation = swordRotation;
        this.allArrows = allArrows;
        this.rotation = rotation;
        this.bowPickedUp = bowPickedUp;
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
