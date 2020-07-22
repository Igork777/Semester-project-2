package server.networking;

import server.manager.OrganizerProfileManager;
import shared.networking.OrganizerProfileServer;
import shared.wrappers.Organizer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class OrganizerProfileServerImpl implements OrganizerProfileServer
{
	private OrganizerProfileManager organizerProfileManager;

	/**
	 * Creates an organizer profile server which will communicate with client. This server
	 * will retrieve data about the organizer
	 * @param registry - registry of the servers on the RMI port
	 * @param organizerProfileManager - manager for the organizer profile data retrieval
	 */

	public OrganizerProfileServerImpl(OrganizerProfileManager organizerProfileManager, Registry registry)
	{
		try
		{
			this.organizerProfileManager = organizerProfileManager;
			UnicastRemoteObject.exportObject(this, 0);
			registry.bind("OrganizerProfileServer", this);
			System.out.println("Organizer profile server started!");
		}
		catch (RemoteException | AlreadyBoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method which retrieves data for the organizer if user has a registered organizer role
	 * @param userId - used id of the organizer
	 * @return Organizer or null
	 */

	@Override public Organizer getOrganizerInfo(int userId)
	{
		return organizerProfileManager.getOrganizerInfo(userId);
	}
}
