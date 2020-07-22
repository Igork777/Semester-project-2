package client.networking;

import shared.networking.RegisterMusicianServer;
import shared.wrappers.Musician;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegisterMusicianClientImpl implements RegisterMusicianClient {
    private RegisterMusicianServer server;

    /**
     * Constructor establishing connection to a server handling musician registration
     */
    public RegisterMusicianClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.server = (RegisterMusicianServer) registry.lookup("RegisterMusicianServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lets the server know to register a musician
     * @param musician to register
     * @return true when musician gets registered; false when musician failed to register
     */
    @Override
    public boolean register(Musician musician) {
        try {
            return server.register(musician);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
