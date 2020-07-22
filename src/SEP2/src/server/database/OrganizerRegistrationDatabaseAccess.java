package server.database;

import shared.wrappers.Organizer;

public interface OrganizerRegistrationDatabaseAccess
{
  /**
   * Method which attempts to add organizer to the database
   * @param organizer - organizer to be added to the database
   * @return message "Happy organizing!" if registration was successful, "Unable to register" if
   * the registration was not successful
   */
  String registerOrganizer(Organizer organizer);

  /**
   * Method which checks whether an user has already registered as an organizer
   * @param organizer - organized which is checked for existing profile
   * @return true if organizer is registered in the system, otherwise false
   */
  boolean isOrganizerRegisteredForUser(Organizer organizer);
}
