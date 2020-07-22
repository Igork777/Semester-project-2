package server.manager;

import shared.wrappers.User;

import java.util.ArrayList;


public interface RegisterManager
{
  /**
   * Method that returns the reply to inquiry to register the user
   * @param user which has to be registered
   * @return message which will be represented in the view controller
   */
  String register(User user);

}
