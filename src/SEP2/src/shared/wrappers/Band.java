package shared.wrappers;

import java.io.Serializable;
import java.time.LocalDate;

public class Band implements Serializable
{
    private String nameOfTheBand;
    private String email;
    private String telephone;
    private String genre;
    private String region;
    private int genreId;
    private int cityId;
    private LocalDate date;
    private int bandAdministratorId;
    private String bio;
    private String cityName;
    private int bandId;
    private String bandAdministratorName;
    private String dateStringRepresentation;


    /**
     * Constructor for the band wrapper
     * @param nameOfTheBand - name of the band
     * @param email - email of the band
     * @param telephone - telephone of the band
     * @param date - foundation date of the band
     * @param bio - biography of the band
     */
    public Band(String nameOfTheBand, String email, String telephone, int genreId,int cityId, LocalDate date, String bio, int bandAdministratorId)
    {
        this.nameOfTheBand = nameOfTheBand;
        this.email = email;
        this.telephone = telephone;
        this.genreId = genreId;
        this.date = date;
        dateStringRepresentation = date.toString();
        this.cityId = cityId;
        this.bio = bio;
        this.bandAdministratorId = bandAdministratorId;
    }



    public Band()
    {
    }

    public Band(String nameOfTheBand, String cityName, int bandId)
    {
        this.nameOfTheBand = nameOfTheBand;
        this.cityName = cityName;
        this.bandId = bandId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setBandAdministratorName(String bandAdministratorName) {
        this.bandAdministratorName = bandAdministratorName;
    }

    public void setDateStringRepresentation(String dateStringRepresentation) {
        this.dateStringRepresentation = dateStringRepresentation;
    }

    public String getDateStringRepresentation() {
        return dateStringRepresentation;
    }

    public String getBandAdministratorName() {
        return bandAdministratorName;
    }

    public int getBandAdministratorId() {
        return bandAdministratorId;
    }

    public void setBandAdministratorId(int bandAdministratorId) {
        this.bandAdministratorId = bandAdministratorId;
    }

    /**
     * Getter for the id of the city
     * @return city id
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Setter for the city id
     * @param cityId id of the city
     */
    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    /**
     * Getter for the name of the band
     * @return name of the band
     */

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getNameOfTheBand() {
        return nameOfTheBand;
    }

    /**
     * Setter for the name of the band
     * @param nameOfTheBand name of the band
     */
    public void setNameOfBand(String nameOfTheBand)
    {
        this.nameOfTheBand = nameOfTheBand;
    }

    /**
     * Getter for the email of the band
     * @return email of the band
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email of the band
     * @param email email of the band
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Getter for the telephone number of the band
     * @return telephone number of the band
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter for the telephone number of the band
     * @param telephone telephone number
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * Getter for the genre of the genre of the band
     * @return genre of the band
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter for the genre of the band
     * @param genre genre of the band
     */
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    /**
     * Getter for the foundation date
     * @return date of the foundation date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for the foundation date
     * @param date foundation date
     */
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    /**
     * Getter for the biography
     * @return biography of the band
     */
    public String getBio()
    {
        return bio;
    }

    /**
     * Setter for the biography
     * @param bio biography of the band
     */
    public void setBio(String bio)
    {
        this.bio = bio;
    }

    /**
     * Getter for the genre id
     * @return genre id
     */
    public int getGenreId()
    {
        return genreId;
    }

    /**
     * Setter for the genre id
     * @param genreId genre id
     */
    public void setGenreId(int genreId)
    {
        this.genreId = genreId;
    }

    /**
     * Setter for the band id
     * @param bandId - id of the band
     */
    public void setBandId(int bandId)
    {
        this.bandId = bandId;
    }

    /**
     * Getter for the id of the band
     * @return id of the band
     */
    public int getBandId()
    {
        return bandId;
    }

    /**
     * Getter for the region the band is located in
     * @return region the band is located in
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * Setter for the region the band is located in
     * @param region region the band is located in
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
}
