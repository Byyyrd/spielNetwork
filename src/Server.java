import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    List<Socket> socketList = new LinkedList<>();


    public Server() throws IOException {
        System.out.println("Server started");
        var serverSocket = new ServerSocket(30000);
        while (true) {
            var socket = serverSocket.accept();
            socketList.add(socket);
            System.out.println("Connected");

            var socketThread = new Thread(() -> {
                System.out.println("new Threat");
                ObjectInputStream inputStream;
                ObjectOutputStream outputstream;
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    while (true) {

                        var recievedObject = inputStream.readObject();
                        for (Socket value : socketList) {
                            if (value != socket) {
                                try {
                                    outputstream = new ObjectOutputStream(value.getOutputStream());
                                    outputstream.writeObject(recievedObject);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Client hat Scheiße geschickt");
                } catch (IOException e) {
                    System.out.println("Lost one Client");
                } finally {
                    socketList.remove(socket);
                }

            });
            socketThread.start();
        }
    }
}
