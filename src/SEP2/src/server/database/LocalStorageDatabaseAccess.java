package server.database;

import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface LocalStorageDatabaseAccess
{
    /**
     * Method which returns id, nickname, dateOfBirth city and region
     * base on the nickname provided
     * Try with resources is used in order to close Connection and PreparedStatement after success or the failure of the query
     * @param nickname argument, that is used for creation of the query
     * @return Hashmap which the name of the attribute as key and value as the value of Hashmap
     */
    User fetchUserInfo(String nickname);

    /**
     * Method, which returns all the instruments from database
     * Try with resources is used in order to close Connection and  PreparedStatement after success or the failure of the query
     *
     * @return Hashmap which the id as key and value as the value of Hashmap
     */
    static HashMap<Integer, String> fetchInstrumentsId() {
        return DataBaseAccess.getInstance().fetchInstrumentsId();
    }

    Musician fetchMusicianInfo(int id);

    ArrayList<Instrument> fetchPlayedInstruments(int id);

    Band fetchBandByName(String bandName);
}
