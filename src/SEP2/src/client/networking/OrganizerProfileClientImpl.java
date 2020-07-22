package client.networking;

import shared.networking.OrganizerProfileServer;
import shared.wrappers.Organizer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class OrganizerProfileClientImpl implements OrganizerProfileClient
{

	private OrganizerProfileServer organizerProfileServer;

	public OrganizerProfileClientImpl()
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			this.organizerProfileServer = (OrganizerProfileServer) registry.lookup("OrganizerProfileServer");
		}
		catch (RemoteException | NotBoundException e)
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
		try
		{
			return organizerProfileServer.getOrganizerInfo(userId);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
