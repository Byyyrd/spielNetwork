import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.System.exit;

public class Client {
    String name;
    Socket socket;
    GameFrame frame;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    ConnectionFrame conFrame;
    Panel panel;
    Slingshot slingshot;
    Message message;
    boolean isClicked = false;
    boolean minePlased = false;
    boolean explodet = false;
    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Client(String host, String name, ConnectionFrame conFrame) throws IOException {
        this.name = name;
        this.conFrame = conFrame;

        conFrame.setVisible(true);
        frame = new GameFrame(this);
        socket = new Socket(host, 30000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        var socketThread = new Thread(() -> {
            while (true) {
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Message recievedObject = (Message) inputStream.readObject();
                    panel.setPlayer2(recievedObject.getX(), recievedObject.getY(), recievedObject.getName());
                    panel.sword2.rotation = recievedObject.getSwordRotation();
                    panel.setRotation2(recievedObject.rotation);
                    panel.player2.setSwordPickedup(recievedObject.swordPickedUp);
                    if (recievedObject.isClicked){
                        panel.slingshot2.player2CreateArrow(recievedObject.mouseX,recievedObject.mouseY);
                    }
                    panel.player2.setSlingshotPickedup(recievedObject.bowPickedUp);
                    if (recievedObject.minePlased){
                        panel.mine2.createMine();
                    }
                    if (recievedObject.explodet && panel.mine2.alleMinen.size() != 0 && panel.mine2.alleMinen != null){
                        panel.mine.explodemines();
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Client hat Scheiße bekommen");
                } catch (IOException e) {
                    System.out.println("Server shutdown");
                    exit(100);
                }
            }
        });
        socketThread.start();
    }
    public void sendMessage(Player player,Sword sword) {
        try {
            message = new Message(player.x, player.y,name, sword.rotation, slingshot.playerRotation,player.isSlingshotPickedup(), slingshot.mousePos.getX(), slingshot.mousePos.getY(),isClicked, player.swordPickedup, minePlased,explodet);
            outputStream.writeObject(message);
            isClicked = false;
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void setClientPanel(Panel panel){
        this.panel = panel;
    }

    public void setWeapon(Slingshot slingshot) {
        this.slingshot = slingshot;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setMinePlased(boolean minePlased) {
        this.minePlased = minePlased;
    }

    public void setExplodet(boolean explodet) {
        this.explodet = explodet;
    }
}