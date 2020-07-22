package server.manager;

import server.database.OrganizerProfileDatabaseAccess;
import server.database.OrganizerProfileDatabaseImpl;
import shared.wrappers.Organizer;

public class OrganizerProfileManagerImpl implements OrganizerProfileManager
{

	private OrganizerProfileDatabaseAccess organizerProfileDatabaseAccess;

	public OrganizerProfileManagerImpl()
	{
		this.organizerProfileDatabaseAccess = new OrganizerProfileDatabaseImpl();
	}

	/**
	 * Method which retrieves the organizer information from the database.
	 * @param userId - used id of the organizer
	 * @return Organizer with the data or null
	 */

	@Override public Organizer getOrganizerInfo(int userId)
	{
		if (organizerProfileDatabaseAccess.isOrganizerRegisteredForUser(userId))
		{
			return organizerProfileDatabaseAccess.getOrganizerInfo(userId);
		}
		else
		{
			return null;
		}
	}
}
