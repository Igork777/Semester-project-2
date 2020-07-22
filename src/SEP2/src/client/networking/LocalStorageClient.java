package client.networking;

import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.ArrayList;

public interface LocalStorageClient
{
    /**
     * This method fetches the user info by the nickname
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    User fetchUserInfo(String nickname);

    Musician fetchMusicianInfo(int id);

    ArrayList<Instrument> fetchPlayedInstruments(int id);

    Band fetchBandByName(String bandName);
}
