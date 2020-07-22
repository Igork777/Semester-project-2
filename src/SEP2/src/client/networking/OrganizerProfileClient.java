package client.networking;

import shared.wrappers.Organizer;

public interface OrganizerProfileClient
{
	/**
	 * Method which retrieves data for the organizer if user has a registered organizer role
	 * @param userId - used id of the organizer
	 * @return Organizer or null
	 */
	Organizer getOrganizerInfo(int userId);

}
