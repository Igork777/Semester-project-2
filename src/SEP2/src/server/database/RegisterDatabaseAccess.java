package server.database;

import shared.wrappers.User;

import java.util.ArrayList;

public interface RegisterDatabaseAccess
{
  /**
   * Method which will be used to access database upon pressing the register button and check whether name is already in use
   */

  /**
   * Method which will be used to access database upon pressing the register button and check whether name is already in use
   * @param loginName login name of the user for his account
   * @return true if name is available for use, false if unavailable
   */

  boolean loginNameTaken(String loginName);

  /**
   * method, which registers the user
   * @param user which has to be registered
   * @return true if registration was successful
   */
  boolean register(User user);

}
