package server.networking;

import server.manager.RegisterOrganizerManager;
import shared.networking.RegisterOrganizerServer;
import shared.wrappers.Organizer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Networking class on server side
 */

public class RegisterOrganizerServerImpl implements RegisterOrganizerServer
{
  private RegisterOrganizerManager registerOrganizerManager;

  /**
   * Constructor which prepares the server to be used for RMI
   * @param registerOrganizerManager - the manager for registration of user as an organizer
   * @param registry - RMI registry
   */

  public RegisterOrganizerServerImpl(RegisterOrganizerManager registerOrganizerManager, Registry registry)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      this.registerOrganizerManager = registerOrganizerManager;
      registry.bind("RegisterOrganizerServer", this);
      System.out.println("Register organizer server started!");
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      throw new RuntimeException("Register organizer server failed to start!");
    }
  }

  /**
   * This method registers the user as an organizer
   * @param organizer - wrapper for organizer data
   * @return message stating whether the registration was successful
   */

  @Override public String registerOrganizer(Organizer organizer)
  {
    return registerOrganizerManager.registerOrganizer(organizer);
  }
}
