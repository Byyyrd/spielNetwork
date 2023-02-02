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
        frame = new GameFrame(this,coop);
        socket = new Socket(host, 30000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        var socketThread = new Thread(() -> {
            while (true) {
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Message receivedObject = (Message) inputStream.readObject();
                    panel.setPlayer2(receivedObject.getX(), receivedObject.getY(), receivedObject.getName());
                    panel.sword2.rotation = receivedObject.getSwordRotation();
                    panel.setRotation2(receivedObject.rotation);
                    panel.player2.setSwordEquipped(receivedObject.swordPickedUp);
                    if (receivedObject.isClicked){
                        panel.bow2.player2CreateArrow(receivedObject.mouseX,receivedObject.mouseY);
                    }
                    panel.player2.setBowEquipped(receivedObject.bowPickedUp);
                    if (receivedObject.minePlaced){
                        panel.mine2.createMine();
                    }
                    if (receivedObject.exploded && panel.mine2.allMines.size() != 0){
                        panel.mine2.time = 1;
                        panel.mine.explodeMines();
                    }
                    if(!receivedObject.message.equals("")) {
                        panel.messageInput.setText(receivedObject.getName() + ": " + receivedObject.message + "\n" + panel.messageInput.getText());
                    }
                    panel.ui.p2normHp = receivedObject.normHp;
                    panel.ui.death2 = receivedObject.death;
                    panel.player2.swordDamage = receivedObject.swordDamage;
                    panel.player2.bowDamage = receivedObject.bowDamage;
                    panel.player2.mineDamage = receivedObject.mineDamage;
                    panel.player2.mineTime = receivedObject.mineTime;
                    panel.player2.maxMines = receivedObject.maxMines;
                    panel.fist1.x2 = receivedObject.fistX;
                    panel.fist2.y2 = receivedObject.fistY;
                    panel.player2.fistEquipped = receivedObject.fistEquipped;
                    panel.sparned = receivedObject.sparned;
                    panel.player2.controled = receivedObject.controled;
                } catch (ClassNotFoundException e) {
                    System.out.println("Client hat Scheiße bekommen");
                } catch (IOException e) {
                    System.out.println("Server shutdown");
                    panel.serverDown = true;
                    exit(100);
                }
            }
        });
        socketThread.start();
    }
    public void sendMessage(Player player,Sword sword) {
        try {
            if(!panel.message.equals("")) {
                panel.messageInput.setText(name + ": " + panel.message + "\n" + panel.messageInput.getText());
            }
            message = new Message(player.x, player.y,name, sword.rotation, bow.playerRotation,player.isBowEquipped(), bow.mousePos.getX(), bow.mousePos.getY(),isClicked, player.swordEquipped, minePlaced, exploded,panel.message, panel.ui.normHp, panel.ui.death, player.swordDamage + panel.inventory.getSwordDamage(), player.bowDamage + panel.inventory.getBowDamage(), player.mineDamage + panel.inventory.getMineDamage(), player.mineTime + panel.inventory.getMineTime(), player.maxMines + panel.inventory.getMaxMines(), panel.fist1.x, panel.fist1.y, panel.player1.fistEquipped, panel.sparned, panel.player1.controled);
            if(!panel.serverDown) {
                outputStream.writeObject(message);
            }
            isClicked = false;
            panel.send = false;
            panel.sparned = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setClientPanel(Panel panel){
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