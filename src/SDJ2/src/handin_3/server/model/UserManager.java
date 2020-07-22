package handin_3.server.model;

import handin_3.shared.ClientCallBack;

import java.rmi.RemoteException;

public interface UserManager {
    void registerUser(String name, ClientCallBack client) throws RemoteException;

    void removeUser(ClientCallBack client) throws RemoteException;

    void getAllNicknames(ClientCallBack client) throws RemoteException;
}
