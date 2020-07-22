package server.networking;

import server.manager.CreateBandManager;
import server.manager.CreateBandManagerImpl;
import shared.networking.CreateBandServer;
import shared.wrappers.Band;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CreateBandServerImpl implements CreateBandServer {
    private CreateBandManager createBandManager;

    public CreateBandServerImpl(CreateBandManagerImpl createBandManager, Registry registry) {
        this.createBandManager = createBandManager;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("CreateBandServer", this);
            System.out.println("CreateBand Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String create(Band band) throws RemoteException {
        return createBandManager.create(band);
    }

}
