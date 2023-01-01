package hr.algebra.java2.thread;

import hr.algebra.java2.jndi.JndiHelper;
import hr.algebra.java2.jndi.ServerConfigurationKey;
import hr.algebra.java2.model.GameStateDto;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements  Runnable{

    private GameStateDto gameStateDto;

    public ClientThread(GameStateDto gameStateDto) {
        this.gameStateDto = gameStateDto;
    }

    @Override
    public void run() {
        int CLIENT_SOCKET_PORT;
        try {
            CLIENT_SOCKET_PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(ServerConfigurationKey.SERVER_CLIENT_SOCKET_PORT));
        } catch (NamingException | IOException e) {
            throw new RuntimeException(e);
        }
        try (ServerSocket serverSocket = new ServerSocket(CLIENT_SOCKET_PORT)) {
            System.err.println("Client #1 listening on port: " + serverSocket.getLocalPort());

            while (true) {

                System.out.println("Client thread started and waiting for server response!");

                Socket clientSocket = serverSocket.accept();

                System.out.println("Server message accepted!");

                //System.out.println("Server message accepted at " + LocalDateTime.now());

                try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream((clientSocket.getOutputStream()))) {
                    GameStateDto newGameStateDto = (GameStateDto) ois.readObject();
                    System.out.println("Received new game state DTO with the name: " + newGameStateDto.getName());
                    System.out.println("New game state loaded!");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public GameStateDto getGameStateDto() {
        return gameStateDto;
    }

    public void setGameStateDto(GameStateDto gameStateDto) {
        this.gameStateDto = gameStateDto;
    }
}
