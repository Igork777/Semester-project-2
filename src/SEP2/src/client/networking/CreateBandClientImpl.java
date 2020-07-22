package client.networking;

import shared.networking.CreateBandServer;
import shared.wrappers.Band;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CreateBandClientImpl implements CreateBandClient {
    private CreateBandServer createBandServer;

    public CreateBandClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.createBandServer = (CreateBandServer) registry.lookup("CreateBandServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Band band) {
        try {
            return createBandServer.create(band);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }



}
