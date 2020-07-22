package handin_3.client.networking;


import handin_3.shared.ClientCallBack;
import handin_3.shared.Subject;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    void addUser(String nickName) throws RemoteException;

    void sendMessage(String message) throws RemoteException;

    void sendMessage(String message, String... nicknameClients) throws RemoteException;

    void addListener(PropertyChangeListener listener) throws RemoteException;

    void addListener(String name, PropertyChangeListener listener) throws RemoteException;

    void removeListener(PropertyChangeListener listener) throws RemoteException;

    void removeListener(String name, PropertyChangeListener listener) throws RemoteException;

    void requestConnectedUsers() throws RemoteException;

    void removeUser() throws RemoteException;

}
