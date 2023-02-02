import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.exit;

public class Client {
    String name;
    Socket socket;
    GameFrame frame;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    ConnectionFrame conFrame;
    Panel panel;
    Bow bow;
    Message message;
    boolean isClicked = false;
    boolean minePlaced = false;
    boolean exploded = false;

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Client(String host, String name, ConnectionFrame conFrame, boolean coop) throws IOException {
        this.name = name;
        this.conFrame = conFrame;

        conFrame.setVisible(true);
        frame = new GameFrame(this, coop);
        socket = new Socket(host, 30000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        var socketThread = new Thread(() -> {
            while (true) {
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Object receivedObject = inputStream.readObject();
                    if (receivedObject.getClass() == Message.class) {
                        Message inMessage = (Message)receivedObject; 
                        panel.setPlayer2(inMessage.getX(), inMessage.getY(), inMessage.getName());
                        panel.sword2.rotation = inMessage.getSwordRotation();
                        panel.setRotation2(inMessage.rotation);
                        panel.player2.setSwordEquipped(inMessage.swordPickedUp);
                        if (inMessage.isClicked) {
                            panel.bow2.player2CreateArrow(inMessage.mouseX, inMessage.mouseY);
                        }
                        panel.player2.setBowEquipped(inMessage.bowPickedUp);
                        if (inMessage.minePlaced) {
                            panel.mine2.createMine();
                        }
                        if (inMessage.exploded && panel.mine2.allMines.size() != 0) {
                            panel.mine2.time = 1;
                            panel.mine.explodeMines();
                        }
                        if (!inMessage.message.equals("")) {
                            panel.messageInput.setText(inMessage.getName() + ": " + inMessage.message + "\n" + panel.messageInput.getText());
                        }
                        panel.ui.p2normHp = inMessage.normHp;
                        panel.ui.death2 = inMessage.death;
                        panel.player2.swordDamage = inMessage.swordDamage;
                        panel.player2.bowDamage = inMessage.bowDamage;
                        panel.player2.mineDamage = inMessage.mineDamage;
                        panel.player2.mineTime = inMessage.mineTime;
                        panel.player2.maxMines = inMessage.maxMines;
                        panel.fist1.x2 = inMessage.fistX;
                        panel.fist2.y2 = inMessage.fistY;
                        panel.player2.fistEquipped = inMessage.fistEquipped;
                        panel.player2.controled = inMessage.controled;
                    }
                    if (receivedObject.getClass() == ArrayList.class) {
                        ArrayList inArray = (ArrayList) receivedObject;
                        System.out.println(inArray);
                    }
                    if (receivedObject.getClass() == Player.class){
                        Player inPlayer  = (Player) receivedObject;
                        System.out.println(inPlayer);
                    }
                    } catch(ClassNotFoundException e){
                        System.out.println("Client hat Scheiße bekommen");
                    } catch(IOException e){
                        System.out.println("Server shutdown");
                        panel.serverDown = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    exit(100);
                    }
            }
        });
        socketThread.start();
    }

    public void sendArray(ArrayList<Integer> array) {
        try {
            outputStream.writeObject(array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(Player player, Sword sword) {
        try {
            if (!panel.message.equals("")) {
                panel.messageInput.setText(name + ": " + panel.message + "\n" + panel.messageInput.getText());
            }
            message = new Message(player.x, player.y, name, sword.rotation, bow.playerRotation, player.isBowEquipped(), bow.mousePos.getX(), bow.mousePos.getY(), isClicked, player.swordEquipped, minePlaced, exploded, panel.message, panel.ui.normHp, panel.ui.death, player.swordDamage + panel.inventory.getSwordDamage(), player.bowDamage + panel.inventory.getBowDamage(), player.mineDamage + panel.inventory.getMineDamage(), player.mineTime + panel.inventory.getMineTime(), player.maxMines + panel.inventory.getMaxMines(), panel.fist1.x, panel.fist1.y, panel.player1.fistEquipped, panel.sparned, panel.player1.controled);
            if (!panel.serverDown) {
                outputStream.writeObject(message);
            }
            isClicked = false;
            panel.send = false;
            panel.sparned = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setClientPanel(Panel panel) {
        this.panel = panel;
    }

    public void setWeapon(Bow bow) {
        this.bow = bow;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setMinePlaced(boolean minePlaced) {
        this.minePlaced = minePlaced;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
}