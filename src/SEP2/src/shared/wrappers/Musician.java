package shared.wrappers;

import client.model.LocalStorageImpl;
import server.networking.LocalStorageServerImpl;
import shared.networking.LocalStorageServer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Musician implements Serializable {
    private String nickname;
    private int userId;
    private String bio;

    private String region;
    private String city;
    private int expertise;
    private String dateOfBirth;
    private HashMap<Integer, Float> instrumentsPlayed = new HashMap<>();
    private static HashMap<Integer, String> instrumentsId;

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public static HashMap getInstrumentsId()
    {
        if (instrumentsId == null || instrumentsId.isEmpty()){
            try {
                instrumentsId = LocalStorageServer.fetchInstrumentsId();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instrumentsId;
    }

    public void setInstrumentsPlayed(HashMap<Integer, Float> instrumentsPlayed) {

        this.instrumentsPlayed = instrumentsPlayed;
    }

    /**
     * returns the id for the given instrument
     * @param instrument
     * @return
     */
    public Integer idOfInstrument(String instrument){
        for(Map.Entry<Integer, String> entry : instrumentsId.entrySet()){
            if(entry.getValue().equals(instrument)){
                return entry.getKey();
            }
        }
        return -1;
    }
    /**
     * Adds instrument to the current Musician Object
     * @param instrument instrument to add
     * @param expertise amount of years playing this instrument
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    public boolean addInstrument(String instrument, float expertise) throws IllegalArgumentException {
        int instrumentId = idOfInstrument(instrument);
        if(instrumentsPlayed.containsKey(instrumentId)){
            throw new IllegalArgumentException("You have already selected this instrument. Delete the old one to add it again.");
        }
        if(instrumentId < 1){
            throw new IllegalArgumentException("Please select an instrument first.");
        }

        this.instrumentsPlayed.put(instrumentId, expertise);
        return true;
    }

    /**
     * Removes an instrument from the current instrument list
     * @param instrument instrument to remove
     * @return true = success, false = fail
     */
    public boolean removeInstrument(String instrument) {
        int instrumentId = idOfInstrument(instrument);
        this.instrumentsPlayed.remove(instrumentId);
        return true;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * a getter for the userId field
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * a setter for the userId field
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * a setter for the nickname field
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * a getter for the nickname field
     * @return
     */
    public String getNickname() {
        return nickname;
    }



    /**
     * a getter for the BIO field
     * @return
     */
    public String getBio() {
        return bio;
    }



    public String getRegion() {
        return region;
    }

    public int getExpertise() {
        return expertise;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    /**
     * a getter for the instrumentsPlayed field
     * @return
     */
    public HashMap<Integer, Float> getInstrumentsPlayed() {
        return instrumentsPlayed;
    }
}
