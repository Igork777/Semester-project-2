package handin_3.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    void addUser(String name, ClientCallBack client) throws RemoteException;

    void removeUser(ClientCallBack client) throws RemoteException;

    void broadcastMessage(String string, ClientCallBack client) throws RemoteException;

    void broadcastMessage(String string, ClientCallBack client, String... nicknameClients) throws RemoteException;

    void requestConnectedUsers(ClientCallBack client) throws RemoteException;
}
