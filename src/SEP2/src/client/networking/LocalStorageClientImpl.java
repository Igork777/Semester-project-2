package client.networking;

import shared.networking.LocalStorageServer;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class LocalStorageClientImpl implements LocalStorageClient
{
    private LocalStorageServer localStorageServer;

    /**
     * Constructor where the client connects to the certain server
     */
    public LocalStorageClientImpl()
    {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.localStorageServer = (LocalStorageServer) registry.lookup("LocalStorageServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method fetches the user info by the nickname
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    @Override
    public User fetchUserInfo(String nickname) {
        try {
            return localStorageServer.fetchUserInfo(nickname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public Musician fetchMusicianInfo(int id) {
        try {
            return localStorageServer.fetchMusicianInfo(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public ArrayList<Instrument> fetchPlayedInstruments(int id) {
        try {
            return localStorageServer.fetchPlayedInstruments(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public Band fetchBandByName(String bandName) {
        try {
            return localStorageServer.fetchBandByName(bandName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
