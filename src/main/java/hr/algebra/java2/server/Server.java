package hr.algebra.java2.server;

import hr.algebra.java2.jndi.JndiHelper;
import hr.algebra.java2.model.GameStateDto;
import hr.algebra.java2.model.SerializablePlayer;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static hr.algebra.java2.jndi.ServerConfigurationKey.SERVER_CLIENT_SOCKET_PORT;
import static hr.algebra.java2.jndi.ServerConfigurationKey.SERVER_PORT;


public class Server {
    public static final String HOST = "localhost";

    private static Map<Integer, Socket> playersData;

    public static void main(String[] args) throws NamingException, IOException {
        playersData = new HashMap<>();
        acceptRequests();
    }

    private static void acceptRequests() throws NamingException, IOException {
        int PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(SERVER_PORT));
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected from port: " + clientSocket.getPort());
                new Thread(() -> processSerializableClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processSerializableClient(Socket clientSocket) {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            SerializablePlayer serializablePlayerInfo = (SerializablePlayer) ois.readObject();


            System.out.println("Received login message: " + serializablePlayerInfo.getPlayerName() + " "
                    + serializablePlayerInfo.getCharacterClass() + " " + serializablePlayerInfo.getHealthPoints() + "HP");

            oos.writeObject("Login success");
            System.out.println("Server sent login success message to " + serializablePlayerInfo.getPlayerName() + " "
                    + serializablePlayerInfo.getCharacterClass()
                    + " !");

            playersData.put(clientSocket.getPort(), clientSocket);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer counter = 0;

        while (true) {

            try {
                int CLIENT_SOCKET_PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(SERVER_CLIENT_SOCKET_PORT));

                Socket serverClientSocket = new Socket(Server.HOST, CLIENT_SOCKET_PORT);

                ObjectOutputStream serverClientObjectOutputStream
                        = new ObjectOutputStream(serverClientSocket.getOutputStream());

                //System.out.println("Server sent message at: " + LocalDateTime.now());

                GameStateDto gameStateDto = new GameStateDto();
                gameStateDto.setName(String.valueOf(counter++));
                serverClientObjectOutputStream.writeObject(gameStateDto);


                Thread.sleep(2000);
            } catch (IOException | InterruptedException | NamingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
