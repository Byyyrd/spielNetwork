import java.io.Serializable;

public class Message implements Serializable {
    private int x;
    private int y;
    private String name;
    private double swordRotation;

    public Message(int x, int y,String name, double swordRotation) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.swordRotation = swordRotation;
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
