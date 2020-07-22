package server.manager;

import server.database.LocalStorageDatabaseAccess;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface LocalStorageManager
{
    /**
     * This method fetches the user info by the nickname
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    User fetchUserInfo(String nickname);

    /**
     * This method fetches all the instruments
     *
     * @return HashMap <Integer, String>
     */
    static HashMap<Integer, String> fetchInstrumentsId() {
        return LocalStorageDatabaseAccess.fetchInstrumentsId();
    }

    Musician fetchMusicianInfo(int id);

    ArrayList<Instrument> fetchPlayedInstruments(int id);

    Band fetchBandByName(String bandName);
}
