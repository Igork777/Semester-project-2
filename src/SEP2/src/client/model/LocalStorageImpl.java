package client.model;

import client.core.ClientFactory;
import client.networking.LocalStorageClient;
import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.ArrayList;

/**
 * This class contains current user information.
 */
public class LocalStorageImpl {

    private static LocalStorageImpl localStorageImpl;
    private LocalStorageClient localStorageClient;
    private User currentUser;
    private Musician musician;
    private ArrayList<Instrument> playedInstrumentsList = new ArrayList<>();
    private Band localBand;


    public Musician getMusician() {
        return musician;
    }


    public ArrayList<Instrument> getPlayedInstrumentsList() {
        return playedInstrumentsList;
    }

    /**
     * Constructor of the LocalStorageImpl class, where localStorage client is passed as the parameter
     *
     * @param localStorageClient argument, which will be assigned to the local variable
     */
    private LocalStorageImpl(LocalStorageClient localStorageClient) {
        this.localStorageClient = localStorageClient;
    }

    /**
     * this method return the variable of its class. If this variable is equal to null - new variable will be create but
     * only once
     *
     * @return localStorageImpl
     */
    public static LocalStorageImpl getInstance() {
        if (localStorageImpl == null) {
            localStorageImpl = new LocalStorageImpl(new ClientFactory().getLocalStorageClient());
        }
        return localStorageImpl;
    }

    /**
     * This method fetches the user info by the nickname
     *
     * @param nickname the search in the database will be executed using this argument
     * @return HashMap <String, String> of users
     */
    public User setCurrentUserByNickname(String nickname) {
        currentUser = localStorageClient.fetchUserInfo(nickname);
        return currentUser;
    }

    public ArrayList<Instrument> fetchPlayedInstruments(int id) {
        return playedInstrumentsList = localStorageClient.fetchPlayedInstruments(id);
    }


    public Musician fetchMusicianInfo(int id) {
        musician = localStorageClient.fetchMusicianInfo(id);
        return musician;
    }

    /**
     * This method returns map of users
     *
     * @return HashMap<String, String> of users
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * This method cleans the userMap
     */
    public void clear() {
        currentUser = null;
    }

    public void setLocalBandByName(String bandName) {
        if (localBand == null || !(localBand.getNameOfTheBand().equals(bandName))) {
            localBand = localStorageClient.fetchBandByName(bandName);
        }
    }

    public Band getLocalBand() {
        return localBand;
    }

}
