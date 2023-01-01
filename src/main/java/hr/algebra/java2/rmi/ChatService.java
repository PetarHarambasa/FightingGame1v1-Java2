package hr.algebra.java2.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
    String REMOTE_OBJECT_NAME = "hr.algebra.java2.rmi.chat_service";

    void sendMessage(String newMessage) throws RemoteException;

    List<String> getChatHistory() throws RemoteException;
}
