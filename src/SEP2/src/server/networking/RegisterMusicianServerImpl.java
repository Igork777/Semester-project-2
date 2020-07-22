package server.networking;

import server.manager.RegisterMusicianManager;
import shared.networking.RegisterMusicianServer;
import shared.wrappers.Musician;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RegisterMusicianServerImpl implements RegisterMusicianServer
{
    private RegisterMusicianManager registerMusicianManager;

    /**
     * Creates a registration musician server to handle requests from register musician client
     * @param registerMusicianManager manager of musician registration
     * @param registry Registry of all RMI servers on the server port
     */
    public RegisterMusicianServerImpl(RegisterMusicianManager registerMusicianManager, Registry registry) {
        this.registerMusicianManager = registerMusicianManager;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("RegisterMusicianServer", this);
            System.out.println("Register musician server started!");
        }
        catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException("Register musician server failed to start");
        }
    }

    /**
     * Passes the responsibility of register a musican to database
     * @param musician to register
     * @return true when musician gets registered; false when musician failed to register
     */
    @Override
    public boolean register(Musician musician) {
        return registerMusicianManager.registerMusician(musician);
    }
}
