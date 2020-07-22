package client.networking;

import shared.networking.MusicianSearchServer;
import shared.networking.OrganizerProfileServer;
import shared.wrappers.Musician;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MusicianSearchClientImpl implements MusicianSearchClient
{
    private MusicianSearchServer musicianSearchServer;

    public MusicianSearchClientImpl() {
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            musicianSearchServer = (MusicianSearchServer) registry.lookup("MusicianSearchServer");
        }
        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Musician> searchForMusicians(Musician musician) {
        try {
            return musicianSearchServer.searchForMusicians(musician);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
