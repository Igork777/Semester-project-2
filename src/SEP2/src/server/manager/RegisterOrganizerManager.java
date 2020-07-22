package server.manager;

import shared.wrappers.Organizer;

public interface RegisterOrganizerManager
{
  /**
   * This method registers the user as an organizer
   * @param organizer - wrapper for organizer data
   * @return message stating whether the registration was successful
   */

  String registerOrganizer(Organizer organizer);
}
