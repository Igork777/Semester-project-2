package shared.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker
{
  public static final int MIN_LENGTH_PASSWORD = 8;
  public static final int MAX_LENGTH_PASSWORD = 30;
  public static final int MIN_LENGTH_NAME = 3;
  public static final int MAX_LENGTH_NAME = 20;

  private final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  /**
   * Here the passwords are checked for illegal characters and boolean is returned if the password is valid for use
   * @param password password chosen by the user during registration
   * @return false if the password contains illegal character, true if it does not
   */

  public boolean isValidPassword(String password)
  {
    for (int i = 0; i < password.length() ; i++)
    {
      char ch = password.charAt(i);

      if(!(is_Digit(ch)) && !(is_Letter(ch))) return false;

    }
    return true;
  }

  /**
   * Here the login names are checked for illegal characters and boolean is returned
   * stating if the login name is valid for use
   * @param loginName login name chosen by the user during registration
   * @return false if the login name contains illegal character, true if it does not
   */
  public boolean isValidName(String loginName)
  {
    for (int i = 0; i < loginName.length(); i++)
    {
      char ch = loginName.charAt(i);
      if(!(is_Digit(ch)) && !(is_Letter(ch)) && !(is_underscore(ch))) return false;
    }
    return true;
  }

  /**
   * Here the full names are checked for illegal characters and boolean returned stating
   * if the login name is valid for use
   * @param fullName - full name chosen by the organizer
   * @return false if full name contains illegal character, true if it does not
   */

  public boolean isValidFullName(String fullName)
  {
    for (int i = 0; i < fullName.length(); i++)
    {
      char ch = fullName.charAt(i);
      if(!(is_Digit(ch)) && !(is_Letter(ch)) && !(is_WhiteSpace(ch))) return false;
    }
    return true;
  }

  /**
   * Here the telephone numbers are checked for validity
   * @param telNo - telephone number input by the organizer
   * @return true if valid, false if invalid
   */

  public boolean isValidTelNo(String telNo)
  {
    telNo = telNo.replace(" ", "");

    if(telNo.length() != 8)
    {
      return false;
    }
    for (int i = 0; i < 8; i++)
    {
      if (!(is_Digit(telNo.charAt(i))))
      {
        return false;
      }
    }
      return true;
  }

  /**
   * Here is a check that ensures that the email has a valid format.
   * @param email - email input by the organizer
   * @return true if email has a valid format, false if invalid
   */
  public boolean isValidEmail(String email)
  {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Here the biography is checked for length
   * @param bio - biography specified by the musician or organizer
   * @return true if biography has 200 characters or below, otherwise false
   */

  public boolean isValidBio(String bio)
  {
    return bio.length() <= 400;
  }

  /**
   * Method checking whether char is a letter
   * @param ch checked character
   * @return true if char is a letter, otherwise false
   */

  public boolean is_Letter(char ch)
  {
    ch = Character.toUpperCase(ch);
    return (ch >= 'A' && ch <= 'Z');
  }

  /**
   * Method checking whether character is a digit
   * @param ch checked character
   * @return true if char is a digit, otherwise false
   */
  public boolean is_Digit(char ch)
  {
    return (ch >= '0' && ch <= '9');
  }

  /**
   * Method checking whether character is a whitespace
   * @param ch checked character
   * @return true if character is a whitespace, otherwise false
   */

  public boolean is_WhiteSpace(char ch)
  {
    return (ch == ' ');
  }

  /**
   * Method checking whether character is an underscore
   * @param ch checked character
   * @return true if character is an underscore, otherwise false
   */

  public boolean is_underscore(char ch)
  {
    return (ch == '_');
  }
}
