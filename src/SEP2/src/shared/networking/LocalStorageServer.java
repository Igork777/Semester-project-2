package shared.networking;

import server.manager.LocalStorageManager;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LocalStorageServer extends Remote
{
    /**
     * This method fetches the user info by the nickname
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    User fetchUserInfo(String nickname) throws RemoteException;

    /**
     * This method fetches all the instruments
     *
     * @return HashMap <Integer, String>
     */
    static HashMap<Integer, String> fetchInstrumentsId() throws RemoteException {
        return LocalStorageManager.fetchInstrumentsId();
    }

    Musician fetchMusicianInfo(int id) throws RemoteException;

    ArrayList<Instrument> fetchPlayedInstruments(int id) throws RemoteException;

    Band fetchBandByName(String bandName) throws RemoteException;
}
