import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        var frameThread = new Thread(ConnectionFrame::new);
        frameThread.start();
        System.out.println("created server");
        new Server();
    }
}