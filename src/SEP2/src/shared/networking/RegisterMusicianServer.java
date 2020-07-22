package shared.networking;

import shared.wrappers.Musician;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterMusicianServer extends Remote {
    /**
     * register the musician within the system
     * @param musician musician object to register within the system
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    boolean register(Musician musician) throws RemoteException;
}
