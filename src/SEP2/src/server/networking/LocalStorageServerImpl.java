package server.networking;
import server.manager.LocalStorageManager;
import shared.networking.LocalStorageServer;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LocalStorageServerImpl implements LocalStorageServer {
    private LocalStorageManager localStorageManager;

    /**
     * Constructor which assigns some localStorage to the localStorageManage
     * Registry is used in order to create a Server and to bind server name to this class.
     * @param localStorageManager
     * @param registry
     */
    public LocalStorageServerImpl(LocalStorageManager localStorageManager, Registry registry) {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            this.localStorageManager = localStorageManager;
            registry.bind("LocalStorageServer", this);
            System.out.println("Local Storage is started");
        } catch (RemoteException | AlreadyBoundException e) {
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
        return localStorageManager.fetchUserInfo(nickname);
    }
    /**
     * This method fetches all the instruments
     * @return HashMap <Integer, String>
     */

    @Override
    public Musician fetchMusicianInfo(int id) {
        return localStorageManager.fetchMusicianInfo(id);
    }

    @Override
    public ArrayList<Instrument> fetchPlayedInstruments(int id) {
        return localStorageManager.fetchPlayedInstruments(id);
    }

    @Override
    public Band fetchBandByName(String bandName)  {
        return localStorageManager.fetchBandByName(bandName);
    }
}
