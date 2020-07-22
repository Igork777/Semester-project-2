package server.manager;

import server.database.OrganizerRegistrationDatabaseAccess;
import server.database.OrganizerRegistrationDatabaseImpl;
import shared.wrappers.Organizer;

public class RegisterOrganizerManagerImpl implements RegisterOrganizerManager
{

  private OrganizerRegistrationDatabaseAccess organizerRegistrationDBAccess;

  /**
   * Constructor initializing the access to the database for the organizer registration
   */

  public RegisterOrganizerManagerImpl()
  {
    organizerRegistrationDBAccess = new OrganizerRegistrationDatabaseImpl();
  }

  /**
   * This method registers the user as an organizer
   * @param organizer - wrapper for organizer data
   * @return message stating whether the registration was successful
   */

  @Override public String registerOrganizer(Organizer organizer)
  {
    if(organizerRegistrationDBAccess.isOrganizerRegisteredForUser(organizer))
    {
      return "Organizer already registered";
    }
    else
    {
      return organizerRegistrationDBAccess.registerOrganizer(organizer);
    }
  }
}

