package client.networking;

import shared.wrappers.User;

import java.util.ArrayList;

public interface RegisterClient {
  /**
   * The method wich registers the user
   * @param user wrapper class
   * @return the answer if user was registered
   */
  String register(User user);

}
