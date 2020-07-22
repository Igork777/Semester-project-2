package client.networking;

import shared.networking.LoginServer;
import shared.wrappers.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * LogInClientImpl class, which connects with LogInServerImpl server and make some logic
 * It implements LoginClientCallback in order to have possibility to receive callbacks from the server
 */
public class LoginClientImpl implements LoginClient
{
    /**
     * field which references to the LoginServer
     */
    private LoginServer loginServer;

    /**
     * Constructor, where we make Client be able to work with RMI
     */
    public LoginClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.loginServer = (LoginServer) registry.lookup("LoginServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  method, which calls another method login() on LoginServer, which returns true or false based on
     *  user input
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     */
    @Override
    public boolean login(User user) {
        try {
            return loginServer.login(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }


}
