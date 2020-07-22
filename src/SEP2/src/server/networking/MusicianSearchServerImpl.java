package server.networking;

import server.manager.MusicianSearchManager;
import shared.networking.MusicianSearchServer;
import shared.wrappers.Musician;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MusicianSearchServerImpl implements MusicianSearchServer {
    private MusicianSearchManager musicianSearchManager;
    public MusicianSearchServerImpl(MusicianSearchManager musicianSearchManager, Registry registry)
    {
        try
        {
            this.musicianSearchManager = musicianSearchManager;
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("MusicianSearchServer", this);
            System.out.println("Musician search server started!");
        }
        catch (RemoteException | AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override

    public ArrayList<Musician> searchForMusicians(Musician musician) throws RemoteException {
        return musicianSearchManager.searchForMusicians(musician);
    }
}
