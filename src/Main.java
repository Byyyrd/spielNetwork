import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var frameThread = new Thread(() -> {
            ConnectionFrame frame = new ConnectionFrame();
        });
        frameThread.run();
        System.out.println("created server");
        Server server = new Server();
    }
}