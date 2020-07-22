package shared.networking;

import shared.wrappers.Organizer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterOrganizerServer extends Remote
{
  /**
   * This method registers the user as an organizer
   * @param organizer wrapper for organizer data
   * @return message stating whether the registration was successful
   * @throws RemoteException - RMI exception
   */

  String registerOrganizer(Organizer organizer) throws RemoteException;
}
