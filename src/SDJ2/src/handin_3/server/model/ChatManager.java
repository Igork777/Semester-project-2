package handin_3.server.model;

import handin_3.shared.ClientCallBack;
import handin_3.shared.Subject;

import java.rmi.RemoteException;

public interface ChatManager {
    void broadcast(String message, ClientCallBack originalClient) throws RemoteException;

    void broadcast(String message, ClientCallBack originalClient, String... nickNamesClientsToWrite) throws RemoteException;
}
