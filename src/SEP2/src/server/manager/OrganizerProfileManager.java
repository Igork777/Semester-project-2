package server.manager;

import shared.wrappers.Organizer;

public interface OrganizerProfileManager
{

	/**
	 * Method which retrieves data for the organizer if user has a registered organizer role
	 * @param userId - used id of the organizer
	 * @return Organizer or null
	 */
	Organizer getOrganizerInfo(int userId);
}
