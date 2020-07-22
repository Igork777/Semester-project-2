package client.viewModel;

import client.model.LocalStorageImpl;
import client.model.RegisterMusicianModel;
import javafx.beans.property.SimpleStringProperty;

public class RegisterMusicianViewModel {
    private RegisterMusicianModel registerMusicianModel;
    private SimpleStringProperty nickname;
    private SimpleStringProperty error;
    private SimpleStringProperty bio;

    /**
     * Constructor
     *
     * @param registerMusicianModel
     */
    public RegisterMusicianViewModel(RegisterMusicianModel registerMusicianModel) {
        this.registerMusicianModel = registerMusicianModel;
        this.nickname = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        this.bio = new SimpleStringProperty();
    }

    public SimpleStringProperty bioProperty() {
        return bio;
    }


    /**
     * Adds an instrument to the current Musician
     *
     * @param instrument
     * @param expertise
     * @return
     */
    public boolean addInstrument(String instrument, float expertise) {
        if (instrument != null) {
            try {
                return registerMusicianModel.addInstrument(instrument, expertise);
            } catch (IllegalArgumentException e) {
                error.set(e.getMessage());
            }
        }
        return false;
    }

    /**
     * getter for the nickname StringProperty
     *
     * @return
     */
    public SimpleStringProperty nicknameProperty() {
        return this.nickname;
    }

    /**
     * getter for the error StringProperty
     *
     * @return
     */
    public SimpleStringProperty errorProperty() {
        return error;
    }


    /**
     * removes the given instrument
     *
     * @param instrumentName
     * @return
     */
    public boolean removeInstrument(String instrumentName) {
        if (instrumentName != null) {
            return registerMusicianModel.removeInstrument(instrumentName);
        }
        return false;
    }

    /**
     * Method triggered after user clicks Done button
     *
     * @param nickname
     * @return
     */
    public boolean registerMusician(String nickname, String bio) {
        try {
            return registerMusicianModel.register(nickname, bio);
        } catch (IllegalArgumentException e) {
            error.set(e.getMessage());
        }
        return false;
    }
}
