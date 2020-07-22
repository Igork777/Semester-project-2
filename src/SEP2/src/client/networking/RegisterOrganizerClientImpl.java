package client.networking;

import shared.networking.RegisterOrganizerServer;
import shared.wrappers.Organizer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterOrganizerClientImpl implements RegisterOrganizerClient
{

  private RegisterOrganizerServer registerOrganizerServer;

  /**
   * Constructor which finds the proper server to plug into
   */

  public RegisterOrganizerClientImpl()
  {
    try
    {
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      this.registerOrganizerServer = (RegisterOrganizerServer) registry.lookup("RegisterOrganizerServer");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * This method registers the user as an organizer
   * @param organizer - wrapper for organizer data
   * @return message stating whether the registration was successful
   */

  @Override public String registerOrganizer(Organizer organizer)
  {
    try
    {
      return registerOrganizerServer.registerOrganizer(organizer);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    throw new RuntimeException("Impossible to register user as organizer");
  }
}
