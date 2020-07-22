package shared.networking;

import shared.wrappers.Musician;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MusicianSearchServer extends Remote {
    ArrayList<Musician> searchForMusicians(Musician musician) throws RemoteException;
}
