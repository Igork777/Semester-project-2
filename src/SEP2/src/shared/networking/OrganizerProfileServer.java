package shared.networking;

import shared.wrappers.Organizer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OrganizerProfileServer extends Remote
{

	/**
	 * Method which retrieves data for the organizer if user has a registered organizer role
	 * @param userId - used id of the organizer
	 * @return Organizer or null
	 */
	Organizer getOrganizerInfo(int userId) throws RemoteException;
}
