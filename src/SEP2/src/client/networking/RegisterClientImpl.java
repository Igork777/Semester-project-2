package client.networking;

import shared.networking.RegisterServer;
import shared.wrappers.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterClientImpl implements RegisterClient {
    private RegisterServer registerServer;

    /**
     * Constructor establishing connection to a server handling user registration
     */
    public RegisterClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.registerServer = (RegisterServer) registry.lookup("RegisterServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that registers a user
     *
     * @param user wrapper class
     * @return the message of user being registered
     */
    @Override
    public String register(User user) {
        try {
            return registerServer.register(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible to register");
    }



}
