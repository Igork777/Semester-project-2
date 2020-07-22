package client.model;

public interface RegisterMusicianModel {
    /**
     * register the musician within the system
     * @param nickname nickname
     * @param bio
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    boolean register(String nickname, String bio) throws IllegalArgumentException;

    /**
     * Adds instrument to the current Musician Object
     * @param instrument id of instrument to add
     * @param expertise amount of years playing this instrument
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    boolean addInstrument(String instrument, float expertise) throws IllegalArgumentException;

    /**
     * Removes an instrument from the current instrument list
     * @param instrument id of instrument to remove
     * @return true = success, false = fail
     */
    boolean removeInstrument(String instrument);


}
