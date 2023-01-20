import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private int x;
    private int y;
    private String name;
    private double swordRotation;
    private ArrayList<Double[]> allArrows;
    private ArrayList<Double[]> test = new ArrayList<>();
    double rotation;
    boolean bowPickedUp;

    public Message(int x, int y, String name, double swordRotation, ArrayList<Double[]> allArrows, double rotation, boolean bowPickedUp) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.swordRotation = swordRotation;
        this.allArrows = allArrows;
        this.rotation = rotation;
        this.bowPickedUp = bowPickedUp;
        Double[] testaray = new Double[9];
        testaray[0] = 546.214;
        testaray[1] = 234.35;
        testaray[2] = 546.214;
        testaray[3] = 234.35;
        testaray[4] = 546.214;
        testaray[5] = 234.35;
        testaray[6] = 546.214;
        testaray[7] = 234.35;
        testaray[8] = 546.214;
        test.add(testaray);

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

    public ArrayList<Double[]> getAllArrows() {
        return allArrows;
    }

    public ArrayList<Double[]> getTest() {
        return test;
    }
}
