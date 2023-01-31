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
    boolean minePlaced = false;
    boolean exploded = false;
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
                    panel.player2.setSwordEquipde(recievedObject.swordPickedUp);
                    if (recievedObject.isClicked){
                        panel.slingshot2.player2CreateArrow(recievedObject.mouseX,recievedObject.mouseY);
                    }
                    panel.player2.setSlingshotEquipde(recievedObject.bowPickedUp);
                    if (recievedObject.minePlaced){
                        panel.mine2.createMine();
                    }
                    if (recievedObject.exploded && panel.mine2.allMines.size() != 0 && panel.mine2.allMines != null){
                        panel.mine2.time = 1;
                        panel.mine.explodeMines();
                    }
                    if(!recievedObject.message.equals("")) {
                        panel.messageInput.setText(recievedObject.getName() + ": " + recievedObject.message + "\n" + panel.messageInput.getText());
                    }
                    panel.ui.p2normHp = recievedObject.normHp;
                    panel.ui.death2 = recievedObject.death;
                    panel.player2.swordDamage = recievedObject.swordDamage;
                    panel.player2.slingshotDamage = recievedObject.slingshotDamage;
                    panel.player2.mineDamage = recievedObject.mineDamage;
                    panel.player2.mineTime = recievedObject.mineTime;
                    panel.player2.maxMines = recievedObject.maxMines;
                    panel.fist1.x2 = recievedObject.fistX;
                    panel.fist2.y2 = recievedObject.fistY;
                    panel.player2.fistequiped = recievedObject.fistEquipde;
                } catch (ClassNotFoundException e) {
                    System.out.println("Client hat Scheiße bekommen");
                } catch (IOException e) {
                    System.out.println("Server shutdown");
                    panel.serverDown = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //throw new RuntimeException(ex);
                    }
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
            message = new Message(player.x, player.y,name, sword.rotation, slingshot.playerRotation,player.isSlingshotEquipde(), slingshot.mousePos.getX(), slingshot.mousePos.getY(),isClicked, player.swordEquipde, minePlaced, exploded,panel.message, panel.ui.normHp, panel.ui.death, player.swordDamage + panel.inventory.getSwordDamage(), player.slingshotDamage + panel.inventory.getSlingshotDamage(), player.mineDamage + panel.inventory.getMineDamage(), player.mineTime + panel.inventory.getMineTime(), player.maxMines + panel.inventory.getMaxMines(), panel.fist1.x, panel.fist1.y, panel.player1.fistequiped);
            if(!panel.serverDown) {
                outputStream.writeObject(message);
            }
            isClicked = false;
            panel.send = false;
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

    public void setMinePlaced(boolean minePlaced) {
        this.minePlaced = minePlaced;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
}