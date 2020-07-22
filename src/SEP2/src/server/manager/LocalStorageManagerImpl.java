package server.manager;

import server.database.LocalStorageDatabaseAccess;
import server.database.LocalStorageDatabaseImpl;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.ArrayList;

public class LocalStorageManagerImpl implements LocalStorageManager
{
    private LocalStorageDatabaseAccess localStorageDatabaseAccess;

    /**
     * Constructor which initializes localStorageDatabaseAccess
     */
    public LocalStorageManagerImpl()
    {
        this.localStorageDatabaseAccess = new LocalStorageDatabaseImpl();
    }

    /**
     * This method fetches the user info by the nickname
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    @Override
    public User fetchUserInfo(String nickname) {
        return localStorageDatabaseAccess.fetchUserInfo(nickname);
    }

    /**
     * This method fetches all the instruments
     * @return HashMap <Integer, String>
     */

    @Override
    public Musician fetchMusicianInfo(int id) {
        return localStorageDatabaseAccess.fetchMusicianInfo(id);
    }

    @Override
    public ArrayList<Instrument> fetchPlayedInstruments(int id) {
        return localStorageDatabaseAccess.fetchPlayedInstruments(id);
    }

    @Override
    public Band fetchBandByName(String bandName) {
        return localStorageDatabaseAccess.fetchBandByName(bandName);
    }
}
