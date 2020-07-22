package server.networking;

import server.manager.RegisterManager;
import shared.networking.RegisterServer;
import shared.wrappers.User;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegisterServerImpl implements RegisterServer
{
    private RegisterManager registerManager;
    public RegisterServerImpl(RegisterManager registerManager, Registry registry)
    {
        try
        {
            UnicastRemoteObject.exportObject(this,0);
            this.registerManager = registerManager;
            registry.bind("RegisterServer", this);
            System.out.println("Register server started!");
        }
        catch (RemoteException | AlreadyBoundException e)
        {
            throw new RuntimeException("Register server failed to start");
        }
    }
    /**
     * Method that returns the reply to inquiry to register the user
     * @param user which has to be registered
     * @return message which will be represented in the view controller
     */
    @Override public String register(User user)
    {
        return registerManager.register(user);
    }

}
