package server.database;

import shared.wrappers.Organizer;

public interface OrganizerProfileDatabaseAccess
{
	/**
	 * Method which retrieves data for the organizer if user has a registered organizer role
	 * @param userId - used id of the organizer
	 * @return Organizer or null
	 */
	Organizer getOrganizerInfo(int userId);

	/**
	 * Method which checks whether an user has already registered as an organizer
	 * @param userId - user id of the user the check is done for
	 * @return true if organizer is registered in the system, otherwise false
	 */
	boolean isOrganizerRegisteredForUser(int userId);
}
