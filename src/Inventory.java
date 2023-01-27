import java.util.ArrayList;

public class Inventory {
    ArrayList[] allItams = new ArrayList[7];
    public Inventory() {
        allItams[0] = new ArrayList<Item>();
        allItams[1] = new ArrayList<Item>();
        allItams[2] = new ArrayList<Item>();
        allItams[3] = new ArrayList<Item>();
        allItams[4] = new ArrayList<Item>();
        allItams[5] = new ArrayList<Item>();
        allItams[6] = new ArrayList<Item>();
    }
}
