package shared.networking;

import shared.wrappers.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServer extends Remote {
    /**
     * method checks if user inputted correct nickname and password or not
     *
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     * @throws RemoteException is thrown because of the extension of Remote interface
     */
    boolean login(User user) throws RemoteException;

}
