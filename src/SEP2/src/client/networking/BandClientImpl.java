package client.networking;

import shared.networking.BandServer;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

public class BandClientImpl implements BandClient {
    private BandServer bandServer;

    public BandClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.bandServer = (BandServer) registry.lookup("BandServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, File> getAllBandsMusicianIsIn(int id) {
        try {
            return bandServer.getAllBandsMusicianIsIn(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public boolean removeMusicianFromBand(Musician currentMusician, Band localBand) {
        try {
            return bandServer.removeMusicianFromBand(currentMusician, localBand);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Musician> getMusiciansInTheBand(int bandId) {
        try {
            return bandServer.getMusiciansInTheBand(bandId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
