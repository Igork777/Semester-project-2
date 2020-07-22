package client.networking;

import shared.wrappers.Organizer;

public interface RegisterOrganizerClient
{
  /**
   * This method registers the user as an organizer
   * @param organizer - wrapper for organizer data
   * @return message stating whether the registration was successful
   */
  String registerOrganizer(Organizer organizer);
}
