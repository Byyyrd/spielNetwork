import java.io.Serializable;

public class Message implements Serializable {
    private int x;
    private int y;
    private String name;

    public Message(int x, int y,String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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
}
