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
    Weapon weapon;

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Client(String host, String name, ConnectionFrame conFrame) throws IOException {
        this.name = name;
        this.conFrame = conFrame;

        conFrame.setVisible(false);
        frame = new GameFrame(this);
        socket = new Socket(host, 30000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        var socketThread = new Thread(() -> {
            while (true) {
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Message recievedObject = (Message) inputStream.readObject();
                    panel.setPlayer2(recievedObject.getX(),recievedObject.getY(),recievedObject.getName());
                    panel.sword2.rotation = recievedObject.getSwordRotation();
                    panel.setRotation2(recievedObject.rotation);
                    panel.setAllArrows(recievedObject.allArrows);
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
            outputStream.writeObject(new Message(player.x, player.y,name, sword.rotation, weapon.allArrows, weapon.playerRotation));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void setClientPanel(Panel panel){
        this.panel = panel;
    }
    public void setWeapon(Weapon wepon) {
        this.weapon = weapon;
    }
}
