package client.model;

import client.networking.RegisterMusicianClient;
import shared.utils.Checker;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.util.HashMap;

public class RegisterMusicianModelImpl implements RegisterMusicianModel {
    private Musician musician = new Musician();
    private RegisterMusicianClient registerMusicianClient;

    private Checker checker = new Checker();

    /**
     * Constructor for the RegisterMusicianModelImpl class
     *
     * @param registerMusicianClient reference
     */
    public RegisterMusicianModelImpl(RegisterMusicianClient registerMusicianClient) {
        this.registerMusicianClient = registerMusicianClient;
    }

    /**
     * Adds instrument to the current Musician Object
     *
     * @param instrument id of instrument to add
     * @param expertise  amount of years playing this instrument
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    @Override
    public boolean addInstrument(String instrument, float expertise) throws IllegalArgumentException {
        if (instrument != null) {
            return this.musician.addInstrument(instrument, expertise);
        } else {
            return false;
        }
    }

    /**
     * Removes an instrument from the current instrument list
     *
     * @param instrument id of instrument to remove
     * @return true = success, false = fail
     */
    @Override
    public boolean removeInstrument(String instrument) {
        return this.musician.removeInstrument(instrument);
    }


    /**
     * register the musician within the system
     *
     * @param nickname nickname
     * @param bio
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    @Override
    public boolean register(String nickname, String bio) throws IllegalArgumentException {
        if (nickname == null || nickname.replace(" ", "").length() < 3 || nickname.length() > 20) {
            throw new IllegalArgumentException("Your artist name should have a length between 3 and 20 chars");
        }
        if(!(checker.isValidBio(bio)))
        {
            throw new IllegalArgumentException("Your bio name shouldn't have length more that 400 symbols");
        }
        if (this.musician.getInstrumentsPlayed().size() < 1) {
            throw new IllegalArgumentException("Please insert at least one instrument");
        }
        User currentUser = LocalStorageImpl.getInstance().getCurrentUser();

        int userId = currentUser.getUserId();

        if (userId < 1) {
            return false;
        }

        this.musician.setNickname(nickname);
        this.musician.setUserId(userId);
        musician.setBio(bio);
        boolean isRegistered = registerMusicianClient.register(this.musician);
        if (isRegistered) {
            LocalStorageImpl.getInstance().fetchMusicianInfo(userId);
            LocalStorageImpl.getInstance().fetchPlayedInstruments(userId);
        }
        return isRegistered;
    }
}
