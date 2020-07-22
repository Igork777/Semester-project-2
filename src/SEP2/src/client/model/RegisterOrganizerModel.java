package client.model;

import shared.wrappers.Organizer;

public interface RegisterOrganizerModel
{
  /**
   * This method registers the user as an organizer.
   * @param fullName - full name of the organizer
   * @param biography - biography of the organizer
   * @param email - email of the organizer
   * @param telNo - telephone number of the organizer
   * @return String message which specifies if the user was registered
   */

  String registerOrganizer(String fullName, String biography, String email, String telNo);
}
