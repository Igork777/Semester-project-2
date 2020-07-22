package server.networking;

import server.manager.LoginManager;
import shared.networking.LoginServer;
import shared.wrappers.User;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Networking class on the server side
 */
public class LogInServerImpl implements LoginServer {
    /**
     * Here we have reference on the proper  manager + arralylist of clients
     */
    private LoginManager loginManager;

    /**
     * Constructor, which makes LogInServerImpl usable for RMI
     *
     * @param loginManager
     */
    public LogInServerImpl(LoginManager loginManager, Registry registry) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            this.loginManager = loginManager;
            registry.bind("LoginServer", this);
            System.out.println("Login Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException("Login Server Failed to Start");
        }
    }

    /**
     * method calls another method  login on the LogInServerImpl
     *
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     */
    @Override
    public boolean login(User user) {
        return loginManager.login(user);
    }

}
