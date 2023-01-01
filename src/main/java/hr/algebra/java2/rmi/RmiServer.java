package hr.algebra.java2.rmi;

import hr.algebra.java2.jndi.JndiHelper;
import hr.algebra.java2.jndi.ServerConfigurationKey;

import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {
    private static final int RANDOM_PORT_HINT = 0;

    public static void main(String[] args) {
        try {

            String rmiPortString = JndiHelper.getConfigurationParameter(
                    ServerConfigurationKey.RMI_SERVER_PORT);
            Integer rmiPort = Integer.parseInt(rmiPortString);

            Registry registry = LocateRegistry.createRegistry(rmiPort);
            ChatService chatService = new ChatServiceImpl();
            ChatService skeleton = (ChatService) UnicastRemoteObject.exportObject(chatService, RANDOM_PORT_HINT);
            registry.rebind(ChatService.REMOTE_OBJECT_NAME, skeleton);
            System.err.println("Object registered in RMI registry");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NamingException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
