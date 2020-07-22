package shared.wrappers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Container for the data of the User
 */
public class User implements Serializable
{
    private String nickname, password, city;
    private String dateOfBirth;
    private int userId;
    private String region;

    /**
     * Constructor which receives loginName and password
     * @param LoginName login name
     * @param password password
     */
    public User(String LoginName, String password) {
        this.nickname = LoginName;
        this.password = password;
    }

    /**
     * Constructor which receives loginName, password, dateOfBirth, city
     * @param LoginName login name
     * @param password password
     * @param dateOfBirth local Date
     * @param city city
     */
    public User(String LoginName, String password, Date dateOfBirth, String city) {
        this(LoginName, password);
        this.city = city;
        this.dateOfBirth = dateOfBirth+"";
    }

    public User(String nickname, String password, LocalDate dateOfBirth, String city) {
        this.nickname = nickname;
        this.password = password;
        this.dateOfBirth = dateOfBirth+"";
        this.city = city;
    }

    public User() { }

    /**
     * Assigns the nickname to the local nickname
     * @param nickname passed nickname
     */
    public void setLoginName(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Assigns password to the local variable password
     * @param password passed password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Assigns city to the local variable city
     * @param city passed city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Assigns localDate to the local variable localDate
     * @param dateOfBirth passed localDate
     */
    public void setDateOfBirth(Date dateOfBirth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        this.dateOfBirth = simpleDateFormat.format(dateOfBirth);
    }

    /**
     * Nickname getter
     * @return nickname
     */
    public String getLoginName() {
        return nickname;
    }

    /**
     * Password getter
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * City getter
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * LocalDate getter
     * @return localDate
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setRegion(String regionName) {
        this.region = regionName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getUserId() {
        return userId;
    }

    public String getRegion() {
        return region;
    }
}
