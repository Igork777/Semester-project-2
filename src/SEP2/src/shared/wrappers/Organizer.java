package shared.wrappers;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Wrapper for organizer registration
 */

public class Organizer implements Serializable
{
  private String fullName, biography, email, telNo, city, region;
  private int userId;
  private LocalDate dateOfBirth;

  /**
   * Empty constructor
   */
  public Organizer() {
  }

  /**
   * Constructor setting all Organizer fields
   * @param fullName of the organizer
   * @param biography of the organizer
   * @param userId of the organizer
   * @param email of the organizer
   * @param telNo of the organizer
   */
  public Organizer(String fullName, String biography, int userId, String email,String telNo)
  {
    this.fullName = fullName;
    this.biography = biography;
    this.userId = userId;
    this.email = email;
    this.telNo = telNo;
  }

  /**
   * Constructor setting the required information of an Organizer
   */
  public Organizer(String fullName, String bio, String email, String telNo) {
    this.fullName = fullName;
    this.biography = bio;
    this.email = email;
    this.telNo = telNo;
  }

  /**
   * Getter for full name
   * @return full name of the organizer
   */
  public String getFullName()
  {
    return fullName;
  }

  /**
   * Setter for full name
   * @param fullName full name of the organizer
   */
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  /**
   * Getter for biography
   * @return biography written by the organizer
   */
  public String getBiography()
  {
    return biography;
  }

  /**
   * Setter for biography
   * @param biography biography written by the organizer
   */
  public void setBiography(String biography)
  {
    this.biography = biography;
  }

  /**
   * Getter for user id
   * @return the user id of the organizer
   */
  public int getUserId()
  {
    return userId;
  }

  /**
   * sets an user id of an organizer
   * @param userId of an organizer
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Getter for email
   * @return the email of the organizer
   */

  public String getEmail()
  {
    return email;
  }

  /**
   * Setter for the email
   * @param email the email of the organizer
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Getter of telephone number
   * @return the telephone number of the organizer
   */
  public String getTelNo()
  {
    return telNo;
  }

  /**
   * Setter of the telephone number
   * @param telNo the telephone number of the organizer
   */
  public void setTelNo(String telNo)
  {
    this.telNo = telNo;
  }

  /**
   * Setter of a city
   * @param city of an organizer
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter of a city
   * @return String city of an organizer
   */
  public String getCity() {
    return city;
  }

  /**
   * Setter of organizer region
   * @param region of organizer's city
   */
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * Getter of an organizer's region
   * @return String organizer's region
   */
  public String getRegion() {
    return region;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String toString(){
    return "Full Name: "+fullName+"\nRegion: "+region;
  }
}
